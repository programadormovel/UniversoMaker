package br.com.uvmarker.makerhub_web.professional.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uvmarker.makerhub_web.person.dto.PersonDTO;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import br.com.uvmarker.makerhub_web.professional.domain.entity.ProfessionalSpecialty;
import br.com.uvmarker.makerhub_web.professional.domain.entity.Specialty;
import br.com.uvmarker.makerhub_web.professional.domain.repository.ProfessionalSpecialtyRepository;
import br.com.uvmarker.makerhub_web.professional.domain.repository.SpecialtyRepository;
import br.com.uvmarker.makerhub_web.professional.dto.ProfessionalSpecialtyDTO;
import br.com.uvmarker.makerhub_web.professional.mapper.CentralMapperProfessional;
import br.com.uvmarker.makerhub_web.professional.service.ProfessionalSpecialtyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessionalSpecialtyServiceImpl implements ProfessionalSpecialtyService {

    private final ProfessionalSpecialtyRepository professionalSpecialtyRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PersonService personService;
    private final CentralMapperProfessional mapper;

    @Override
    public List<ProfessionalSpecialtyDTO> findAllActive() {
        return mapper.toProfessionalSpecialtyDTOList(professionalSpecialtyRepository.findByIsActiveTrue());
    }

    @Override
    public ProfessionalSpecialtyDTO findById(Long id) {
        ProfessionalSpecialty entity = professionalSpecialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProfessionalSpecialty not found with id: " + id));
        return mapper.toProfessionalSpecialtyDTO(entity);
    }

    @Override
    @Transactional
    public ProfessionalSpecialtyDTO save(ProfessionalSpecialtyDTO dto) {
        // Verifica se a pessoa existe
        personService.findByPersonId(dto.getPersonId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + dto.getPersonId()));

        // Busca a especialidade
        Specialty specialty = specialtyRepository.findById(dto.getSpecialtyId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Especialidade não encontrada com ID: " + dto.getSpecialtyId()));

        // Converte DTO para entidade
        ProfessionalSpecialty entity = mapper.toProfessionalSpecialty(dto);
        entity.setSpecialty(specialty);

        // Salva e retorna DTO
        ProfessionalSpecialty saved = professionalSpecialtyRepository.save(entity);
        return mapper.toProfessionalSpecialtyDTO(saved);
    }

    @Override
    @Transactional
    public ProfessionalSpecialty inactivateById(Long id) {
        ProfessionalSpecialty entity = professionalSpecialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProfessionalSpecialty not found with id: " + id));
        entity.setIsActive(false);
        return professionalSpecialtyRepository.save(entity);
    }

    @Override
    public ProfessionalSpecialtyDTO findByDocumentNr(String documentNr) {
        PersonDTO person = personService.findByDocument(documentNr);
        return professionalSpecialtyRepository.findAll().stream()
                .filter(ps -> ps.getPersonId() != null && ps.getPersonId().equals(person.getId()))
                .findFirst()
                .map(mapper::toProfessionalSpecialtyDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "ProfessionalSpecialty not found for person with document number: " + documentNr));
    }

    @Override
    public List<ProfessionalSpecialtyDTO> findByPersonId(Long personId) {
        // Busca todas as especialidades do profissional pelo personId
        List<ProfessionalSpecialty> specialties = professionalSpecialtyRepository.findByPersonId(personId);

        if (specialties.isEmpty()) {
            throw new EntityNotFoundException(
                    "Nenhuma especialidade encontrada para o profissional com ID: " + personId);
        }

        // Converte para DTO usando o mapper
        return mapper.toProfessionalSpecialtyDTOList(specialties);
    }

        @Override
    public List<ProfessionalSpecialtyDTO> findBySpecialtyId(Long specialtyId) {
        // Busca todas as especialidades do profissional pelo specialtyId
        List<ProfessionalSpecialty> specialties = professionalSpecialtyRepository.findBySpecialty_Id(specialtyId);

        if (specialties.isEmpty()) {
            throw new EntityNotFoundException(
                    "Nenhum profissional encontrado para a especialidade com ID: " + specialtyId);
        }

        // Converte para DTO usando o mapper
        return mapper.toProfessionalSpecialtyDTOList(specialties);
    }

    @Override
    public List<ProfessionalSpecialty> findAll() {
        return professionalSpecialtyRepository.findAll();
    }
}