package br.com.uvmarker.makerhub_web.person.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.person.domain.entity.RelationshipType;
import br.com.uvmarker.makerhub_web.person.domain.repository.RelationshipTypeRepository;
import br.com.uvmarker.makerhub_web.person.service.RelationshipTypeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelationshipTypeServiceImpl implements RelationshipTypeService {

    private final RelationshipTypeRepository relationshipTypeRepository;

   @Override
    public List<RelationshipType> findAll() {
        return relationshipTypeRepository.findAll();
    }

    @Override
    public List<RelationshipType> findAllActive() {
        return relationshipTypeRepository.findByIsActiveTrue();
    }

    @Override
    public RelationshipType findById(Long id) {
        return relationshipTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de relação não encontrado"));
    }

    @Override
    public RelationshipType save(RelationshipType relationshipType) {
        RelationshipType relationshipTypeToSave = RelationshipType.builder()
            .typeName(relationshipType.getTypeName())
            .description(relationshipType.getDescription())
            .createdAt(LocalDateTime.now())
            .isActive(true)
            .build();

        return relationshipTypeRepository.save(relationshipTypeToSave); 
    }

    @Override
    public RelationshipType inactivateById(Long id) {
        RelationshipType type = relationshipTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de relação não encontrado"));

        type.setIsActive(false);
        return relationshipTypeRepository.save(type);
    }
}
