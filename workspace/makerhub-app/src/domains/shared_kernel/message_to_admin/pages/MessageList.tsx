import DashboardLayout from '../../../../shared/layouts/DashboardLayout'
import { useEffect, useState } from 'react'
import { fetchAllMessages, inactivateMessage } from '../api'
import type { MessageToAdmin } from '../types'

export default function MessageList() {
    const [messages, setMessages] = useState<MessageToAdmin[]>([])
    const [loading, setLoading] = useState(true)
    const [filter, setFilter] = useState<'all' | 'active'>('active')

    useEffect(() => {
        loadMessages()
    }, [filter])

    const loadMessages = async () => {
        setLoading(true)
        try {
            const data = await fetchAllMessages()
            const filtered = filter === 'active' ? data.filter(m => m.isActive) : data
            setMessages(filtered)
        } catch (error) {
            console.error('Failed to load messages', error)
        } finally {
            setLoading(false)
        }
    }

    const handleInactivate = async (id: number) => {
        if (!confirm('Deseja realmente inativar esta mensagem?')) return

        try {
            await inactivateMessage(id)
            loadMessages()
        } catch (error) {
            console.error('Failed to inactivate message', error)
            alert('Erro ao inativar mensagem')
        }
    }

    return (
        <DashboardLayout>
            <div className="space-y-6">
                <div className="flex items-center justify-between">
                    <div>
                        <h1 className="text-2xl font-bold text-brand-900">Mensagens dos Usuários</h1>
                        <p className="text-gray-500">Visualizando mensagens enviadas ao administrador</p>
                    </div>
                    <div className="flex gap-2">
                        <button
                            onClick={() => setFilter('active')}
                            className={`px-4 py-2 rounded-lg transition-colors ${filter === 'active' ? 'bg-brand-600 text-white' : 'bg-gray-200 text-gray-700 hover:bg-gray-300'}`}
                        >
                            Ativas
                        </button>
                        <button
                            onClick={() => setFilter('all')}
                            className={`px-4 py-2 rounded-lg transition-colors ${filter === 'all' ? 'bg-brand-600 text-white' : 'bg-gray-200 text-gray-700 hover:bg-gray-300'}`}
                        >
                            Todas
                        </button>
                    </div>
                </div>

                <div className="space-y-4">
                    {loading ? (
                        <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-8 text-center text-gray-500">
                            Carregando mensagens...
                        </div>
                    ) : messages.length === 0 ? (
                        <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-8 text-center text-gray-500">
                            Nenhuma mensagem encontrada.
                        </div>
                    ) : (
                        messages.map(msg => (
                            <div key={msg.id} className={`bg-white rounded-xl shadow-sm border p-6 ${msg.isActive ? 'border-gray-100' : 'border-gray-300 bg-gray-50'}`}>
                                <div className="flex items-start justify-between mb-4">
                                    <div className="flex-1">
                                        <div className="flex items-center gap-3 mb-2">
                                            <h3 className="text-lg font-semibold text-brand-900">{msg.subject}</h3>
                                            {!msg.isActive && (
                                                <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-gray-200 text-gray-700">
                                                    Inativa
                                                </span>
                                            )}
                                        </div>
                                        <div className="flex items-center gap-4 text-sm text-gray-600">
                                            <span className="flex items-center gap-1">
                                                <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                                                </svg>
                                                {msg.sender}
                                            </span>
                                            <span className="flex items-center gap-1">
                                                <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                                                </svg>
                                                {msg.email}
                                            </span>
                                            {msg.createdAt && (
                                                <span className="flex items-center gap-1">
                                                    <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                                                    </svg>
                                                    {new Date(msg.createdAt).toLocaleString('pt-BR')}
                                                </span>
                                            )}
                                        </div>
                                    </div>
                                    {msg.isActive && (
                                        <button
                                            onClick={() => handleInactivate(msg.id)}
                                            className="text-red-600 hover:text-red-800 text-sm font-medium px-3 py-1 rounded hover:bg-red-50 transition-colors"
                                        >
                                            Inativar
                                        </button>
                                    )}
                                </div>
                                <p className="text-gray-700 whitespace-pre-wrap">{msg.message}</p>
                            </div>
                        ))
                    )}
                </div>
            </div>
        </DashboardLayout>
    )
}
