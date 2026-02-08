package br.com.uvmarker.makerhub_web.institution.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.institution.domain.entity.Institution;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionTypeRepository;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionDTO;
import br.com.uvmarker.makerhub_web.institution.mapper.CentralMapperInstitution;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionService;
import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionType;
import br.com.uvmarker.makerhub_web.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionTypeRepository institutionTypeRepository;
    private final UserService userService;
    private final CentralMapperInstitution mapper;

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public List<InstitutionDTO> findAllActive() {
        return mapper.toInstitutionDTOList(
            institutionRepository.findByIsActiveTrue());
    }

    @Override
    public List<InstitutionDTO> findByTypeId(Long typeId) {
        return mapper.toInstitutionDTOList(
            institutionRepository.findByType_IdAndIsActiveTrue(typeId));
    }

    @Override
    public List<InstitutionDTO> findByName(String name) {
        return mapper.toInstitutionDTOList(
            institutionRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(name));
    }

    @Override
    public InstitutionDTO findById(Long id) {
        return mapper.toInstitutionDTO(
            institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institution not found")));
    }

    @Override
    public List<InstitutionDTO> findByUserId(Long userId) {
       return mapper.toInstitutionDTOList(
            institutionRepository.findByUserIdAndIsActiveTrue(userId)
                .map(List::of)
                .orElseThrow(() -> new RuntimeException("Institution not found for user ID: " + userId)));
    }

    @Override
    public InstitutionDTO save(InstitutionDTO dto) {
        userService.findUserById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getUserId()));

        InstitutionType type = institutionTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de instituição não encontrado com ID: " + dto.getTypeId()));

        Institution institution = mapper.toInstitution(dto);
        institution.setType(type);  
        Institution saved = institutionRepository.save(institution);
        return mapper.toInstitutionDTO(saved);
    }

    @Override
    public InstitutionDTO update(Long id, InstitutionDTO dto) {
        userService.findUserById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getUserId()));

        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instituição não encontrada com ID: " + id));

        InstitutionType type = institutionTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de instituição não encontrado com ID: " + dto.getTypeId()));
        
        Institution updatedInstitution = mapper.toInstitution(dto);
        updatedInstitution.setType(type);
        updatedInstitution.setId(institution.getId());
        Institution saved = institutionRepository.save(updatedInstitution);
        return mapper.toInstitutionDTO(saved);
    }

    @Override
    public Institution inactivateById(Long id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        institution.setIsActive(false);
        return institutionRepository.save(institution);
    }

}
