import React, { useState, useEffect } from 'react';
import { Modal, Button } from 'react-bootstrap';

interface CookieConsentProps {
  onAccept: () => void;
}

const CookieConsent: React.FC<CookieConsentProps> = ({ onAccept }) => {
  const [show, setShow] = useState(false);

  useEffect(() => {
    const consent = localStorage.getItem('cookie_consent');
    if (!consent) {
      setShow(true);
    }
  }, []);

  const handleAccept = () => {
    localStorage.setItem('cookie_consent', 'accepted');
    setShow(false);
    onAccept();
  };

  return (
    <Modal show={show} backdrop="static" keyboard={false} centered>
      <Modal.Header>
        <Modal.Title>🍪 Permissão de Cookies</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <p>Este aplicativo precisa armazenar cookies para manter sua sessão ativa e garantir uma melhor experiência.</p>
        <p className="mb-0">Ao continuar, você concorda com o uso de cookies.</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={handleAccept}>
          Aceitar e Continuar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default CookieConsent;
