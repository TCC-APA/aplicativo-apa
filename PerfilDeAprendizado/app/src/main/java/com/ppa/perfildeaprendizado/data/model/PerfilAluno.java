package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class PerfilAluno implements Serializable {

    private Long idPerfil;
    private String matriculaAluno;
    private String nomeAluno;
    private Long idAluno;
    private List<Estilo> estilos;
    private Date dataRealizado;
    private String nomeQuestionario;
    private Long idQuestionario;
    private Map<String, Long> pontuacaoPorEstilo;

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Date getDataRealizado() {
        return dataRealizado;
    }

    public void setDataRealizado(Date dataRealizado) {
        this.dataRealizado = dataRealizado;
    }

    public String getNomeQuestionario() {
        return nomeQuestionario;
    }

    public void setNomeQuestionario(String nomeQuestionario) {
        this.nomeQuestionario = nomeQuestionario;
    }

    public Long getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public Map<String, Long> getPontuacaoPorEstilo() {
        return pontuacaoPorEstilo;
    }

    public void setPontuacaoPorEstilo(Map<String, Long> pontuacaoPorEstilo) {
        this.pontuacaoPorEstilo = pontuacaoPorEstilo;
    }

    public List<Estilo> getEstilos() {
        return estilos;
    }

    public void setEstilos(List<Estilo> estilos) {
        this.estilos = estilos;
    }

    public void addEstilo(Estilo estilo) {

        if(estilo != null) {
            if(this.estilos == null)
                this.estilos = new ArrayList<Estilo>();
        }
        this.estilos.add(estilo);
    }

    @NonNull
    @Override
    public String toString() {
        return "PerfilAluno{" +
                "  idPerfil=" + idPerfil +
                ", matriculaAluno='" + matriculaAluno + '\'' +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", idAluno=" + idAluno +
                ", estilos=" + estilos.toString() +
                ", dataRealizado='" + dataRealizado + '\'' +
                ", nomeQuestionario='" + nomeQuestionario + '\'' +
                ", idQuestionario=" + idQuestionario +
                ", pontuacaoPorEstilo=" + pontuacaoPorEstilo.toString() +
                '}';
    }
}
