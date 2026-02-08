import React from 'react'
import '../App.css'
import AppProviders from './providers'
import Routes from './routes'

const App: React.FC = () => {
  return (
    <AppProviders>
      <Routes />
    </AppProviders>
  )
}

export default App
