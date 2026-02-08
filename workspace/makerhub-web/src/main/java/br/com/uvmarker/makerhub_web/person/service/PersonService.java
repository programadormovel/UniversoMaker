package br.com.uvmarker.makerhub_web.person.service;

import java.util.List;
import java.util.Optional;

import br.com.uvmarker.makerhub_web.person.domain.entity.Person;
import br.com.uvmarker.makerhub_web.person.dto.PersonDTO;
import br.com.uvmarker.makerhub_web.person.dto.PersonSummaryDTO;

public interface PersonService {

    List<Person> findAll();

    List<PersonDTO> findAllActive();

    List<PersonDTO> findByTypeId(Long typeId);

    List<PersonDTO> findByName(String name);

    PersonDTO findByDocument(String document);

    PersonDTO findById(Long id);

    Optional<PersonDTO> findByUserId(Long userId);

    PersonDTO save(PersonDTO dto);

    PersonDTO update(Long id, PersonDTO dto);

    Person inactivateById(Long id);
    
    Optional<PersonSummaryDTO> findByPersonId(Long personId); // troca de dados entre domínios
    
    boolean existsByDocument(String document); // troca de dados entre domínios

}
