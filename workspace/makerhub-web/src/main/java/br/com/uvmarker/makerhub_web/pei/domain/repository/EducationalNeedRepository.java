package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeed;

@Repository
public interface EducationalNeedRepository extends JpaRepository<EducationalNeed, Long> {

    List<EducationalNeed> findByIsActiveTrue();
}