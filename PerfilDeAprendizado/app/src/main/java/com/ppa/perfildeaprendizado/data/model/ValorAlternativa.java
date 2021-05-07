package com.ppa.perfildeaprendizado.data.model;

public class ValorAlternativa {

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
        return "ValorAlternativaDTO [valor=" + valor + ", textoAlternativa=" + textoAlternativa + "]";
    }

}
