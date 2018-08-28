package dto;

import java.io.Serializable;

public class CorDTO implements Serializable {
    private int corId;
    private String cor;

    public int getCorId() {
        return corId;
    }

    public void setCorId(int corId) {
        this.corId = corId;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
