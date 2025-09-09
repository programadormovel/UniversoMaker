package com.universomaker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PEI")
public class PEI {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AlunoId")
    private Aluno aluno;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CriadorId")
    private Usuario criador;

    private Integer versao = 1;

    @Enumerated(EnumType.STRING)
    private Status status = Status.Rascunho;

    @Column(name = "DataCriacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "DataAprovacao")
    private LocalDateTime dataAprovacao;

    public enum Status {
        Rascunho, Aprovado, Arquivado
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Usuario getCriador() { return criador; }
    public void setCriador(Usuario criador) { this.criador = criador; }
    public Integer getVersao() { return versao; }
    public void setVersao(Integer versao) { this.versao = versao; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public LocalDateTime getDataAprovacao() { return dataAprovacao; }
    public void setDataAprovacao(LocalDateTime dataAprovacao) { this.dataAprovacao = dataAprovacao; }
}