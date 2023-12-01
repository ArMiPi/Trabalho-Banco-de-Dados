package uel.bd.Bulbapedia.models;

public class BaseGoStats {
    private int id_stats_go;
    private int id_pokemon;
    private int stamina;
    private int defense;
    private int attack;


    public BaseGoStats() {
    }

    public BaseGoStats(int id_stats_go, int id_pokemon, int stamina, int defense, int attack) {
        this.id_stats_go = id_stats_go;
        this.id_pokemon = id_pokemon;
        this.stamina = stamina;
        this.defense = defense;
        this.attack = attack;
    }

    public int getId_stats_go() {
        return id_stats_go;
    }

    public void setId_stats_go(int id_stats_go) {
        this.id_stats_go = id_stats_go;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
