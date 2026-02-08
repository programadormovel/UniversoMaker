package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionContact;

@Repository
public interface InstitutionContactRepository extends JpaRepository<InstitutionContact, Long> {

   List<InstitutionContact> findByIsActiveTrue();

   List<InstitutionContact> findByInstitution_IdAndIsActiveTrue(Long institutionId);
   
}