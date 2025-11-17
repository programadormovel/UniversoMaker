import { useState, useEffect } from 'react'
import { Container, Navbar, Alert, Button } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
import './App.css'
import StudentSelector from './components/StudentSelector'
import EvaluationForm from './components/EvaluationForm'
import EvaluationHistory from './components/EvaluationHistory'
import StudentForm from './components/StudentForm'
import { Student, DailyEvaluation } from './types'
import logo from './assets/logo-app-v4.jpg'
import mascot from './assets/mascote.png'

function App() {
  const navigate = useNavigate();
  const [students] = useState<Student[]>([
    { id: '1', name: 'Ana Silva', age: 8, diagnosis: 'TEA Leve' },
    { id: '2', name: 'João Santos', age: 10, diagnosis: 'TDAH' },
    { id: '3', name: 'Maria Costa', age: 7, diagnosis: 'Síndrome de Down' },
    { id: '4', name: 'Pedro Lima', age: 9, diagnosis: 'Dislexia' },
    { id: '5', name: 'Sofia Mendes', age: 6, diagnosis: 'Deficiência Intelectual' },
    { id: '6', name: 'Lucas Oliveira', age: 11, diagnosis: 'Típico' }
  ])
  
  const [selectedStudent, setSelectedStudent] = useState<string>('')
  const [evaluations, setEvaluations] = useState<DailyEvaluation[]>([])
  const [showSuccess, setShowSuccess] = useState(false)
  const [showStudentForm, setShowStudentForm] = useState(false)
  const [studentList, setStudentList] = useState<Student[]>(students)

  useEffect(() => {
    const savedEvaluations = localStorage.getItem('tea-evaluations')
    if (savedEvaluations) {
      setEvaluations(JSON.parse(savedEvaluations))
    }
    
    const savedStudents = localStorage.getItem('students')
    if (savedStudents) {
      setStudentList(JSON.parse(savedStudents))
    }
  }, [])

  const handleSaveEvaluation = (evaluation: Omit<DailyEvaluation, 'id'>) => {
    const newEvaluation: DailyEvaluation = {
      ...evaluation,
      id: Date.now().toString()
    }
    
    const updated = [...evaluations, newEvaluation]
    setEvaluations(updated)
    localStorage.setItem('tea-evaluations', JSON.stringify(updated))
    
    setShowSuccess(true)
    setTimeout(() => setShowSuccess(false), 3000)
  }

  const handleSaveStudent = (student: Omit<Student, 'id'>) => {
    const newStudent: Student = {
      ...student,
      id: Date.now().toString()
    }
    
    const updated = [...studentList, newStudent]
    setStudentList(updated)
    localStorage.setItem('students', JSON.stringify(updated))
  }

  const studentEvaluations = evaluations
    .filter(evaluation => evaluation.studentId === selectedStudent)
    .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())

  return (
    <>
      <Navbar bg="primary" variant="dark" className="mb-2 py-2">
        <Container fluid className="px-2 px-sm-3">
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
            className="d-flex align-items-center gap-1"
            onClick={() => navigate('/dashboard')}
          >
            <span className="d-none d-sm-inline">📈</span>
            <span className="fs-7 fs-sm-6">Dashboard</span>
          </Button>
        </Container>
      </Navbar>
      
      <Container fluid className="px-1 px-sm-2 px-md-3">
        <div className="row justify-content-center">
          <div className="col-12 col-md-10 col-lg-8 col-xl-6">
            {showSuccess && (
              <Alert variant="success" dismissible onClose={() => setShowSuccess(false)}>
                Avaliação salva com sucesso!
              </Alert>
            )}
            
            <div className="row g-2 mb-3">
              <div className="col-12 col-sm-8 col-md-9">
                <StudentSelector
                  students={studentList}
                  selectedStudent={selectedStudent}
                  onStudentChange={setSelectedStudent}
                />
              </div>
              <div className="col-12 col-sm-4 col-md-3">
                <div className="d-grid">
                  <Button 
                    variant="outline-primary" 
                    size="sm"
                    onClick={() => setShowStudentForm(true)}
                  >
                    + Aluno
                  </Button>
                </div>
              </div>
            </div>
            
            <StudentForm
              show={showStudentForm}
              onHide={() => setShowStudentForm(false)}
              onSave={handleSaveStudent}
            />
            
            {selectedStudent && (
              <div className="row g-3">
                <div className="col-12">
                  <EvaluationForm
                    studentId={selectedStudent}
                    onSave={handleSaveEvaluation}
                  />
                </div>
                <div className="col-12">
                  <EvaluationHistory evaluations={studentEvaluations} />
                </div>
              </div>
            )}
          </div>
        </div>
        
        <div className="row justify-content-center mt-4">
          <div className="col-auto">
            <img 
              src={mascot} 
              alt="Mascote Universo Maker" 
              style={{ height: '80px', opacity: 0.7 }}
              className="d-block mx-auto"
            />
          </div>
        </div>
      </Container>
    </>
  )
}

export default App