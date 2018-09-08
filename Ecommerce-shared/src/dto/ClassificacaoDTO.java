package dto;

import java.io.Serializable;

public class ClassificacaoDTO implements Serializable {
    private int classId;
    private SubclassificacaoDTO subclass;
    private String classNome;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public SubclassificacaoDTO getSubclass() {
        return subclass;
    }

    public void setSubclass(SubclassificacaoDTO subclass) {
        this.subclass = subclass;
    }

    public String getClassNome() {
        return classNome;
    }

    public void setClassNome(String classNome) {
        this.classNome = classNome;
    }
}
