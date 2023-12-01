package uel.bd.Bulbapedia.models;

public class TypeRelations {
    private int id_attacker;
    private int id_defender;
    private float multiplier;


    public TypeRelations() {
    }

    public TypeRelations(int id_attacker, int id_defender, float multiplier) {
        this.id_attacker = id_attacker;
        this.id_defender = id_defender;
        this.multiplier = multiplier;
    }

    public int getId_attacker() {
        return id_attacker;
    }

    public void setId_attacker(int id_attacker) {
        this.id_attacker = id_attacker;
    }

    public int getId_defender() {
        return id_defender;
    }

    public void setId_defender(int id_defender) {
        this.id_defender = id_defender;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}
