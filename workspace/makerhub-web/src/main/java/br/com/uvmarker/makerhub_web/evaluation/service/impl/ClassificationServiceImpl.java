package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Classification;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.ClassificationRepository;
import br.com.uvmarker.makerhub_web.evaluation.service.ClassificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

 @Override
    public List<Classification> findAll() {
       return classificationRepository.findAll();
    }

    @Override
    public List<Classification> findAllActive() {
        return classificationRepository.findByIsActiveTrue();
    }

    @Override
    public Classification findById(Long id) {
        return classificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Classificação não encontrada"));
    }

    @SuppressWarnings("null")
    @Override
    public Classification save(Classification classification) {
        Classification classificationToSave = Classification.builder()
            .classificationName(classification.getClassificationName())
            .description(classification.getDescription())
            .isActive(true)
            .createdAt(LocalDateTime.now())
            .build();

        return classificationRepository.save(classificationToSave); 
    }

    @Override
    public Classification inactivateById(Long id) {
        @SuppressWarnings("null")
        Classification classification = classificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conselho de classe não encontrado"));
        
        classification.setIsActive(false);
        return classificationRepository.save(classification);
    }   

  
}
