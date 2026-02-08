package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeedPei;

@Repository
public interface EducationalNeedPeiRepository extends JpaRepository<EducationalNeedPei, Long> {

    List<EducationalNeedPei> findByIsActiveTrue();

    List<EducationalNeedPei> findByPei_IdAndIsActiveTrue(Long peiId);

    List<EducationalNeedPei> findByProfessionalIdAndIsActiveTrue(Long professionalId);
}