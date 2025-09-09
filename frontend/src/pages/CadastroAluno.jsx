import { useState, useEffect } from 'react'
import { motion } from 'framer-motion'
import { useForm } from 'react-hook-form'
import { Save, ArrowLeft, User, Calendar, FileText, Camera } from 'lucide-react'
import { Link } from 'react-router-dom'
import toast from 'react-hot-toast'
import api from '../services/api'

const CadastroAluno = () => {
  const [loading, setLoading] = useState(false)
  const [alunos, setAlunos] = useState([])
  const { register, handleSubmit, reset, formState: { errors } } = useForm()

  useEffect(() => {
    fetchAlunos()
  }, [])

  const fetchAlunos = async () => {
    try {
      const response = await api.get('/alunos')
      setAlunos(response.data)
    } catch (error) {
      console.error('Erro ao carregar alunos:', error)
    }
  }

  const onSubmit = async (data) => {
    setLoading(true)
    try {
      await api.post('/alunos', data)
      toast.success('Aluno cadastrado com sucesso!')
      reset()
      fetchAlunos()
    } catch (error) {
      toast.error('Erro ao cadastrar aluno')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-primary">Cadastrar Novo Aluno</h1>
          <p className="text-gray-600">Adicione um novo aluno ao sistema</p>
        </div>
        <Link
          to="/alunos"
          className="btn-secondary flex items-center w-fit"
        >
          <ArrowLeft size={20} className="mr-2" />
          Voltar
        </Link>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Formulário */}
        <motion.div
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
          className="card"
        >
          <div className="flex items-center mb-6">
            <div className="w-12 h-12 bg-secondary rounded-lg flex items-center justify-center mr-4">
              <User className="text-white" size={24} />
            </div>
            <div>
              <h2 className="text-xl font-semibold text-primary">Dados do Aluno</h2>
              <p className="text-gray-600 text-sm">Preencha as informações básicas</p>
            </div>
          </div>

          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-primary mb-2">
                Nome Completo *
              </label>
              <input
                {...register('nome', { required: 'Nome é obrigatório' })}
                className="input-field"
                placeholder="Digite o nome completo"
              />
              {errors.nome && (
                <p className="text-danger text-sm mt-1">{errors.nome.message}</p>
              )}
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  Data de Nascimento *
                </label>
                <div className="relative">
                  <Calendar className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
                  <input
                    {...register('dataNascimento', { required: 'Data é obrigatória' })}
                    type="date"
                    className="input-field pl-10"
                  />
                </div>
                {errors.dataNascimento && (
                  <p className="text-danger text-sm mt-1">{errors.dataNascimento.message}</p>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  CPF
                </label>
                <input
                  {...register('cpf')}
                  className="input-field"
                  placeholder="000.000.000-00"
                  maxLength={14}
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium text-primary mb-2">
                Histórico Escolar
              </label>
              <div className="relative">
                <FileText className="absolute left-3 top-3 text-gray-400" size={20} />
                <textarea
                  {...register('historicoEscolar')}
                  className="input-field pl-10 min-h-[100px] resize-none"
                  placeholder="Descreva o histórico escolar do aluno..."
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium text-primary mb-2">
                Histórico Clínico
              </label>
              <div className="relative">
                <FileText className="absolute left-3 top-3 text-gray-400" size={20} />
                <textarea
                  {...register('historicoClinico')}
                  className="input-field pl-10 min-h-[100px] resize-none"
                  placeholder="Descreva o histórico clínico do aluno..."
                />
              </div>
            </div>

            <motion.button
              whileHover={{ scale: 1.02 }}
              whileTap={{ scale: 0.98 }}
              type="submit"
              disabled={loading}
              className="w-full btn-primary disabled:opacity-50"
            >
              {loading ? (
                <div className="flex items-center justify-center">
                  <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white mr-2"></div>
                  Salvando...
                </div>
              ) : (
                <div className="flex items-center justify-center">
                  <Save size={20} className="mr-2" />
                  Cadastrar Aluno
                </div>
              )}
            </motion.button>
          </form>
        </motion.div>

        {/* Lista de Alunos Recentes */}
        <motion.div
          initial={{ opacity: 0, x: 20 }}
          animate={{ opacity: 1, x: 0 }}
          className="card"
        >
          <h3 className="text-lg font-semibold text-primary mb-4">Alunos Cadastrados</h3>
          
          <div className="space-y-3 max-h-96 overflow-y-auto">
            {alunos.length === 0 ? (
              <div className="text-center py-8">
                <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-3">
                  <User size={24} className="text-gray-400" />
                </div>
                <p className="text-gray-600">Nenhum aluno cadastrado ainda</p>
              </div>
            ) : (
              alunos.map((aluno, index) => (
                <motion.div
                  key={aluno.id}
                  initial={{ opacity: 0, y: 10 }}
                  animate={{ opacity: 1, y: 0 }}
                  transition={{ delay: index * 0.1 }}
                  className="flex items-center p-3 bg-light rounded-lg hover:bg-gray-100 transition-colors"
                >
                  <div className="w-10 h-10 bg-gradient-to-br from-secondary to-accent rounded-full flex items-center justify-center text-white font-bold mr-3">
                    {aluno.nome.charAt(0)}
                  </div>
                  <div className="flex-1">
                    <p className="font-medium text-primary">{aluno.nome}</p>
                    <p className="text-sm text-gray-600">
                      {aluno.dataNascimento ? new Date(aluno.dataNascimento).toLocaleDateString('pt-BR') : 'Data não informada'}
                    </p>
                  </div>
                </motion.div>
              ))
            )}
          </div>
        </motion.div>
      </div>
    </div>
  )
}

export default CadastroAluno