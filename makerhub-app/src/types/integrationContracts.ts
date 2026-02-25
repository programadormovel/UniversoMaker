export type ActivityStatus = 'assigned' | 'in_progress' | 'submitted' | 'reviewed';

export interface ActivitySummary {
  id: string;
  title: string;
  description: string;
  imageUrl?: string;
  createdAt: string;
  dueAt?: string;
  status?: ActivityStatus;
  studentId?: string;
  classId?: string;
  poleId?: string;
}

export interface ActivityAnswerPayload {
  activityId: string;
  studentId: string;
  guardianId?: string;
  answerText: string;
  attachments?: string[];
}

export interface ActivityAnswer extends ActivityAnswerPayload {
  id: string;
  createdAt: string;
  reviewedAt?: string;
  feedback?: string;
  score?: number;
}

export type ChatMessageRole = 'user' | 'assistant' | 'professional' | 'system';

export interface ChatMessage {
  id: string;
  threadId: string;
  senderRole: ChatMessageRole;
  senderName?: string;
  content: string;
  createdAt: string;
  status?: 'queued' | 'processing' | 'sent' | 'failed';
}

export interface ChatMessageRequest {
  threadId: string;
  studentId: string;
  guardianId?: string;
  content: string;
}

export interface AsyncChatEnqueueResponse {
  messageId: string;
  threadId: string;
  status: 'queued' | 'processing' | 'sent';
  createdAt: string;
}
