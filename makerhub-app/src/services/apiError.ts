import { AxiosError } from 'axios';

export type ApiErrorCode =
  | 'UNAUTHORIZED'
  | 'FORBIDDEN'
  | 'SERVER_ERROR'
  | 'TIMEOUT'
  | 'NETWORK_ERROR'
  | 'UNKNOWN_ERROR';

export interface ApiError extends Error {
  isApiError: true;
  code: ApiErrorCode;
  status?: number;
  endpoint?: string;
  retryable: boolean;
  details?: unknown;
}

const createApiError = (
  message: string,
  code: ApiErrorCode,
  status?: number,
  endpoint?: string,
  details?: unknown
): ApiError => {
  const error = new Error(message) as ApiError;
  error.isApiError = true;
  error.code = code;
  error.status = status;
  error.endpoint = endpoint;
  error.retryable = code === 'TIMEOUT' || code === 'NETWORK_ERROR' || code === 'SERVER_ERROR';
  error.details = details;
  return error;
};

export const normalizeApiError = (error: unknown): ApiError => {
  const axiosError = error as AxiosError<any>;
  const status = axiosError?.response?.status;
  const endpoint = axiosError?.config?.url;
  const details = axiosError?.response?.data;

  if (axiosError?.code === 'ECONNABORTED' || String(axiosError?.message).toLowerCase().includes('timeout')) {
    return createApiError('Tempo de resposta excedido. Tente novamente.', 'TIMEOUT', status, endpoint, details);
  }

  if (status === 401) {
    return createApiError('Sessão expirada. Faça login novamente.', 'UNAUTHORIZED', status, endpoint, details);
  }

  if (status === 403) {
    return createApiError('Acesso negado para esta operação.', 'FORBIDDEN', status, endpoint, details);
  }

  if (status && status >= 500) {
    return createApiError('Erro interno no servidor. Tente novamente em instantes.', 'SERVER_ERROR', status, endpoint, details);
  }

  if (!axiosError?.response) {
    return createApiError('Falha de conexão com a API.', 'NETWORK_ERROR', undefined, endpoint, details);
  }

  return createApiError('Erro inesperado ao processar a requisição.', 'UNKNOWN_ERROR', status, endpoint, details);
};

export const getApiErrorMessage = (error: unknown, fallback = 'Não foi possível concluir a operação.'): string => {
  const apiError = error as Partial<ApiError>;
  if (apiError?.isApiError && apiError.message) {
    return apiError.message;
  }
  return fallback;
};

