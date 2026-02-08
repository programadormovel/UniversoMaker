import api from '../../../../app/config/http'
import type { MessageToAdmin } from '../types'

export const fetchAllMessages = async (): Promise<MessageToAdmin[]> => {
    const response = await api.get<MessageToAdmin[]>('/v1/message/all')
    return response.data
}

export const fetchActiveMessages = async (): Promise<MessageToAdmin[]> => {
    const response = await api.get<MessageToAdmin[]>('/v1/message/active')
    return response.data
}

export const fetchMessageById = async (id: number): Promise<MessageToAdmin> => {
    const response = await api.get<MessageToAdmin>(`/v1/message/${id}`)
    return response.data
}

export const sendMessage = async (message: Omit<MessageToAdmin, 'id' | 'isActive' | 'createdAt' | 'updatedAt'>): Promise<MessageToAdmin> => {
    const response = await api.post<MessageToAdmin>('/v1/message/send', message)
    return response.data
}

export const inactivateMessage = async (id: number): Promise<MessageToAdmin> => {
    const response = await api.put<MessageToAdmin>(`/v1/message/inactivate/${id}`)
    return response.data
}
