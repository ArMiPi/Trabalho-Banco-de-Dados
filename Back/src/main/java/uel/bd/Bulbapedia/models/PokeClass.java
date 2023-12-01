package uel.bd.Bulbapedia.models;

public class PokeClass {
    private int idClass;
    private String type;


    public  PokeClass() {
    }

    public PokeClass(int idClass, String type) {
        this.idClass = idClass;
        this.type = type;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
