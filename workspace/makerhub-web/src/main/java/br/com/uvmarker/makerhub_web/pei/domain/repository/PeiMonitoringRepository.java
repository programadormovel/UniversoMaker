package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.pei.domain.entity.PeiMonitoring;

@Repository
public interface PeiMonitoringRepository extends JpaRepository<PeiMonitoring, Long> {

    List<PeiMonitoring> findByIsActiveTrue();

    List<PeiMonitoring> findByPei_IdAndIsActiveTrue(Long peiId);

    List<PeiMonitoring> findByProfessionalIdAndIsActiveTrue(Long professionalId);
}