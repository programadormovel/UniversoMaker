package br.com.uvmarker.makerhub_web.shared_kernel.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.City;
import br.com.uvmarker.makerhub_web.shared_kernel.domain.repository.CityRepository;
import br.com.uvmarker.makerhub_web.shared_kernel.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> findAllActive() {
        return cityRepository.findByIsActiveTrue();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));
    }

    @Override
    public City save(City city) {
        City cityToSave = City.builder()
                .cityName(city.getCityName())
                .uf(city.getUf())
                .ibgeNr(city.getIbgeNr())
                .cityImageUrl(city.getCityImageUrl())
                .cityFileUrl(city.getCityFileUrl())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return cityRepository.save(cityToSave);
    }


    @Override
    public City inactivateById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));
        city.setUpdatedAt(LocalDateTime.now());
        city.setIsActive(false);
        return cityRepository.save(city);
    }


}