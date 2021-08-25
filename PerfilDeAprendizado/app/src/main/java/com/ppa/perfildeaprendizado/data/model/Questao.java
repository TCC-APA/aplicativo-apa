package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;

public class Questao implements Serializable {

    private Long idQuestao;
    private String texto;

    //Relacionado ao índice da lista de estilos no Questionario
    private String estiloKey;

    public Long getIdQuestao() {
        return idQuestao;
    }
    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getEstiloKey() {
        return estiloKey;
    }
    public void setEstiloKey(String estiloKey) {
        this.estiloKey = estiloKey;
    }
    @Override
    public String toString() {
        return "Questao{" +
                " idQuestao=" + idQuestao +
                ", texto='" + texto + '\'' +
                ", estiloKey='" + estiloKey + "'}";
    }
}
