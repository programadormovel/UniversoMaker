import DashboardLayout from '../../../shared/layouts/DashboardLayout'
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { fetchUsers } from '../api'
import type { User } from '../types'

export default function UserList() {
    const [users, setUsers] = useState<User[]>([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        let mounted = true
        fetchUsers().then((data) => {
            if (mounted) {
                setUsers(data)
                setLoading(false)
            }
        }).catch(() => {
            if (mounted) setLoading(false)
        })
        return () => { mounted = false }
    }, [])

    return (
        <DashboardLayout>
            <div className="space-y-6">
                <div className="flex items-center justify-between">
                    <div>
                        <Link to="/users" className="text-sm text-brand-600 hover:text-brand-800 flex items-center gap-1 mb-2">
                            <span>←</span> Voltar
                        </Link>
                        <h1 className="text-2xl font-bold text-brand-900">Lista de Usuários</h1>
                        <p className="text-gray-500">Visualizando todos os usuários</p>
                    </div>
                    <Link to="/users/new" className="bg-accent hover:bg-accent2 text-white px-4 py-2 rounded-lg shadow-sm transition-colors flex items-center gap-2">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                            <path fillRule="evenodd" d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z" clipRule="evenodd" />
                        </svg>
                        Novo Usuário
                    </Link>
                </div>

                <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
                    <div className="overflow-x-auto">
                        <table className="w-full text-left">
                            <thead>
                                <tr className="bg-gray-50 border-b border-gray-100 text-xs uppercase text-gray-500 font-semibold tracking-wider">
                                    <th className="px-6 py-4">Nome</th>
                                    <th className="px-6 py-4">Email</th>
                                    <th className="px-6 py-4">Status</th>
                                    <th className="px-6 py-4 text-right">Ações</th>
                                </tr>
                            </thead>
                            <tbody className="divide-y divide-gray-100">
                                {loading ? (
                                    <tr>
                                        <td colSpan={4} className="px-6 py-8 text-center text-gray-500">Carregando usuários...</td>
                                    </tr>
                                ) : users.length === 0 ? (
                                    <tr>
                                        <td colSpan={4} className="px-6 py-8 text-center text-gray-500">Nenhum usuário encontrado.</td>
                                    </tr>
                                ) : (
                                    users.map(u => (
                                        <tr key={u.id} className="hover:bg-gray-50 transition-colors">
                                            <td className="px-6 py-4">
                                                <div className="flex items-center gap-3">
                                                    <div className="h-8 w-8 rounded-full bg-brand-100 flex items-center justify-center text-brand-700 font-bold text-xs">
                                                        {u.name.charAt(0)}
                                                    </div>
                                                    <span className="font-medium text-gray-900">{u.name}</span>
                                                </div>
                                            </td>
                                            <td className="px-6 py-4 text-gray-600">{u.email}</td>
                                            <td className="px-6 py-4">
                                                <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
                                                    Ativo
                                                </span>
                                            </td>
                                            <td className="px-6 py-4 text-right">
                                                <Link to={`/users/edit?email=${u.email}`} className="text-brand-600 hover:text-brand-800 font-medium text-sm">Editar</Link>
                                            </td>
                                        </tr>
                                    ))
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </DashboardLayout>
    )
}
