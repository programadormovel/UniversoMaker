import React from 'react';
import { Alert } from 'react-bootstrap';
import { FaExclamationCircle } from 'react-icons/fa';

interface AlertMessageProps {
  message: string;
  variant?: 'danger' | 'success' | 'warning' | 'info';
  onClose?: () => void;
}

const AlertMessage: React.FC<AlertMessageProps> = ({ message, variant = 'danger', onClose }) => {
  return (
    <Alert variant={variant} onClose={onClose} dismissible={!!onClose} className="mb-3 text-start shadow-sm">
      <FaExclamationCircle className="me-2" />
      {message}
    </Alert>
  );
};

export default AlertMessage;
