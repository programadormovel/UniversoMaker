import { Link } from 'react-router-dom'
import { useState } from 'react'

export default function ForgotPassword() {

    const [email, setEmail] = useState('')
    const [submitted, setSubmitted] = useState(false)

    const submit = (e: React.FormEvent) => {
        e.preventDefault()
        // Mock submission logic
        console.log('Recovering password for:', email)
        setSubmitted(true)
        // In a real app, you would call an API here
    }

    return (
        <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-brand-900 to-brand-700 p-4">
            <div className="w-full max-w-md bg-white/10 backdrop-blur-lg rounded-2xl shadow-2xl p-8 border border-white/20">
                <div className="text-center mb-8">
                    <h1 className="text-3xl font-bold text-white mb-2">Recuperar Senha</h1>
                    <p className="text-brand-200">
                        {submitted
                            ? 'Verifique seu e-mail para instruções.'
                            : 'Digite seu e-mail para receber o link de recuperação.'}
                    </p>
                </div>

                {!submitted ? (
                    <form onSubmit={submit} className="space-y-6">
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium text-brand-100 mb-1">E-mail</label>
                            <input
                                id="email"
                                type="email"
                                className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-3 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all"
                                placeholder="seu@email.com"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            className="w-full bg-accent hover:bg-accent2 text-white font-bold py-3 rounded-lg shadow-lg transform transition hover:-translate-y-0.5 active:translate-y-0"
                        >
                            Enviar Link
                        </button>
                    </form>
                ) : (
                    <div className="text-center space-y-6">
                        <div className="bg-green-500/20 text-green-200 p-4 rounded-lg border border-green-500/30">
                            E-mail enviado com sucesso!
                        </div>
                        <button
                            onClick={() => setSubmitted(false)}
                            className="text-accent hover:text-accent2 text-sm underline"
                        >
                            Tentar outro e-mail
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
