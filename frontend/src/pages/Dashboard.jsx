import { motion } from 'framer-motion'
import { Users, FileText, BookOpen, TrendingUp } from 'lucide-react'

const Dashboard = () => {
  const stats = [
    { name: 'Total de Alunos', value: '24', icon: Users, color: 'bg-secondary' },
    { name: 'PEIs Ativos', value: '18', icon: FileText, color: 'bg-accent' },
    { name: 'Materiais', value: '156', icon: BookOpen, color: 'bg-primary' },
    { name: 'Progresso Médio', value: '78%', icon: TrendingUp, color: 'bg-danger' },
  ]

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        staggerChildren: 0.1
      }
    }
  }

  const itemVariants = {
    hidden: { y: 20, opacity: 0 },
    visible: {
      y: 0,
      opacity: 1,
      transition: {
        duration: 0.5
      }
    }
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <motion.div
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        className="bg-gradient-to-r from-primary to-secondary rounded-xl p-6 text-white"
      >
        <h1 className="text-3xl font-bold mb-2">Dashboard</h1>
        <p className="text-light/90">
          Bem-vindo à plataforma Universo Maker! Aqui você pode acompanhar o progresso dos alunos.
        </p>
      </motion.div>

      {/* Stats Grid */}
      <motion.div
        variants={containerVariants}
        initial="hidden"
        animate="visible"
        className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6"
      >
        {stats.map((stat, index) => {
          const Icon = stat.icon
          return (
            <motion.div
              key={stat.name}
              variants={itemVariants}
              whileHover={{ scale: 1.05 }}
              className="card"
            >
              <div className="flex items-center">
                <div className={`${stat.color} p-3 rounded-lg`}>
                  <Icon className="text-white" size={24} />
                </div>
                <div className="ml-4">
                  <p className="text-sm font-medium text-gray-600">{stat.name}</p>
                  <p className="text-2xl font-bold text-primary">{stat.value}</p>
                </div>
              </div>
            </motion.div>
          )
        })}
      </motion.div>

      {/* Recent Activity */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <motion.div
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ delay: 0.3 }}
          className="card"
        >
          <h3 className="text-lg font-semibold text-primary mb-4">Atividades Recentes</h3>
          <div className="space-y-3">
            {[
              { action: 'Novo aluno cadastrado', student: 'Maria Silva', time: '2 horas atrás' },
              { action: 'PEI atualizado', student: 'João Santos', time: '4 horas atrás' },
              { action: 'Avaliação realizada', student: 'Ana Costa', time: '1 dia atrás' },
            ].map((activity, index) => (
              <motion.div
                key={index}
                initial={{ opacity: 0, y: 10 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: 0.4 + index * 0.1 }}
                className="flex items-center p-3 bg-light rounded-lg"
              >
                <div className="w-2 h-2 bg-secondary rounded-full mr-3"></div>
                <div className="flex-1">
                  <p className="text-sm font-medium text-primary">{activity.action}</p>
                  <p className="text-xs text-gray-600">{activity.student} • {activity.time}</p>
                </div>
              </motion.div>
            ))}
          </div>
        </motion.div>

        <motion.div
          initial={{ opacity: 0, x: 20 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ delay: 0.4 }}
          className="card"
        >
          <h3 className="text-lg font-semibold text-primary mb-4">Próximas Tarefas</h3>
          <div className="space-y-3">
            {[
              { task: 'Revisar PEI - Pedro Oliveira', priority: 'Alta', due: 'Hoje' },
              { task: 'Avaliação trimestral - Sofia Ferreira', priority: 'Média', due: 'Amanhã' },
              { task: 'Reunião com responsáveis', priority: 'Baixa', due: '3 dias' },
            ].map((task, index) => (
              <motion.div
                key={index}
                initial={{ opacity: 0, y: 10 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: 0.5 + index * 0.1 }}
                className="flex items-center justify-between p-3 bg-light rounded-lg"
              >
                <div>
                  <p className="text-sm font-medium text-primary">{task.task}</p>
                  <p className="text-xs text-gray-600">Vence em {task.due}</p>
                </div>
                <span className={`px-2 py-1 text-xs rounded-full ${
                  task.priority === 'Alta' ? 'bg-danger/20 text-danger' :
                  task.priority === 'Média' ? 'bg-accent/20 text-accent' :
                  'bg-secondary/20 text-secondary'
                }`}>
                  {task.priority}
                </span>
              </motion.div>
            ))}
          </div>
        </motion.div>
      </div>
    </div>
  )
}

export default Dashboard