package com.ppa.perfildeaprendizado.data.model;

import java.io.Serializable;

public class Estilo implements Serializable {

    private Long id;
    private String nome;
    private String caracteristicas;
    private String sugestoes;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public String getSugestoes() {
        return sugestoes;
    }
    public void setSugestoes(String sugestoes) {
        this.sugestoes = sugestoes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estilo other = (Estilo) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Estilo{" +
                "  id=" + id +
                ", nome=" + nome + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", sugestoes='" + sugestoes + '\'' +
                '}';
    }
}
