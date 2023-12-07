package uel.bd.Bulbapedia.models;

public class IsOfType {
    private int id_pokemon;
    private int id_type;
    private int slot;


    public IsOfType() {
    }

    public IsOfType(int id_pokemon, int id_type, int slot) {
        this.id_pokemon = id_pokemon;
        this.id_type = id_type;
        this.slot = slot;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getSlot() { return slot; }

    public void setSlot(int slot) { this.slot = slot; }
}
