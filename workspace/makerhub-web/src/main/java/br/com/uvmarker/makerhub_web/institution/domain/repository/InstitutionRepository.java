package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    List<Institution> findByIsActiveTrue();

    List<Institution> findByType_IdAndIsActiveTrue(Long typeId);

    List<Institution> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    Optional<Institution> findByUserIdAndIsActiveTrue(Long id);

}