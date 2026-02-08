package br.com.uvmarker.makerhub_web.professional.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ClassCouncil;
import br.com.uvmarker.makerhub_web.professional.domain.entity.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    List<Specialty> findByIsActiveTrue();

    Optional<Specialty> findByCouncil(ClassCouncil council);

}