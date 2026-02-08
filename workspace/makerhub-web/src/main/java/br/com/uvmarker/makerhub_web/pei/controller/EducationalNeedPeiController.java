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

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeedPei;
import br.com.uvmarker.makerhub_web.pei.dto.EducationalNeedPeiDTO;
import br.com.uvmarker.makerhub_web.pei.service.EducationalNeedPeiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/educational-need-pei")
@RequiredArgsConstructor
@Validated
public class EducationalNeedPeiController {

    private final EducationalNeedPeiService educationalNeedPeiService;

    @GetMapping
    public ResponseEntity<List<EducationalNeedPei>> findAll() {
        return ResponseEntity.ok(educationalNeedPeiService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EducationalNeedPeiDTO>> findAllActive() {
        return ResponseEntity.ok(educationalNeedPeiService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalNeedPeiDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(educationalNeedPeiService.findById(id));
    }

    @GetMapping("/pei/{peiId}")
    public ResponseEntity<List<EducationalNeedPeiDTO>> findByPeiId(@PathVariable Long peiId) {
        return ResponseEntity.ok(educationalNeedPeiService.findByPeiId(peiId));
    }

    @PostMapping
    public ResponseEntity<EducationalNeedPeiDTO> save(@Valid @RequestBody EducationalNeedPeiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationalNeedPeiService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationalNeedPeiDTO> update(@PathVariable Long id,
            @Valid @RequestBody EducationalNeedPeiDTO dto) {
        return ResponseEntity.ok(educationalNeedPeiService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        educationalNeedPeiService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
