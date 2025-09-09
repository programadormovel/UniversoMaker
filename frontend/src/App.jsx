import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Toaster } from 'react-hot-toast'
import { AuthProvider } from './hooks/useAuth'
import Layout from './components/Layout'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import Alunos from './pages/Alunos'
import CadastroAluno from './pages/CadastroAluno'
import PEI from './pages/PEI'
import CadastroPEI from './pages/CadastroPEI'
import ProtectedRoute from './components/ProtectedRoute'

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="min-h-screen bg-light">
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/" element={
              <ProtectedRoute>
                <Layout>
                  <Dashboard />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/alunos" element={
              <ProtectedRoute>
                <Layout>
                  <Alunos />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/alunos/novo" element={
              <ProtectedRoute>
                <Layout>
                  <CadastroAluno />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/pei" element={
              <ProtectedRoute>
                <Layout>
                  <PEI />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/pei/novo" element={
              <ProtectedRoute>
                <Layout>
                  <CadastroPEI />
                </Layout>
              </ProtectedRoute>
            } />
          </Routes>
          <Toaster position="top-right" />
        </div>
      </Router>
    </AuthProvider>
  )
}

export default App