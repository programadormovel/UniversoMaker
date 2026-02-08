export interface Student {
  id: string;
  name: string;
  age: number;
  diagnosis: string;
}

export interface DailyEvaluation {
  id: string;
  studentId: string;
  date: string;
  professional: string;
  // Atividade
  activityName: string;
  activityObjective: string;
  activitySteps: string[];
  complexityLevel: 'baixo' | 'medio' | 'alto';
  // Regulação emocional
  emotionalRegulation: 'regulado' | 'pequena_frustracao' | 'frustracao_moderada' | 'abandono';
  emotionalStrategies: string;
  // Interação social
  socialInteraction: 'sozinho' | 'dupla' | 'mediacao_constante' | 'compartilhou' | 'conflitos';
  socialConflicts: string;
  // Comunicação
  communicationRequest: 'verbal' | 'gestual' | 'silencio';
  communicationExplanation: 'descreveu' | 'frases_curtas' | 'nao_explicou';
  vocabulary: string;
  // Atenção
  attentionDuration: 'menos_5min' | '5_10min' | '10_20min' | 'mais_20min';
  // Compreensão
  instructionComprehension: 'simples' | 'repetir' | 'visuais' | 'apoio_intenso';
  // Sequenciação
  stepSequencing: 'completo_sozinho' | 'lembretes' | 'fora_ordem' | 'nao_completou';
  // Motricidade
  fineMotor: 'excelente' | 'adequada' | 'dificuldade_moderada' | 'nao_conseguiu';
  grossMotor: 'adequada' | 'dificuldade_leve' | 'dificuldade_moderada' | 'comprometida';
  motorObservations: string;
  // Solução de problemas
  problemSolving: string[];
  problemDescription: string;
  solutionDescription: string;
  // Linguagem
  reading: 'instrucoes' | 'palavras_isoladas' | 'nao_leu';
  writing: 'registro_escrito' | 'desenhou' | 'nao_escreveu';
  orality: 'explicou' | 'narrou_passos' | 'nao_comunicou';
  // Autonomia
  autonomy: 'alta' | 'moderada' | 'apoio_frequente' | 'total_dependencia';
  // Desempenho
  performance: 'concluiu_sucesso' | 'concluiu_parcial' | 'nao_concluiu';
  performanceReason: string;
  // Síntese
  strengths: string;
  attentionPoints: string;
  relevantBehaviors: string;
  suggestions: string;
  notes: string;
}

export interface EvaluationCriteria {
  communication: string;
  socialInteraction: string;
  behavior: string;
  attention: string;
}