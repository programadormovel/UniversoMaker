import { useState } from 'react'
import { motion, AnimatePresence } from 'framer-motion'
import { Plus, UserPlus, FileText, X } from 'lucide-react'
import { Link } from 'react-router-dom'

const QuickMenu = () => {
  const [isOpen, setIsOpen] = useState(false)

  const menuItems = [
    {
      name: 'Novo Aluno',
      href: '/alunos/novo',
      icon: UserPlus,
      color: 'bg-secondary',
      description: 'Cadastrar novo aluno'
    },
    {
      name: 'Novo PEI',
      href: '/pei/novo',
      icon: FileText,
      color: 'bg-accent',
      description: 'Criar plano educacional'
    }
  ]

  return (
    <div className="fixed bottom-6 right-6 z-50 md:hidden">
      <AnimatePresence>
        {isOpen && (
          <motion.div
            initial={{ opacity: 0, scale: 0.8 }}
            animate={{ opacity: 1, scale: 1 }}
            exit={{ opacity: 0, scale: 0.8 }}
            className="absolute bottom-16 right-0 space-y-3"
          >
            {menuItems.map((item, index) => {
              const Icon = item.icon
              return (
                <motion.div
                  key={item.name}
                  initial={{ opacity: 0, x: 20 }}
                  animate={{ opacity: 1, x: 0 }}
                  exit={{ opacity: 0, x: 20 }}
                  transition={{ delay: index * 0.1 }}
                >
                  <Link
                    to={item.href}
                    onClick={() => setIsOpen(false)}
                    className={`flex items-center ${item.color} text-white px-4 py-3 rounded-full shadow-lg hover:shadow-xl transition-all duration-200 transform hover:scale-105`}
                  >
                    <Icon size={20} className="mr-2" />
                    <span className="font-medium">{item.name}</span>
                  </Link>
                </motion.div>
              )
            })}
          </motion.div>
        )}
      </AnimatePresence>

      <motion.button
        whileHover={{ scale: 1.1 }}
        whileTap={{ scale: 0.9 }}
        onClick={() => setIsOpen(!isOpen)}
        className={`w-14 h-14 rounded-full shadow-lg flex items-center justify-center transition-all duration-200 ${
          isOpen ? 'bg-danger rotate-45' : 'bg-primary'
        } text-white`}
      >
        {isOpen ? <X size={24} /> : <Plus size={24} />}
      </motion.button>
    </div>
  )
}

export default QuickMenu