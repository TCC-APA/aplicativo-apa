package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;
import java.util.Map;

import androidx.annotation.NonNull;

public class PerfilRespostas implements Serializable {
    private String matriculaAluno;
    private String dataRealizado;
    private Long idQuestionario;
    private Map<String, Long> pontuacaoPorEstilo; //Key: Id Estilo, Value: pontuacao

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getDataRealizado() {
        return dataRealizado;
    }

    public void setDataRealizado(String dataRealizado) {
        this.dataRealizado = dataRealizado;
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

    @NonNull
    @Override
    public String toString() {
        return "PerfilRespostas{" +
                "  matriculaAluno='" + matriculaAluno + '\'' +
                ", dataRealizado='" + dataRealizado + '\'' +
                ", idQuestionario=" + idQuestionario +
                ", pontuacaoPorEstilo='" + pontuacaoPorEstilo.toString() + '\'' +
                '}';
    }
}
