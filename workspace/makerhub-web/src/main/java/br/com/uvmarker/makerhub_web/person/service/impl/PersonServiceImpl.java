package br.com.uvmarker.makerhub_web.person.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.person.domain.entity.Person;
import br.com.uvmarker.makerhub_web.person.domain.entity.PersonType;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonRepository;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonTypeRepository;
import br.com.uvmarker.makerhub_web.person.dto.PersonDTO;
import br.com.uvmarker.makerhub_web.person.dto.PersonSummaryDTO;
import br.com.uvmarker.makerhub_web.person.mapper.CentralMapperPerson;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import br.com.uvmarker.makerhub_web.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonTypeRepository personTypeRepository;
    private final UserService userService;
    private final CentralMapperPerson mapper;


    @Override
    public PersonDTO save(PersonDTO dto) {
        userService.findUserById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getUserId()));

        PersonType type = personTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de pessoa não encontrado com ID: " + dto.getTypeId()));

        Person person = mapper.toPerson(dto);
        person.setType(type);

        Person saved = personRepository.save(person);
        return mapper.toPersonDTO(saved);
    }

    @Override
    public List<PersonDTO> findAllActive() {
        return mapper.toPersonDTOList(personRepository.findByIsActiveTrue());
    }

    @Override
    public List<PersonDTO> findByName(String name) {
        return mapper.toPersonDTOList(personRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(name));
    }

    @Override
    public PersonDTO findByDocument(String document) {
        Person person = personRepository.findByDocument(document)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com documento: " + document));
        return mapper.toPersonDTO(person);
    }

    @Override
    public Person inactivateById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        person.setIsActive(false);
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));
        return mapper.toPersonDTO(person);
    }

    @Override
    public PersonDTO update(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));

        PersonType type = personTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de pessoa não encontrado com ID: " + dto.getTypeId()));

        person.setName(dto.getName());
        person.setDocument(dto.getDocument());
        person.setBirthDate(dto.getBirthDate());    
        person.setType(type);

        Person updated = personRepository.save(person);
        return mapper.toPersonDTO(updated);
    }

    @Override
    public List<PersonDTO> findByTypeId(Long typeId) {
        return mapper.toPersonDTOList(personRepository.findByType_IdAndIsActiveTrue(typeId));
    }

    @Override
    public Optional<PersonSummaryDTO> findByPersonId(Long id) {
        return personRepository.findById(id)
                .map(mapper::toPersonSummaryDTO);
    }

    @Override
    public Optional<PersonDTO> findByUserId(Long userId) {
        return personRepository.findByUserIdAndIsActiveTrue(userId)
                .map(mapper::toPersonDTO);
    }

    @Override
    public boolean existsByDocument(String document) {
        return personRepository.existsByDocument(document);
    }
}