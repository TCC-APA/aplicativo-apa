package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class Questionario implements Serializable {

    private Long id;
    private String nome;
    private List<Questao> questoes = new ArrayList<>();
    private List<ValorAlternativa> valoresAlternativas = new ArrayList<>();
    //Estilos com um indice para facilitar a transformacao em JSON
    private Map<String, Estilo> estilosIndexados = new HashMap<>();

    public Questionario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public List<ValorAlternativa> getValoresAlternativas() {
        return valoresAlternativas;
    }

    public void setValoresAlternativas(List<ValorAlternativa> valoresAlternativas) {
        this.valoresAlternativas = valoresAlternativas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Estilo> getEstilosIndexados() {
        return estilosIndexados;
    }

    public void setEstilosIndexados(Map<String, Estilo> estilosIndexados) {
        this.estilosIndexados = estilosIndexados;
    }

    public void putEstilosIndexados(String key, Estilo value) {
        if(this.estilosIndexados == null)
            this.estilosIndexados = new HashMap<String, Estilo>();

        this.estilosIndexados.put(key, value);
    }

    public void addQuestao(Questao questao) {
        if(this.questoes == null)
            this.questoes = new ArrayList<Questao>();

        this.questoes.add(questao);
    }

    public void addValoresAlternativas(ValorAlternativa valorAlternativa) {
        if(this.valoresAlternativas == null)
            this.valoresAlternativas = new ArrayList<ValorAlternativa>();

        this.valoresAlternativas.add(valorAlternativa);
    }

    @NonNull
    @Override
    public String toString() {
        return "QuestionarioDTO{" +
                "  estilosIndexados='" + estilosIndexados + '\'' +
                "  id='" + id + '\'' +
                ", nome=" + nome +
                ", questoes='" + questoes + '\'' +
                ", valoresAlternativas='" + valoresAlternativas + '\'' +
                "}";

    }
}
