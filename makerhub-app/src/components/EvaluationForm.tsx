import { useState } from 'react';
import { Form, Button, Card } from 'react-bootstrap';
import { DailyEvaluation } from '../types';

interface EvaluationFormProps {
  studentId: string;
  onSave: (evaluation: Omit<DailyEvaluation, 'id'>) => void;
}

const EvaluationForm = ({ studentId, onSave }: EvaluationFormProps) => {
  const [evaluation, setEvaluation] = useState({
    communication: 3,
    socialInteraction: 3,
    behavior: 3,
    attention: 3,
    notes: '',
    date: new Date().toISOString().split('T')[0]
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave({
      studentId,
      date: evaluation.date,
      communication: evaluation.communication,
      socialInteraction: evaluation.socialInteraction,
      behavior: evaluation.behavior,
      attention: evaluation.attention,
      notes: evaluation.notes
    });
    setEvaluation({
      communication: 3,
      socialInteraction: 3,
      behavior: 3,
      attention: 3,
      notes: '',
      date: new Date().toISOString().split('T')[0]
    });
  };

  const criteria = [
    { key: 'communication', label: 'Comunicação' },
    { key: 'socialInteraction', label: 'Socialização' },
    { key: 'behavior', label: 'Comportamento' },
    { key: 'attention', label: 'Concentração' }
  ];

  return (
    <Card className="border-0 shadow-sm">
      <Card.Header className="bg-light py-2">
        <div className="d-flex justify-content-between align-items-center">
          <h6 className="mb-0">Avaliação</h6>
          <Form.Control
            type="date"
            size="sm"
            value={evaluation.date}
            onChange={(e) => setEvaluation(prev => ({
              ...prev,
              date: e.target.value
            }))}
            style={{ width: 'auto' }}
          />
        </div>
      </Card.Header>
      <Card.Body className="p-2">
        <Form onSubmit={handleSubmit}>
          {criteria.map(({ key, label }) => (
            <div key={key} className="mb-3">
              <div className="d-flex justify-content-between align-items-center mb-1">
                <Form.Label className="mb-0 small fw-bold">{label}</Form.Label>
                <span className="badge bg-primary">
                  {evaluation[key as keyof typeof evaluation] as number}
                </span>
              </div>
              <Form.Range
                min={1}
                max={5}
                value={evaluation[key as keyof typeof evaluation] as number}
                onChange={(e) => setEvaluation(prev => ({
                  ...prev,
                  [key]: parseInt(e.target.value)
                }))}
              />
              <div className="d-flex justify-content-between">
                <small className="text-muted">Difícil</small>
                <small className="text-muted">Excelente</small>
              </div>
            </div>
          ))}
          
          <Form.Group className="mb-3">
            <Form.Label className="small fw-bold">Observações</Form.Label>
            <Form.Control
              as="textarea"
              rows={2}
              size="sm"
              value={evaluation.notes}
              onChange={(e) => setEvaluation(prev => ({
                ...prev,
                notes: e.target.value
              }))}
              placeholder="Observações..."
            />
          </Form.Group>
          
          <div className="d-grid">
            <Button type="submit" variant="primary" size="sm">
              ✓ Salvar Avaliação
            </Button>
          </div>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default EvaluationForm;