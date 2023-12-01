package uel.bd.Bulbapedia.models;

public class Shiny {
    private int id_shiny;
    private int id_pokemon;
    private int egg;
    private int raid;
    private int wild;
    private String sprite;


    public Shiny() {
    }

    public Shiny(int id_shiny, int id_pokemon, int egg, int raid, int wild, String sprite) {
        this.id_shiny = id_shiny;
        this.id_pokemon = id_pokemon;
        this.egg = egg;
        this.raid = raid;
        this.wild = wild;
        this.sprite = sprite;
    }

    public int getId_shiny() {
        return id_shiny;
    }

    public void setId_shiny(int id_shiny) {
        this.id_shiny = id_shiny;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getEgg() {
        return egg;
    }

    public void setEgg(int egg) {
        this.egg = egg;
    }

    public int getRaid() {
        return raid;
    }

    public void setRaid(int raid) {
        this.raid = raid;
    }

    public int getWild() {
        return wild;
    }

    public void setWild(int wild) {
        this.wild = wild;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
