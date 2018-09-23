package dto;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private int categoriaId;
    private String categoria;

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
