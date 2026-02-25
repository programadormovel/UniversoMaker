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

const extractFromNotes = (notes: string | undefined, key: string): string => {
  if (!notes) return '';
  const line = notes
    .split('\n')
    .find((entry) => entry.toLowerCase().startsWith(`${key.toLowerCase()}:`));
  return line ? line.split(':').slice(1).join(':').trim() : '';
};

const normalizeEvaluation = (raw: any): DailyEvaluation => {
  const notes = String(raw?.notes ?? '');
  const rawDate =
    raw?.date ??
    raw?.evaluationDate ??
    raw?.createdAt ??
    extractFromNotes(notes, 'Data') ??
    today();
  const date = String(rawDate).slice(0, 10);

  const complexityFromNotes = extractFromNotes(notes, 'Complexidade').toLowerCase();
  const complexityLevel: DailyEvaluation['complexityLevel'] =
    complexityFromNotes === 'baixo' || complexityFromNotes === 'medio' || complexityFromNotes === 'alto'
      ? complexityFromNotes
      : 'medio';

  return {
    ...EMPTY_EVALUATION,
    id: String(raw?.id ?? raw?.evaluationId ?? crypto.randomUUID()),
    studentId: String(raw?.studentId ?? raw?.student?.id ?? ''),
    date,
    professional: String(raw?.professional ?? raw?.professionalName ?? raw?.professional?.name ?? ''),
    activityName: String(raw?.activityName ?? raw?.evaluationName ?? 'Atividade Maker'),
    activityObjective: String(raw?.activityObjective ?? raw?.objective ?? ''),
    complexityLevel,
    strengths: String(raw?.strengths ?? raw?.positivePoints ?? ''),
    attentionPoints: String(raw?.attentionPoints ?? raw?.improvementPoints ?? ''),
    suggestions: String(raw?.suggestions ?? ''),
    notes
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
  const endpoints = [`/evaluation/${id}`, `/evaluations/${id}`, `/evaluation/delete/${id}`];
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
    const response = await api.post('/evaluations', evaluation);
    return normalizeEvaluation(response.data);
  } catch {
    try {
      const response = await api.post('/evaluation/save', evaluation);
      return normalizeEvaluation(response.data);
    } catch (error) {
      console.error('Erro ao salvar avaliação:', error);
      return null;
    }
  }
};
