import { useState } from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import { Student } from '../types';

interface StudentFormProps {
  show: boolean;
  onHide: () => void;
  onSave: (student: Omit<Student, 'id'>) => void;
}

const StudentForm = ({ show, onHide, onSave }: StudentFormProps) => {
  const [student, setStudent] = useState({
    name: '',
    age: 6,
    diagnosis: ''
  });

  const diagnosisOptions = [
    'TEA Leve',
    'TEA Moderado', 
    'TEA Severo',
    'TDAH',
    'Síndrome de Down',
    'Dislexia',
    'Deficiência Intelectual',
    'Deficiência Auditiva',
    'Deficiência Visual',
    'Paralisia Cerebral',
    'Típico',
    'Outro'
  ];

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (student.name && student.diagnosis) {
      onSave(student);
      setStudent({ name: '', age: 6, diagnosis: '' });
      onHide();
    }
  };

  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Adicionar Aluno</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Nome</Form.Label>
            <Form.Control
              type="text"
              value={student.name}
              onChange={(e) => setStudent(prev => ({ ...prev, name: e.target.value }))}
              required
            />
          </Form.Group>
          
          <Form.Group className="mb-3">
            <Form.Label>Idade</Form.Label>
            <Form.Control
              type="number"
              min="3"
              max="18"
              value={student.age}
              onChange={(e) => setStudent(prev => ({ ...prev, age: parseInt(e.target.value) }))}
              required
            />
          </Form.Group>
          
          <Form.Group className="mb-3">
            <Form.Label>Diagnóstico/Condição</Form.Label>
            <Form.Select
              value={student.diagnosis}
              onChange={(e) => setStudent(prev => ({ ...prev, diagnosis: e.target.value }))}
              required
            >
              <option value="">Selecione...</option>
              {diagnosisOptions.map(option => (
                <option key={option} value={option}>{option}</option>
              ))}
            </Form.Select>
          </Form.Group>
          
          <div className="d-flex gap-2">
            <Button variant="secondary" onClick={onHide}>
              Cancelar
            </Button>
            <Button type="submit" variant="primary">
              Salvar
            </Button>
          </div>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default StudentForm;