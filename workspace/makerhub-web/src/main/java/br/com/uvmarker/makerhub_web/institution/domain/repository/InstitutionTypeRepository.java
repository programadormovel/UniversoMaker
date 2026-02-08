package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionType;

@Repository
public interface InstitutionTypeRepository extends JpaRepository<InstitutionType, Long> {

    List<InstitutionType> findByIsActiveTrue();
}