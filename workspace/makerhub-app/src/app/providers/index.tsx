import React from 'react'
import { BrowserRouter } from 'react-router-dom'
import AuthProvider from './AuthProvider'

export const AppProviders: React.FC<{children?: React.ReactNode}> = ({ children }) => {
  return (
    <BrowserRouter>
      <AuthProvider>
        {children}
      </AuthProvider>
    </BrowserRouter>
  )
}

export default AppProviders
