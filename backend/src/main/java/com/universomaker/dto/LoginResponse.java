package com.universomaker.dto;

public class LoginResponse {
    private String token;
    private String nome;
    private String tipoUsuario;

    public LoginResponse(String token, String nome, String tipoUsuario) {
        this.token = token;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}