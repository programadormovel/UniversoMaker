package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.uvmarker.makerhub_web.pei.domain.entity.PeiFile;

@Repository
public interface PeiFileRepository extends JpaRepository<PeiFile, Long> {

    List<PeiFile> findByIsActiveTrue();

    List<PeiFile> findByPei_IdAndIsActiveTrue(Long peiId);
}