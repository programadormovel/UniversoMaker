package br.com.uvmarker.makerhub_web.institution.service.impl;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalClass;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionClassRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.ProfessionalClassRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.ProfessionalRoleRepository;
import br.com.uvmarker.makerhub_web.institution.dto.ProfessionalClassDTO;
import br.com.uvmarker.makerhub_web.institution.mapper.CentralMapperInstitution;
import br.com.uvmarker.makerhub_web.institution.service.ProfessionalClassService;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalClassServiceImpl implements ProfessionalClassService {

    private final ProfessionalClassRepository professionalClassRepository;
    private final ProfessionalRoleRepository professionalRoleRepository;
    private final InstitutionClassRepository institutionClassRepository;
    private final CentralMapperInstitution mapper;
    private final PersonService personService;

    @Override
    public List<ProfessionalClass> findAll() {
        return professionalClassRepository.findAll();
    }

    @Override
    public List<ProfessionalClassDTO> findAllActive() {
        return mapper.toProfessionalClassDTOList(professionalClassRepository.findByIsActiveTrue());
    }

    @Override
    public ProfessionalClassDTO findById(Long id) {
        return mapper.toProfessionalClassDTO(
                professionalClassRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProfessionalClassDTO> findByClassId(Long classId) {
        return mapper.toProfessionalClassDTOList(
                professionalClassRepository.findByInstitutionClass_IdAndIsActiveTrue(classId));
    }

    @Override
    public List<ProfessionalClassDTO> findByProfessionalId(Long professionalId) {
        return mapper.toProfessionalClassDTOList(
                professionalClassRepository.findByProfessionalIdAndIsActiveTrue(professionalId));
    }

    @Override
    public ProfessionalClassDTO save(ProfessionalClassDTO dto) {
        ProfessionalClass entity = mapper.toProfessionalClass(dto);
        entity.setInstitutionClass(
                institutionClassRepository.findById(dto.getClassId()).orElseThrow());
        entity.setProfessionalRole(
                professionalRoleRepository.findById(dto.getProfessionalRoleId()).orElseThrow());
        personService.findById(dto.getProfessionalId());

        ProfessionalClass savedEntity = professionalClassRepository.save(entity);
        return mapper.toProfessionalClassDTO(savedEntity);
    }

    @Override
    public ProfessionalClassDTO update(Long id, ProfessionalClassDTO dto) {
        ProfessionalClass existingEntity = professionalClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo profissional-turma não encontrado com ID: " + id));

        existingEntity.setInstitutionClass(
                institutionClassRepository.findById(dto.getClassId()).orElseThrow());
        existingEntity.setProfessionalRole(
                professionalRoleRepository.findById(dto.getProfessionalRoleId()).orElseThrow());
        existingEntity.setNotes(dto.getNotes());
        personService.findById(dto.getProfessionalId());

        ProfessionalClass updatedEntity = professionalClassRepository.save(existingEntity);
        return mapper.toProfessionalClassDTO(updatedEntity);
    }

    @Override
    public ProfessionalClass inactivateById(Long id) {
        ProfessionalClass professionalClass = professionalClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo profissional-turma não encontrado com ID: " + id));
        professionalClass.setIsActive(false);
        return professionalClassRepository.save(professionalClass);
    }

    @Override
    public List<ProfessionalClassDTO> findByProfessionalRoleId(Long professionalRoleId) {
        return mapper.toProfessionalClassDTOList(
                professionalClassRepository.findByProfessionalRole_IdAndIsActiveTrue(professionalRoleId));
    }

}
