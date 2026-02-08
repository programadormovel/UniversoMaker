package br.com.uvmarker.makerhub_web.institution.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionClass;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionClassDTO;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/institution-class")
@RequiredArgsConstructor
@Validated
public class InstitutionClassController {

    private final InstitutionClassService institutionClassService;

    @GetMapping
    public ResponseEntity<List<InstitutionClass>> findAll() {
        return ResponseEntity.ok(institutionClassService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<InstitutionClassDTO>> findAllActive() {
        return ResponseEntity.ok(institutionClassService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionClassDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionClassService.findById(id));
    }

    @GetMapping("/institution/{institutionId}")
    public ResponseEntity<List<InstitutionClassDTO>> findByInstitutionId(@PathVariable Long institutionId) {
        return ResponseEntity.ok(institutionClassService.findByInstitutionId(institutionId));
    }

    @PostMapping
    public ResponseEntity<InstitutionClassDTO> save(@Valid @RequestBody InstitutionClassDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionClassService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionClassDTO> update(@PathVariable Long id,
            @Valid @RequestBody InstitutionClassDTO dto) {
        return ResponseEntity.ok(institutionClassService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<InstitutionClass> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionClassService.inactivateById(id));
    }
}
