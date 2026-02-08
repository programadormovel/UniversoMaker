package br.com.uvmarker.makerhub_web.institution.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionType;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionTypeRepository;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionTypeServiceImpl implements InstitutionTypeService {

    private final InstitutionTypeRepository institutionTypeRepository;

    @Override
    public List<InstitutionType> findAll() {
        return institutionTypeRepository.findAll();
    }

    @Override
    public List<InstitutionType> findAllActive() {
              return institutionTypeRepository.findByIsActiveTrue();
    }

    @Override
    public InstitutionType findById(Long id) {
            return institutionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de instituição não encontrado com ID: " + id));
    }

    @Override
    public InstitutionType save(InstitutionType institutionType) {
        InstitutionType institutionTypeToSave = InstitutionType.builder()
                .typeName(institutionType.getTypeName())
                .description(institutionType.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return institutionTypeRepository.save(institutionTypeToSave);
    }

    @Override
    public InstitutionType inactivateById(Long id) {
        InstitutionType institutionType = institutionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
        institutionType.setIsActive(false);
        return institutionTypeRepository.save(institutionType);
    }
      
}
