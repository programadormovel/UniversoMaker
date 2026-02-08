package br.com.uvmarker.makerhub_web.professional.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ClassCouncil;
import br.com.uvmarker.makerhub_web.professional.domain.repository.ClassCouncilRepository;
import br.com.uvmarker.makerhub_web.professional.service.ClassCouncilService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassCouncilServiceImpl implements ClassCouncilService {

    private final ClassCouncilRepository classCouncilRepository;

    @Override
    public List<ClassCouncil> findAll() {
       return classCouncilRepository.findAll();
    }

    @Override
    public List<ClassCouncil> findAllActive() {
        return classCouncilRepository.findByIsActiveTrue().stream()
            .map(council -> ClassCouncil.builder()
                .id(council.getId())
                .councilName(council.getCouncilName())
                .description(council.getDescription())
                .build())
            .toList();
    }

    @Override
    public ClassCouncil findById(Long id) {
        @SuppressWarnings("null")
        ClassCouncil council = classCouncilRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conselho de classe não encontrado"));

        return ClassCouncil.builder()
            .id(council.getId())
            .councilName(council.getCouncilName())
            .description(council.getDescription())
            .build();
    }

    @SuppressWarnings("null")
    @Override
    public ClassCouncil save(ClassCouncil classCouncil) {
        ClassCouncil classCouncilToSave = ClassCouncil.builder()
            .councilName(classCouncil.getCouncilName())
            .description(classCouncil.getDescription())
            .build();

        return classCouncilRepository.save(classCouncilToSave); 
    }

    @Override
    public ClassCouncil inactivateById(Long id) {
        @SuppressWarnings("null")
        ClassCouncil council = classCouncilRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conselho de classe não encontrado"));
        
        council.setIsActive(false);
        return classCouncilRepository.save(council);
    }   

}
