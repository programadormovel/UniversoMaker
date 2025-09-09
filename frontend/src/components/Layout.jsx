import { useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import { motion, AnimatePresence } from 'framer-motion'
import { Menu, X, Home, Users, FileText, LogOut, UserPlus, Plus } from 'lucide-react'
import { useAuth } from '../hooks/useAuth'
import QuickMenu from './QuickMenu'

const Layout = ({ children }) => {
  const [sidebarOpen, setSidebarOpen] = useState(false)
  const { user, logout } = useAuth()
  const location = useLocation()

  const navigation = [
    { name: 'Dashboard', href: '/', icon: Home },
    { name: 'Alunos', href: '/alunos', icon: Users },
    { name: 'Cadastrar Aluno', href: '/alunos/novo', icon: UserPlus },
    { name: 'PEI', href: '/pei', icon: FileText },
    { name: 'Novo PEI', href: '/pei/novo', icon: Plus },
  ]

  return (
    <div className="flex h-screen bg-light">
      {/* Sidebar */}
      <motion.div
        initial={{ x: -300 }}
        animate={{ x: sidebarOpen ? 0 : -300 }}
        transition={{ type: "spring", damping: 25, stiffness: 200 }}
        className="fixed inset-y-0 left-0 z-50 w-72 bg-gradient-to-b from-primary via-primary to-secondary shadow-2xl lg:static lg:translate-x-0"
      >
        <div className="flex items-center justify-between h-20 px-6 bg-gradient-to-r from-primary to-secondary">
          <div className="flex items-center space-x-3">
            <motion.img 
              src="/logo.png" 
              alt="Universo Maker" 
              className="h-12 w-auto"
              whileHover={{ scale: 1.1, rotate: 5 }}
              transition={{ type: "spring", stiffness: 300 }}
            />
            <div className="hidden lg:block">
              <h2 className="text-white font-bold text-lg">Universo</h2>
              <p className="text-accent text-sm font-medium">Maker</p>
            </div>
          </div>
          <button
            onClick={() => setSidebarOpen(false)}
            className="lg:hidden text-white hover:text-accent transition-colors p-2 rounded-lg hover:bg-white/10"
          >
            <X size={24} />
          </button>
        </div>
        
        {/* Mascote */}
        <div className="px-6 py-4">
          <motion.div 
            className="bg-white/10 rounded-xl p-4 text-center"
            whileHover={{ scale: 1.02 }}
            transition={{ type: "spring", stiffness: 300 }}
          >
            <motion.img 
              src="/mascote.jpg" 
              alt="Mascote" 
              className="w-16 h-16 mx-auto rounded-full border-3 border-accent shadow-lg"
              animate={{ y: [0, -5, 0] }}
              transition={{ duration: 2, repeat: Infinity, ease: "easeInOut" }}
            />
            <p className="text-white text-sm mt-2 font-medium">Olá! Como posso ajudar?</p>
          </motion.div>
        </div>
        
        <nav className="mt-4 px-4">
          {navigation.map((item, index) => {
            const Icon = item.icon
            const isActive = location.pathname === item.href
            
            return (
              <motion.div
                key={item.name}
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ delay: index * 0.1 }}
              >
                <Link
                  to={item.href}
                  className={`flex items-center px-4 py-3 mb-2 rounded-xl transition-all duration-300 transform hover:scale-105 ${
                    isActive
                      ? 'bg-accent text-primary shadow-lg font-semibold'
                      : 'text-light hover:bg-white/10 hover:text-accent'
                  }`}
                >
                  <Icon size={20} className="mr-3" />
                  {item.name}
                </Link>
              </motion.div>
            )
          })}
        </nav>

        <div className="absolute bottom-4 left-4 right-4">
          <div className="bg-white/10 rounded-xl p-4 mb-4">
            <p className="text-light text-sm font-medium">{user?.nome}</p>
            <p className="text-light/70 text-xs">{user?.tipoUsuario}</p>
          </div>
          <motion.button
            onClick={logout}
            whileHover={{ scale: 1.05 }}
            whileTap={{ scale: 0.95 }}
            className="flex items-center w-full px-4 py-3 text-light hover:text-danger hover:bg-white/10 rounded-xl transition-all duration-200"
          >
            <LogOut size={20} className="mr-3" />
            Sair
          </motion.button>
        </div>
      </motion.div>

      {/* Main content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header */}
        <header className="bg-white shadow-sm border-b border-gray-200">
          <div className="flex items-center justify-between h-16 px-6">
            <button
              onClick={() => setSidebarOpen(true)}
              className="lg:hidden text-primary hover:text-secondary transition-colors p-2 rounded-lg hover:bg-gray-100"
            >
              <Menu size={24} />
            </button>
            
            <div className="flex items-center space-x-4">
              <div className="hidden sm:flex items-center space-x-3">
                <img src="/logo.png" alt="Universo Maker" className="h-8 w-auto" />
              </div>
              
              {/* Menu de Ações Rápidas */}
              <div className="flex items-center space-x-2">
                <Link
                  to="/alunos/novo"
                  className="hidden md:flex items-center px-3 py-2 bg-secondary text-white rounded-lg hover:bg-secondary/90 transition-colors text-sm"
                >
                  <UserPlus size={16} className="mr-1" />
                  Novo Aluno
                </Link>
                <Link
                  to="/pei/novo"
                  className="hidden md:flex items-center px-3 py-2 bg-accent text-primary rounded-lg hover:bg-accent/90 transition-colors text-sm"
                >
                  <Plus size={16} className="mr-1" />
                  Novo PEI
                </Link>
                
                {/* Menu Mobile */}
                <div className="md:hidden relative">
                  <motion.button
                    whileTap={{ scale: 0.95 }}
                    className="p-2 bg-primary text-white rounded-lg"
                    onClick={() => setSidebarOpen(true)}
                  >
                    <Plus size={20} />
                  </motion.button>
                </div>
              </div>
            </div>
          </div>
        </header>

        {/* Page content */}
        <main className="flex-1 overflow-auto pr-4 md:pr-6 pt-4 md:pt-6 pb-4 md:pb-6">
          <div className="w-full">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.3 }}
            >
              {children}
            </motion.div>
          </div>
        </main>
      </div>

      {/* Overlay */}
      <AnimatePresence>
        {sidebarOpen && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            className="fixed inset-0 bg-black bg-opacity-50 z-40 lg:hidden"
            onClick={() => setSidebarOpen(false)}
          />
        )}
      </AnimatePresence>
      
      {/* Menu Flutuante Mobile */}
      <QuickMenu />
    </div>
  )
}

export default Layout