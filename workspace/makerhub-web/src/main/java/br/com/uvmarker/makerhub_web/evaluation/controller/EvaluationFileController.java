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

import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationFileDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation-file")
@RequiredArgsConstructor
@Validated
public class EvaluationFileController {

    private final EvaluationFileService evaluationFileService;

    @GetMapping
    public ResponseEntity<List<EvaluationFileDTO>> findAll() {
        return ResponseEntity.ok(evaluationFileService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationFileDTO>> findAllActive() {
        return ResponseEntity.ok(evaluationFileService.findAllActive());
    }

    @GetMapping("/evaluation/{evaluationId}")
    public ResponseEntity<List<EvaluationFileDTO>> findAllActiveByEvaluationId(@PathVariable Long evaluationId) {
        return ResponseEntity.ok(evaluationFileService.findAllActiveByEvaluationId(evaluationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationFileDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationFileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EvaluationFileDTO> save(@Valid @RequestBody EvaluationFileDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationFileService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationFileDTO> update(@PathVariable Long id, @Valid @RequestBody EvaluationFileDTO dto) {
        return ResponseEntity.ok(evaluationFileService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        evaluationFileService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
