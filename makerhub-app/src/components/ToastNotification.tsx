import { Toast, ToastContainer } from 'react-bootstrap';

interface ToastNotificationProps {
  show: boolean;
  message: string;
  type: 'success' | 'error' | 'info' | 'warning';
  onClose: () => void;
}

const ToastNotification = ({ show, message, type, onClose }: ToastNotificationProps) => {
  const getVariant = () => {
    switch (type) {
      case 'success': return 'success';
      case 'error': return 'danger';
      case 'warning': return 'warning';
      default: return 'info';
    }
  };

  return (
    <ToastContainer position="top-end" className="p-3" style={{ zIndex: 1050, position: 'fixed' }}>
      <Toast onClose={onClose} show={show} delay={3000} autohide bg={getVariant()}>
        <Toast.Header>
          <strong className="me-auto">Universo Maker</strong>
        </Toast.Header>
        <Toast.Body className={type === 'warning' || type === 'info' ? 'text-dark' : 'text-white'}>
          {message}
        </Toast.Body>
      </Toast>
    </ToastContainer>
  );
};

export default ToastNotification;