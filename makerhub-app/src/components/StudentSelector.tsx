import { Form } from 'react-bootstrap';
import { Student } from '../types';

interface StudentSelectorProps {
  students: Student[];
  selectedStudent: string;
  onStudentChange: (studentId: string) => void;
}

const StudentSelector = ({ students, selectedStudent, onStudentChange }: StudentSelectorProps) => {
  return (
    <Form.Group className="mb-0">
      <Form.Label className="fw-bold small">Aluno</Form.Label>
      <Form.Select 
        value={selectedStudent} 
        onChange={(e) => onStudentChange(e.target.value)}
        size="sm"
      >
        <option value="">Escolha um aluno...</option>
        {students.map(student => (
          <option key={student.id} value={student.id}>
            {student.name} ({student.age}a)
          </option>
        ))}
      </Form.Select>
    </Form.Group>
  );
};

export default StudentSelector;