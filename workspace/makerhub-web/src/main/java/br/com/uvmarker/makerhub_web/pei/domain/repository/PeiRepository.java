package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;

@Repository
public interface PeiRepository extends JpaRepository<Pei, Long> {

    List<Pei> findByIsActiveTrue();

    List<Pei> findByStudentIdAndIsActiveTrue(Long studentId);

    List<Pei> findByProfessionalIdAndIsActiveTrue(Long professionalId);
    
}