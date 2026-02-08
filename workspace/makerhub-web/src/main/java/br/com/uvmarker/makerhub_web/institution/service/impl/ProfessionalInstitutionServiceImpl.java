package br.com.uvmarker.makerhub_web.institution.service.impl;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalInstitution;
import br.com.uvmarker.makerhub_web.institution.domain.repository.ProfessionalInstitutionRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.ProfessionalRoleRepository;
import br.com.uvmarker.makerhub_web.institution.dto.ProfessionalInstitutionDTO;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionRepository;
import br.com.uvmarker.makerhub_web.institution.mapper.CentralMapperInstitution;
import br.com.uvmarker.makerhub_web.institution.service.ProfessionalInstitutionService;

@Service
@RequiredArgsConstructor
public class ProfessionalInstitutionServiceImpl implements ProfessionalInstitutionService {

    private final ProfessionalInstitutionRepository professionalInstitutionRepository;
    private final ProfessionalRoleRepository professionalRoleRepository;
    private final InstitutionRepository institutionRepository;
    private final CentralMapperInstitution mapper;
    private final PersonService personService;

    @Override
    public List<ProfessionalInstitution> findAll() {
        return professionalInstitutionRepository.findAll();
    }

    @Override
    public List<ProfessionalInstitutionDTO> findAllActive() {
        return mapper.toProfessionalInstitutionDTOList(professionalInstitutionRepository.findByIsActiveTrue());
    }

    @Override
    public ProfessionalInstitutionDTO findById(Long id) {
        return mapper.toProfessionalInstitutionDTO(
                professionalInstitutionRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProfessionalInstitutionDTO> findByInstitutionId(Long institutionId) {
        return mapper.toProfessionalInstitutionDTOList(
                professionalInstitutionRepository.findByInstitution_IdAndIsActiveTrue(institutionId));
    }

    @Override
    public List<ProfessionalInstitutionDTO> findByProfessionalId(Long professionalId) {
        return mapper.toProfessionalInstitutionDTOList(
                professionalInstitutionRepository.findByProfessionalIdAndIsActiveTrue(professionalId));
    }

    @Override
    public ProfessionalInstitutionDTO save(ProfessionalInstitutionDTO dto) {
        ProfessionalInstitution entity = mapper.toProfessionalInstitution(dto);
        entity.setInstitution(
                institutionRepository.findById(dto.getInstitutionId()).orElseThrow());
        entity.setProfessionalRole(
                professionalRoleRepository.findById(dto.getProfessionalRoleId()).orElseThrow());
        personService.findById(dto.getProfessionalId());

        ProfessionalInstitution savedEntity = professionalInstitutionRepository.save(entity);
        return mapper.toProfessionalInstitutionDTO(savedEntity);
    }

    @Override
    public ProfessionalInstitutionDTO update(Long id, ProfessionalInstitutionDTO dto) {
        ProfessionalInstitution existingEntity = professionalInstitutionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo profissional-turma não encontrado com ID: " + id));

        existingEntity.setInstitution(
                institutionRepository.findById(dto.getInstitutionId()).orElseThrow());
        existingEntity.setProfessionalRole(
                professionalRoleRepository.findById(dto.getProfessionalRoleId()).orElseThrow());
        existingEntity.setNotes(dto.getNotes());
        personService.findById(dto.getProfessionalId());

        ProfessionalInstitution updatedEntity = professionalInstitutionRepository.save(existingEntity);
        return mapper.toProfessionalInstitutionDTO(updatedEntity);
    }

    @Override
    public ProfessionalInstitution inactivateById(Long id) {
        ProfessionalInstitution professionalInstitution = professionalInstitutionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo profissional-turma não encontrado com ID: " + id));
        professionalInstitution.setIsActive(false);
        return professionalInstitutionRepository.save(professionalInstitution);
    }

    @Override
    public List<ProfessionalInstitutionDTO> findByProfessionalRoleId(Long professionalRoleId) {
        return mapper.toProfessionalInstitutionDTOList(
                professionalInstitutionRepository.findByProfessionalRole_IdAndIsActiveTrue(professionalRoleId));
    }

}
