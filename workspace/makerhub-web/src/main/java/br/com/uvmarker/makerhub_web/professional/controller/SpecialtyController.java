package br.com.uvmarker.makerhub_web.professional.controller;

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

import br.com.uvmarker.makerhub_web.professional.domain.entity.Specialty;
import br.com.uvmarker.makerhub_web.professional.dto.SpecialtyDTO;
import br.com.uvmarker.makerhub_web.professional.service.SpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/specialty")
@RequiredArgsConstructor
@Validated
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping
    public ResponseEntity<List<Specialty>> findAll() {
        return ResponseEntity.ok(specialtyService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SpecialtyDTO>> findAllActive() {
        return ResponseEntity.ok(specialtyService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(specialtyService.findById(id));
    }

    @GetMapping("/council/{councilId}")
    public ResponseEntity<SpecialtyDTO> findByCouncilId(@PathVariable Long councilId) {
        return ResponseEntity.ok(specialtyService.findByCouncilId(councilId));
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> save(@Valid @RequestBody SpecialtyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specialtyService.save(dto));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Specialty> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(specialtyService.inactivateById(id));
    }
}
