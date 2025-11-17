import { Card, Row, Col, Badge, ProgressBar, Button } from 'react-bootstrap';
import { DailyEvaluation, Student } from '../types';

interface StudentDashboardProps {
  student: Student;
  evaluations: DailyEvaluation[];
}

const StudentDashboard = ({ student, evaluations }: StudentDashboardProps) => {
  const getScoreColor = (score: number) => {
    if (score >= 4) return 'success';
    if (score >= 3) return 'warning';
    return 'danger';
  };

  const getAverage = (criteria: keyof DailyEvaluation) => {
    if (evaluations.length === 0) return 0;
    const sum = evaluations.reduce((acc, e) => acc + (e[criteria] as number), 0);
    return Math.round((sum / evaluations.length) * 10) / 10;
  };

  const avgComm = getAverage('communication');
  const avgSocial = getAverage('socialInteraction');
  const avgBehavior = getAverage('behavior');
  const avgAttention = getAverage('attention');
  const overallAvg = evaluations.length > 0 ? ((avgComm + avgSocial + avgBehavior + avgAttention) / 4) : 0;

  const generateReport = () => {
    const reportText = `📊 *Relatório - ${student.name}*

👤 *Dados do Aluno:*
• Nome: ${student.name}
• Idade: ${student.age} anos
• Diagnóstico: ${student.diagnosis}

📈 *Desempenho Geral:* ${overallAvg.toFixed(1)}/5.0

📋 *Médias por Área:*
• Comunicação: ${avgComm}/5
• Socialização: ${avgSocial}/5
• Comportamento: ${avgBehavior}/5
• Concentração: ${avgAttention}/5

📅 *Período:* ${evaluations.length} avaliações realizadas

🏫 *Gerado pelo Universo Maker*`;

    return reportText;
  };

  const shareWhatsApp = () => {
    const report = generateReport();
    const encodedText = encodeURIComponent(report);
    const whatsappUrl = `https://wa.me/?text=${encodedText}`;
    window.open(whatsappUrl, '_blank');
  };

  const getProgressVariant = (score: number) => {
    if (score >= 4) return 'success';
    if (score >= 3) return 'warning';
    return 'danger';
  };

  return (
    <div>
      <Card className="mb-4 border-0 shadow">
        <Card.Header className="bg-primary text-white">
          <div className="d-flex justify-content-between align-items-center">
            <div>
              <h5 className="mb-0">{student.name}</h5>
              <small>{student.diagnosis} • {student.age} anos</small>
            </div>
            <Button 
              variant="light" 
              size="sm"
              onClick={shareWhatsApp}
              className="d-flex align-items-center gap-1"
            >
              📱 Compartilhar
            </Button>
          </div>
        </Card.Header>
        <Card.Body>
          {evaluations.length === 0 ? (
            <p className="text-muted mb-0">Nenhuma avaliação registrada para este aluno.</p>
          ) : (
            <>
              <div className="mb-4">
                <div className="d-flex justify-content-between mb-2">
                  <h6>Desempenho Geral</h6>
                  <Badge bg={getScoreColor(overallAvg)} className="fs-6">
                    {overallAvg.toFixed(1)}/5.0
                  </Badge>
                </div>
                <ProgressBar 
                  now={(overallAvg / 5) * 100} 
                  variant={getProgressVariant(overallAvg)}
                  style={{ height: '12px' }}
                />
              </div>

              <Row className="g-3 mb-4">
                <Col xs={6} md={3}>
                  <div className="text-center">
                    <div className="mb-2">
                      <Badge bg={getScoreColor(avgComm)} className="fs-6 px-3 py-2">
                        {avgComm}
                      </Badge>
                    </div>
                    <small className="text-muted">Comunicação</small>
                    <ProgressBar 
                      now={(avgComm / 5) * 100} 
                      variant={getProgressVariant(avgComm)}
                      className="mt-1"
                      style={{ height: '6px' }}
                    />
                  </div>
                </Col>
                <Col xs={6} md={3}>
                  <div className="text-center">
                    <div className="mb-2">
                      <Badge bg={getScoreColor(avgSocial)} className="fs-6 px-3 py-2">
                        {avgSocial}
                      </Badge>
                    </div>
                    <small className="text-muted">Socialização</small>
                    <ProgressBar 
                      now={(avgSocial / 5) * 100} 
                      variant={getProgressVariant(avgSocial)}
                      className="mt-1"
                      style={{ height: '6px' }}
                    />
                  </div>
                </Col>
                <Col xs={6} md={3}>
                  <div className="text-center">
                    <div className="mb-2">
                      <Badge bg={getScoreColor(avgBehavior)} className="fs-6 px-3 py-2">
                        {avgBehavior}
                      </Badge>
                    </div>
                    <small className="text-muted">Comportamento</small>
                    <ProgressBar 
                      now={(avgBehavior / 5) * 100} 
                      variant={getProgressVariant(avgBehavior)}
                      className="mt-1"
                      style={{ height: '6px' }}
                    />
                  </div>
                </Col>
                <Col xs={6} md={3}>
                  <div className="text-center">
                    <div className="mb-2">
                      <Badge bg={getScoreColor(avgAttention)} className="fs-6 px-3 py-2">
                        {avgAttention}
                      </Badge>
                    </div>
                    <small className="text-muted">Concentração</small>
                    <ProgressBar 
                      now={(avgAttention / 5) * 100} 
                      variant={getProgressVariant(avgAttention)}
                      className="mt-1"
                      style={{ height: '6px' }}
                    />
                  </div>
                </Col>
              </Row>

              <Card className="bg-light">
                <Card.Body className="p-3">
                  <h6 className="mb-3">Últimas Avaliações</h6>
                  <div className="d-flex flex-column gap-2">
                    {evaluations.slice(0, 5).map(evaluation => (
                      <div key={evaluation.id} className="d-flex justify-content-between align-items-center">
                        <small className="fw-bold">
                          {new Date(evaluation.date).toLocaleDateString('pt-BR')}
                        </small>
                        <div className="d-flex gap-1">
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
                    ))}
                  </div>
                </Card.Body>
              </Card>
            </>
          )}
        </Card.Body>
      </Card>
    </div>
  );
};

export default StudentDashboard;