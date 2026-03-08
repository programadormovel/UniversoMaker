import axios from 'axios';
import { normalizeApiError } from './apiError';

const api = axios.create({
  baseURL: 'https://makerhub.api.mongreltech.com.br/api/v1', 
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 15000,
  withCredentials: true,
});

api.interceptors.request.use(
  async (config) => {
    console.log('Interceptor - Request:', config.method?.toUpperCase(), config.url);
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
    const normalizedError = normalizeApiError(error);
    console.error('Interceptor - Response Error:', normalizedError.code, normalizedError.status, normalizedError.endpoint);
    return Promise.reject(normalizedError);
  }
);

export default api;
