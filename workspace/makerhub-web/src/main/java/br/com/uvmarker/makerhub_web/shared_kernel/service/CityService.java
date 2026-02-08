package br.com.uvmarker.makerhub_web.shared_kernel.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.City;

public interface CityService {

    List<City> findAll();

    List<City> findAllActive();

    City findById(Long id);

    City save(City city);

    City inactivateById(Long id);
}
