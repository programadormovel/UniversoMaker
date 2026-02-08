import React, { createContext, useCallback, useEffect, useState } from 'react'

type AuthContextType = {
  isAuthenticated: boolean
  user?: { name?: string }
  login: (payload?: { name?: string }) => void
  logout: () => void
}

export const AuthContext = createContext<AuthContextType>({
  isAuthenticated: false,
  login: () => {},
  logout: () => {},
})

const AUTH_STORAGE_KEY = 'uh_auth_v1'

export const AuthProvider: React.FC<{children?: React.ReactNode}> = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    try {
      const raw = localStorage.getItem(AUTH_STORAGE_KEY)
      return raw ? JSON.parse(raw).isAuthenticated === true : false
    } catch {
      return false
    }
  })

  const [user, setUser] = useState<{ name?: string } | undefined>(() => {
    try {
      const raw = localStorage.getItem(AUTH_STORAGE_KEY)
      return raw ? JSON.parse(raw).user : undefined
    } catch {
      return undefined
    }
  })

  useEffect(() => {
    try {
      localStorage.setItem(AUTH_STORAGE_KEY, JSON.stringify({ isAuthenticated, user }))
    } catch {}
  }, [isAuthenticated, user])

  const login = useCallback((payload?: { name?: string }) => {
    const name = payload?.name ?? 'Usuário'
    setUser({ name })
    setIsAuthenticated(true)
  }, [])

  const logout = useCallback(() => {
    setIsAuthenticated(false)
    setUser(undefined)
  }, [])

  return (
    <AuthContext.Provider value={{ isAuthenticated, user, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider
