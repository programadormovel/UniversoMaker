
package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.FunctionType;


public interface FunctionTypeService {

    List<FunctionType> findAll();

    List<FunctionType> findAllActive();

    FunctionType findById(Long id);

    FunctionType save(FunctionType functionType);

    FunctionType inactivateById(Long id);
}
