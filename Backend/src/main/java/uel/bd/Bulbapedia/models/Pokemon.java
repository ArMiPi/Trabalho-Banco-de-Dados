package uel.bd.Bulbapedia.models;

public class Pokemon {
    private int idPokedex;
    private String name;
    private int height;
    private int weight;
    private int captureRate;
    private char rarity;
    private String sprite;
    private int idGeneration;
    private int evolvesFrom;


    public Pokemon(int idPokedex, String name, int height, int weight, int captureRate, char rarity, String sprite) {
        this.idPokedex = idPokedex;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.captureRate = captureRate;
        this.rarity = rarity;
        this.sprite = sprite;
    }

    public int getIdPokedex() {
        return idPokedex;
    }

    public void setIdPokedex(int idPokedex) {
        this.idPokedex = idPokedex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public char getRarity() {
        return rarity;
    }

    public void setRarity(char rarity) {
        this.rarity = rarity;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getIdGeneration() {
        return idGeneration;
    }

    public void setIdGeneration(int idGeneration) {
        this.idGeneration = idGeneration;
    }

    public int getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(int evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }
}
