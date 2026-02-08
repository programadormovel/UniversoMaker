package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Classification;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    List<Classification> findByIsActiveTrue();
}