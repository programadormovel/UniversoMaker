import { useState, useEffect } from 'react';
import { Container, Button, Form, Card, Row, Col, Badge } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { Student, DailyEvaluation } from '../types';
import ModernHeader from '../components/ModernHeader';
import ModernFooter from '../components/ModernFooter';
import { fetchStudents } from '../services/studentService';
import { fetchEvaluations, deleteEvaluation } from '../services/evaluationService';

const DashboardPage = () => {
  const navigate = useNavigate();
  const [students, setStudents] = useState<Student[]>([]);
  const [selectedStudent, setSelectedStudent] = useState<string>('');
  const [evaluations, setEvaluations] = useState<DailyEvaluation[]>([]);
  const [studentEvaluations, setStudentEvaluations] = useState<DailyEvaluation[]>([]);

  useEffect(() => {
    const loadData = async () => {
      const studentsData = await fetchStudents();
      setStudents(studentsData);

      const evaluationsData = await fetchEvaluations();
      setEvaluations(evaluationsData);
    };
    loadData();
  }, []);

  useEffect(() => {
    if (selectedStudent) {
      const filtered = evaluations.filter(e => e.studentId === selectedStudent);
      setStudentEvaluations(filtered);
    } else {
      setStudentEvaluations([]);
    }
  }, [selectedStudent, evaluations]);

  const getStudentName = () => {
    const student = students.find(s => s.id === selectedStudent);
    return student ? student.name : '';
  };

  return (
    <div style={{ backgroundColor: '#fafafa', minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <ModernHeader title="Dashboard" backTo="/" />
      
      <div style={{ paddingTop: '80px', paddingBottom: '40px', flex: 1 }}>
      <Container style={{ maxWidth: '935px' }}>
        <div className="d-flex justify-content-between align-items-center mb-4">
          <Form.Group className="flex-grow-1 me-3 mb-0">
            <Form.Label className="fw-bold">Selecionar Aluno</Form.Label>
            <Form.Select value={selectedStudent} onChange={(e) => setSelectedStudent(e.target.value)}>
              <option value="">Escolha um aluno...</option>
              {students.map(student => (
                <option key={student.id} value={student.id}>
                  {student.name} - {student.diagnosis}
                </option>
              ))}
            </Form.Select>
          </Form.Group>
          <Button 
            onClick={() => navigate('/pei', { state: { studentId: selectedStudent } })} 
            disabled={!selectedStudent}
            style={{
              backgroundColor: selectedStudent ? '#0095f6' : '#e0e0e0',
              border: 'none',
              color: 'white',
              fontWeight: 600,
              fontSize: '14px',
              padding: '10px 20px',
              borderRadius: '8px',
              marginTop: '28px'
            }}
          >
            Ver PEI
          </Button>
        </div>
        
        {selectedStudent && studentEvaluations.length > 0 && (
          <>
            <h5 className="mb-3">Relatório de {getStudentName()}</h5>
            <Row className="g-3">
              <Col xs={12} md={6} lg={3}>
                <Card className="border-0 shadow-sm h-100">
                  <Card.Body>
                    <h6 className="text-muted">Total de Avaliações</h6>
                    <h2 className="fw-bold text-primary">{studentEvaluations.length}</h2>
                  </Card.Body>
                </Card>
              </Col>
              <Col xs={12} md={6} lg={3}>
                <Card className="border-0 shadow-sm h-100">
                  <Card.Body>
                    <h6 className="text-muted">Última Avaliação</h6>
                    <h5 className="fw-bold">{new Date(studentEvaluations[studentEvaluations.length - 1].date).toLocaleDateString('pt-BR')}</h5>
                  </Card.Body>
                </Card>
              </Col>
            </Row>

            <Card className="mt-4 border-0 shadow-sm">
              <Card.Header className="bg-white border-bottom py-3">
                <h6 className="mb-0 fw-bold">Histórico de Avaliações</h6>
              </Card.Header>
              <Card.Body>
                {studentEvaluations.map(evaluation => (
                  <Card key={evaluation.id} className="mb-3 border rounded-3">
                    <Card.Body className="p-2">
                      <div className="d-flex justify-content-between align-items-start">
                        <div className="flex-grow-1">
                          <strong className="d-block mb-1">{evaluation.activityName}</strong>
                          <div className="small text-muted">{new Date(evaluation.date).toLocaleDateString('pt-BR')} - {evaluation.professional}</div>
                        </div>
                        <div className="d-flex gap-1 align-items-start">
                          <Badge bg="light" text="dark" className="border">{evaluation.complexityLevel}</Badge>
                          <Button 
                            size="sm" 
                            variant="link" 
                            onClick={() => navigate('/', { state: { editEvaluation: evaluation } })}
                            className="text-decoration-none p-0 mx-1"
                          >
                            <span style={{ fontSize: '1.1rem' }}>✏️</span>
                          </Button>
                          <Button 
                            size="sm" 
                            variant="link" 
                            onClick={async () => {
                              if (confirm('Deseja excluir esta avaliação?')) {
                                const success = await deleteEvaluation(evaluation.id);
                                if (success) {
                                  setEvaluations(prev => prev.filter(e => e.id !== evaluation.id));
                                }
                              }
                            }}
                            className="text-decoration-none p-0 mx-1"
                          >
                            <span style={{ fontSize: '1.1rem' }}>🗑️</span>
                          </Button>
                        </div>
                      </div>
                      {evaluation.strengths && (
                        <div className="mt-2 small">
                          <strong className="text-success">Pontos Fortes:</strong> {evaluation.strengths}
                        </div>
                      )}
                      {evaluation.attentionPoints && (
                        <div className="mt-1 small">
                          <strong className="text-warning">Atenção:</strong> {evaluation.attentionPoints}
                        </div>
                      )}
                    </Card.Body>
                  </Card>
                ))}
              </Card.Body>
            </Card>
          </>
        )}

        {selectedStudent && studentEvaluations.length === 0 && (
          <div className="alert alert-light border text-center mt-4">
            Nenhuma avaliação encontrada para este aluno.
          </div>
        )}
      </Container>
      </div>

      <ModernFooter />
    </div>
  );
};

export default DashboardPage;
