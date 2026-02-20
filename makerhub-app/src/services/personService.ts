import api from './api';

export enum PersonType {
  PROFESSOR = 1,
  RESPONSAVEL = 2,
  ALUNO = 3,
  PACIENTE = 4,
  PROFISSIONAL = 5,
  ADMINISTRADOR = 6,
  GESTOR = 7,
  ASSISTENTE = 8
}

export interface Person {
  id: string;
  name: string;
  age?: number;
  diagnosis?: string;
  type?: PersonType;
}

export const personService = {
  fetchByType: async (type: PersonType): Promise<Person[]> => {
    try {
      const response = await api.get(`/person/type/${type}`);
      return response.data.map((p: any) => ({ ...p, id: String(p.id) }));
    } catch (error) {
      console.error(`Erro ao buscar pessoas do tipo ${type}:`, error);
      return [];
    }
  },

  fetchStudents: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.ALUNO);
  },

  fetchTeachers: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.PROFESSOR);
  },

  fetchResponsibles: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.RESPONSAVEL);
  },

  fetchPatients: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.PACIENTE);
  },

  fetchProfessionals: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.PROFISSIONAL);
  },

  fetchAdministrators: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.ADMINISTRADOR);
  },

  fetchManagers: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.GESTOR);
  },

  fetchAssistants: async (): Promise<Person[]> => {
    return personService.fetchByType(PersonType.ASSISTENTE);
  }
};
