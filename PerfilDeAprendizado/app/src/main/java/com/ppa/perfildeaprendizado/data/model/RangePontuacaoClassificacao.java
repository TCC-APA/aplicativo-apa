package com.ppa.perfildeaprendizado.data.model;

public class RangePontuacaoClassificacao {

    private Integer minValue;
    private Integer maxValue;
    private String classificacao;
    //Relacionado ao índice da lista de estilos no QuestionarioDTO
    private String estiloKey;

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getEstiloKey() {
        return estiloKey;
    }

    public void setEstiloKey(String estiloKey) {
        this.estiloKey = estiloKey;
    }


}
