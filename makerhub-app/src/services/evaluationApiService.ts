import api from './api';

export interface EvaluationItemRequest {
  id?: number;
  itemName: string;
  description: string;
  typeId?: number;
  isActive: boolean;
}

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

export interface EvaluationItemAnswerRequest {
  id?: number;
  evaluationId: number;
  evaluationItemId: number;
  answer: string;
  observation: string;
  isActive: boolean;
}

export const evaluationApiService = {
  createItem: async (item: EvaluationItemRequest) => {
    console.log('Criando item de avaliacao:', item);
    const response = await api.post('/evaluation-item/save', item);
    console.log('Item criado:', response.data);
    return response.data;
  },

  listItemsByType: async (typeId: number) => {
    try {
      const response = await api.get(`/evaluation-item/type/${typeId}`);
      return response.data;
    } catch (error) {
      console.warn('Falha ao listar itens de avaliacao por tipo:', error);
      return [];
    }
  },

  createEvaluation: async (evaluation: EvaluationRequest) => {
    console.log('Criando avaliacao:', evaluation);
    const response = await api.post('/evaluation/save', evaluation);
    console.log('Avaliacao criada:', response.data);
    return response.data;
  },

  listByStudent: async (studentId: number) => {
    const response = await api.get(`/evaluation/student/${studentId}`);
    return response.data;
  },

  saveItemAnswer: async (answer: EvaluationItemAnswerRequest) => {
    console.log('Salvando resposta:', answer);
    const response = await api.post('/evaluation-item-answer/save', answer);
    console.log('Resposta salva (evaluation-item-answer):', response.data);
    return response.data;
  },

  listAnswersByEvaluation: async (evaluationId: number) => {
    const endpoints = [
      `/evaluation-item-answer/evaluation/${evaluationId}`,
      `/evaluation-answer/evaluation/${evaluationId}`,
      `/evaluation-answer/list/${evaluationId}`
    ];

    for (const endpoint of endpoints) {
      try {
        const response = await api.get(endpoint);
        return response.data;
      } catch {
        // try next endpoint
      }
    }

    return [];
  }
};
