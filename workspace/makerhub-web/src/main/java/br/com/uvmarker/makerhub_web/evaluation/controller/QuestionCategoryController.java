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

import br.com.uvmarker.makerhub_web.evaluation.dto.QuestionCategoryDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.QuestionCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/question-category")
@RequiredArgsConstructor
@Validated
public class QuestionCategoryController {

    private final QuestionCategoryService questionCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<QuestionCategoryDTO>> findAll() {
        return ResponseEntity.ok(questionCategoryService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<QuestionCategoryDTO>> findAllActive() {
        return ResponseEntity.ok(questionCategoryService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionCategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(questionCategoryService.findById(id));
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<QuestionCategoryDTO>> findByTypeId(@PathVariable Long typeId) {
        return ResponseEntity.ok(questionCategoryService.findByTypeId(typeId));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<QuestionCategoryDTO> save(@Valid @RequestBody QuestionCategoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionCategoryService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionCategoryDTO> update(@PathVariable Long id,
            @Valid @RequestBody QuestionCategoryDTO dto) {
        return ResponseEntity.ok(questionCategoryService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        questionCategoryService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
