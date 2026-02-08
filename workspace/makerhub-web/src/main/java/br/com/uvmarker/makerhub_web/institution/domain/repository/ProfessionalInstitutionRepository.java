package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalInstitution;

@Repository
public interface ProfessionalInstitutionRepository extends JpaRepository<ProfessionalInstitution, Long> {

    List<ProfessionalInstitution> findByIsActiveTrue();

    List<ProfessionalInstitution> findByProfessionalIdAndIsActiveTrue(Long professionalId);

    List<ProfessionalInstitution> findByInstitution_IdAndIsActiveTrue(Long institutionId);

    List<ProfessionalInstitution> findByProfessionalRole_IdAndIsActiveTrue(Long professionalRoleId);
}