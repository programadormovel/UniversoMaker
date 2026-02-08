package br.com.uvmarker.makerhub_web.pei.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.pei.domain.entity.PeiMonitoring;
import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiMonitoringRepository;
import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiRepository;
import br.com.uvmarker.makerhub_web.pei.dto.PeiMonitoringDTO;
import br.com.uvmarker.makerhub_web.pei.mapper.CentralMapperPei;
import br.com.uvmarker.makerhub_web.pei.service.PeiMonitoringService;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeiMonitoringServiceImpl implements PeiMonitoringService {

    private final PeiMonitoringRepository peiMonitoringRepository;
    private final PeiRepository peiRepository;
    private final CentralMapperPei mapper;
    private final PersonService personService;

    @Override
    public List<PeiMonitoring> findAll() {
        return peiMonitoringRepository.findAll();
    }

    @Override
    public List<PeiMonitoringDTO> findAllActive() {
        return mapper.toPeiMonitoringDTOList(peiMonitoringRepository.findByIsActiveTrue());
    }

    @Override
    public List<PeiMonitoringDTO> findByPeiId(Long peiId) {
        return mapper.toPeiMonitoringDTOList(peiMonitoringRepository.findByPei_IdAndIsActiveTrue(peiId));
    }

    @Override
    public PeiMonitoringDTO findById(Long id) {
        PeiMonitoring peiMonitoring = peiMonitoringRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitoramento do PEI não encontrado com ID: " + id));
        return mapper.toPeiMonitoringDTO(peiMonitoring);
    }

    @Override
    public PeiMonitoringDTO save(PeiMonitoringDTO dto) {
        PeiMonitoring peiMonitoring = mapper.toPeiMonitoring(dto);

        peiRepository.findById(dto.getPeiId())
                .orElseThrow(() -> new EntityNotFoundException("PEI não encontrado com ID: " + dto.getPeiId()));

        personService.findById(dto.getProfessionalId());

        PeiMonitoring savedEntity = peiMonitoringRepository.save(peiMonitoring);
        return mapper.toPeiMonitoringDTO(savedEntity);
    }

    @Override
    public PeiMonitoringDTO update(Long id, PeiMonitoringDTO dto) {
        PeiMonitoring existingEntity = peiMonitoringRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitoramento do PEI não encontrado com ID: " + id));

        existingEntity.setNotes(dto.getNotes());

        PeiMonitoring updatedEntity = peiMonitoringRepository.save(existingEntity);
        return mapper.toPeiMonitoringDTO(updatedEntity);
    }

    @Override
    public void inactivateById(Long id) {
        PeiMonitoring existingEntity = peiMonitoringRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitoramento do PEI não encontrado com ID: " + id));
        existingEntity.setIsActive(false);
        peiMonitoringRepository.save(existingEntity);
    }

}
