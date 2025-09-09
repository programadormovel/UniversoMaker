import { useState } from 'react'
import { Navigate } from 'react-router-dom'
import { motion } from 'framer-motion'
import { useForm } from 'react-hook-form'
import { Eye, EyeOff, Mail, Lock } from 'lucide-react'
import toast from 'react-hot-toast'
import { useAuth } from '../hooks/useAuth'

const Login = () => {
  const [showPassword, setShowPassword] = useState(false)
  const [loading, setLoading] = useState(false)
  const { user, login } = useAuth()
  const { register, handleSubmit, formState: { errors } } = useForm()

  if (user) {
    return <Navigate to="/" />
  }

  const onSubmit = async (data) => {
    setLoading(true)
    try {
      await login(data.email, data.senha)
      toast.success('Login realizado com sucesso!')
    } catch (error) {
      toast.error('Credenciais inválidas')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-primary via-secondary to-accent p-4">
      <motion.div
        initial={{ opacity: 0, scale: 0.9 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.5 }}
        className="w-full max-w-md"
      >
        <div className="bg-white rounded-2xl shadow-2xl p-8">
          {/* Logo */}
          <div className="text-center mb-8">
            <motion.img
              src="/logo.png"
              alt="Universo Maker"
              className="h-16 w-auto mx-auto mb-4 animate-bounce-gentle"
              initial={{ y: -20 }}
              animate={{ y: 0 }}
              transition={{ delay: 0.2 }}
            />
            <h1 className="text-2xl font-bold text-primary mb-2">
              Bem-vindo ao Universo Maker
            </h1>
            <p className="text-gray-600">
              Faça login para acessar a plataforma
            </p>
          </div>

          {/* Form */}
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-primary mb-2">
                Email
              </label>
              <div className="relative">
                <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
                <input
                  {...register('email', { 
                    required: 'Email é obrigatório',
                    pattern: {
                      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                      message: 'Email inválido'
                    }
                  })}
                  type="email"
                  className="input-field pl-10"
                  placeholder="seu@email.com"
                />
              </div>
              {errors.email && (
                <p className="text-danger text-sm mt-1">{errors.email.message}</p>
              )}
            </div>

            <div>
              <label className="block text-sm font-medium text-primary mb-2">
                Senha
              </label>
              <div className="relative">
                <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
                <input
                  {...register('senha', { required: 'Senha é obrigatória' })}
                  type={showPassword ? 'text' : 'password'}
                  className="input-field pl-10 pr-10"
                  placeholder="••••••••"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-primary"
                >
                  {showPassword ? <EyeOff size={20} /> : <Eye size={20} />}
                </button>
              </div>
              {errors.senha && (
                <p className="text-danger text-sm mt-1">{errors.senha.message}</p>
              )}
            </div>

            <motion.button
              whileHover={{ scale: 1.02 }}
              whileTap={{ scale: 0.98 }}
              type="submit"
              disabled={loading}
              className="w-full btn-primary disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {loading ? (
                <div className="flex items-center justify-center">
                  <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white mr-2"></div>
                  Entrando...
                </div>
              ) : (
                'Entrar'
              )}
            </motion.button>
          </form>

          {/* Demo credentials */}
          <div className="mt-6 p-4 bg-light rounded-lg">
            <p className="text-sm text-primary font-medium mb-2">Credenciais de teste:</p>
            <p className="text-xs text-gray-600">Email: admin@universomaker.com</p>
            <p className="text-xs text-gray-600">Senha: admin123</p>
          </div>
        </div>
      </motion.div>
    </div>
  )
}

export default Login