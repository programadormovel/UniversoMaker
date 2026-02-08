package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.pei.domain.entity.FunctionType;

@Repository
public interface FunctionTypeRepository extends JpaRepository<FunctionType, Long> {

    List<FunctionType> findByIsActiveTrue();
}