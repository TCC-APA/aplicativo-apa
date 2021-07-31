package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Aluno implements Serializable {

    private Long id;
    private String matricula;
    private String nome;
    private Date dataNascimento;
    private String genero;
    private String senha;

    public Aluno(){

    }

    public Aluno(String matricula, String nome, Date dataNascimento, String genero, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "  nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento.toString() +
                ", genero='" + genero + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
