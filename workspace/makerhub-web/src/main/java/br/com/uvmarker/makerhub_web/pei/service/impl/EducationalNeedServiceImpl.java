package br.com.uvmarker.makerhub_web.pei.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeed;
import br.com.uvmarker.makerhub_web.pei.domain.repository.EducationalNeedRepository;
import br.com.uvmarker.makerhub_web.pei.service.EducationalNeedService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationalNeedServiceImpl implements EducationalNeedService {

    private final EducationalNeedRepository educationalNeedRepository;

    @Override
    public List<EducationalNeed> findAll() {
        return educationalNeedRepository.findAll();
    }

    @Override
    public List<EducationalNeed> findAllActive() {
        return educationalNeedRepository.findByIsActiveTrue();
    }

    @SuppressWarnings("null")
    @Override
    public EducationalNeed findById(Long id) {
        return educationalNeedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
    }

    @SuppressWarnings("null")
    @Override
    public EducationalNeed save(EducationalNeed educationalNeed) {
        EducationalNeed educationalNeedToSave = EducationalNeed.builder()
                .educationalNeedName(educationalNeed.getEducationalNeedName())
                .description(educationalNeed.getDescription())
                .build();
        return educationalNeedRepository.save(educationalNeedToSave);
    }

    @Override
    public EducationalNeed inactivateById(Long id) {
        @SuppressWarnings("null")
        EducationalNeed educationalNeed = educationalNeedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
        educationalNeed.setIsActive(false);
        return educationalNeedRepository.save(educationalNeed);
    }

}
