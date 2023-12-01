package uel.bd.Bulbapedia.models;

public class Ability {
    private int idAbility;
    private String name;


    public Ability() {
    }

    public Ability(int idAbility, String name) {
        this.idAbility = idAbility;
        this.name = name;
    }

    public int getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(int idAbility) {
        this.idAbility = idAbility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
