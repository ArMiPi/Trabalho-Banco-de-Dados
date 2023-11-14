package uel.bd.Bulbapedia.models;

public class LearnMove {
    private int id_pokemon;
    private int id_move;

    public LearnMove(int id_pokemon, int id_move) {
        this.id_pokemon = id_pokemon;
        this.id_move = id_move;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getId_move() {
        return id_move;
    }

    public void setId_move(int id_move) {
        this.id_move = id_move;
    }
}
