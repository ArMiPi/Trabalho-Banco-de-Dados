package uel.bd.Bulbapedia.models;

public class PokemonGo {
    private int id_pokemon_go;
    private int id_pokemon;
    private boolean raid_exclusive;
    private int max_cp;
    private int buddy_distance;
    private int candy_to_evolve;


    public PokemonGo(int id_pokemon_go, int id_pokemon, boolean raid_exclusive, int max_cp, int buddy_distance, int candy_to_evolve) {
        this.id_pokemon_go = id_pokemon_go;
        this.id_pokemon = id_pokemon;
        this.raid_exclusive = raid_exclusive;
        this.max_cp = max_cp;
        this.buddy_distance = buddy_distance;
        this.candy_to_evolve = candy_to_evolve;
    }

    public int getId_pokemon_go() {
        return id_pokemon_go;
    }

    public void setId_pokemon_go(int id_pokemon_go) {
        this.id_pokemon_go = id_pokemon_go;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public boolean getRaid_exclusive() {
        return raid_exclusive;
    }

    public void setRaid_exclusive(boolean raid_exclusive) {
        this.raid_exclusive = raid_exclusive;
    }

    public int getMax_cp() {
        return max_cp;
    }

    public void setMax_cp(int max_cp) {
        this.max_cp = max_cp;
    }

    public int getBuddy_distance() {
        return buddy_distance;
    }

    public void setBuddy_distance(int buddy_distance) {
        this.buddy_distance = buddy_distance;
    }

    public int getCandy_to_evolve() {
        return candy_to_evolve;
    }

    public void setCandy_to_evolve(int candy_to_evolve) {
        this.candy_to_evolve = candy_to_evolve;
    }
}
