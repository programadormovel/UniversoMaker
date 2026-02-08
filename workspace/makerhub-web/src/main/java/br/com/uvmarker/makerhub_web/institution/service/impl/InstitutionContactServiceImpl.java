package br.com.uvmarker.makerhub_web.institution.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.institution.domain.entity.Institution;
import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionContact;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionContactRepository;
import br.com.uvmarker.makerhub_web.institution.domain.repository.InstitutionRepository;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionContactDTO;
import br.com.uvmarker.makerhub_web.institution.mapper.CentralMapperInstitution;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionContactService;
import br.com.uvmarker.makerhub_web.shared_kernel.service.ContactTypeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionContactServiceImpl implements InstitutionContactService {

    private final InstitutionContactRepository institutionContactRepository;
    private final InstitutionRepository institutionRepository;
    private final ContactTypeService typeService;
    private final CentralMapperInstitution mapper;

    @Override
    public List<InstitutionContact> findAll() {
        return institutionContactRepository.findAll();
    }

    @Override
    public List<InstitutionContactDTO> findAllActive() {
        return mapper.toInstitutionContactDTOList(
            institutionContactRepository.findByIsActiveTrue());
    }

    @Override
    public InstitutionContactDTO findById(Long id) {
        return mapper.toInstitutionContactDTO(
            institutionContactRepository.findById(id).orElseThrow());
    }

    @Override
    public List<InstitutionContactDTO> findByInstitutionId(Long institutionId) {
        return mapper.toInstitutionContactDTOList(
            institutionContactRepository.findByInstitution_IdAndIsActiveTrue(institutionId));
    }

    @Override
    public InstitutionContactDTO save(InstitutionContactDTO dto) {
        Institution institution = institutionRepository.findById(dto.getInstitutionId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        typeService.findById(dto.getTypeId());

        InstitutionContact entity = mapper.toInstitutionContact(dto);
        entity.setInstitution(institution);
        entity.setTypeId(dto.getTypeId());

        InstitutionContact saved = institutionContactRepository.save(entity);
        return mapper.toInstitutionContactDTO(saved);
    }

    @Override
    public InstitutionContact inactivateById(Long id) {
       return institutionContactRepository.findById(id).map(contact -> {
            contact.setIsActive(false);
            return institutionContactRepository.save(contact);
        }).orElseThrow();
    }

        @Override
    public InstitutionContactDTO update(Long id, InstitutionContactDTO dto) {
        return institutionContactRepository.findById(id).map(existingContact -> {
            Institution institution = institutionRepository.findById(dto.getInstitutionId())
                    .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

            typeService.findById(dto.getTypeId());

            existingContact.setInstitution(institution);
            existingContact.setTypeId(dto.getTypeId());
            existingContact.setContact(dto.getContact());
            existingContact.setNotes(dto.getNotes());

            InstitutionContact updated = institutionContactRepository.save(existingContact);
            return mapper.toInstitutionContactDTO(updated);
        }).orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

}
