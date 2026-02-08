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

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationItem;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation-item")
@RequiredArgsConstructor
@Validated
public class EvaluationItemController {

    private final EvaluationItemService evaluationItemService;

    @GetMapping("/all")
    public ResponseEntity<List<EvaluationItem>> findAll() {
        return ResponseEntity.ok(evaluationItemService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationItem>> findAllActive() {
        return ResponseEntity.ok(evaluationItemService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationItem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationItemService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EvaluationItem> save(@Valid @RequestBody EvaluationItem evaluationItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationItemService.save(evaluationItem));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EvaluationItem> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationItemService.inactivateById(id));
    }
}
