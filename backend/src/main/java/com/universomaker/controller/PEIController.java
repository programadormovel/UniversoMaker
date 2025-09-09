package com.universomaker.controller;

import com.universomaker.entity.PEI;
import com.universomaker.repository.PEIRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pei")
@Tag(name = "PEI", description = "Plano Educacional Individualizado")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PEIController {
    @Autowired
    private PEIRepository peiRepository;

    @GetMapping("/aluno/{alunoId}")
    @Operation(summary = "Listar PEIs do aluno", description = "Lista todos os PEIs de um aluno")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta') or hasRole('Familia')")
    public List<PEI> listarPEIsPorAluno(@PathVariable UUID alunoId) {
        return peiRepository.findByAlunoId(alunoId);
    }

    @PostMapping
    @Operation(summary = "Criar PEI", description = "Cria novo PEI")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta')")
    public PEI criarPEI(@Valid @RequestBody PEI pei) {
        return peiRepository.save(pei);
    }

    @PutMapping("/{id}/aprovar")
    @Operation(summary = "Aprovar PEI", description = "Aprova um PEI")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PEI> aprovarPEI(@PathVariable UUID id) {
        return peiRepository.findById(id)
                .map(pei -> {
                    pei.setStatus(PEI.Status.Aprovado);
                    pei.setDataAprovacao(LocalDateTime.now());
                    return ResponseEntity.ok(peiRepository.save(pei));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar PEI", description = "Busca PEI por ID")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta') or hasRole('Familia')")
    public ResponseEntity<PEI> buscarPEI(@PathVariable UUID id) {
        return peiRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}