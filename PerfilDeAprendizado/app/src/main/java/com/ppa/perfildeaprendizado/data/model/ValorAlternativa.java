package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;

public class ValorAlternativa implements Serializable {

    private Integer valor;
    private String textoAlternativa;

    public Integer getValor() {
        return valor;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
    }
    public String getTextoAlternativa() {
        return textoAlternativa;
    }
    public void setTextoAlternativa(String textoAlternativa) {
        this.textoAlternativa = textoAlternativa;
    }

    @Override
    public String toString() {
        return "ValorAlternativa{" +
                "  valor=" + valor +
                ", textoAlternativa=" + textoAlternativa + '\'' +
                '}';
    }

}
