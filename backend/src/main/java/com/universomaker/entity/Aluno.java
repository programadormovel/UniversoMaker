package com.universomaker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;

    @NotBlank
    @Column(length = 100)
    private String nome;

    @NotNull
    @Column(name = "DataNascimento")
    private LocalDate dataNascimento;

    @Column(length = 14, unique = true)
    private String cpf;

    private String foto;

    @Column(name = "HistoricoEscolar", columnDefinition = "NVARCHAR(MAX)")
    private String historicoEscolar;

    @Column(name = "HistoricoClinico", columnDefinition = "NVARCHAR(MAX)")
    private String historicoClinico;

    private Boolean ativo = true;

    @Column(name = "DataCriacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "DataAtualizacao")
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public String getHistoricoEscolar() { return historicoEscolar; }
    public void setHistoricoEscolar(String historicoEscolar) { this.historicoEscolar = historicoEscolar; }
    public String getHistoricoClinico() { return historicoClinico; }
    public void setHistoricoClinico(String historicoClinico) { this.historicoClinico = historicoClinico; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}