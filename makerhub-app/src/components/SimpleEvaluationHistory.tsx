import { Card, Badge } from 'react-bootstrap';
import { DailyEvaluation } from '../types';

interface SimpleEvaluationHistoryProps {
  evaluations: DailyEvaluation[];
}

const SimpleEvaluationHistory = ({ evaluations }: SimpleEvaluationHistoryProps) => {
  const getStatusColor = (status: string) => {
    if (status.includes('excelente') || status.includes('sucesso') || status.includes('regulado')) return 'success';
    if (status.includes('adequada') || status.includes('moderada') || status.includes('parcial')) return 'warning';
    return 'danger';
  };

  return (
    <Card className="border-0 shadow-sm">
      <Card.Header className="bg-light py-2">
        <h6 className="mb-0">Histórico de Avaliações</h6>
      </Card.Header>
      <Card.Body className="p-2">
        {evaluations.length === 0 ? (
          <p className="text-muted small mb-0">Nenhuma avaliação ainda.</p>
        ) : (
          <div className="d-flex flex-column gap-2">
            {evaluations.slice(0, 5).map(evaluation => (
              <div key={evaluation.id} className="card border-0 bg-light">
                <div className="card-body p-2">
                  <div className="d-flex justify-content-between align-items-center mb-1">
                    <small className="fw-bold">
                      {new Date(evaluation.date).toLocaleDateString('pt-BR')}
                    </small>
                    <div className="d-flex gap-1 flex-wrap">
                      <Badge bg={getStatusColor(evaluation.emotionalRegulation)} className="px-1">
                        Emoc: {evaluation.emotionalRegulation.replace('_', ' ')}
                      </Badge>
                      <Badge bg={getStatusColor(evaluation.socialInteraction)} className="px-1">
                        Social: {evaluation.socialInteraction}
                      </Badge>
                    </div>
                  </div>
                  <div className="mb-1">
                    <small className="text-muted">
                      <strong>Atividade:</strong> {evaluation.activityName || 'Não informada'}
                    </small>
                  </div>
                  <div className="mb-1">
                    <small className="text-muted">
                      <strong>Profissional:</strong> {evaluation.professional || 'Não informado'}
                    </small>
                  </div>
                  {evaluation.strengths && (
                    <small className="text-success d-block">
                      <strong>Pontos fortes:</strong> {evaluation.strengths.length > 50 
                        ? evaluation.strengths.substring(0, 50) + '...' 
                        : evaluation.strengths}
                    </small>
                  )}
                  {evaluation.notes && (
                    <small className="text-muted d-block">
                      {evaluation.notes.length > 50 
                        ? evaluation.notes.substring(0, 50) + '...' 
                        : evaluation.notes}
                    </small>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}
      </Card.Body>
    </Card>
  );
};

export default SimpleEvaluationHistory;