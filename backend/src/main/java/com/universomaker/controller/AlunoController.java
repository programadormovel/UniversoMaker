package com.universomaker.controller;

import com.universomaker.entity.Aluno;
import com.universomaker.repository.AlunoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos", description = "Gestão de alunos")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    @Operation(summary = "Listar alunos", description = "Lista todos os alunos ativos")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta')")
    public List<Aluno> listarAlunos() {
        return alunoRepository.findByAtivoTrue();
    }

    @PostMapping
    @Operation(summary = "Cadastrar aluno", description = "Cadastra novo aluno")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta')")
    public Aluno criarAluno(@Valid @RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno", description = "Busca aluno por ID")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta') or hasRole('Familia')")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable UUID id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza dados do aluno")
    @PreAuthorize("hasRole('Administrador') or hasRole('Terapeuta')")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable UUID id, @Valid @RequestBody Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoAtualizado.getNome());
                    aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
                    aluno.setCpf(alunoAtualizado.getCpf());
                    aluno.setHistoricoEscolar(alunoAtualizado.getHistoricoEscolar());
                    aluno.setHistoricoClinico(alunoAtualizado.getHistoricoClinico());
                    return ResponseEntity.ok(alunoRepository.save(aluno));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}