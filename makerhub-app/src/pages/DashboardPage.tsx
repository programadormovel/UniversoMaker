import { useState, useEffect } from 'react';
import { Container, Navbar, Button, Form } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import StudentDashboard from '../components/StudentDashboard';
import { Student, DailyEvaluation } from '../types';
import logo from '../assets/logo-app-v4.jpg';

const DashboardPage = () => {
  const navigate = useNavigate();
  const [students, setStudents] = useState<Student[]>([
    { id: '1', name: 'Ana Silva', age: 8, diagnosis: 'TEA Leve' },
    { id: '2', name: 'João Santos', age: 10, diagnosis: 'TDAH' },
    { id: '3', name: 'Maria Costa', age: 7, diagnosis: 'Síndrome de Down' },
    { id: '4', name: 'Pedro Lima', age: 9, diagnosis: 'Dislexia' },
    { id: '5', name: 'Sofia Mendes', age: 6, diagnosis: 'Deficiência Intelectual' },
    { id: '6', name: 'Lucas Oliveira', age: 11, diagnosis: 'Típico' }
  ]);
  
  const [evaluations, setEvaluations] = useState<DailyEvaluation[]>([]);
  const [selectedStudent, setSelectedStudent] = useState<string>('');

  useEffect(() => {
    const savedEvaluations = localStorage.getItem('tea-evaluations');
    if (savedEvaluations) {
      setEvaluations(JSON.parse(savedEvaluations));
    }
    
    const savedStudents = localStorage.getItem('students');
    if (savedStudents) {
      setStudents(JSON.parse(savedStudents));
    }
  }, []);

  return (
    <>
      <Navbar bg="success" variant="dark" className="mb-2 py-2">
        <Container className="px-2 px-sm-3">
          <Navbar.Brand className="d-flex align-items-center gap-2">
            <img 
              src={logo} 
              alt="Universo Maker" 
              style={{ height: '32px', width: '32px', objectFit: 'cover', borderRadius: '4px' }}
            />
            <span className="fs-6 fs-sm-5 fs-md-4">Universo Maker</span>
          </Navbar.Brand>
          <Button 
            variant="outline-light" 
            size="sm" 
            onClick={() => navigate('/')}
          >
            ← Voltar
          </Button>
        </Container>
      </Navbar>
      
      <Container className="px-2 px-sm-3 px-lg-4">
        <div className="row justify-content-center">
          <div className="col-12 col-md-10 col-lg-8 col-xl-6">
        <Form.Group className="mb-3">
          <Form.Label className="fw-bold">Selecionar Aluno para Relatório</Form.Label>
          <Form.Select 
            value={selectedStudent} 
            onChange={(e) => setSelectedStudent(e.target.value)}
          >
            <option value="">Escolha um aluno...</option>
            {students.map(student => (
              <option key={student.id} value={student.id}>
                {student.name} - {student.diagnosis}
              </option>
            ))}
          </Form.Select>
        </Form.Group>
        
        {selectedStudent && (
          <StudentDashboard 
            student={students.find(s => s.id === selectedStudent)!}
            evaluations={evaluations.filter(e => e.studentId === selectedStudent)}
          />
        )}
          </div>
        </div>
      </Container>
    </>
  );
};

export default DashboardPage;