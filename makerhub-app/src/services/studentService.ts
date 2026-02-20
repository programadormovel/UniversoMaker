import { Student } from '../types';
import { personService } from './personService';

export const fetchStudents = async (): Promise<Student[]> => {
  try {
    const students = await personService.fetchStudents();
    return students as Student[];
  } catch (error) {
    console.error('Erro ao buscar alunos da API:', error);
    return [];
  }
};