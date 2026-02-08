package br.com.uvmarker.makerhub_web.pei.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.pei.domain.entity.FunctionType;
import br.com.uvmarker.makerhub_web.pei.service.FunctionTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/function-type")
@RequiredArgsConstructor
@Validated
public class FunctionTypeController {

    private final FunctionTypeService functionTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<FunctionType>> findAll() {
        return ResponseEntity.ok(functionTypeService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<FunctionType>> findAllActive() {
        return ResponseEntity.ok(functionTypeService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FunctionType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(functionTypeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FunctionType> save(@Valid @RequestBody FunctionType functionType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(functionTypeService.save(functionType));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FunctionType> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(functionTypeService.inactivateById(id));
    }
}
