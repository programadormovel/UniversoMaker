import api from './api';
import {
  ActivityAnswer,
  ActivitySummary,
  AsyncChatEnqueueResponse,
  ChatMessage,
  ChatMessageRequest
} from '../types/integrationContracts';

const asString = (value: unknown, fallback = '') => (value === undefined || value === null ? fallback : String(value));
const API_PATHS = {
  activitiesByClass: (classId: string) => `/activity/class/${classId}`,
  activitiesByPole: (poleId: string) => `/activity/pole/${poleId}`,
  answersByActivity: (activityId: string) => `/activity-answer/activity/${activityId}`,
  sendChatAsync: '/chat/message/async',
  listThreadMessages: (threadId: string) => `/chat/thread/${threadId}/messages`
} as const;

const normalizeActivity = (raw: any): ActivitySummary => ({
  id: asString(raw?.id ?? raw?.activityId),
  title: asString(raw?.title ?? raw?.activityName ?? 'Atividade'),
  description: asString(raw?.description ?? raw?.objective ?? ''),
  imageUrl: raw?.imageUrl ?? raw?.image,
  createdAt: asString(raw?.createdAt ?? new Date().toISOString()),
  dueAt: raw?.dueAt,
  status: raw?.status,
  studentId: raw?.studentId ? asString(raw.studentId) : undefined,
  classId: raw?.classId ? asString(raw.classId) : undefined,
  poleId: raw?.poleId ? asString(raw.poleId) : undefined
});

const normalizeAnswer = (raw: any): ActivityAnswer => ({
  id: asString(raw?.id ?? raw?.answerId),
  activityId: asString(raw?.activityId),
  studentId: asString(raw?.studentId),
  guardianId: raw?.guardianId ? asString(raw.guardianId) : undefined,
  answerText: asString(raw?.answerText ?? raw?.answer ?? ''),
  attachments: Array.isArray(raw?.attachments) ? raw.attachments.map(String) : [],
  createdAt: asString(raw?.createdAt ?? new Date().toISOString()),
  reviewedAt: raw?.reviewedAt,
  feedback: raw?.feedback,
  score: raw?.score
});

const normalizeChat = (raw: any): ChatMessage => ({
  id: asString(raw?.id ?? raw?.messageId),
  threadId: asString(raw?.threadId),
  senderRole: (raw?.senderRole ?? raw?.role ?? 'user') as ChatMessage['senderRole'],
  senderName: raw?.senderName,
  content: asString(raw?.content ?? raw?.text ?? ''),
  createdAt: asString(raw?.createdAt ?? new Date().toISOString()),
  status: raw?.status
});

const getSingle = async <T>(endpoint: string, normalize: (payload: any) => T): Promise<T> => {
  const response = await api.get(endpoint);
  return normalize(response.data);
};

const postSingle = async <T>(endpoint: string, payload: unknown, normalize: (payload: any) => T): Promise<T> => {
  const response = await api.post(endpoint, payload);
  return normalize(response.data);
};

export const clientIntegrationService = {
  async listActivitiesByClass(classId: string): Promise<ActivitySummary[]> {
    return getSingle(API_PATHS.activitiesByClass(classId), (data) =>
      Array.isArray(data) ? data.map(normalizeActivity) : []
    );
  },

  async listActivitiesByPole(poleId: string): Promise<ActivitySummary[]> {
    return getSingle(API_PATHS.activitiesByPole(poleId), (data) =>
      Array.isArray(data) ? data.map(normalizeActivity) : []
    );
  },

  async listActivityAnswers(activityId: string): Promise<ActivityAnswer[]> {
    return getSingle(API_PATHS.answersByActivity(activityId), (data) =>
      Array.isArray(data) ? data.map(normalizeAnswer) : []
    );
  },

  async sendProfessionalChatMessage(payload: ChatMessageRequest): Promise<AsyncChatEnqueueResponse> {
    const backendPayload = {
      threadId: payload.threadId,
      studentId: Number(payload.studentId),
      guardianId: payload.guardianId ? Number(payload.guardianId) : undefined,
      message: payload.content,
      senderRole: 'professional',
      isActive: true
    };

    return postSingle(API_PATHS.sendChatAsync, backendPayload, (data) => ({
      messageId: asString(data?.messageId ?? data?.id),
      threadId: asString(data?.threadId ?? payload.threadId),
      status: (data?.status ?? 'queued') as AsyncChatEnqueueResponse['status'],
      createdAt: asString(data?.createdAt ?? new Date().toISOString())
    }));
  },

  async listThreadMessages(threadId: string): Promise<ChatMessage[]> {
    return getSingle(API_PATHS.listThreadMessages(threadId), (data) =>
      Array.isArray(data) ? data.map(normalizeChat) : []
    );
  }
};
