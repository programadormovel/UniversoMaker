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

import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation")
@RequiredArgsConstructor
@Validated
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping
    public ResponseEntity<List<EvaluationDTO>> findAll() {
        return ResponseEntity.ok(evaluationService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationDTO>> findAllActive() {
        return ResponseEntity.ok(evaluationService.findAllActive());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EvaluationDTO>> findByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(evaluationService.findByStudentId(studentId));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<EvaluationDTO>> findByProfessionalId(@PathVariable Long professionalId) {
        return ResponseEntity.ok(evaluationService.findByProfessionalId(professionalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EvaluationDTO> save(@Valid @RequestBody EvaluationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationDTO> update(@PathVariable Long id, @Valid @RequestBody EvaluationDTO dto) {
        return ResponseEntity.ok(evaluationService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        evaluationService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
