import api from './api';

interface LoginCredentials {
  username: string;
  password: string;
}

interface LoginResponse {
  user?: any;
  message?: string;
}

export const authService = {
  login: async (credentials: LoginCredentials): Promise<LoginResponse> => {
    console.log('authService.login chamado com:', credentials);
    
    const response = await api.post<LoginResponse>('/auth/login', credentials);
    
    console.log('Resposta da API:', response.data);
    console.log('Headers Set-Cookie:', response.headers['set-cookie']);
    
    // Extrair token do header Set-Cookie
    const setCookieHeader = response.headers['set-cookie'];
    let token = null;
    
    if (setCookieHeader) {
      const cookieString = Array.isArray(setCookieHeader) ? setCookieHeader[0] : setCookieHeader;
      const match = cookieString.match(/accessToken=([^;]+)/);
      if (match) {
        token = match[1];
        console.log('Token extraído do Set-Cookie:', token);
      }
    }
    
    if (token) {
      localStorage.setItem('makerhub_token', token);
      localStorage.setItem('makerhub_authenticated', 'true');
      console.log('Token salvo no localStorage');
    } else {
      // Fallback: marcar como autenticado mesmo sem conseguir extrair o token
      localStorage.setItem('makerhub_authenticated', 'true');
      console.log('Autenticação marcada sem token extraído');
    }
    
    if (response.data.user) {
      localStorage.setItem('makerhub_user', JSON.stringify(response.data.user));
      console.log('Usuário salvo:', response.data.user);
    }
    
    return response.data;
  },

  logout: () => {
    localStorage.removeItem('makerhub_authenticated');
    localStorage.removeItem('makerhub_token');
    localStorage.removeItem('makerhub_user');
    api.post('/auth/logout').catch(err => console.error('Erro no logout:', err));
  },

  getToken: (): string | null => {
    return localStorage.getItem('makerhub_token');
  },

  isAuthenticated: (): boolean => {
    const isAuth = localStorage.getItem('makerhub_authenticated') === 'true';
    console.log('isAuthenticated:', isAuth);
    return isAuth;
  }
};
