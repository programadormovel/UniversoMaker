import api from './api';

interface LoginCredentials {
  username: string;
  password: string;
}

interface LoginResponse {
  user?: unknown;
  message?: string;
}

const SESSION_FLAG = 'makerhub_authenticated';
const USER_KEY = 'makerhub_user';

const saveLocalSession = (user?: unknown) => {
  localStorage.setItem(SESSION_FLAG, 'true');
  if (user) {
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }
};

const clearLocalSession = () => {
  localStorage.removeItem(SESSION_FLAG);
  localStorage.removeItem('makerhub_token');
  localStorage.removeItem(USER_KEY);
};

export const authService = {
  login: async (credentials: LoginCredentials): Promise<LoginResponse> => {
    console.log('authService.login chamado com:', credentials);

    try {
      const response = await api.post<LoginResponse>('/auth/login', credentials);
      console.log('Resposta da API:', response.data);

      const userPayload = response.data?.user ?? { username: credentials.username };
      saveLocalSession(userPayload);

      return response.data;
    } catch (error) {
      clearLocalSession();
      throw error;
    }
  },

  logout: () => {
    clearLocalSession();
    api.post('/auth/logout').catch((err) => console.error('Erro no logout:', err));
  },

  getToken: (): string | null => {
    return null;
  },

  isAuthenticated: (): boolean => {
    const isAuth = localStorage.getItem(SESSION_FLAG) === 'true';
    console.log('isAuthenticated:', isAuth);
    return isAuth;
  }
};
