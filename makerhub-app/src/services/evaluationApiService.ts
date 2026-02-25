import api from './api';

// Interfaces baseadas no Swagger
export interface EvaluationItemRequest {
  id?: number;
  itemName: string;
  description: string;
  typeId: number;
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
  // Criar item de avaliação
  createItem: async (item: EvaluationItemRequest) => {
    console.log('Criando item de avaliação:', item);
    const response = await api.post('/evaluation-item/save', item);
    console.log('Item criado:', response.data);
    return response.data;
  },

  // Listar itens de avaliação por tipo
  listItemsByType: async (typeId: number) => {
    const response = await api.get(`/evaluation-item/type/${typeId}`);
    return response.data;
  },

  // Criar avaliação
  createEvaluation: async (evaluation: EvaluationRequest) => {
    console.log('Criando avaliação:', evaluation);
    const response = await api.post('/evaluation/save', evaluation);
    console.log('Avaliação criada:', response.data);
    return response.data;
  },

  // Listar avaliações por aluno
  listByStudent: async (studentId: number) => {
    const response = await api.get(`/evaluation/student/${studentId}`);
    return response.data;
  },

  // Salvar resposta de item de avaliação
  saveItemAnswer: async (answer: EvaluationItemAnswerRequest) => {
    console.log('Salvando resposta:', answer);
    const response = await api.post('/evaluation-item-answer/save', answer);
    console.log('Resposta salva:', response.data);
    return response.data;
  },

  // Listar respostas por avaliação
  listAnswersByEvaluation: async (evaluationId: number) => {
    const response = await api.get(`/evaluation-item-answer/evaluation/${evaluationId}`);
    return response.data;
  }
};
