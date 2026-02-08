package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.ClinicalHistory;

@Repository
public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Long> {

    List<ClinicalHistory> findByIsActiveTrue();

    List<ClinicalHistory> findByStudentIdAndIsActiveTrue(Long studentId);

    List<ClinicalHistory> findByProfessionalIdAndIsActiveTrue(Long professionalId);

}
