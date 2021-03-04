package com.ppa.perfildeaprendizado.data.model;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Aluno implements Serializable {

    private Integer id;
    private String matricula;
    private String nome;
    private String turma;
    private Integer idade;
    private String genero;
    private String senha;
    private Integer perfilAtivo;
    private Integer perfilReflexivo;
    private Integer perfilPragmatico;
    private Integer perfilTeorico;

    public Aluno(){

    }

    public Aluno(String matricula, String nome, String turma, Integer idade, String genero, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.turma = turma;
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

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
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

    public Integer getPerfilAtivo() {
        return perfilAtivo;
    }

    public void setPerfilAtivo(Integer perfilAtivo) {
        this.perfilAtivo = perfilAtivo;
    }

    public Integer getPerfilReflexivo() {
        return perfilReflexivo;
    }

    public void setPerfilReflexivo(Integer perfilReflexivo) {
        this.perfilReflexivo = perfilReflexivo;
    }

    public Integer getPerfilPragmatico() {
        return perfilPragmatico;
    }

    public void setPerfilPragmatico(Integer perfilPragmatico) {
        this.perfilPragmatico = perfilPragmatico;
    }

    public Integer getPerfilTeorico() {
        return perfilTeorico;
    }

    public void setPerfilTeorico(Integer perfilTeorico) {
        this.perfilTeorico = perfilTeorico;
    }

    public boolean temPerfis(){
        if(this.perfilAtivo == null || this.perfilPragmatico == null || this.perfilReflexivo == null || this.perfilTeorico == null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                ", matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", turma='" + turma + '\'' +
                ", idade=" + idade +
                ", genero='" + genero + '\'' +
                '}';
    }
}
