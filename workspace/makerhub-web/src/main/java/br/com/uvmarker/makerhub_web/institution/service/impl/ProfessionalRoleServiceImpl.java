package br.com.uvmarker.makerhub_web.institution.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalRole;
import br.com.uvmarker.makerhub_web.institution.domain.repository.ProfessionalRoleRepository;
import br.com.uvmarker.makerhub_web.institution.service.ProfessionalRoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessionalRoleServiceImpl implements ProfessionalRoleService {

    private final ProfessionalRoleRepository professionalRoleRepository;

    @Override
    public List<ProfessionalRole> findAll() {
        return professionalRoleRepository.findAll();
    }

    @Override
    public List<ProfessionalRole> findAllActive() {
        return professionalRoleRepository.findByIsActiveTrue();
    }

    @Override
    public ProfessionalRole findById(Long id) {
        return professionalRoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo profissional não encontrado com ID: " + id));
    }

    @Override
    public ProfessionalRole save(ProfessionalRole professionalRole) {
        ProfessionalRole professionalRoleToSave = ProfessionalRole.builder()
                .professionalRoleName(professionalRole.getProfessionalRoleName())
                .description(professionalRole.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return professionalRoleRepository.save(professionalRoleToSave);
    }

    @Override
    public ProfessionalRole inactivateById(Long id) {
        ProfessionalRole professionalRole = professionalRoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo profissional não encontrado com ID: " + id));
        professionalRole.setIsActive(false);
        return professionalRoleRepository.save(professionalRole);
    }
    
}
