package model;

public class Classificacao {
    private int classId;
    private Subclassificacao subclass;
    private String classNome;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public Subclassificacao getSubclass() {
        return subclass;
    }

    public void setSubclass(Subclassificacao subclass) {
        this.subclass = subclass;
    }

    public String getClassNome() {
        return classNome;
    }

    public void setClassNome(String classNome) {
        this.classNome = classNome;
    }
}
