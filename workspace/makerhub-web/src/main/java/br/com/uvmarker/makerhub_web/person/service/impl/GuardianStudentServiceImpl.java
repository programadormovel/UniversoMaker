package br.com.uvmarker.makerhub_web.person.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.person.domain.entity.GuardianStudent;
import br.com.uvmarker.makerhub_web.person.domain.entity.Person;
import br.com.uvmarker.makerhub_web.person.domain.entity.RelationshipType;
import br.com.uvmarker.makerhub_web.person.domain.repository.GuardianStudentRepository;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonRepository;
import br.com.uvmarker.makerhub_web.person.domain.repository.RelationshipTypeRepository;
import br.com.uvmarker.makerhub_web.person.dto.GuardianStudentDTO;
import br.com.uvmarker.makerhub_web.person.mapper.CentralMapperPerson;
import br.com.uvmarker.makerhub_web.person.service.GuardianStudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuardianStudentServiceImpl implements GuardianStudentService {

        private final GuardianStudentRepository guardianStudentRepository;
        private final PersonRepository personRepository;
        private final RelationshipTypeRepository relationshipTypeRepository;
        private final CentralMapperPerson mapper;

        @Override
        public List<GuardianStudent> findAll() {
                return guardianStudentRepository.findAll();
        }

        @Override
        public List<GuardianStudentDTO> findAllActive() {
                return mapper.toGuardianStudentDTOList(guardianStudentRepository.findByIsActiveTrue());
        }

        @Override
        public List<GuardianStudentDTO> findByGuardianId(Long guardianId) {
                return guardianStudentRepository.findByIsActiveTrue().stream()
                                .filter(gs -> gs.getGuardian().getId().equals(guardianId))
                                .map(mapper::toGuardianStudentDTO)
                                .toList();
        }

        @Override
        public List<GuardianStudentDTO> findByStudentId(Long studentId) {
                return guardianStudentRepository.findByIsActiveTrue().stream()
                                .filter(gs -> gs.getStudent().getId().equals(studentId))
                                .map(mapper::toGuardianStudentDTO)
                                .toList();
        }

        @Override
        public GuardianStudentDTO findById(Long id) {
                GuardianStudent guardianStudent = guardianStudentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Responsável para o aluno não encontrado com ID: " + id));
                return mapper.toGuardianStudentDTO(guardianStudent);
        }

        @Override
        public GuardianStudentDTO save(GuardianStudentDTO dto) {

                // Busca entidades relacionadas
                Person guardian = personRepository.findById(dto.getGuardianId())
                                .orElseThrow(() -> new RuntimeException("Pessoa (responsável) não encontrada"));

                Person student = personRepository.findById(dto.getStudentId())
                                .orElseThrow(() -> new RuntimeException("Pessoa (aluno) não encontrada"));

                RelationshipType relationship = relationshipTypeRepository.findById(dto.getRelationshipId())
                                .orElseThrow(() -> new RuntimeException("Tipo de relação não encontrado"));

                // Converte DTO para entidade
                GuardianStudent guardianStudent = mapper.toGuardianStudent(dto);

                // Ajusta entidades relacionadas e metadados
                guardianStudent.setGuardian(guardian);
                guardianStudent.setStudent(student);
                guardianStudent.setRelationship(relationship);

                // Salva e retorna DTO com objetos aninhados
                GuardianStudent saved = guardianStudentRepository.save(guardianStudent);
                return mapper.toGuardianStudentDTO(saved);
        }

        @Override
        public GuardianStudentDTO update(Long id, GuardianStudentDTO dto) {
                GuardianStudent guardianStudent = guardianStudentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Responsável para o aluno não encontrado com ID: " + id));

                Person guardian = personRepository.findById(dto.getGuardianId())
                                .orElseThrow(() -> new RuntimeException("Pessoa (responsável) não encontrada"));

                Person student = personRepository.findById(dto.getStudentId())
                                .orElseThrow(() -> new RuntimeException("Pessoa (aluno) não encontrada"));

                RelationshipType relationship = relationshipTypeRepository.findById(dto.getRelationshipId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Tipo de relação não encontrado com ID: " + dto.getRelationshipId()));

                guardianStudent.setGuardian(guardian);
                guardianStudent.setStudent(student);
                guardianStudent.setRelationship(relationship);
                guardianStudent.setNotes(dto.getNotes());
  
                GuardianStudent updated = guardianStudentRepository.save(guardianStudent);
                return mapper.toGuardianStudentDTO(updated);
        }

        @Override
        public GuardianStudent inactivateById(Long id) {
                GuardianStudent guardianStudent = guardianStudentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Vínculo não encontrado"));

                guardianStudent.setIsActive(false);
                return guardianStudentRepository.save(guardianStudent);
        }
}