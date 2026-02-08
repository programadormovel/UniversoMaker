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

import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationQuestionDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation-question")
@RequiredArgsConstructor
@Validated
public class EvaluationQuestionController {

    private final EvaluationQuestionService evaluationQuestionService;

    @GetMapping
    public ResponseEntity<List<EvaluationQuestionDTO>> findAll() {
        return ResponseEntity.ok(evaluationQuestionService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationQuestionDTO>> findAllActive() {
        return ResponseEntity.ok(evaluationQuestionService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationQuestionDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationQuestionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EvaluationQuestionDTO> save(@Valid @RequestBody EvaluationQuestionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationQuestionService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationQuestionDTO> update(@PathVariable Long id,
            @Valid @RequestBody EvaluationQuestionDTO dto) {
        return ResponseEntity.ok(evaluationQuestionService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        evaluationQuestionService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
