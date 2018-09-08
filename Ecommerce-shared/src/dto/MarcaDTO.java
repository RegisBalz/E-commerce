package dto;

import java.io.Serializable;

public class MarcaDTO implements Serializable {
    private int marcaId;
    private String marcaNome;

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public String getMarcaNome() {
        return marcaNome;
    }

    public void setMarcaNome(String marcaNome) {
        this.marcaNome = marcaNome;
    }
}
