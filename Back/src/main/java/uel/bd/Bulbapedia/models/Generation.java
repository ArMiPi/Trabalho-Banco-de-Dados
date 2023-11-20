package uel.bd.Bulbapedia.models;

public class Generation {
    private int idGeneration;
    private int genNumber;
    private String region;


    public Generation(int idGeneration, int genNumber, String region) {
        this.idGeneration = idGeneration;
        this.genNumber = genNumber;
        this.region = region;
    }

    public int getIdGeneration() {
        return idGeneration;
    }

    public void setIdGeneration(int idGeneration) {
        this.idGeneration = idGeneration;
    }

    public int getGenNumber() {
        return genNumber;
    }

    public void setGenNumber(int genNumber) {
        this.genNumber = genNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
