import { DailyEvaluation } from '../types';

const API_URL = 'http://localhost:8080';

export const fetchEvaluations = async (): Promise<DailyEvaluation[]> => {
  try {
    const response = await fetch(`${API_URL}/evaluations`);
    if (response.ok) {
      const data = await response.json();
      // Garante que os IDs sejam strings para compatibilidade
      return data.map((e: any) => ({
        ...e,
        id: String(e.id),
        studentId: String(e.studentId)
      }));
    }
    return [];
  } catch (error) {
    console.error('Erro ao buscar avaliações da API:', error);
    return [];
  }
};

export const deleteEvaluation = async (id: string): Promise<boolean> => {
  try {
    const response = await fetch(`${API_URL}/evaluations/${id}`, {
      method: 'DELETE',
    });
    return response.ok;
  } catch (error) {
    console.error('Erro ao excluir avaliação na API:', error);
    return false;
  }
};

export const createEvaluation = async (evaluation: Omit<DailyEvaluation, 'id'>): Promise<DailyEvaluation | null> => {
  try {
    const response = await fetch(`${API_URL}/evaluations`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(evaluation),
    });
    if (response.ok) {
      return await response.json();
    }
    return null;
  } catch (error) {
    console.error('Erro ao salvar avaliação:', error);
    return null;
  }
};