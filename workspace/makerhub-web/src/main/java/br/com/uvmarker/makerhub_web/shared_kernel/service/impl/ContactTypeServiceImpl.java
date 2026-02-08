package br.com.uvmarker.makerhub_web.shared_kernel.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.ContactType;
import br.com.uvmarker.makerhub_web.shared_kernel.domain.repository.ContactTypeRepository;
import br.com.uvmarker.makerhub_web.shared_kernel.service.ContactTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactType> findAll() {
        return contactTypeRepository.findAll();
    }

    @Override
    public List<ContactType> findAllActive() {
        return contactTypeRepository.findByIsActiveTrue();
    }

    @Override
    public ContactType findById(Long id) {
        return contactTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
    }

    @Override
    public ContactType save(ContactType contactType) {
        ContactType contactTypeToSave = ContactType.builder()
                .typeName(contactType.getTypeName())
                .description(contactType.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return contactTypeRepository.save(contactTypeToSave);
    }

    @Override
    public ContactType inactivateById(Long id) {
        @SuppressWarnings("null")
        ContactType contactType = contactTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
        contactType.setIsActive(false);
        return contactTypeRepository.save(contactType);
    }

    
}
