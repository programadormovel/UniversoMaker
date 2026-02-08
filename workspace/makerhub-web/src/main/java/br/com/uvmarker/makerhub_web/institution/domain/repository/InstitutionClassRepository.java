package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionClass;

@Repository
public interface InstitutionClassRepository extends JpaRepository<InstitutionClass, Long> {

    List<InstitutionClass> findByIsActiveTrue();

    List<InstitutionClass> findByInstitution_IdAndIsActiveTrue(Long institutionId);

    List<InstitutionClass> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);
}