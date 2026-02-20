import axios, { InternalAxiosRequestConfig } from 'axios';

const api = axios.create({
  baseURL: 'https://makerhub.api.mongreltech.com.br/api/v1', 
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000,
  withCredentials: true,
});

api.interceptors.request.use(
  async (config: InternalAxiosRequestConfig) => {
    console.log('Interceptor - Request:', config.method?.toUpperCase(), config.url);
    
    const token = localStorage.getItem('makerhub_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      console.log('Token adicionado ao header Authorization');
    }
    
    return config;
  },
  (error) => {
    console.error('Interceptor - Request Error:', error);
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => {
    console.log('Interceptor - Response:', response.status);
    return response;
  },
  (error) => {
    console.error('Interceptor - Response Error:', error.response?.status, error.response?.data);
    
    if (error.response?.status === 401) {
      localStorage.removeItem('makerhub_authenticated');
      localStorage.removeItem('makerhub_token');
      localStorage.removeItem('makerhub_user');
      window.location.href = '/';
    }
    
    return Promise.reject(error);
  }
);

export default api;
