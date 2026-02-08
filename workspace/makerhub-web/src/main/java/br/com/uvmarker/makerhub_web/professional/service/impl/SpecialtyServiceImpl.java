package br.com.uvmarker.makerhub_web.professional.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ClassCouncil;
import br.com.uvmarker.makerhub_web.professional.domain.entity.Specialty;
import br.com.uvmarker.makerhub_web.professional.domain.repository.ClassCouncilRepository;
import br.com.uvmarker.makerhub_web.professional.domain.repository.SpecialtyRepository;
import br.com.uvmarker.makerhub_web.professional.dto.SpecialtyDTO;
import br.com.uvmarker.makerhub_web.professional.mapper.CentralMapperProfessional;
import br.com.uvmarker.makerhub_web.professional.service.SpecialtyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final ClassCouncilRepository classCouncilRepository;
    private final CentralMapperProfessional mapper;

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    // SE FOR USAR FILTROS
    /*
     * @Override
     * public List<SpecialtyDTO> findAllActive() {
     * return specialtyRepository.findByIsActiveTrue().stream()
     * .map(mapper::toDTO)
     * .toList();
     * }
     */

    // SE NÃO FOR PRECISO USAR FILTROS
    @Override
    public List<SpecialtyDTO> findAllActive() {
        return mapper.toSpecialtyDTOList(specialtyRepository.findByIsActiveTrue());
    }

    @Override
    public SpecialtyDTO findById(Long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada com ID: " + id));
        return mapper.toSpecialtyDTO(specialty);
    }

    @Override
    public SpecialtyDTO save(SpecialtyDTO specialtyDTO) {

        ClassCouncil classCouncil = classCouncilRepository.findById(specialtyDTO.getCouncilId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Conselho de classe não encontrado com o id: " + specialtyDTO.getCouncilId()));

        Specialty specialty = mapper.toSpecialty(specialtyDTO);

        specialty.setCouncil(classCouncil);

        Specialty savedSpecialty = specialtyRepository.save(specialty);

        return mapper.toSpecialtyDTO(savedSpecialty);
    }

    @Override
    public Specialty inactivateById(Long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada com ID: " + id));

        specialty.setIsActive(false);
        return specialtyRepository.save(specialty);
    }

    @Override
    public SpecialtyDTO findByCouncilId(Long councilId) {
        // Verifica se o conselho existe
        ClassCouncil council = classCouncilRepository.findById(councilId)
                .orElseThrow(() -> new EntityNotFoundException("Conselho não encontrado com ID: " + councilId));

        // Busca a especialidade pelo conselho
        Specialty specialty = specialtyRepository.findByCouncil(council)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Especialidade não encontrada para o Conselho com ID: " + councilId));

        return mapper.toSpecialtyDTO(specialty);
    }

}
