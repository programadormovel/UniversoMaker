package br.com.uvmarker.makerhub_web.pei.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;
import br.com.uvmarker.makerhub_web.pei.domain.entity.PeiFile;
import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiFileRepository;
import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiRepository;
import br.com.uvmarker.makerhub_web.pei.dto.PeiFileDTO;
import br.com.uvmarker.makerhub_web.pei.mapper.CentralMapperPei;
import br.com.uvmarker.makerhub_web.pei.service.PeiFileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeiFileServiceImpl implements PeiFileService {

    private final PeiFileRepository peiFileRepository;
    private final CentralMapperPei mapper;
    private final PeiRepository peiRepository;

    @Override
    public List<PeiFileDTO> findAll() {
        return mapper.toPeiFileDTOList(peiFileRepository.findAll());
    }

    @Override
    public List<PeiFileDTO> findAllActive() {
        return mapper.toPeiFileDTOList(peiFileRepository.findByIsActiveTrue());
    }

    @Override
    public PeiFileDTO findById(Long id) {
        PeiFile peiFile = peiFileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo de avaliação não encontrado com ID: " + id));
        return mapper.toPeiFileDTO(peiFile);
    }

    @Override
    public PeiFileDTO save(PeiFileDTO dto) {
        Pei pei = peiRepository.findById(dto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getId()));

        PeiFile entity = mapper.toPeiFile(dto);
        entity.setPei(pei);

        PeiFile saved = peiFileRepository.save(entity);
        return mapper.toPeiFileDTO(saved);
    }

    @Override
    public PeiFileDTO update(Long id, PeiFileDTO dto) {
        PeiFile existing = peiFileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo de avaliação não encontrado com ID: " + id));

        Pei pei = peiRepository.findById(dto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getId()));

        existing.setPei(pei);
        existing.setFileUrl(dto.getFileUrl());
        existing.setDescription(dto.getDescription());

        PeiFile updated = peiFileRepository.save(existing);
        return mapper.toPeiFileDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        PeiFile peiFile = peiFileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo de avaliação não encontrado com ID: " + id));
        peiFile.setIsActive(false);
        peiFileRepository.save(peiFile);
    }

    @Override
    public List<PeiFileDTO> findAllActiveByPeiId(Long peiId) {
        return mapper.toPeiFileDTOList(
            peiFileRepository.findByPei_IdAndIsActiveTrue(peiId));
    }
}
