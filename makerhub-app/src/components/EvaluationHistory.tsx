import { Card, Badge } from 'react-bootstrap';
import { DailyEvaluation } from '../types';

interface EvaluationHistoryProps {
  evaluations: DailyEvaluation[];
}

const EvaluationHistory = ({ evaluations }: EvaluationHistoryProps) => {
  const getScoreColor = (score: number) => {
    if (score >= 4) return 'success';
    if (score >= 3) return 'warning';
    return 'danger';
  };

  return (
    <Card className="border-0 shadow-sm">
      <Card.Header className="bg-light py-2">
        <h6 className="mb-0">Histórico</h6>
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
                      <Badge bg={getScoreColor(evaluation.communication)} className="px-1">
                        C{evaluation.communication}
                      </Badge>
                      <Badge bg={getScoreColor(evaluation.socialInteraction)} className="px-1">
                        S{evaluation.socialInteraction}
                      </Badge>
                      <Badge bg={getScoreColor(evaluation.behavior)} className="px-1">
                        B{evaluation.behavior}
                      </Badge>
                      <Badge bg={getScoreColor(evaluation.attention)} className="px-1">
                        A{evaluation.attention}
                      </Badge>
                    </div>
                  </div>
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

export default EvaluationHistory;