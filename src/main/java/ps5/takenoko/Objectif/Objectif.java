package ps5.takenoko.Objectif;


import java.util.ArrayList;

public abstract class Objectif {
    private final int point;

    public Objectif(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
