import React, { useState, FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';
import AlertMessage from '../components/AlertMessage';
import './Login.css';

const Login: React.FC = () => {
  const [username, setUsername] = useState<string>(() => localStorage.getItem('saved_username') || '');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string>('');
  const [consent, setConsent] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);
  const [rememberMe, setRememberMe] = useState<boolean>(() => !!localStorage.getItem('saved_username'));
  const navigate = useNavigate();

  const handleLogin = async (e: FormEvent) => {
    e.preventDefault();
    console.log('handleLogin chamado');
    console.log('Username:', username);
    console.log('Password:', password);
    
    setError('');
    setLoading(true);
    document.body.style.cursor = 'wait';
    
    try {
      console.log('Chamando authService.login...');
      await authService.login({ username, password });
      console.log('Login bem-sucedido!');
      console.log('isAuthenticated:', authService.isAuthenticated());
      
      if (rememberMe) {
        localStorage.setItem('saved_username', username);
      } else {
        localStorage.removeItem('saved_username');
      }
      
      console.log('Navegando para /app...');
      navigate('/app', { replace: true });
    } catch (err: any) {
      console.error('Erro no login:', err);
      const errorMessage = err.response?.data?.message || 'Falha no login. Verifique suas credenciais.';
      setError(errorMessage);
      setLoading(false);
      document.body.style.cursor = 'default';
    }
  };

  return (
    <div className="login-container" style={{ backgroundColor: '#fafafa' }}>
      <div className="login-card">
        <h1>UniversoMaker</h1>
        <p>Gerencie sua Sala Maker</p>
        <form onSubmit={handleLogin}>
          <div className="input-group">
            <label>Usuário</label>
            <input 
              type="text" 
              value={username} 
              onChange={(e) => setUsername(e.target.value)} 
              disabled={loading}
              required 
            />
          </div>
          <div className="input-group">
            <label>Senha</label>
            <input 
              type="password" 
              value={password} 
              onChange={(e) => setPassword(e.target.value)} 
              disabled={loading}
              required 
            />
          </div>
          <div className="form-check text-start mb-3">
            <input 
              className="form-check-input"
              type="checkbox" 
              id="rememberMe"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
              disabled={loading}
            />
            <label className="form-check-label" htmlFor="rememberMe" style={{ fontSize: '0.9rem' }}>
              Lembrar meu usuário
            </label>
          </div>
          <div className="form-check text-start mb-3">
            <input 
              className="form-check-input"
              type="checkbox" 
              id="consent"
              checked={consent}
              onChange={(e) => setConsent(e.target.checked)}
              disabled={loading}
            />
            <label className="form-check-label" htmlFor="consent" style={{ fontSize: '0.9rem' }}>
              Li e concordo com a <a href="http://www.mongreltech.com.br/politica_maker.html" target="_blank" rel="noopener noreferrer">Política de Privacidade</a>
            </label>
          </div>
          {error && <AlertMessage message={error} onClose={() => setError('')} />}
          <button type="submit" disabled={!consent || loading} style={{ opacity: (consent && !loading) ? 1 : 0.6, cursor: (consent && !loading) ? 'pointer' : 'not-allowed' }}>
            {loading ? '⏳ Entrando...' : 'Entrar'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
