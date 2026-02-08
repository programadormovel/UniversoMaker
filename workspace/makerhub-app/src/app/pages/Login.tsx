import { useNavigate, Link } from 'react-router-dom'
import { useState } from 'react'
import { useAuth } from '../hooks/useAuth'

export default function Login() {
  const nav = useNavigate()
  const auth = useAuth()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const submit = (e: React.FormEvent) => {
    e.preventDefault()
    // call the client-side auth for now and navigate
    auth.login({ name: username || 'Usuário' })
    nav('/dashboard')
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-brand-900 to-brand-700 p-4">
      <div className="w-full max-w-md bg-white/10 backdrop-blur-lg rounded-2xl shadow-2xl p-8 border border-white/20">
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold text-white mb-2">MakerHub</h1>
          <p className="text-brand-200">Bem-vindo de volta!</p>
        </div>

        <form onSubmit={submit} className="space-y-6">
          <div>
            <label htmlFor="username" className="block text-sm font-medium text-brand-100 mb-1">Usuário</label>
            <input
              id="username"
              className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-3 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all"
              placeholder="Digite seu usuário"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div>
            <label htmlFor="password" className="block text-sm font-medium text-brand-100 mb-1">Senha</label>
            <input
              id="password"
              type="password"
              className="w-full bg-white/5 border border-brand-500/30 rounded-lg px-4 py-3 text-white placeholder-brand-300/50 focus:outline-none focus:ring-2 focus:ring-accent focus:border-transparent transition-all"
              placeholder="Digite sua senha"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <div className="flex items-center justify-between text-sm">
            <label className="flex items-center text-brand-200 cursor-pointer hover:text-white">
              <input type="checkbox" className="mr-2 rounded border-brand-500/30 bg-white/5 text-accent focus:ring-accent" />
              Lembrar-me
            </label>
            <Link to="/forgot-password" className="text-accent hover:text-accent2 transition-colors">Esqueceu a senha?</Link>
          </div>

          <button
            type="submit"
            className="w-full bg-accent hover:bg-accent2 text-white font-bold py-3 rounded-lg shadow-lg transform transition hover:-translate-y-0.5 active:translate-y-0"
          >
            Entrar
          </button>
        </form>

        <div className="mt-8 text-center text-sm text-brand-300">
          <p>Não tem uma conta? <Link to="/contact-admin" className="text-white hover:underline">Contate o administrador</Link></p>
        </div>
      </div>
    </div>
  )
}
