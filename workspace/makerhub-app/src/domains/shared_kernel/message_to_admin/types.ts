export interface MessageToAdmin {
    id: number
    sender: string
    email: string
    subject: string
    message: string
    isActive: boolean
    createdAt?: string
    updatedAt?: string
}
