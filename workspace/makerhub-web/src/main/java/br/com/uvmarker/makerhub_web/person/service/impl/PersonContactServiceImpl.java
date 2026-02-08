package br.com.uvmarker.makerhub_web.person.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.person.domain.entity.Person;
import br.com.uvmarker.makerhub_web.person.domain.entity.PersonContact;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonContactRepository;
import br.com.uvmarker.makerhub_web.person.domain.repository.PersonRepository;
import br.com.uvmarker.makerhub_web.person.dto.PersonContactDTO;
import br.com.uvmarker.makerhub_web.person.mapper.CentralMapperPerson;
import br.com.uvmarker.makerhub_web.person.service.PersonContactService;
import br.com.uvmarker.makerhub_web.shared_kernel.service.ContactTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonContactServiceImpl implements PersonContactService {

    private final PersonContactRepository personContactRepository;
    private final PersonRepository personRepository;
    private final ContactTypeService typeService;
    private final CentralMapperPerson mapper;

    @Override
    public List<PersonContact> findAll() {
        return personContactRepository.findAll();
    }

    @Override
    public List<PersonContactDTO> findAllActive() {
        return mapper.toPersonContactDTOList(personContactRepository.findByIsActiveTrue());
    }

    @Override
    public PersonContactDTO findById(Long id) {
        PersonContact contact = personContactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado com ID: " + id));
        return mapper.toPersonContactDTO(contact);
    }

    @Override
    public List<PersonContactDTO> findByPersonId(Long personId) {
        List<PersonContact> contacts = personContactRepository.findByPerson_IdAndIsActiveTrue(personId);

        if (contacts.isEmpty()) {
            throw new RuntimeException("Nenhum contato encontrado para a pessoa com ID: " + personId);
        }

        return mapper.toPersonContactDTOList(contacts);
    }

    @Override
    public PersonContactDTO save(PersonContactDTO dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        typeService.findById(dto.getTypeId());

        PersonContact entity = mapper.toPersonContact(dto);
        entity.setPerson(person);
        entity.setTypeId(dto.getTypeId());

        PersonContact saved = personContactRepository.save(entity);
        return mapper.toPersonContactDTO(saved);
    }

    @Override
    public PersonContact inactivateById(Long id) {
        PersonContact contact = personContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
        contact.setIsActive(false);
        return personContactRepository.save(contact);
    }

    @Override
    public PersonContactDTO update(Long id, PersonContactDTO dto) {
        return personContactRepository.findById(id).map(existingContact -> {
            Person person = personRepository.findById(dto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

            typeService.findById(dto.getTypeId());

            existingContact.setPerson(person);
            existingContact.setTypeId(dto.getTypeId());
            existingContact.setContact(dto.getContact());
            existingContact.setNotes(dto.getNotes());

            PersonContact updated = personContactRepository.save(existingContact);
            return mapper.toPersonContactDTO(updated);
        }).orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

}
