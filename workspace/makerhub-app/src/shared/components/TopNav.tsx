import { Link } from 'react-router-dom'
import { useAuth } from '../../app/hooks/useAuth'

type Props = {
  onToggle?: () => void
  ariaExpanded?: boolean
  ariaControls?: string
}

export default function TopNav({ onToggle, ariaExpanded, ariaControls }: Props) {
  const { user, logout } = useAuth()

  return (
    <div className="w-full bg-white border-b border-gray-200 shadow-sm sticky top-0 z-20">
      <div className="px-6 py-3 flex items-center justify-between">
        <div className="flex items-center gap-4">
          <button
            aria-label="Open sidebar"
            onClick={onToggle}
            aria-expanded={ariaExpanded}
            aria-controls={ariaControls}
            className="md:hidden p-2 rounded-lg hover:bg-gray-100 text-brand-600 focus:outline-none focus:ring-2 focus:ring-brand-500"
          >
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
          <Link to="/dashboard" className="font-bold text-brand-900 flex items-center gap-3 text-lg">
            <div className="h-8 w-8 bg-accent rounded-lg flex items-center justify-center text-white font-bold">
              <img src="assets/logo.png" alt="logo" />
            </div>
            <span className="hidden sm:inline">Universo Maker</span>
          </Link>
        </div>
        <div className="flex items-center gap-4">
          <div className="flex items-center gap-3">
            <div className="text-right hidden sm:block">
              <p className="text-sm font-medium text-gray-900">{user?.name || 'Usuário'}</p>
              <p className="text-xs text-gray-500">Admin</p>
            </div>
            <div className="h-10 w-10 bg-brand-100 rounded-full flex items-center justify-center text-brand-700 font-bold border-2 border-white shadow-sm">
              {user?.name?.charAt(0) || 'U'}
            </div>
          </div>
          <button onClick={logout} className="text-sm text-red-600 hover:text-red-800 font-medium ml-2">Sair</button>
        </div>
      </div>
    </div>
  )
}
