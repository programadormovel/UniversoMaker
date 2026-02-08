package br.com.uvmarker.makerhub_web.evaluation.controller;

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

import br.com.uvmarker.makerhub_web.evaluation.dto.ClinicalHistoryDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.ClinicalHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clinical-history")
@RequiredArgsConstructor
@Validated
public class ClinicalHistoryController {

    private final ClinicalHistoryService clinicalHistoryService;

    @GetMapping
    public ResponseEntity<List<ClinicalHistoryDTO>> findAll() {
        return ResponseEntity.ok(clinicalHistoryService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ClinicalHistoryDTO>> findAllActive() {
        return ResponseEntity.ok(clinicalHistoryService.findAllActive());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ClinicalHistoryDTO>> findByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(clinicalHistoryService.findByStudentId(studentId));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<ClinicalHistoryDTO>> findByProfessionalId(@PathVariable Long professionalId) {
        return ResponseEntity.ok(clinicalHistoryService.findByProfessionalId(professionalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalHistoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clinicalHistoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClinicalHistoryDTO> save(@Valid @RequestBody ClinicalHistoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicalHistoryService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalHistoryDTO> update(@PathVariable Long id,
            @Valid @RequestBody ClinicalHistoryDTO dto) {
        return ResponseEntity.ok(clinicalHistoryService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        clinicalHistoryService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
