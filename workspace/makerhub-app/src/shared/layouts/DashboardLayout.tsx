import React, { useEffect, useState } from 'react'
import Sidebar from '../components/Sidebar'
import TopNav from '../components/TopNav'

const SIDEBAR_KEY = 'uh_sidebar_open'

export const DashboardLayout: React.FC<{children?: React.ReactNode}> = ({ children }) => {
  const [sidebarOpen, setSidebarOpen] = useState<boolean>(() => {
    try {
      const raw = localStorage.getItem(SIDEBAR_KEY)
      return raw ? JSON.parse(raw) === true : false
    } catch {
      return false
    }
  })

  useEffect(() => {
    try {
      localStorage.setItem(SIDEBAR_KEY, JSON.stringify(sidebarOpen))
    } catch {}
  }, [sidebarOpen])

  const open = () => setSidebarOpen(true)
  const close = () => setSidebarOpen(false)

  return (
    <div className="min-h-screen flex flex-col bg-gray-50">
      <TopNav onToggle={open} ariaExpanded={sidebarOpen} ariaControls="primary-sidebar" />
      <div className="flex flex-1">
        {/* Sidebar for desktop and slide-in for mobile */}
        <aside className="w-64 md:relative md:translate-x-0" aria-hidden={!sidebarOpen && undefined}>
          <Sidebar isOpen={sidebarOpen} onClose={close} />
        </aside>

        {/* Overlay when sidebar is open on small screens */}
        {sidebarOpen && (
          <div onClick={close} className="fixed inset-0 z-20 bg-black/40 md:hidden" role="button" aria-label="Close sidebar overlay" />
        )}

        <main className="flex-1 p-6" id="main-content">{children}</main>
      </div>
    </div>
  )
}

export default DashboardLayout
