import { Link } from 'react-router-dom'
import { useState } from 'react'
import { sendMessage } from '../../domains/shared_kernel/message_to_admin/api'

export default function ContactAdmin() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        subject: '',
        message: ''
    })
    const [submitted, setSubmitted] = useState(false)
    const [loading, setLoading] = useState(false)

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
        const { name, value } = e.target

        setFormData(prev => {
            if (name === 'subject') {
                let newMessage = prev.message;
                if (value === 'Solicitação de acesso') {
                    newMessage = 'Gostaria de solicitar acesso ao sistema';
                } else if (value === 'Inativação de conta') {
                    newMessage = 'Gostaria de solicitar a inativação da minha conta de acesso ao sistema';
                } else if (value === 'Outro assunto') {
                    newMessage = '';
                }
                return { ...prev, [name]: value, message: newMessage };
            }
            return { ...prev, [name]: value };
        });
    }

    const submit = async (e: React.FormEvent) => {
        e.preventDefault()
        setLoading(true)
        try {
            await sendMessage({
                sender: formData.name,
                email: formData.email,
                subject: formData.subject,
                message: formData.message
            })
            setSubmitted(true)
        } catch (error) {
            console.error('Failed to send message', error)
            alert('Erro ao enviar mensagem. Tente novamente.')
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-brand-900 to-brand-700 p-4">
            <div className="w-full max-w-lg bg-white/10 backdrop-blur-lg rounded-2xl shadow-2xl p-8 border border-white/20">
                <div className="text-center mb-8">
                    <h1 className="text-3xl font-bold text-white mb-2">Fale com o Admin</h1>
                    <p className="text-brand-200">
                        {submitted
                            ? 'Sua mensagem foi enviada.'
                            : 'Preencha o formulário abaixo para entrar em contato.'}
                    </p>
                </div>

                {!submitted ? (
                    <form onSubmit={submit} className="space-y-4">
                        <div>
                            <label htmlFor="name" className="block text-sm font-medium text-brand-100 mb-1">Nome</label>
                            <input
                                id="name"
                                name="name"
                                className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-2 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all"
                                placeholder="Seu nome"
                                value={formData.name}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium text-brand-100 mb-1">E-mail</label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-2 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all"
                                placeholder="seu@email.com"
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div>
                            <label htmlFor="subject" className="block text-sm font-medium text-brand-100 mb-1">Assunto</label>
                            <select
                                id="subject"
                                name="subject"
                                className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-2 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all [&>option]:text-brand-900"
                                value={formData.subject}
                                onChange={handleChange}
                                required
                            >
                                <option value="" disabled>Selecione um assunto</option>
                                <option value="Solicitação de acesso">Solicitação de acesso</option>
                                <option value="Inativação de conta">Inativação de conta</option>
                                <option value="Outro assunto">Outro assunto</option>
                            </select>
                        </div>

                        <div>
                            <label htmlFor="message" className="block text-sm font-medium text-brand-100 mb-1">Mensagem</label>
                            <textarea
                                id="message"
                                name="message"
                                rows={4}
                                className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-2 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all resize-none"
                                placeholder="Descreva sua solicitação..."
                                value={formData.message}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            disabled={loading}
                            className="w-full bg-accent hover:bg-accent2 text-white font-bold py-3 rounded-lg shadow-lg transform transition hover:-translate-y-0.5 active:translate-y-0 mt-2 disabled:opacity-50 disabled:cursor-not-allowed"
                        >
                            {loading ? 'Enviando...' : 'Enviar Mensagem'}
                        </button>
                    </form>
                ) : (
                    <div className="text-center space-y-6">
                        <div className="bg-green-500/20 text-green-200 p-4 rounded-lg border border-green-500/30">
                            Mensagem enviada com sucesso! Entraremos em contato em breve.
                        </div>
                        <button
                            onClick={() => setSubmitted(false)}
                            className="text-accent hover:text-accent2 text-sm underline"
                        >
                            Enviar nova mensagem
                        </button>
                    </div>
                )}

                <div className="mt-8 text-center text-sm">
                    <Link to="/login" className="text-brand-300 hover:text-white transition-colors flex items-center justify-center gap-2">
                        <span>←</span> Voltar para o Login
                    </Link>
                </div>
            </div>
        </div>
    )
}
