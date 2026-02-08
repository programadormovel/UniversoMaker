package br.com.uvmarker.makerhub_web.person.controller;

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

import br.com.uvmarker.makerhub_web.person.domain.entity.StudentFile;
import br.com.uvmarker.makerhub_web.person.dto.StudentFileDTO;
import br.com.uvmarker.makerhub_web.person.service.StudentFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student-file")
@RequiredArgsConstructor
@Validated
public class StudentFileController {

    private final StudentFileService studentFileService;

    @GetMapping
    public ResponseEntity<List<StudentFile>> findAll() {
        return ResponseEntity.ok(studentFileService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<StudentFileDTO>> findAllActive() {
        return ResponseEntity.ok(studentFileService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFileDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentFileService.findById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentFileDTO>> findByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentFileService.findByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<StudentFileDTO> save(@Valid @RequestBody StudentFileDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentFileService.save(dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<StudentFile> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(studentFileService.inactivateById(id));
    }
}
