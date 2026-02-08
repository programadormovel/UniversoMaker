import { Navbar, Container, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import logo from '../assets/logo-app-v4.jpg';

interface ModernHeaderProps {
  title: string;
  backTo?: string;
  showBackButton?: boolean;
}

const ModernHeader = ({ title, backTo = '/dashboard', showBackButton = true }: ModernHeaderProps) => {
  const navigate = useNavigate();

  return (
    <Navbar bg="white" className="border-bottom fixed-top shadow-sm" style={{ height: '60px', zIndex: 1030 }}>
      <Container style={{ maxWidth: '935px' }} className="d-flex justify-content-between align-items-center">
        <Navbar.Brand 
          className="d-flex align-items-center gap-2" 
          onClick={() => navigate('/')} 
          style={{ cursor: 'pointer', margin: 0 }}
        >
          <img 
            src={logo} 
            alt="Universo Maker" 
            style={{ 
              height: '32px', 
              width: '32px', 
              objectFit: 'cover', 
              borderRadius: '50%',
              border: '2px solid #e1e8ed'
            }}
          />
          <span 
            className="fw-bold d-none d-sm-inline" 
            style={{ 
              color: '#1a1a1a', 
              fontSize: '20px',
              letterSpacing: '-0.3px',
              fontWeight: 600
            }}
          >
            {title}
          </span>
        </Navbar.Brand>
        
        {showBackButton && (
          <Button 
            onClick={() => navigate(backTo)}
            style={{
              backgroundColor: 'transparent',
              border: 'none',
              color: '#0095f6',
              fontWeight: 600,
              fontSize: '14px',
              padding: '8px 16px',
              borderRadius: '8px',
              transition: 'all 0.2s ease',
              display: 'flex',
              alignItems: 'center',
              gap: '6px'
            }}
            onMouseEnter={(e) => {
              e.currentTarget.style.backgroundColor = '#f0f8ff';
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.backgroundColor = 'transparent';
            }}
          >
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
              <path d="M19 12H5M12 19l-7-7 7-7"/>
            </svg>
            <span className="d-none d-sm-inline">Voltar</span>
          </Button>
        )}
      </Container>
    </Navbar>
  );
};

export default ModernHeader;
