package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.ClinicalHistory;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationItem;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.ClinicalHistoryRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationItemRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.ClinicalHistoryDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.ClinicalHistoryService;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicalHistoryServiceImpl implements ClinicalHistoryService {

    private final ClinicalHistoryRepository clinicalHistoryRepository;
    private final CentralMapperEvaluation mapper;
    private final EvaluationItemRepository evaluationItemRepository;
    private final PersonService personService;

    @Override
    public List<ClinicalHistoryDTO> findAll() {
        return mapper.toClinicalHistoryDTOList(clinicalHistoryRepository.findAll());
    }

    @Override
    public List<ClinicalHistoryDTO> findAllActive() {
        return mapper.toClinicalHistoryDTOList(clinicalHistoryRepository.findByIsActiveTrue());
    }

    @Override
    public ClinicalHistoryDTO findById(Long id) {
        ClinicalHistory clinicalHistory = clinicalHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));
        return mapper.toClinicalHistoryDTO(clinicalHistory);
    }

    @Override
    public ClinicalHistoryDTO save(ClinicalHistoryDTO dto) {
        EvaluationItem item = evaluationItemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de avaliação não encontrado com ID: " + dto.getItemId()));

        personService.findByPersonId(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getStudentId()));

        personService.findByPersonId(dto.getProfessionalId())
                .orElseThrow(() -> new EntityNotFoundException( "Profissional não encontrado com ID: " + dto.getProfessionalId()));

        ClinicalHistory entity = mapper.toClinicalHistory(dto);
        entity.setItem(item);

        ClinicalHistory saved = clinicalHistoryRepository.save(entity);
        return mapper.toClinicalHistoryDTO(saved);
    }

    @Override
    public ClinicalHistoryDTO update(Long id, ClinicalHistoryDTO dto) {
        ClinicalHistory existing = clinicalHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));

        EvaluationItem item = evaluationItemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de avaliação não encontrado com ID: " + dto.getItemId()));

        personService.findByPersonId(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getStudentId()));

        personService.findByPersonId(dto.getProfessionalId())
                .orElseThrow(() -> new EntityNotFoundException( "Profissional não encontrado com ID: " + dto.getProfessionalId()));

        existing.setItem(item);
        existing.setNotes(dto.getNotes());


        ClinicalHistory updated = clinicalHistoryRepository.save(existing);
        return mapper.toClinicalHistoryDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        ClinicalHistory clinicalHistory = clinicalHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));
        clinicalHistory.setIsActive(false);
        clinicalHistoryRepository.save(clinicalHistory);
    }

    @Override
    public List<ClinicalHistoryDTO> findByStudentId(Long studentId) {
        return mapper.toClinicalHistoryDTOList(
                clinicalHistoryRepository.findByStudentIdAndIsActiveTrue(studentId));
    }

    @Override
    public List<ClinicalHistoryDTO> findByProfessionalId(Long professionalId) {
        return mapper.toClinicalHistoryDTOList(
                clinicalHistoryRepository.findByProfessionalIdAndIsActiveTrue(professionalId));
    }
}
