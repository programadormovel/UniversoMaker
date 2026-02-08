import api from '../../../app/config/http'
import type { User } from '../types'

export const fetchUsers = async (): Promise<User[]> => {
  const response = await api.get<User[]>('/v1/user/all')
  return response.data
}

export const createUser = async (user: Omit<User, 'id'>): Promise<User> => {
  const response = await api.post<User>('/v1/user/register', {
    name: user.name,
    email: user.email,
    password: 'defaultPassword123' // TODO: Add password field to form
  })
  return response.data
}

export const updateUser = async (user: User): Promise<User> => {
  await new Promise(resolve => setTimeout(resolve, 800))
  return user
}

export const getUserByEmail = async (email: string): Promise<User | null> => {
  await new Promise(resolve => setTimeout(resolve, 800))
  const users = await fetchUsers()
  return users.find(u => u.email === email) || null
}
