package br.com.uvmarker.makerhub_web.pei.service.impl;

import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiRepository;
import br.com.uvmarker.makerhub_web.pei.dto.PeiDTO;
import br.com.uvmarker.makerhub_web.pei.service.PeiService;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;
import br.com.uvmarker.makerhub_web.pei.mapper.CentralMapperPei;

@Service
@RequiredArgsConstructor
public class PeiServiceImpl implements PeiService {

    private final PeiRepository peiRepository;
    private final CentralMapperPei mapper;
    private final PersonService personService;

    @Override
    public List<Pei> findAll() {
        return peiRepository.findAll();
    }

    @Override
    public List<PeiDTO> findAllActive() {
        return mapper.toPeiDTOList(peiRepository.findByIsActiveTrue());
    }

    @Override
    public List<PeiDTO> findByStudentId(Long studentId) {
        return mapper.toPeiDTOList(peiRepository.findByStudentIdAndIsActiveTrue(studentId));
    }

    @Override
    public List<PeiDTO> findByProfessionalId(Long professionalId) {
        return mapper.toPeiDTOList(peiRepository.findByProfessionalIdAndIsActiveTrue(professionalId));
    }

    @Override
    public PeiDTO findById(Long id) {
        Pei pei = peiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PEI não encontrado com ID: " + id));
        return mapper.toPeiDTO(pei);
    }

    @Override
    public PeiDTO save(PeiDTO dto) {
        personService.findByPersonId(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getStudentId()));

        personService.findByPersonId(dto.getProfessionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com ID: " + dto.getProfessionalId()));

        Pei saved = peiRepository.save(mapper.toPei(dto));

        return mapper.toPeiDTO(saved);
    }

    @Override
    public PeiDTO update(Long id, PeiDTO dto) {
        Pei existing = peiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));

        personService.findByPersonId(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getStudentId()));

        personService.findByPersonId(dto.getProfessionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com ID: " + dto.getProfessionalId()));

        existing.setNotes(dto.getNotes());
        existing.setDiagnostic(dto.getDiagnostic());

        Pei updated = peiRepository.save(existing);
        return mapper.toPeiDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        Pei pei = peiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PEI não encontrado com ID: " + id));
        pei.setIsActive(false);
        peiRepository.save(pei);
    }

}
