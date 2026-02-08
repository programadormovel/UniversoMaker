import { useState, useEffect } from 'react'
import { Container, Alert, Button } from 'react-bootstrap'
import { useNavigate, useLocation } from 'react-router-dom'
import './App.css'
import StudentSelector from './components/StudentSelector'
import ComprehensiveEvaluationForm from './components/ComprehensiveEvaluationForm'
import StudentForm from './components/StudentForm'
import ModernHeader from './components/ModernHeader'
import ModernFooter from './components/ModernFooter'
import { Student, DailyEvaluation } from './types'
import { fetchStudents } from './services/studentService'

function App() {
  const navigate = useNavigate();
  const location = useLocation();
  
  const [selectedStudent, setSelectedStudent] = useState<string>('')
  const [evaluations, setEvaluations] = useState<DailyEvaluation[]>([])
  const [showSuccess, setShowSuccess] = useState(false)
  const [showStudentForm, setShowStudentForm] = useState(false)
  const [studentList, setStudentList] = useState<Student[]>([])
  const [editingEvaluation, setEditingEvaluation] = useState<DailyEvaluation | undefined>(undefined)

  useEffect(() => {
    const loadStudents = async () => {
      const data = await fetchStudents();
      setStudentList(data);
    };
    loadStudents();

    const savedEvaluations = localStorage.getItem('tea-evaluations')
    if (savedEvaluations) {
      setEvaluations(JSON.parse(savedEvaluations))
    }

    if ((location.state as any)?.editEvaluation) {
      const evalToEdit = (location.state as any).editEvaluation;
      setSelectedStudent(evalToEdit.studentId);
      setEditingEvaluation(evalToEdit);
    }
  }, [location.state])

  const handleSaveEvaluation = (evaluation: Omit<DailyEvaluation, 'id'>) => {
    console.log('handleSaveEvaluation chamado:', evaluation);
    try {
      let updated: DailyEvaluation[]
      
      if (editingEvaluation) {
        const updatedEvaluation: DailyEvaluation = {
          ...evaluation,
          id: editingEvaluation.id
        }
        updated = evaluations.map(e => e.id === editingEvaluation.id ? updatedEvaluation : e)
        setEditingEvaluation(undefined)
      } else {
        const newEvaluation: DailyEvaluation = {
          ...evaluation,
          id: Date.now().toString()
        }
        updated = [...evaluations, newEvaluation]
      }
      
      setEvaluations(updated)
      localStorage.setItem('tea-evaluations', JSON.stringify(updated))
      
      window.scrollTo({ top: 0, behavior: 'smooth' });
      setShowSuccess(true)
      setTimeout(() => setShowSuccess(false), 3000)
    } catch (error) {
      alert('Erro ao salvar avaliação. Tente novamente.');
      console.error('Erro ao salvar:', error);
    }
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

  const handleCancelEdit = () => {
    setEditingEvaluation(undefined)
  }

  return (
    <div style={{ backgroundColor: '#fafafa', minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <ModernHeader title="Universo Maker" showBackButton={false} />
      
      <div style={{ paddingTop: '80px', flex: 1, paddingBottom: '40px' }}>
        <Container style={{ maxWidth: '935px' }}>
          <div className="d-flex justify-content-end gap-2 mb-4">
            <Button 
              onClick={() => navigate('/dashboard')}
              style={{
                backgroundColor: 'transparent',
                border: 'none',
                color: '#0095f6',
                fontWeight: 600,
                fontSize: '14px',
                padding: '8px 16px',
                borderRadius: '8px',
                transition: 'all 0.2s ease'
              }}
              onMouseEnter={(e) => {
                e.currentTarget.style.backgroundColor = '#f0f8ff';
              }}
              onMouseLeave={(e) => {
                e.currentTarget.style.backgroundColor = 'transparent';
              }}
            >
              📊 Dashboard
            </Button>
            <Button 
              onClick={() => {
                if (confirm('Deseja realmente limpar todas as avaliações?')) {
                  localStorage.removeItem('tea-evaluations');
                  setEvaluations([]);
                  alert('Avaliações removidas com sucesso!');
                }
              }}
              style={{
                backgroundColor: 'transparent',
                border: 'none',
                color: '#ed4956',
                fontWeight: 600,
                fontSize: '14px',
                padding: '8px 16px',
                borderRadius: '8px',
                transition: 'all 0.2s ease'
              }}
              onMouseEnter={(e) => {
                e.currentTarget.style.backgroundColor = '#fff0f1';
              }}
              onMouseLeave={(e) => {
                e.currentTarget.style.backgroundColor = 'transparent';
              }}
            >
              🗑️ Limpar
            </Button>
          </div>

          <div className="row">
            <div className="col-12">
            {showSuccess && (
              <Alert variant="success" dismissible onClose={() => setShowSuccess(false)} className="mb-4 shadow-sm border-0">
                <strong>✓ Sucesso!</strong> Avaliação salva com sucesso!
              </Alert>
            )}
            
            <div className="row g-3 mb-4">
              <div className="col-md-8 col-lg-9">
                <div className="p-3 bg-white border rounded shadow-sm h-100 d-flex align-items-center">
                  <StudentSelector
                    students={studentList}
                    selectedStudent={selectedStudent}
                    onStudentChange={setSelectedStudent}
                  />
                </div>
              </div>
              <div className="col-md-4 col-lg-3">
                <div className="d-grid h-100">
                  <Button 
                    className="w-100 h-100 d-flex align-items-center justify-content-center fw-bold shadow-sm"
                    onClick={() => setShowStudentForm(true)}
                    style={{ 
                      backgroundColor: '#0095f6', 
                      borderColor: '#0095f6',
                      border: 'none',
                      borderRadius: '8px',
                      fontWeight: 600,
                      transition: 'all 0.2s ease'
                    }}
                    onMouseEnter={(e) => {
                      e.currentTarget.style.backgroundColor = '#0084e0';
                    }}
                    onMouseLeave={(e) => {
                      e.currentTarget.style.backgroundColor = '#0095f6';
                    }}
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
              <div className="row">
                <div className="col-12">
                  <div className="bg-white border rounded shadow-sm p-4">
                    <ComprehensiveEvaluationForm
                      studentId={selectedStudent}
                      onSave={handleSaveEvaluation}
                      editingEvaluation={editingEvaluation}
                      onCancelEdit={handleCancelEdit}
                    />
                  </div>
                </div>
              </div>
            )}
          </div>
        </div>
        </Container>
      </div>

      <ModernFooter />
    </div>
  )
}

export default App
