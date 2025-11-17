import { Card, Row, Col, Badge, ProgressBar } from 'react-bootstrap';
import { DailyEvaluation, Student } from '../types';

interface DashboardProps {
  evaluations: DailyEvaluation[];
  students: Student[];
}

const Dashboard = ({ evaluations, students }: DashboardProps) => {


  const getScoreColor = (score: number) => {
    if (score >= 4) return 'success';
    if (score >= 3) return 'warning';
    return 'danger';
  };

  const getAverageScore = (studentId: string, criteria: keyof DailyEvaluation) => {
    const studentEvals = evaluations.filter(e => e.studentId === studentId);
    if (studentEvals.length === 0) return 0;
    const sum = studentEvals.reduce((acc, e) => acc + (e[criteria] as number), 0);
    return Math.round((sum / studentEvals.length) * 10) / 10;
  };

  const getTotalEvaluations = (studentId: string) => 
    evaluations.filter(e => e.studentId === studentId).length;

  const getLastEvaluation = (studentId: string) => {
    const studentEvals = evaluations
      .filter(e => e.studentId === studentId)
      .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
    return studentEvals[0];
  };

  return (
    <div>
      <h5 className="mb-3">Dashboard - Acompanhamento Pedagógico</h5>
      
      <Row className="g-3">
        {students.map(student => {
          const totalEvals = getTotalEvaluations(student.id);
          const lastEval = getLastEvaluation(student.id);
          const avgComm = getAverageScore(student.id, 'communication');
          const avgSocial = getAverageScore(student.id, 'socialInteraction');
          const avgBehavior = getAverageScore(student.id, 'behavior');
          const avgAttention = getAverageScore(student.id, 'attention');
          const overallAvg = totalEvals > 0 ? ((avgComm + avgSocial + avgBehavior + avgAttention) / 4) : 0;

          return (
            <Col key={student.id} xs={12} md={6} lg={4}>
              <Card className="h-100">
                <Card.Header className="bg-light">
                  <div className="d-flex justify-content-between align-items-center">
                    <h6 className="mb-0">{student.name}</h6>
                    <Badge bg="secondary">{totalEvals} avaliações</Badge>
                  </div>
                  <small className="text-muted">{student.diagnosis}</small>
                </Card.Header>
                <Card.Body className="p-3">
                  {totalEvals === 0 ? (
                    <p className="text-muted mb-0">Nenhuma avaliação registrada</p>
                  ) : (
                    <>
                      <div className="mb-3">
                        <div className="d-flex justify-content-between mb-1">
                          <small>Progresso Geral</small>
                          <small>{overallAvg.toFixed(1)}/5</small>
                        </div>
                        <ProgressBar 
                          now={(overallAvg / 5) * 100} 
                          variant={getScoreColor(overallAvg)}
                          style={{ height: '8px' }}
                        />
                      </div>

                      <div className="row g-2 mb-3">
                        <div className="col-6">
                          <small className="text-muted d-block">Comunicação</small>
                          <Badge bg={getScoreColor(avgComm)}>{avgComm}</Badge>
                        </div>
                        <div className="col-6">
                          <small className="text-muted d-block">Social</small>
                          <Badge bg={getScoreColor(avgSocial)}>{avgSocial}</Badge>
                        </div>
                        <div className="col-6">
                          <small className="text-muted d-block">Comportamento</small>
                          <Badge bg={getScoreColor(avgBehavior)}>{avgBehavior}</Badge>
                        </div>
                        <div className="col-6">
                          <small className="text-muted d-block">Atenção</small>
                          <Badge bg={getScoreColor(avgAttention)}>{avgAttention}</Badge>
                        </div>
                      </div>

                      {lastEval && (
                        <div className="border-top pt-2">
                          <small className="text-muted d-block">Última avaliação:</small>
                          <small className="fw-bold">
                            {new Date(lastEval.date).toLocaleDateString('pt-BR')}
                          </small>
                          {lastEval.notes && (
                            <small className="d-block text-muted mt-1">
                              "{lastEval.notes.substring(0, 40)}..."
                            </small>
                          )}
                        </div>
                      )}
                    </>
                  )}
                </Card.Body>
              </Card>
            </Col>
          );
        })}
      </Row>

      {evaluations.length > 0 && (
        <Card className="mt-4">
          <Card.Header>
            <h6 className="mb-0">Estatísticas Gerais</h6>
          </Card.Header>
          <Card.Body>
            <Row className="text-center">
              <Col xs={6} md={3}>
                <h4 className="text-primary mb-0">{evaluations.length}</h4>
                <small className="text-muted">Total de Avaliações</small>
              </Col>
              <Col xs={6} md={3}>
                <h4 className="text-success mb-0">
                  {students.filter(s => getTotalEvaluations(s.id) > 0).length}
                </h4>
                <small className="text-muted">Alunos Avaliados</small>
              </Col>
              <Col xs={6} md={3}>
                <h4 className="text-info mb-0">
                  {Math.round(evaluations.length / students.length * 10) / 10}
                </h4>
                <small className="text-muted">Média por Aluno</small>
              </Col>
              <Col xs={6} md={3}>
                <h4 className="text-warning mb-0">
                  {evaluations.length > 0 ? 
                    new Date(Math.max(...evaluations.map(e => new Date(e.date).getTime())))
                      .toLocaleDateString('pt-BR') : '-'}
                </h4>
                <small className="text-muted">Última Avaliação</small>
              </Col>
            </Row>
          </Card.Body>
        </Card>
      )}
    </div>
  );
};

export default Dashboard;