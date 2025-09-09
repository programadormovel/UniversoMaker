import { useState, useEffect } from 'react'
import { motion } from 'framer-motion'
import { useForm } from 'react-hook-form'
import { Save, ArrowLeft, FileText, Users, Target, Calendar } from 'lucide-react'
import { Link } from 'react-router-dom'
import toast from 'react-hot-toast'
import api from '../services/api'

const CadastroPEI = () => {
  const [loading, setLoading] = useState(false)
  const [alunos, setAlunos] = useState([])
  const [peis, setPeis] = useState([])
  const { register, handleSubmit, reset, formState: { errors } } = useForm()

  useEffect(() => {
    fetchAlunos()
    fetchPEIs()
  }, [])

  const fetchAlunos = async () => {
    try {
      const response = await api.get('/alunos')
      setAlunos(response.data)
    } catch (error) {
      console.error('Erro ao carregar alunos:', error)
    }
  }

  const fetchPEIs = async () => {
    try {
      // Mock data para demonstração
      setPeis([
        {
          id: '1',
          aluno: { nome: 'Pedro Oliveira' },
          versao: 1,
          status: 'Rascunho',
          dataCriacao: '2024-01-15'
        },
        {
          id: '2',
          aluno: { nome: 'Sofia Ferreira' },
          versao: 2,
          status: 'Aprovado',
          dataCriacao: '2024-01-10'
        }
      ])
    } catch (error) {
      console.error('Erro ao carregar PEIs:', error)
    }
  }

  const onSubmit = async (data) => {
    setLoading(true)
    try {
      await api.post('/pei', data)
      toast.success('PEI criado com sucesso!')
      reset()
      fetchPEIs()
    } catch (error) {
      toast.error('Erro ao criar PEI')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-primary">Criar Novo PEI</h1>
          <p className="text-gray-600">Plano Educacional Individualizado</p>
        </div>
        <Link
          to="/pei"
          className="btn-secondary flex items-center w-fit"
        >
          <ArrowLeft size={20} className="mr-2" />
          Voltar
        </Link>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Formulário */}
        <div className="lg:col-span-2">
          <motion.div
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            className="card"
          >
            <div className="flex items-center mb-6">
              <div className="w-12 h-12 bg-primary rounded-lg flex items-center justify-center mr-4">
                <FileText className="text-white" size={24} />
              </div>
              <div>
                <h2 className="text-xl font-semibold text-primary">Dados do PEI</h2>
                <p className="text-gray-600 text-sm">Configure o plano educacional</p>
              </div>
            </div>

            <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  Selecionar Aluno *
                </label>
                <div className="relative">
                  <Users className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
                  <select
                    {...register('alunoId', { required: 'Aluno é obrigatório' })}
                    className="input-field pl-10"
                  >
                    <option value="">Selecione um aluno</option>
                    {alunos.map(aluno => (
                      <option key={aluno.id} value={aluno.id}>
                        {aluno.nome}
                      </option>
                    ))}
                  </select>
                </div>
                {errors.alunoId && (
                  <p className="text-danger text-sm mt-1">{errors.alunoId.message}</p>
                )}
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-primary mb-2">
                    Versão
                  </label>
                  <input
                    {...register('versao')}
                    type="number"
                    defaultValue={1}
                    min={1}
                    className="input-field"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-primary mb-2">
                    Status
                  </label>
                  <select
                    {...register('status')}
                    className="input-field"
                    defaultValue="Rascunho"
                  >
                    <option value="Rascunho">Rascunho</option>
                    <option value="Aprovado">Aprovado</option>
                    <option value="Arquivado">Arquivado</option>
                  </select>
                </div>
              </div>

              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  Objetivos Gerais
                </label>
                <div className="relative">
                  <Target className="absolute left-3 top-3 text-gray-400" size={20} />
                  <textarea
                    {...register('objetivosGerais')}
                    className="input-field pl-10 min-h-[120px] resize-none"
                    placeholder="Descreva os objetivos gerais do PEI..."
                  />
                </div>
              </div>

              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  Estratégias Pedagógicas
                </label>
                <textarea
                  {...register('estrategias')}
                  className="input-field min-h-[120px] resize-none"
                  placeholder="Descreva as estratégias pedagógicas a serem utilizadas..."
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  Recursos Necessários
                </label>
                <textarea
                  {...register('recursos')}
                  className="input-field min-h-[100px] resize-none"
                  placeholder="Liste os recursos necessários para implementação..."
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-primary mb-2">
                  Critérios de Avaliação
                </label>
                <textarea
                  {...register('criteriosAvaliacao')}
                  className="input-field min-h-[100px] resize-none"
                  placeholder="Defina como o progresso será avaliado..."
                />
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
                    Criar PEI
                  </div>
                )}
              </motion.button>
            </form>
          </motion.div>
        </div>

        {/* Sidebar com PEIs Recentes */}
        <motion.div
          initial={{ opacity: 0, x: 20 }}
          animate={{ opacity: 1, x: 0 }}
          className="space-y-6"
        >
          {/* PEIs Recentes */}
          <div className="card">
            <h3 className="text-lg font-semibold text-primary mb-4">PEIs Recentes</h3>
            
            <div className="space-y-3 max-h-64 overflow-y-auto">
              {peis.length === 0 ? (
                <div className="text-center py-6">
                  <div className="w-12 h-12 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-2">
                    <FileText size={20} className="text-gray-400" />
                  </div>
                  <p className="text-gray-600 text-sm">Nenhum PEI criado ainda</p>
                </div>
              ) : (
                peis.map((pei, index) => (
                  <motion.div
                    key={pei.id}
                    initial={{ opacity: 0, y: 10 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: index * 0.1 }}
                    className="p-3 bg-light rounded-lg hover:bg-gray-100 transition-colors"
                  >
                    <div className="flex items-start justify-between">
                      <div className="flex-1">
                        <p className="font-medium text-primary text-sm">{pei.aluno.nome}</p>
                        <p className="text-xs text-gray-600">Versão {pei.versao}</p>
                        <p className="text-xs text-gray-500">
                          {new Date(pei.dataCriacao).toLocaleDateString('pt-BR')}
                        </p>
                      </div>
                      <span className={`px-2 py-1 text-xs rounded-full ${
                        pei.status === 'Aprovado' ? 'bg-secondary/20 text-secondary' :
                        pei.status === 'Rascunho' ? 'bg-accent/20 text-accent' :
                        'bg-gray-200 text-gray-600'
                      }`}>
                        {pei.status}
                      </span>
                    </div>
                  </motion.div>
                ))
              )}
            </div>
          </div>

          {/* Dicas */}
          <div className="card bg-gradient-to-br from-secondary/10 to-accent/10">
            <h4 className="font-semibold text-primary mb-3">💡 Dicas para PEI</h4>
            <ul className="space-y-2 text-sm text-gray-700">
              <li>• Defina objetivos específicos e mensuráveis</li>
              <li>• Considere as necessidades individuais do aluno</li>
              <li>• Estabeleça prazos realistas</li>
              <li>• Envolva a família no processo</li>
              <li>• Revise periodicamente o progresso</li>
            </ul>
          </div>
        </motion.div>
      </div>
    </div>
  )
}

export default CadastroPEI