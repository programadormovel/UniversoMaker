package br.com.uvmarker.makerhub_web.professional.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ProfessionalSpecialty;

@Repository
public interface ProfessionalSpecialtyRepository extends JpaRepository<ProfessionalSpecialty, Long> {

    List<ProfessionalSpecialty> findByIsActiveTrue();

    ProfessionalSpecialty findByDocumentNr(String documentNr);

    Optional<ProfessionalSpecialty> findByPersonIdAndIsActiveTrue(Long personId);

    List<ProfessionalSpecialty> findByPersonId(Long personId);

    List<ProfessionalSpecialty> findBySpecialty_Id(Long specialtyId);
}