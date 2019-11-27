package com.ppa.perfildeaprendizado.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Aluno {

    private Integer id;
    private String cpf;
    private String matricula;
    private String nome;
    private String email;
    private String turma;
    private Integer idade;
    private String genero;
    private String senha;

    public Aluno(String cpf, String matricula, String nome, String email, String turma, Integer idade, String genero, String senha) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Aluno{" +
                "cpf='" + cpf + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", turma='" + turma + '\'' +
                ", idade=" + idade +
                ", genero='" + genero + '\'' +
                '}';
    }
}
