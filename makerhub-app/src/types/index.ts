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
  communication: number; // 1-5
  socialInteraction: number; // 1-5
  behavior: number; // 1-5
  attention: number; // 1-5
  notes: string;
}

export interface EvaluationCriteria {
  communication: string;
  socialInteraction: string;
  behavior: string;
  attention: string;
}