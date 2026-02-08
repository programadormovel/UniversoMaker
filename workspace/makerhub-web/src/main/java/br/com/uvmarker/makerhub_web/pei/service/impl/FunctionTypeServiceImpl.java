package br.com.uvmarker.makerhub_web.pei.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.pei.domain.entity.FunctionType;
import br.com.uvmarker.makerhub_web.pei.domain.repository.FunctionTypeRepository;
import br.com.uvmarker.makerhub_web.pei.service.FunctionTypeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FunctionTypeServiceImpl implements FunctionTypeService {

    private final FunctionTypeRepository functionTypeRepository;

    @Override
    public List<FunctionType> findAll() {
       return functionTypeRepository.findAll();
    }

    @Override
    public List<FunctionType> findAllActive() {
        return functionTypeRepository.findByIsActiveTrue();
    }

    @Override
    public FunctionType findById(Long id) {
        return functionTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conselho de classe não encontrado"));
    }

    @SuppressWarnings("null")
    @Override
    public FunctionType save(FunctionType functionType) {
        FunctionType functionTypeToSave = FunctionType.builder()
            .functionName(functionType.getFunctionName())
            .description(functionType.getDescription())
            .build();

        return functionTypeRepository.save(functionTypeToSave); 
    }

    @Override
    public FunctionType inactivateById(Long id) {
        @SuppressWarnings("null")
        FunctionType function = functionTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conselho de classe não encontrado"));
        
        function.setIsActive(false);
        return functionTypeRepository.save(function);
    }   
}
