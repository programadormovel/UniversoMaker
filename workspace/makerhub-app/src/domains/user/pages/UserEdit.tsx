import DashboardLayout from '../../../shared/layouts/DashboardLayout'
import { useState, useEffect } from 'react'
import { useNavigate, useSearchParams, Link } from 'react-router-dom'
import { getUserByEmail, updateUser } from '../api'
import type { User } from '../types'

export default function UserEdit() {
    const nav = useNavigate()
    const [searchParams] = useSearchParams()
    const [searchEmail, setSearchEmail] = useState(searchParams.get('email') || '')
    const [user, setUser] = useState<User | null>(null)
    const [formData, setFormData] = useState({ name: '', email: '' })
    const [loading, setLoading] = useState(false)
    const [searching, setSearching] = useState(false)
    const [error, setError] = useState('')

    useEffect(() => {
        if (searchEmail && searchParams.get('email')) {
            handleSearch()
        }
    }, [])

    const handleSearch = async (e?: React.FormEvent) => {
        if (e) e.preventDefault()
        if (!searchEmail) return

        setSearching(true)
        setError('')
        setUser(null)

        try {
            const foundUser = await getUserByEmail(searchEmail)
            if (foundUser) {
                setUser(foundUser)
                setFormData({ name: foundUser.name, email: foundUser.email })
            } else {
                setError('Usuário não encontrado com este e-mail.')
            }
        } catch (err) {
            setError('Erro ao buscar usuário.')
        } finally {
            setSearching(false)
        }
    }

    const handleSave = async (e: React.FormEvent) => {
        e.preventDefault()
        if (!user) return

        setLoading(true)
        try {
            await updateUser({ ...user, ...formData })
            nav('/users/list')
        } catch (err) {
            console.error('Failed to update user', err)
            setLoading(false)
        }
    }

    return (
        <DashboardLayout>
            <div className="max-w-2xl mx-auto space-y-8">
                <div>
                    <Link to="/users" className="text-sm text-brand-600 hover:text-brand-800 flex items-center gap-1 mb-2">
                        <span>←</span> Voltar
                    </Link>
                    <h1 className="text-2xl font-bold text-brand-900">Editar Usuário</h1>
                    <p className="text-gray-500">Busque pelo e-mail do usuário para editar</p>
                </div>

                {/* Search Section */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-6">
                    <form onSubmit={handleSearch} className="flex gap-4">
                        <div className="flex-1">
                            <label htmlFor="search" className="sr-only">Buscar por e-mail</label>
                            <input
                                id="search"
                                type="email"
                                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-brand-500 focus:border-transparent outline-none transition-all"
                                placeholder="Digite o e-mail do usuário..."
                                value={searchEmail}
                                onChange={e => setSearchEmail(e.target.value)}
                            />
                        </div>
                        <button
                            type="submit"
                            disabled={searching || !searchEmail}
                            className="bg-brand-600 hover:bg-brand-700 text-white px-6 py-2 rounded-lg shadow-sm transition-colors disabled:opacity-50"
                        >
                            {searching ? 'Buscando...' : 'Buscar'}
                        </button>
                    </form>
                    {error && <p className="text-red-500 text-sm mt-2">{error}</p>}
                </div>

                {/* Edit Form */}
                {user && (
                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-6 animate-fade-in">
                        <h2 className="text-lg font-semibold text-brand-900 mb-4">Dados do Usuário</h2>
                        <form onSubmit={handleSave} className="space-y-6">
                            <div>
                                <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-1">Nome Completo</label>
                                <input
                                    id="name"
                                    className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-brand-500 focus:border-transparent outline-none transition-all"
                                    value={formData.name}
                                    onChange={e => setFormData(prev => ({ ...prev, name: e.target.value }))}
                                    required
                                />
                            </div>

                            <div>
                                <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-1">E-mail</label>
                                <input
                                    id="email"
                                    type="email"
                                    className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-brand-500 focus:border-transparent outline-none transition-all bg-gray-50 cursor-not-allowed"
                                    value={formData.email}
                                    readOnly
                                    title="O e-mail não pode ser alterado"
                                />
                                <p className="text-xs text-gray-500 mt-1">O e-mail é usado como identificador e não pode ser alterado.</p>
                            </div>

                            <div className="flex items-center justify-end gap-4 pt-4">
                                <button
                                    type="button"
                                    onClick={() => { setUser(null); setSearchEmail(''); }}
                                    className="px-4 py-2 text-gray-600 hover:text-gray-800 font-medium"
                                >
                                    Cancelar
                                </button>
                                <button
                                    type="submit"
                                    disabled={loading}
                                    className="bg-accent hover:bg-accent2 text-white px-6 py-2 rounded-lg shadow-sm transition-colors disabled:opacity-50"
                                >
                                    {loading ? 'Salvando...' : 'Salvar Alterações'}
                                </button>
                            </div>
                        </form>
                    </div>
                )}
            </div>
        </DashboardLayout>
    )
}
