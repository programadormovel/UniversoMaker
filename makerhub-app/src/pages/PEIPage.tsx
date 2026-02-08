import { useState, useEffect } from 'react';
import { Container, Form, Card, ListGroup, Spinner } from 'react-bootstrap';
import { useLocation } from 'react-router-dom';
import { Student, DailyEvaluation } from '../types';
import ModernHeader from '../components/ModernHeader';
import ModernFooter from '../components/ModernFooter';
import { fetchStudents } from '../services/studentService';
import { fetchEvaluations } from '../services/evaluationService';

const PEIPage = () => {
  const location = useLocation();
  const [students, setStudents] = useState<Student[]>([]);
  const [selectedStudent, setSelectedStudent] = useState<string>((location.state as any)?.studentId || '');
  const [evaluations, setEvaluations] = useState<DailyEvaluation[]>([]);
  const [studentEvaluations, setStudentEvaluations] = useState<DailyEvaluation[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const loadData = async () => {
      setLoading(true);
      try {
        const studentsData = await fetchStudents();
        setStudents(studentsData);
        const evaluationsData = await fetchEvaluations();
        setEvaluations(evaluationsData);
      } catch (error) {
        console.error('Erro ao carregar dados:', error);
      } finally {
        setLoading(false);
      }
    };
    loadData();

    if ((location.state as any)?.studentId) {
      setSelectedStudent((location.state as any).studentId);
    }
  }, [location.state]);

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

  const generatePEISuggestions = () => {
    if (studentEvaluations.length === 0) return [];

    const suggestions = studentEvaluations
      .map(e => e.suggestions)
      .filter(s => s)
      .join(', ')
      .split(',')
      .map(s => s.trim())
      .filter(s => s);

    return [...new Set(suggestions)];
  };

  const getAttentionPoints = () => {
    if (studentEvaluations.length === 0) return [];

    const points = studentEvaluations
      .map(e => e.attentionPoints)
      .filter(s => s)
      .join(', ')
      .split(',')
      .map(s => s.trim())
      .filter(s => s);

    return [...new Set(points)];
  };

  const getStrengths = () => {
    if (studentEvaluations.length === 0) return [];

    const strengths = studentEvaluations
      .map(e => e.strengths)
      .filter(s => s)
      .join(', ')
      .split(',')
      .map(s => s.trim())
      .filter(s => s);

    return [...new Set(strengths)];
  };

  if (loading) {
    return (
      <Container className="d-flex justify-content-center align-items-center" style={{ height: '100vh' }}>
        <Spinner animation="border" role="status" variant="info">
          <span className="visually-hidden">Carregando...</span>
        </Spinner>
      </Container>
    );
  }

  return (
    <div style={{ backgroundColor: '#fafafa', minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <ModernHeader title="Sugestões PEI" backTo="/dashboard" />
      
      <div style={{ paddingTop: '80px', paddingBottom: '40px', flex: 1 }}>
      <Container style={{ maxWidth: '935px' }}>
        <Form.Group className="mb-4">
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
        
        {selectedStudent && studentEvaluations.length > 0 && (
          <>
            <h5 className="mb-3">Plano Educacional Individualizado - {getStudentName()}</h5>
            
            <Card className="mb-4 border-0 shadow-sm">
              <Card.Header className="bg-white border-bottom py-3">
                <h6 className="mb-0 fw-bold text-success">✓ Pontos Fortes Identificados</h6>
              </Card.Header>
              <Card.Body>
                <ListGroup variant="flush">
                  {getStrengths().map((strength, idx) => (
                    <ListGroup.Item key={idx} className="border-0 px-0 py-2">• {strength}</ListGroup.Item>
                  ))}
                </ListGroup>
              </Card.Body>
            </Card>

            <Card className="mb-4 border-0 shadow-sm">
              <Card.Header className="bg-white border-bottom py-3">
                <h6 className="mb-0 fw-bold text-warning">⚠︝ Ýreas de Atenção</h6>
              </Card.Header>
              <Card.Body>
                <ListGroup variant="flush">
                  {getAttentionPoints().map((point, idx) => (
                    <ListGroup.Item key={idx} className="border-0 px-0 py-2">• {point}</ListGroup.Item>
                  ))}
                </ListGroup>
              </Card.Body>
            </Card>

            <Card className="mb-4 border-0 shadow-sm">
              <Card.Header className="bg-white border-bottom py-3">
                <h6 className="mb-0 fw-bold text-primary">🎯 Sugestões de Metas para o PEI</h6>
              </Card.Header>
              <Card.Body>
                <ListGroup variant="flush">
                  {generatePEISuggestions().map((suggestion, idx) => (
                    <ListGroup.Item key={idx} className="border-0 px-0 py-2">• {suggestion}</ListGroup.Item>
                  ))}
                </ListGroup>
              </Card.Body>
            </Card>

            <Card className="border-0 shadow-sm mt-4">
              <Card.Header className="bg-white border-bottom py-3">
                <h6 className="mb-0 fw-bold text-muted">📊 Resumo das Avaliações</h6>
              </Card.Header>
              <Card.Body>
                <p><strong>Total de avaliações:</strong> {studentEvaluations.length}</p>
                <p><strong>Período:</strong> {new Date(studentEvaluations[0].date).toLocaleDateString('pt-BR')} até {new Date(studentEvaluations[studentEvaluations.length - 1].date).toLocaleDateString('pt-BR')}</p>
              </Card.Body>
            </Card>
          </>
        )}

        {selectedStudent && studentEvaluations.length === 0 && (
          <div className="alert alert-light border text-center mt-4">
            Nenhuma avaliação encontrada para gerar sugestões de PEI.
          </div>
        )}
      </Container>
      </div>

      <ModernFooter />
    </div>
  );
};

export default PEIPage;
