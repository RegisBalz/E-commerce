package dto;

import java.io.Serializable;

public class SubClassificacaoDTO implements Serializable {
    private int subClassId;
    private String subClassificacao;

    public int getSubClassId() {
        return subClassId;
    }

    public void setSubClassId(int subClassId) {
        this.subClassId = subClassId;
    }

    public String getSubClassificacao() {
        return subClassificacao;
    }

    public void setSubClassificacao(String subClassificacao) {
        this.subClassificacao = subClassificacao;
    }
    
    @Override
    public String toString() {
        return subClassificacao;
    }
}
