package dto;

import java.io.Serializable;

public class ClassificacaoDTO implements Serializable {
    private int classId;
    private SubClassificacaoDTO subClassificacao;
    private String classificacao;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public SubClassificacaoDTO getSubClassificacao() {
        return subClassificacao;
    }

    public void setSubClassificacao(SubClassificacaoDTO subClassificacao) {
        this.subClassificacao = subClassificacao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}
