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

import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationAnswerDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationAnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation-answer")
@RequiredArgsConstructor
@Validated
public class EvaluationAnswerController {

    private final EvaluationAnswerService evaluationAnswerService;

    @GetMapping
    public ResponseEntity<List<EvaluationAnswerDTO>> findAll() {
        return ResponseEntity.ok(evaluationAnswerService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationAnswerDTO>> findAllActive() {
        return ResponseEntity.ok(evaluationAnswerService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationAnswerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationAnswerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EvaluationAnswerDTO> save(@Valid @RequestBody EvaluationAnswerDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationAnswerService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationAnswerDTO> update(@PathVariable Long id,
            @Valid @RequestBody EvaluationAnswerDTO dto) {
        return ResponseEntity.ok(evaluationAnswerService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        evaluationAnswerService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
