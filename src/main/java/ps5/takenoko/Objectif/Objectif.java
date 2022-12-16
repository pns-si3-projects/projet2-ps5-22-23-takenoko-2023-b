package ps5.takenoko.Objectif;


import ps5.takenoko.Plateau.Plateau;

import java.util.ArrayList;

public abstract class Objectif {
    private final int point;

    public Objectif(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
    public abstract boolean verifie(Plateau board);
}
