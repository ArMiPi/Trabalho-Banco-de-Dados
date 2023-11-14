package uel.bd.Bulbapedia.models;

public class BaseStats {
    private long idStats;
    private long idPokemon;
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;


    public BaseStats(long idStats, long idPokemon, int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.idStats = idStats;
        this.idPokemon = idPokemon;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
    }

    public long getIdStats() {
        return idStats;
    }

    public void setIdStats(long idStats) {
        this.idStats = idStats;
    }

    public long getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(long idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(int spAttack) {
        this.spAttack = spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(int spDefense) {
        this.spDefense = spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
