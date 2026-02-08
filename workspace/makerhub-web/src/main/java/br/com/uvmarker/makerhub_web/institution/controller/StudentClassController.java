package br.com.uvmarker.makerhub_web.institution.controller;

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

import br.com.uvmarker.makerhub_web.institution.domain.entity.StudentClass;
import br.com.uvmarker.makerhub_web.institution.dto.StudentClassDTO;
import br.com.uvmarker.makerhub_web.institution.service.StudentClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student-class")
@RequiredArgsConstructor
@Validated
public class StudentClassController {

    private final StudentClassService studentClassService;

    @GetMapping
    public ResponseEntity<List<StudentClass>> findAll() {
        return ResponseEntity.ok(studentClassService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<StudentClassDTO>> findAllActive() {
        return ResponseEntity.ok(studentClassService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentClassDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentClassService.findById(id));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<StudentClassDTO>> findByClassId(@PathVariable Long classId) {
        return ResponseEntity.ok(studentClassService.findByClassId(classId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentClassDTO>> findByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentClassService.findByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<StudentClassDTO> save(@Valid @RequestBody StudentClassDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentClassService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentClassDTO> update(@PathVariable Long id, @Valid @RequestBody StudentClassDTO dto) {
        return ResponseEntity.ok(studentClassService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<StudentClass> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(studentClassService.inactivateById(id));
    }
}
