import { useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import { motion } from 'framer-motion'
import { Menu, X, Home, Users, FileText, LogOut } from 'lucide-react'
import { useAuth } from '../hooks/useAuth'

const Layout = ({ children }) => {
  const [sidebarOpen, setSidebarOpen] = useState(false)
  const { user, logout } = useAuth()
  const location = useLocation()

  const navigation = [
    { name: 'Dashboard', href: '/', icon: Home },
    { name: 'Alunos', href: '/alunos', icon: Users },
    { name: 'PEI', href: '/pei', icon: FileText },
  ]

  return (
    <div className="flex h-screen bg-light">
      {/* Sidebar */}
      <motion.div
        initial={{ x: -300 }}
        animate={{ x: sidebarOpen ? 0 : -300 }}
        className="fixed inset-y-0 left-0 z-50 w-64 bg-primary shadow-lg lg:static lg:translate-x-0"
      >
        <div className="flex items-center justify-between h-16 px-6 bg-primary">
          <img src="/logo.png" alt="Universo Maker" className="h-10 w-auto" />
          <button
            onClick={() => setSidebarOpen(false)}
            className="lg:hidden text-white hover:text-accent"
          >
            <X size={24} />
          </button>
        </div>
        
        <nav className="mt-8 px-4">
          {navigation.map((item) => {
            const Icon = item.icon
            const isActive = location.pathname === item.href
            
            return (
              <Link
                key={item.name}
                to={item.href}
                className={`flex items-center px-4 py-3 mb-2 rounded-lg transition-all duration-200 ${
                  isActive
                    ? 'bg-secondary text-white shadow-lg'
                    : 'text-light hover:bg-primary/80 hover:text-accent'
                }`}
              >
                <Icon size={20} className="mr-3" />
                {item.name}
              </Link>
            )
          })}
        </nav>

        <div className="absolute bottom-4 left-4 right-4">
          <div className="bg-primary/50 rounded-lg p-4 mb-4">
            <p className="text-light text-sm font-medium">{user?.nome}</p>
            <p className="text-light/70 text-xs">{user?.tipoUsuario}</p>
          </div>
          <button
            onClick={logout}
            className="flex items-center w-full px-4 py-3 text-light hover:text-danger hover:bg-primary/80 rounded-lg transition-all duration-200"
          >
            <LogOut size={20} className="mr-3" />
            Sair
          </button>
        </div>
      </motion.div>

      {/* Main content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header */}
        <header className="bg-white shadow-sm border-b border-gray-200">
          <div className="flex items-center justify-between h-16 px-6">
            <button
              onClick={() => setSidebarOpen(true)}
              className="lg:hidden text-primary hover:text-secondary"
            >
              <Menu size={24} />
            </button>
            
            <div className="flex items-center space-x-4">
              <div className="hidden sm:block">
                <h1 className="text-xl font-semibold text-primary">
                  Universo Maker
                </h1>
              </div>
            </div>
          </div>
        </header>

        {/* Page content */}
        <main className="flex-1 overflow-auto p-6">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.3 }}
          >
            {children}
          </motion.div>
        </main>
      </div>

      {/* Overlay */}
      {sidebarOpen && (
        <div
          className="fixed inset-0 bg-black bg-opacity-50 z-40 lg:hidden"
          onClick={() => setSidebarOpen(false)}
        />
      )}
    </div>
  )
}

export default Layout