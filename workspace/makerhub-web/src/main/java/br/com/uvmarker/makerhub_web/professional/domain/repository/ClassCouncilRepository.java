package br.com.uvmarker.makerhub_web.professional.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ClassCouncil;

@Repository
public interface ClassCouncilRepository extends JpaRepository<ClassCouncil, Long> {

    List<ClassCouncil> findByIsActiveTrue();
}