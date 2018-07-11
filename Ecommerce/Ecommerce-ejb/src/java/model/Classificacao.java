package model;

public class Classificacao {
    private int classId;
    private SubClassificacao subClassificacao;
    private String classificacao;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public SubClassificacao getSubClassificacao() {
        return subClassificacao;
    }

    public void setSubClassificacao(SubClassificacao subClassificacao) {
        this.subClassificacao = subClassificacao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}
