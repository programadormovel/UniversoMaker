import api from './api';
import { DailyEvaluation } from '../types';

const EMPTY_EVALUATION: Omit<DailyEvaluation, 'id' | 'studentId'> = {
  date: '',
  professional: '',
  activityName: '',
  activityObjective: '',
  activitySteps: [],
  complexityLevel: 'medio',
  emotionalRegulation: 'regulado',
  emotionalStrategies: '',
  socialInteraction: 'sozinho',
  socialConflicts: '',
  communicationRequest: 'verbal',
  communicationExplanation: 'descreveu',
  vocabulary: '',
  attentionDuration: '10_20min',
  instructionComprehension: 'simples',
  stepSequencing: 'completo_sozinho',
  fineMotor: 'adequada',
  grossMotor: 'adequada',
  motorObservations: '',
  problemSolving: [],
  problemDescription: '',
  solutionDescription: '',
  reading: 'instrucoes',
  writing: 'registro_escrito',
  orality: 'explicou',
  autonomy: 'moderada',
  performance: 'concluiu_sucesso',
  performanceReason: '',
  strengths: '',
  attentionPoints: '',
  relevantBehaviors: '',
  suggestions: '',
  notes: ''
};

const today = () => new Date().toISOString().split('T')[0];

type SnapshotItem = {
  name?: unknown;
  value?: unknown;
  observation?: unknown;
};

type EvaluationSnapshot = {
  date?: unknown;
  studentId?: unknown;
  professionalId?: unknown;
  professionalName?: unknown;
  activityName?: unknown;
  activityObjective?: unknown;
  activitySteps?: unknown;
  complexityLevel?: unknown;
  items?: unknown;
};

type ParsedItem = {
  value: string;
  observation: string;
};

const SNAPSHOT_MARKER = '--- Snapshot JSON ---';

const EMOTIONAL_REGULATION_VALUES = ['regulado', 'pequena_frustracao', 'frustracao_moderada', 'abandono'] as const;
const SOCIAL_INTERACTION_VALUES = ['sozinho', 'dupla', 'mediacao_constante', 'compartilhou', 'conflitos'] as const;
const COMMUNICATION_REQUEST_VALUES = ['verbal', 'gestual', 'silencio'] as const;
const COMMUNICATION_EXPLANATION_VALUES = ['descreveu', 'frases_curtas', 'nao_explicou'] as const;
const ATTENTION_DURATION_VALUES = ['menos_5min', '5_10min', '10_20min', 'mais_20min'] as const;
const INSTRUCTION_COMPREHENSION_VALUES = ['simples', 'repetir', 'visuais', 'apoio_intenso'] as const;
const STEP_SEQUENCING_VALUES = ['completo_sozinho', 'lembretes', 'fora_ordem', 'nao_completou'] as const;
const FINE_MOTOR_VALUES = ['excelente', 'adequada', 'dificuldade_moderada', 'nao_conseguiu'] as const;
const GROSS_MOTOR_VALUES = ['adequada', 'dificuldade_leve', 'dificuldade_moderada', 'comprometida'] as const;
const READING_VALUES = ['instrucoes', 'palavras_isoladas', 'nao_leu'] as const;
const WRITING_VALUES = ['registro_escrito', 'desenhou', 'nao_escreveu'] as const;
const ORALITY_VALUES = ['explicou', 'narrou_passos', 'nao_comunicou'] as const;
const AUTONOMY_VALUES = ['alta', 'moderada', 'apoio_frequente', 'total_dependencia'] as const;
const PERFORMANCE_VALUES = ['concluiu_sucesso', 'concluiu_parcial', 'nao_concluiu'] as const;
const COMPLEXITY_VALUES = ['baixo', 'medio', 'alto'] as const;

const normalizeText = (value: unknown): string => String(value ?? '').trim();

const normalizeToken = (value: unknown): string =>
  normalizeText(value)
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toLowerCase()
    .replace(/[^\w\s-]/g, '')
    .replace(/[\s-]+/g, '_')
    .replace(/_+/g, '_')
    .replace(/^_+|_+$/g, '');

const sanitizeFreeText = (value: unknown): string => {
  const text = normalizeText(value);
  const token = normalizeToken(text);
  if (!text || token === 'nao_informado' || token === 'nao_informada' || token === 'n_a' || token === 'na') {
    return '';
  }
  return text;
};

const normalizeDate = (value: unknown): string => {
  const text = normalizeText(value);
  if (!text) return today();

  const direct = text.slice(0, 10);
  if (/^\d{4}-\d{2}-\d{2}$/.test(direct)) {
    return direct;
  }

  const parsed = new Date(text);
  if (Number.isNaN(parsed.getTime())) {
    return today();
  }

  return parsed.toISOString().slice(0, 10);
};

const parseList = (value: unknown): string[] =>
  sanitizeFreeText(value)
    .split(',')
    .map((entry) => entry.trim())
    .filter(Boolean);

const parseEnum = <T extends string>(
  value: unknown,
  allowed: readonly T[],
  fallback: T,
  aliases: Record<string, T> = {}
): T => {
  const token = normalizeToken(value);
  if (!token) return fallback;
  if (aliases[token]) return aliases[token];
  return (allowed as readonly string[]).includes(token) ? (token as T) : fallback;
};

const parseSnapshot = (notes: string): EvaluationSnapshot | null => {
  const markerIndex = notes.indexOf(SNAPSHOT_MARKER);
  if (markerIndex === -1) return null;

  const rawJson = notes.slice(markerIndex + SNAPSHOT_MARKER.length).trim();
  if (!rawJson) return null;

  try {
    const parsed = JSON.parse(rawJson);
    return parsed && typeof parsed === 'object' ? (parsed as EvaluationSnapshot) : null;
  } catch {
    return null;
  }
};

const parseNotes = (
  notes: string
): {
  keyValues: Map<string, string>;
  items: Map<string, ParsedItem>;
} => {
  const keyValues = new Map<string, string>();
  const items = new Map<string, ParsedItem>();

  const lines = notes.split(/\r?\n/);
  for (const line of lines) {
    const trimmed = line.trim();
    if (!trimmed || trimmed.startsWith('---')) continue;

    if (trimmed.startsWith('- ')) {
      const content = trimmed.slice(2);
      const colonIndex = content.indexOf(':');
      if (colonIndex === -1) continue;

      const rawName = content.slice(0, colonIndex).trim();
      const tail = content.slice(colonIndex + 1).trim();

      const obsMatch = /\|\s*obs:\s*/i.exec(tail);
      const valuePart = obsMatch ? tail.slice(0, obsMatch.index).trim() : tail;
      const observationPart = obsMatch ? tail.slice(obsMatch.index + obsMatch[0].length).trim() : '';

      items.set(normalizeToken(rawName), {
        value: sanitizeFreeText(valuePart),
        observation: sanitizeFreeText(observationPart)
      });
      continue;
    }

    const separatorIndex = trimmed.indexOf(':');
    if (separatorIndex === -1) continue;

    const key = normalizeToken(trimmed.slice(0, separatorIndex));
    const value = trimmed.slice(separatorIndex + 1).trim();
    if (key && value) {
      keyValues.set(key, value);
    }
  }

  return { keyValues, items };
};

const parseSnapshotItems = (snapshot: EvaluationSnapshot | null): Map<string, ParsedItem> => {
  const items = new Map<string, ParsedItem>();
  if (!snapshot || !Array.isArray(snapshot.items)) return items;

  for (const entry of snapshot.items as SnapshotItem[]) {
    const name = normalizeToken(entry?.name);
    if (!name) continue;

    items.set(name, {
      value: sanitizeFreeText(entry?.value),
      observation: sanitizeFreeText(entry?.observation)
    });
  }

  return items;
};

const getFromKeyValues = (keyValues: Map<string, string>, keys: string[]): string => {
  for (const key of keys) {
    const value = keyValues.get(normalizeToken(key));
    if (value) return value;
  }
  return '';
};

const getFromItems = (
  itemMap: Map<string, ParsedItem>,
  aliases: string[]
): ParsedItem | null => {
  for (const alias of aliases) {
    const item = itemMap.get(normalizeToken(alias));
    if (item) return item;
  }
  return null;
};

const normalizeEvaluation = (raw: any): DailyEvaluation => {
  const notes = normalizeText(raw?.notes);
  const { keyValues, items: noteItems } = parseNotes(notes);
  const snapshot = parseSnapshot(notes);
  const snapshotItems = parseSnapshotItems(snapshot);
  const itemMap = new Map<string, ParsedItem>([...noteItems, ...snapshotItems]);

  const activityStepsFromSnapshot = Array.isArray(snapshot?.activitySteps)
    ? (snapshot?.activitySteps as unknown[])
        .map((entry) => sanitizeFreeText(entry))
        .filter(Boolean)
    : [];

  const activityStepsFromNotes = parseList(getFromKeyValues(keyValues, ['Etapas']));
  const activitySteps = activityStepsFromSnapshot.length > 0 ? activityStepsFromSnapshot : activityStepsFromNotes;

  const emotional = getFromItems(itemMap, ['Regulacao Emocional']);
  const social = getFromItems(itemMap, ['Interacao Social']);
  const communicationRequest = getFromItems(itemMap, ['Comunicacao - Pedido']);
  const communicationExplanation = getFromItems(itemMap, ['Comunicacao - Explicacao']);
  const attentionDuration = getFromItems(itemMap, ['Duracao da Atencao']);
  const instructionComprehension = getFromItems(itemMap, ['Compreensao de Instrucoes']);
  const stepSequencing = getFromItems(itemMap, ['Sequenciamento de Etapas']);
  const fineMotor = getFromItems(itemMap, ['Motricidade Fina']);
  const grossMotor = getFromItems(itemMap, ['Motricidade Grossa']);
  const problemSolving = getFromItems(itemMap, ['Resolucao de Problemas']);
  const reading = getFromItems(itemMap, ['Leitura']);
  const writing = getFromItems(itemMap, ['Escrita']);
  const orality = getFromItems(itemMap, ['Oralidade']);
  const autonomy = getFromItems(itemMap, ['Autonomia']);
  const performance = getFromItems(itemMap, ['Desempenho']);
  const strengths = getFromItems(itemMap, ['Pontos Fortes']);
  const attentionPoints = getFromItems(itemMap, ['Pontos de Atencao']);
  const relevantBehaviors = getFromItems(itemMap, ['Comportamentos Relevantes']);
  const suggestions = getFromItems(itemMap, ['Sugestoes']);
  const notesField = getFromItems(itemMap, ['Observacoes Gerais']);

  const professionalName =
    sanitizeFreeText(raw?.professional) ||
    sanitizeFreeText(raw?.professionalName) ||
    sanitizeFreeText(raw?.professional?.name) ||
    sanitizeFreeText(snapshot?.professionalName) ||
    sanitizeFreeText(getFromKeyValues(keyValues, ['Profissional', 'ProfissionalNome']));

  const professionalId =
    sanitizeFreeText(raw?.professionalId) ||
    sanitizeFreeText(snapshot?.professionalId) ||
    sanitizeFreeText(getFromKeyValues(keyValues, ['ProfissionalId']));

  return {
    ...EMPTY_EVALUATION,
    id: String(raw?.id ?? raw?.evaluationId ?? crypto.randomUUID()),
    studentId: String(
      raw?.studentId ??
        raw?.student?.id ??
        snapshot?.studentId ??
        getFromKeyValues(keyValues, ['AlunoId']) ??
        ''
    ),
    date: normalizeDate(raw?.date ?? raw?.evaluationDate ?? raw?.createdAt ?? snapshot?.date ?? getFromKeyValues(keyValues, ['Data'])),
    professional: professionalName || (professionalId ? `Profissional #${professionalId}` : ''),
    activityName: sanitizeFreeText(raw?.activityName ?? raw?.evaluationName ?? snapshot?.activityName) || 'Atividade Maker',
    activityObjective:
      sanitizeFreeText(raw?.activityObjective ?? raw?.objective ?? snapshot?.activityObjective) ||
      sanitizeFreeText(getFromKeyValues(keyValues, ['Objetivo'])),
    activitySteps,
    complexityLevel: parseEnum(
      raw?.complexityLevel ?? snapshot?.complexityLevel ?? getFromKeyValues(keyValues, ['Complexidade']),
      COMPLEXITY_VALUES,
      'medio',
      { medio: 'medio', media: 'medio' }
    ),
    emotionalRegulation: parseEnum(
      raw?.emotionalRegulation ?? emotional?.value,
      EMOTIONAL_REGULATION_VALUES,
      'regulado'
    ),
    emotionalStrategies: sanitizeFreeText(raw?.emotionalStrategies ?? emotional?.observation),
    socialInteraction: parseEnum(raw?.socialInteraction ?? social?.value, SOCIAL_INTERACTION_VALUES, 'sozinho'),
    socialConflicts: sanitizeFreeText(raw?.socialConflicts ?? social?.observation),
    communicationRequest: parseEnum(
      raw?.communicationRequest ?? communicationRequest?.value,
      COMMUNICATION_REQUEST_VALUES,
      'verbal'
    ),
    communicationExplanation: parseEnum(
      raw?.communicationExplanation ?? communicationExplanation?.value,
      COMMUNICATION_EXPLANATION_VALUES,
      'descreveu'
    ),
    vocabulary: sanitizeFreeText(raw?.vocabulary ?? communicationExplanation?.observation),
    attentionDuration: parseEnum(
      raw?.attentionDuration ?? attentionDuration?.value,
      ATTENTION_DURATION_VALUES,
      '10_20min'
    ),
    instructionComprehension: parseEnum(
      raw?.instructionComprehension ?? instructionComprehension?.value,
      INSTRUCTION_COMPREHENSION_VALUES,
      'simples'
    ),
    stepSequencing: parseEnum(
      raw?.stepSequencing ?? stepSequencing?.value,
      STEP_SEQUENCING_VALUES,
      'completo_sozinho'
    ),
    fineMotor: parseEnum(raw?.fineMotor ?? fineMotor?.value, FINE_MOTOR_VALUES, 'adequada'),
    grossMotor: parseEnum(raw?.grossMotor ?? grossMotor?.value, GROSS_MOTOR_VALUES, 'adequada'),
    motorObservations: sanitizeFreeText(
      raw?.motorObservations ?? fineMotor?.observation ?? grossMotor?.observation
    ),
    problemSolving: Array.isArray(raw?.problemSolving) ? raw.problemSolving.map(String) : parseList(problemSolving?.value),
    problemDescription: sanitizeFreeText(raw?.problemDescription ?? problemSolving?.observation),
    solutionDescription: sanitizeFreeText(raw?.solutionDescription),
    reading: parseEnum(raw?.reading ?? reading?.value, READING_VALUES, 'instrucoes'),
    writing: parseEnum(raw?.writing ?? writing?.value, WRITING_VALUES, 'registro_escrito'),
    orality: parseEnum(raw?.orality ?? orality?.value, ORALITY_VALUES, 'explicou'),
    autonomy: parseEnum(raw?.autonomy ?? autonomy?.value, AUTONOMY_VALUES, 'moderada'),
    performance: parseEnum(raw?.performance ?? performance?.value, PERFORMANCE_VALUES, 'concluiu_sucesso'),
    performanceReason: sanitizeFreeText(raw?.performanceReason ?? performance?.observation),
    strengths: sanitizeFreeText(raw?.strengths ?? raw?.positivePoints ?? strengths?.value),
    attentionPoints: sanitizeFreeText(
      raw?.attentionPoints ?? raw?.improvementPoints ?? attentionPoints?.value
    ),
    relevantBehaviors: sanitizeFreeText(raw?.relevantBehaviors ?? relevantBehaviors?.value),
    suggestions: sanitizeFreeText(raw?.suggestions ?? suggestions?.value),
    notes: sanitizeFreeText(notesField?.value) || notes
  };
};

const normalizeList = (payload: any): DailyEvaluation[] => {
  if (!Array.isArray(payload)) return [];
  return payload.map(normalizeEvaluation);
};

export const fetchEvaluationsByStudentIds = async (studentIds: string[]): Promise<DailyEvaluation[]> => {
  const uniqueIds = [
    ...new Set(studentIds.map((id) => Number(id)).filter((id) => Number.isInteger(id) && id > 0))
  ];
  if (uniqueIds.length === 0) return [];

  const requests = uniqueIds.map(async (studentId) => {
    try {
      const response = await api.get(`/evaluation/student/${studentId}`);
      return normalizeList(response.data);
    } catch (error) {
      console.warn(`Falha ao buscar avaliações do aluno ${studentId}:`, error);
      return [];
    }
  });

  const grouped = await Promise.all(requests);
  return grouped.flat().sort((a, b) => a.date.localeCompare(b.date));
};

export const fetchEvaluations = async (): Promise<DailyEvaluation[]> => {
  try {
    const response = await api.get('/evaluations');
    return normalizeList(response.data);
  } catch (error) {
    console.warn('Endpoint /evaluations indisponível. Retornando lista vazia.', error);
    return [];
  }
};

export const deleteEvaluation = async (id: string): Promise<boolean> => {
  const endpoints = [`/evaluation/${id}`, `/evaluation/delete/${id}`, `/evaluations/${id}`];
  for (const endpoint of endpoints) {
    try {
      await api.delete(endpoint);
      return true;
    } catch {
      // tenta próximo endpoint
    }
  }
  return false;
};

export const createEvaluation = async (
  evaluation: Omit<DailyEvaluation, 'id'>
): Promise<DailyEvaluation | null> => {
  try {
    const response = await api.post('/evaluation/save', evaluation);
    return normalizeEvaluation(response.data);
  } catch {
    try {
      const response = await api.post('/evaluations', evaluation);
      return normalizeEvaluation(response.data);
    } catch (error) {
      console.error('Erro ao salvar avaliação:', error);
      return null;
    }
  }
};
