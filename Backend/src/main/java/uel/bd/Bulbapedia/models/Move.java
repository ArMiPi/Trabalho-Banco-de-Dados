package uel.bd.Bulbapedia.models;

public class Move {
    private long idMove;
    private String name;
    private int accuracy;
    private int idClass;
    private int power;
    private int pp;
    private int idType;

    public Move(long idMove, String name, int accuracy, int idClass, int power, int pp, int idType) {
        this.idMove = idMove;
        this.name = name;
        this.accuracy = accuracy;
        this.idClass = idClass;
        this.power = power;
        this.pp = pp;
        this.idType = idType;
    }

    public long getIdMove() {
        return idMove;
    }

    public void setIdMove(long idMove) {
        this.idMove = idMove;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
}
