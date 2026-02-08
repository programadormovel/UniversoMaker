package br.com.uvmarker.makerhub_web.person.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.person.domain.entity.PersonType;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonTypeRepository;
import br.com.uvmarker.makerhub_web.person.service.PersonTypeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonTypeServiceImpl implements PersonTypeService {

    private final PersonTypeRepository personTypeRepository;

    @Override
    public List<PersonType> findAll() {
        return personTypeRepository.findAll();
    }

    @Override
    public List<PersonType> findAllActive() {
        return personTypeRepository.findByIsActiveTrue();
    }

    @Override
    public PersonType findById(Long id) {
        return personTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de pessoa não encontrada"));
    }

    @SuppressWarnings("null")
    @Override
    public PersonType save(PersonType persontype) {
        PersonType persontypeToSave = PersonType.builder()
            .typeName(persontype.getTypeName())
            .description(persontype.getDescription())
            .createdAt(LocalDateTime.now())
            .isActive(true)
            .build();
        return personTypeRepository.save(persontypeToSave); 
    }

    @Override
    public PersonType inactivateById(Long id) {
        @SuppressWarnings("null")
        PersonType type = personTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de pessoa não encontrado"));

        type.setIsActive(false);
        return personTypeRepository.save(type);
    }
}
