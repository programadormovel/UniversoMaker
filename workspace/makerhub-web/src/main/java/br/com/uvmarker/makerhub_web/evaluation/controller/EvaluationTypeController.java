package br.com.uvmarker.makerhub_web.evaluation.controller;

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

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation-type")
@RequiredArgsConstructor
@Validated
public class EvaluationTypeController {

    private final EvaluationTypeService evaluationTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<EvaluationType>> findAll() {
        return ResponseEntity.ok(evaluationTypeService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationType>> findAllActive() {
        return ResponseEntity.ok(evaluationTypeService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationTypeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EvaluationType> save(@Valid @RequestBody EvaluationType evaluationType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationTypeService.save(evaluationType));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EvaluationType> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationTypeService.inactivateById(id));
    }
}
