import { useState, useEffect } from 'react'
import { motion } from 'framer-motion'
import { Plus, FileText, Clock, CheckCircle, AlertCircle } from 'lucide-react'
import toast from 'react-hot-toast'
import api from '../services/api'

const PEI = () => {
  const [peis, setPeis] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    // Dados mockados para demonstração
    setTimeout(() => {
      setPeis([
        {
          id: '1',
          aluno: { nome: 'Pedro Oliveira' },
          versao: 1,
          status: 'Rascunho',
          dataCriacao: '2024-01-15',
          criador: { nome: 'Dr. João Silva' }
        },
        {
          id: '2',
          aluno: { nome: 'Sofia Ferreira' },
          versao: 2,
          status: 'Aprovado',
          dataCriacao: '2024-01-10',
          dataAprovacao: '2024-01-20',
          criador: { nome: 'Maria Santos' }
        }
      ])
      setLoading(false)
    }, 1000)
  }, [])

  const getStatusIcon = (status) => {
    switch (status) {
      case 'Rascunho':
        return <Clock className="text-accent" size={20} />
      case 'Aprovado':
        return <CheckCircle className="text-secondary" size={20} />
      case 'Arquivado':
        return <AlertCircle className="text-gray-400" size={20} />
      default:
        return <FileText className="text-primary" size={20} />
    }
  }

  const getStatusColor = (status) => {
    switch (status) {
      case 'Rascunho':
        return 'bg-accent/20 text-accent'
      case 'Aprovado':
        return 'bg-secondary/20 text-secondary'
      case 'Arquivado':
        return 'bg-gray-200 text-gray-600'
      default:
        return 'bg-primary/20 text-primary'
    }
  }

  if (loading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-secondary"></div>
      </div>
    )
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-primary">PEI - Planos Educacionais</h1>
          <p className="text-gray-600">Gerencie os planos educacionais individualizados</p>
        </div>
        <motion.button
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.95 }}
          className="btn-primary flex items-center"
        >
          <Plus size={20} className="mr-2" />
          Novo PEI
        </motion.button>
      </div>

      {/* PEI List */}
      <div className="space-y-4">
        {peis.map((pei, index) => (
          <motion.div
            key={pei.id}
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: index * 0.1 }}
            whileHover={{ scale: 1.02 }}
            className="card"
          >
            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-4">
                <div className="w-12 h-12 bg-gradient-to-br from-primary to-secondary rounded-lg flex items-center justify-center">
                  <FileText className="text-white" size={24} />
                </div>
                
                <div>
                  <h3 className="font-semibold text-primary text-lg">
                    PEI - {pei.aluno.nome}
                  </h3>
                  <div className="flex items-center space-x-4 text-sm text-gray-600 mt-1">
                    <span>Versão {pei.versao}</span>
                    <span>•</span>
                    <span>Criado por {pei.criador.nome}</span>
                    <span>•</span>
                    <span>{new Date(pei.dataCriacao).toLocaleDateString('pt-BR')}</span>
                  </div>
                </div>
              </div>

              <div className="flex items-center space-x-3">
                <div className="flex items-center space-x-2">
                  {getStatusIcon(pei.status)}
                  <span className={`px-3 py-1 rounded-full text-sm font-medium ${getStatusColor(pei.status)}`}>
                    {pei.status}
                  </span>
                </div>
                
                <div className="flex space-x-2">
                  <motion.button
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                    className="bg-secondary/10 text-secondary px-4 py-2 rounded-lg text-sm font-medium hover:bg-secondary/20 transition-colors"
                  >
                    Visualizar
                  </motion.button>
                  
                  {pei.status === 'Rascunho' && (
                    <motion.button
                      whileHover={{ scale: 1.05 }}
                      whileTap={{ scale: 0.95 }}
                      className="bg-accent/10 text-accent px-4 py-2 rounded-lg text-sm font-medium hover:bg-accent/20 transition-colors"
                    >
                      Editar
                    </motion.button>
                  )}
                </div>
              </div>
            </div>

            {pei.dataAprovacao && (
              <div className="mt-3 pt-3 border-t border-gray-100">
                <p className="text-sm text-gray-600">
                  <span className="font-medium">Aprovado em:</span> {' '}
                  {new Date(pei.dataAprovacao).toLocaleDateString('pt-BR')}
                </p>
              </div>
            )}
          </motion.div>
        ))}
      </div>

      {peis.length === 0 && (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          className="text-center py-12"
        >
          <div className="w-24 h-24 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <FileText size={32} className="text-gray-400" />
          </div>
          <h3 className="text-lg font-medium text-primary mb-2">Nenhum PEI encontrado</h3>
          <p className="text-gray-600">Comece criando o primeiro plano educacional</p>
        </motion.div>
      )}
    </div>
  )
}

export default PEI