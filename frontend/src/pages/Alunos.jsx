import { useState, useEffect } from 'react'
import { motion } from 'framer-motion'
import { Plus, Search, Edit, Eye } from 'lucide-react'
import toast from 'react-hot-toast'
import api from '../services/api'

const Alunos = () => {
  const [alunos, setAlunos] = useState([])
  const [loading, setLoading] = useState(true)
  const [searchTerm, setSearchTerm] = useState('')

  useEffect(() => {
    fetchAlunos()
  }, [])

  const fetchAlunos = async () => {
    try {
      const response = await api.get('/alunos')
      setAlunos(response.data)
    } catch (error) {
      toast.error('Erro ao carregar alunos')
    } finally {
      setLoading(false)
    }
  }

  const filteredAlunos = alunos.filter(aluno =>
    aluno.nome.toLowerCase().includes(searchTerm.toLowerCase())
  )

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
          <h1 className="text-2xl font-bold text-primary">Alunos</h1>
          <p className="text-gray-600">Gerencie os alunos da plataforma</p>
        </div>
        <motion.button
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.95 }}
          className="btn-primary flex items-center"
        >
          <Plus size={20} className="mr-2" />
          Novo Aluno
        </motion.button>
      </div>

      {/* Search */}
      <div className="relative">
        <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
        <input
          type="text"
          placeholder="Buscar alunos..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="input-field pl-10"
        />
      </div>

      {/* Alunos Grid */}
      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6"
      >
        {filteredAlunos.map((aluno, index) => (
          <motion.div
            key={aluno.id}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: index * 0.1 }}
            whileHover={{ y: -5 }}
            className="card"
          >
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 bg-gradient-to-br from-secondary to-accent rounded-full flex items-center justify-center text-white font-bold text-lg">
                {aluno.nome.charAt(0)}
              </div>
              <div className="ml-3">
                <h3 className="font-semibold text-primary">{aluno.nome}</h3>
                <p className="text-sm text-gray-600">
                  {new Date().getFullYear() - new Date(aluno.dataNascimento).getFullYear()} anos
                </p>
              </div>
            </div>

            <div className="space-y-2 mb-4">
              {aluno.historicoEscolar && (
                <p className="text-sm text-gray-600">
                  <span className="font-medium">Escolar:</span> {aluno.historicoEscolar}
                </p>
              )}
              {aluno.historicoClinico && (
                <p className="text-sm text-gray-600">
                  <span className="font-medium">Clínico:</span> {aluno.historicoClinico}
                </p>
              )}
            </div>

            <div className="flex space-x-2">
              <motion.button
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                className="flex-1 bg-secondary/10 text-secondary px-3 py-2 rounded-lg text-sm font-medium hover:bg-secondary/20 transition-colors flex items-center justify-center"
              >
                <Eye size={16} className="mr-1" />
                Ver
              </motion.button>
              <motion.button
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                className="flex-1 bg-accent/10 text-accent px-3 py-2 rounded-lg text-sm font-medium hover:bg-accent/20 transition-colors flex items-center justify-center"
              >
                <Edit size={16} className="mr-1" />
                Editar
              </motion.button>
            </div>
          </motion.div>
        ))}
      </motion.div>

      {filteredAlunos.length === 0 && (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          className="text-center py-12"
        >
          <div className="w-24 h-24 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <Users size={32} className="text-gray-400" />
          </div>
          <h3 className="text-lg font-medium text-primary mb-2">Nenhum aluno encontrado</h3>
          <p className="text-gray-600">
            {searchTerm ? 'Tente ajustar sua busca' : 'Comece cadastrando o primeiro aluno'}
          </p>
        </motion.div>
      )}
    </div>
  )
}

export default Alunos