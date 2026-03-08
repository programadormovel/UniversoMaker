import { Student } from '../types';
import { personService } from './personService';

const calculateAge = (birthDate?: string): number => {
  if (!birthDate) return 0;

  const parsedDate = new Date(birthDate);
  if (Number.isNaN(parsedDate.getTime())) return 0;

  const now = new Date();
  let age = now.getFullYear() - parsedDate.getFullYear();
  const monthDiff = now.getMonth() - parsedDate.getMonth();

  if (monthDiff < 0 || (monthDiff === 0 && now.getDate() < parsedDate.getDate())) {
    age -= 1;
  }

  return Math.max(age, 0);
};

const normalizeStudent = (raw: any): Student => ({
  id: String(raw?.id ?? raw?.personId ?? raw?.studentId ?? ''),
  name: String(raw?.name ?? raw?.fullName ?? raw?.personName ?? 'Sem nome'),
  age: Number.isFinite(Number(raw?.age)) ? Number(raw.age) : calculateAge(raw?.birthDate),
  diagnosis: String(raw?.diagnosis ?? raw?.medicalDiagnosis ?? raw?.cid ?? 'Nao informado')
});

const getStoredStudents = (): Student[] => {
  try {
    const stored = localStorage.getItem('students');
    if (!stored) return [];

    const parsed = JSON.parse(stored);
    if (!Array.isArray(parsed)) return [];

    return parsed.map(normalizeStudent);
  } catch (error) {
    console.warn('Falha ao ler alunos do armazenamento local:', error);
    return [];
  }
};

export const fetchStudents = async (): Promise<Student[]> => {
  try {
    const students = await personService.fetchStudents();
    const normalized = students
      .map(normalizeStudent)
      .filter((student) => student.id && student.name);

    if (normalized.length > 0) {
      return normalized;
    }

    return getStoredStudents();
  } catch (error) {
    console.error('Erro ao buscar alunos da API:', error);
    return getStoredStudents();
  }
};
