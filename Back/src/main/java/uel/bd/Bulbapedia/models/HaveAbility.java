package uel.bd.Bulbapedia.models;

public class HaveAbility {
    private int id_pokemon;
    private int id_ability;
    private int is_hidden;


    public HaveAbility() {
        this.id_pokemon = 0;
        this.id_ability = 0;
        this.is_hidden = 0;
    }

    public HaveAbility(int id_pokemon, int id_ability, int is_hidden) {
        this.id_pokemon = id_pokemon;
        this.id_ability = id_ability;
        this.is_hidden = is_hidden;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getId_ability() {
        return id_ability;
    }

    public void setId_ability(int id_ability) {
        this.id_ability = id_ability;
    }

    public int getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(int is_hidden) {
        this.is_hidden = is_hidden;
    }
}
