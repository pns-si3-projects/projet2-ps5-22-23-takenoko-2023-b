package ps5.takenoko.Objectif;

public abstract class Objectif {
    private final int point;
    public int getPoint() {
        return point;
    }

    public Objectif(int point) {
        this.point = point;
    }

    public boolean verifierValidite(){
        return false;
    }
}
