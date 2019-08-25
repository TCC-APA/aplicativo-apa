package com.ppa.perfildeaprendizado.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Aluno {

    private String id;
    private String nome;
    private String email;
    private String turma;
    private int idade;
    private String genero;

    public Aluno(String id, String nome, String email, String turma, int idade, String genero) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.turma = turma;
        this.idade = idade;
        this.genero = genero;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTurma() {
        return turma;
    }

    public int getIdade() {
        return idade;
    }

    public String getGenero() {
        return genero;
    }
}
