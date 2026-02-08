package br.com.uvmarker.makerhub_web.institution.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.institution.domain.entity.Institution;
import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionClass;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionClassRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionRepository;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionClassDTO;
import br.com.uvmarker.makerhub_web.institution.mapper.CentralMapperInstitution;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionClassService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionClassServiceImpl implements InstitutionClassService {

    private final InstitutionClassRepository institutionClassRepository;
    private final InstitutionRepository institutionRepository;
    private final CentralMapperInstitution mapper;

    @Override
    public List<InstitutionClass> findAll() {
        return institutionClassRepository.findAll();
    }

    @Override
    public List<InstitutionClassDTO> findAllActive() {
        return mapper.toInstitutionClassDTOList(
            institutionClassRepository.findByIsActiveTrue());
    }

    @Override
    public InstitutionClassDTO findById(Long id) {
        return mapper.toInstitutionClassDTO(
            institutionClassRepository.findById(id).orElseThrow());
    }

    @Override
    public List<InstitutionClassDTO> findByInstitutionId(Long institutionId) {
        return mapper.toInstitutionClassDTOList(
            institutionClassRepository.findByInstitution_IdAndIsActiveTrue(institutionId));
    }

    @Override
    public InstitutionClassDTO save(InstitutionClassDTO dto) {
        Institution institution = institutionRepository.findById(dto.getInstitutionId())
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

        InstitutionClass entity = mapper.toInstitutionClass(dto);
        entity.setInstitution(institution);
        entity.setName(dto.getName());
        entity.setNotes(dto.getNotes());
        
        InstitutionClass saved = institutionClassRepository.save(entity);
        return mapper.toInstitutionClassDTO(saved);
    }

    @Override
    public InstitutionClassDTO update(Long id, InstitutionClassDTO dto) {
        InstitutionClass existing = institutionClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe da instituição não encontrada"));

        existing.setName(dto.getName());
        existing.setNotes(dto.getNotes());

        InstitutionClass updated = institutionClassRepository.save(existing);
        return mapper.toInstitutionClassDTO(updated);
    }

    @Override
    public InstitutionClass inactivateById(Long id) {
        InstitutionClass entity = institutionClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe da instituição não encontrada"));

        entity.setIsActive(false);
        return institutionClassRepository.save(entity);
    }


    
}
