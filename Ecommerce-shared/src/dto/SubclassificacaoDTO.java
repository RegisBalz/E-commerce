package dto;

import java.io.Serializable;

public class SubclassificacaoDTO implements Serializable {
    private int subclassId;
    private String subclassNome;

    public int getSubclassId() {
        return subclassId;
    }

    public void setSubclassId(int subclassId) {
        this.subclassId = subclassId;
    }

    public String getSubclassNome() {
        return subclassNome;
    }

    public void setSubclassNome(String subclassNome) {
        this.subclassNome = subclassNome;
    }
}
