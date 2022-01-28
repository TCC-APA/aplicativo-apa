package com.ppa.perfildeaprendizado.data;

import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.data.model.RangePontuacaoClassificacao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DetalhesEstilosVO implements Serializable {
    List<Estilo> estilosPredominantes;
    List<Estilo> estilosNaoPredominantes;
    List<Estilo> estiloMaisPredominante;
    Map<Long, RangePontuacaoClassificacao> idEstiloRange;

    public List<Estilo> getEstilosPredominantes() {
        return estilosPredominantes;
    }

    public void setEstilosPredominantes(List<Estilo> estilosPredominantes) {
        this.estilosPredominantes = estilosPredominantes;
    }

    public List<Estilo> getEstilosNaoPredominantes() {
        return estilosNaoPredominantes;
    }

    public void setEstilosNaoPredominantes(List<Estilo> estilosNaoPredominantes) {
        this.estilosNaoPredominantes = estilosNaoPredominantes;
    }

    public Map<Long, RangePontuacaoClassificacao> getIdEstiloRange() {
        return idEstiloRange;
    }

    public void setIdEstiloRange(Map<Long, RangePontuacaoClassificacao> idEstiloRange) {
        this.idEstiloRange = idEstiloRange;
    }

    public List<Estilo> getEstiloMaisPredominante() {
        return estiloMaisPredominante;
    }

    public void setEstiloMaisPredominante(List<Estilo> estiloMaisPredominante) {
        this.estiloMaisPredominante = estiloMaisPredominante;
    }
}
