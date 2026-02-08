import { Student } from '../types';

const API_URL = 'http://localhost:8080';

export const fetchStudents = async (): Promise<Student[]> => {
  try {
    const response = await fetch(`${API_URL}/students`);
    if (response.ok) {
      const data = await response.json();
      // Garante que o ID seja string para compatibilidade com o select e componentes
      return data.map((s: any) => ({ ...s, id: String(s.id) }));
    }
    return [];
  } catch (error) {
    console.error('Erro ao buscar alunos da API:', error);
    return [];
  }
};