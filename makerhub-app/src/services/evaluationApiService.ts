import api from './api';

export interface EvaluationRequest {
  id?: number;
  evaluationName: string;
  objective: string;
  typeId: number;
  studentId: number;
  professionalId: number;
  notes: string;
  rate: number;
  isActive: boolean;
}

export interface EvaluationResponse {
  id: number;
  evaluationName: string;
  objective: string;
  typeId: number;
  studentId: number;
  professionalId: number;
  notes: string;
  rate: number;
  isActive: boolean;
}

export const evaluationApiService = {
  save: async (evaluation: EvaluationRequest): Promise<EvaluationResponse> => {
    console.log('Salvando avaliação na API:', evaluation);
    const response = await api.post<EvaluationResponse>('/evaluation/save', evaluation);
    console.log('Avaliação salva com sucesso:', response.data);
    return response.data;
  }
};
