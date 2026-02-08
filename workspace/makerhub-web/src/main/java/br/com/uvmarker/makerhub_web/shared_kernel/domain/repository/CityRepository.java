package br.com.uvmarker.makerhub_web.shared_kernel.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByIsActiveTrue();
}