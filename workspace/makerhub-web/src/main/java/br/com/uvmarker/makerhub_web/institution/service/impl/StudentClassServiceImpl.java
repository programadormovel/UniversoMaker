package br.com.uvmarker.makerhub_web.institution.service.impl;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionClass;
import br.com.uvmarker.makerhub_web.institution.domain.entity.StudentClass;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionClassRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.StudentClassRepository;
import br.com.uvmarker.makerhub_web.institution.dto.StudentClassDTO;
import br.com.uvmarker.makerhub_web.institution.mapper.CentralMapperInstitution;
import br.com.uvmarker.makerhub_web.institution.service.StudentClassService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import br.com.uvmarker.makerhub_web.person.service.PersonService;

@Service
@RequiredArgsConstructor
public class StudentClassServiceImpl implements StudentClassService {

    private final StudentClassRepository studentClassRepository;
    private final CentralMapperInstitution mapper;
    private final InstitutionClassRepository institutionClassRepository;
    private final PersonService personService;

    @Override
    public List<StudentClass> findAll() {
       return studentClassRepository.findAll();
    }

    @Override
    public List<StudentClassDTO> findAllActive() {
        return mapper.toStudentClassDTOList(
            studentClassRepository.findByIsActiveTrue());
    }

    @Override
    public StudentClassDTO findById(Long id) {
        return mapper.toStudentClassDTO(
            studentClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentClass not found with id: " + id))
        );
    }

    @Override
    public List<StudentClassDTO> findByClassId(Long classId) {
        return mapper.toStudentClassDTOList(
            studentClassRepository.findByInstitutionClass_IdAndIsActiveTrue(classId));
    }

    @Override
    public List<StudentClassDTO> findByStudentId(Long studentId) {
        return mapper.toStudentClassDTOList(
            studentClassRepository.findByStudentIdAndIsActiveTrue(studentId));
    }

    @Override
    public StudentClassDTO save(StudentClassDTO dto) {
        InstitutionClass institutionClass = institutionClassRepository.findById(dto.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + dto.getClassId()));

        personService.findByPersonId(dto.getStudentId());

        StudentClass entity = mapper.toStudentClass(dto);
        entity.setInstitutionClass(institutionClass);
  
        StudentClass saved = studentClassRepository.save(entity);
        return mapper.toStudentClassDTO(saved);
    }

    @Override
    public StudentClassDTO update(Long id, StudentClassDTO dto) {
        StudentClass existing = studentClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo aluno-turma não encontrado com ID: " + id));

        InstitutionClass institutionClass = institutionClassRepository.findById(dto.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + dto.getClassId()));

        personService.findByPersonId(dto.getStudentId());

        existing.setInstitutionClass(institutionClass);
        existing.setNotes(dto.getNotes());

        StudentClass updated = studentClassRepository.save(existing);
        return mapper.toStudentClassDTO(updated);
    }

    @Override
    public StudentClass inactivateById(Long id) {
        StudentClass studentClass = studentClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo aluno-turma não encontrado com ID: " + id));
        studentClass.setIsActive(false);
        return studentClassRepository.save(studentClass);
    }

 
}
