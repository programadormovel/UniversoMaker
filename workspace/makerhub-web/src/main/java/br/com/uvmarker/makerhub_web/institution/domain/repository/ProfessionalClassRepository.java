package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalClass;

@Repository
public interface ProfessionalClassRepository extends JpaRepository<ProfessionalClass, Long> {

    List<ProfessionalClass> findByIsActiveTrue();
       
    List<ProfessionalClass> findByProfessionalIdAndIsActiveTrue(Long professionalId);

    List<ProfessionalClass> findByInstitutionClass_IdAndIsActiveTrue(Long classId);

    List<ProfessionalClass> findByProfessionalRole_IdAndIsActiveTrue(Long professionalRoleId);
    
}