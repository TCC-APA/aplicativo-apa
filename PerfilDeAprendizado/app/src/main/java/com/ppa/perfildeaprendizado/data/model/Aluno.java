package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Aluno implements Serializable {

    private Long id;
    private String matricula;
    private String nome;
    private String dataNascimento;
    private Long idade;
    private String genero;
    private String senha;

    public Aluno(){

    }

    public Aluno(String matricula, String nome, String dataNascimento, String genero, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.senha = senha;
        this.idade = null;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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
                ", dataNascimento='" + dataNascimento + '\'' +
                ", genero='" + genero + '\'' +
                ", matricula='" + matricula + '\'' +
                ", idade='" + idade + '\'' +
                '}';
    }
}
