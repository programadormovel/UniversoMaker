package br.com.uvmarker.makerhub_web.person.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.person.domain.entity.Person;
import br.com.uvmarker.makerhub_web.person.domain.entity.StudentFile;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonRepository;
import br.com.uvmarker.makerhub_web.person.domain.repository.StudentFileRepository;
import br.com.uvmarker.makerhub_web.person.dto.StudentFileDTO;
import br.com.uvmarker.makerhub_web.person.mapper.CentralMapperPerson;
import br.com.uvmarker.makerhub_web.person.service.StudentFileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentFileServiceImpl implements StudentFileService {

    private final StudentFileRepository studentFileRepository;
    private final PersonRepository personRepository;
    private final CentralMapperPerson mapper;

    @Override
    public List<StudentFile> findAll() {
        return studentFileRepository.findAll();
    }

    @Override
    public List<StudentFileDTO> findAllActive() {
        return mapper.toStudentFileDTOList(studentFileRepository.findByIsActiveTrue());
    }

    @Override
    public List<StudentFileDTO> findByStudentId(Long studentId) {
        List<StudentFile> files = studentFileRepository.findByStudent_IdAndIsActiveTrue(studentId);

        if (files.isEmpty()) {
            throw new RuntimeException("Nenhum arquivo encontrado para o estudante com ID: " + studentId);
        }

        return mapper.toStudentFileDTOList(files);
    }

    @Override
    public StudentFileDTO save(StudentFileDTO studentFileDTO) {
        Person person = personRepository.findById(studentFileDTO.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pessoa não encontrada com ID: " + studentFileDTO.getStudentId()));

        StudentFile studentFile = StudentFile.builder()
                .student(person)
                .fileUrl(studentFileDTO.getFileUrl())
                .description(studentFileDTO.getDescription())               
                .build();

        StudentFile savedFile = studentFileRepository.save(studentFile);
        return mapper.toStudentFileDTO(savedFile);
    }

    @Override
    public StudentFile inactivateById(Long id) {
        StudentFile studentFile = studentFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquivo do estudante não encontrado"));

        studentFile.setIsActive(false);
        return studentFileRepository.save(studentFile);
    }

    @Override
    public StudentFileDTO findById(Long id) {
        return studentFileRepository.findById(id)
                .map(mapper::toStudentFileDTO)
                .orElseThrow(() -> new RuntimeException("Arquivo do estudante não encontrado"));
    }
}
