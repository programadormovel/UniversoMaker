package br.com.uvmarker.makerhub_web.pei.controller;

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

import br.com.uvmarker.makerhub_web.pei.domain.entity.EvaluationFunctionPei;
import br.com.uvmarker.makerhub_web.pei.dto.EvaluationFunctionPeiDTO;
import br.com.uvmarker.makerhub_web.pei.service.EvaluationFunctionPeiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluation-function-pei")
@RequiredArgsConstructor
@Validated
public class EvaluationFunctionPeiController {

    private final EvaluationFunctionPeiService evaluationFunctionPeiService;

    @GetMapping
    public ResponseEntity<List<EvaluationFunctionPei>> findAll() {
        return ResponseEntity.ok(evaluationFunctionPeiService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvaluationFunctionPeiDTO>> findAllActive() {
        return ResponseEntity.ok(evaluationFunctionPeiService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationFunctionPeiDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationFunctionPeiService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EvaluationFunctionPeiDTO> save(@Valid @RequestBody EvaluationFunctionPeiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationFunctionPeiService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationFunctionPeiDTO> update(@PathVariable Long id,
            @Valid @RequestBody EvaluationFunctionPeiDTO dto) {
        return ResponseEntity.ok(evaluationFunctionPeiService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        evaluationFunctionPeiService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
