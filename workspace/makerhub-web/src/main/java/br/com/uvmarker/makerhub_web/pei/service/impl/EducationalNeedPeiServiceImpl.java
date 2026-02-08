package br.com.uvmarker.makerhub_web.pei.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeed;
import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeedPei;
import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;
import br.com.uvmarker.makerhub_web.pei.domain.repository.EducationalNeedPeiRepository;
import br.com.uvmarker.makerhub_web.pei.domain.repository.EducationalNeedRepository;
import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiRepository;
import br.com.uvmarker.makerhub_web.pei.dto.EducationalNeedPeiDTO;
import br.com.uvmarker.makerhub_web.pei.mapper.CentralMapperPei;
import br.com.uvmarker.makerhub_web.pei.service.EducationalNeedPeiService;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationalNeedPeiServiceImpl implements EducationalNeedPeiService {

    private final EducationalNeedPeiRepository educationalNeedPeiRepository;
    private final PeiRepository peiRepository;
    private final EducationalNeedRepository educationalNeedRepository;
    private final CentralMapperPei mapper;
    private final PersonService personService;

    @Override
    public List<EducationalNeedPei> findAll() {
        return educationalNeedPeiRepository.findAll();
    }

    @Override
    public List<EducationalNeedPeiDTO> findAllActive() {
       return mapper.toEducationalNeedPeiDTOList(educationalNeedPeiRepository.findByIsActiveTrue());
    }

    @Override
    public List<EducationalNeedPeiDTO> findByPeiId(Long peiId) {
        return mapper.toEducationalNeedPeiDTOList(educationalNeedPeiRepository.findByPei_IdAndIsActiveTrue(peiId));
    }

    @Override
    public EducationalNeedPeiDTO findById(Long id) {
        EducationalNeedPei educationalNeedPei = educationalNeedPeiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Necessidade educacional não encontrada com ID: " + id));
        return mapper.toEducationalNeedPeiDTO(educationalNeedPei);
    }

    @Override
    public EducationalNeedPeiDTO save(EducationalNeedPeiDTO dto) {
        EducationalNeedPei educationalNeedPei = mapper.toEducationalNeedPei(dto);

        Pei pei = peiRepository.findById(dto.getPeiId())
                .orElseThrow(() -> new EntityNotFoundException("PEI não encontrado com ID: " + dto.getPeiId()));

        EducationalNeed educationalNeed = educationalNeedRepository.findById(dto.getEducationalNeedId())
                .orElseThrow(() -> new EntityNotFoundException("Necessidade educacional não encontrada com ID: " + dto.getEducationalNeedId()));

        personService.findById(dto.getProfessionalId());

        educationalNeedPei.setPei(pei);
        educationalNeedPei.setEducationalNeed(educationalNeed);
     
        EducationalNeedPei savedEntity = educationalNeedPeiRepository.save(educationalNeedPei);
        return mapper.toEducationalNeedPeiDTO(savedEntity);
    }

    @Override
    public EducationalNeedPeiDTO update(Long id, EducationalNeedPeiDTO dto) {
        EducationalNeedPei existingEntity = educationalNeedPeiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Necessidade educacional não encontrada com ID: " + id));

        existingEntity.setNotes(dto.getNotes());

        EducationalNeedPei updatedEntity = educationalNeedPeiRepository.save(existingEntity);
        return mapper.toEducationalNeedPeiDTO(updatedEntity);
    }

    @Override
    public void inactivateById(Long id) {
        EducationalNeedPei existingEntity = educationalNeedPeiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Necessidade educacional não encontrada com ID: " + id));
        existingEntity.setIsActive(false);
        educationalNeedPeiRepository.save(existingEntity);
    }


}
