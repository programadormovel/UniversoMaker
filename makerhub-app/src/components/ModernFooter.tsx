import mascot from '../assets/mascote.png';

const ModernFooter = () => {
  return (
    <footer 
      className="mt-auto py-4 border-top" 
      style={{ 
        backgroundColor: '#fff',
        borderTop: '1px solid #dbdbdb'
      }}
    >
      <div className="container" style={{ maxWidth: '935px' }}>
        <div className="d-flex flex-column flex-md-row justify-content-between align-items-center gap-3">
          <div className="d-flex align-items-center gap-3">
            <img 
              src={mascot} 
              alt="Mascote Universo Maker" 
              style={{ 
                height: '48px',
                opacity: 0.7,
                transition: 'opacity 0.3s ease'
              }}
              onMouseEnter={(e) => e.currentTarget.style.opacity = '1'}
              onMouseLeave={(e) => e.currentTarget.style.opacity = '0.7'}
            />
            <div className="text-start">
              <p className="mb-0 fw-semibold" style={{ fontSize: '14px', color: '#262626' }}>
                Universo Maker
              </p>
              <p className="mb-0 text-muted" style={{ fontSize: '12px' }}>
                © 2026 Todos os direitos reservados
              </p>
            </div>
          </div>
          
          <a 
            href="http://www.mongreltech.com.br/politica_maker.html" 
            target="_blank" 
            rel="noopener noreferrer"
            style={{
              fontSize: '13px',
              color: '#8e8e8e',
              textDecoration: 'none',
              fontWeight: 500,
              transition: 'color 0.2s ease'
            }}
            onMouseEnter={(e) => e.currentTarget.style.color = '#262626'}
            onMouseLeave={(e) => e.currentTarget.style.color = '#8e8e8e'}
          >
            Política de Privacidade
          </a>
        </div>
      </div>
    </footer>
  );
};

export default ModernFooter;
