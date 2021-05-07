package com.ppa.perfildeaprendizado.data.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Aluno implements Serializable {

    private Integer id;
    private String matricula;
    private String nome;
    private Integer idade;
    private String genero;
    private String senha;

    public Aluno(){

    }

    public Aluno(String matricula, String nome, Integer idade, String genero, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
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
                ", idade=" + idade +
                ", genero='" + genero + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
