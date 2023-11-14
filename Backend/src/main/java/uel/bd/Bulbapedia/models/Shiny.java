package uel.bd.Bulbapedia.models;

public class Shiny {
    private int id_shiny;
    private int id_pokemon;
    private boolean egg;
    private boolean raid;
    private boolean wild;
    private String sprite;


    public Shiny(int id_shiny, int id_pokemon, boolean egg, boolean raid, boolean wild, String sprite) {
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

    public boolean getEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public boolean getRaid() {
        return raid;
    }

    public void setRaid(boolean raid) {
        this.raid = raid;
    }

    public boolean getWild() {
        return wild;
    }

    public void setWild(boolean wild) {
        this.wild = wild;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
