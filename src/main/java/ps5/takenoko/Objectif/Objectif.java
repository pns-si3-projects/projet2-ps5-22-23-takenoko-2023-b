package ps5.takenoko.Objectif;


import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

import java.util.ArrayList;

public abstract class Objectif {
    private String description;
    private final int point;
    private Couleur[] couleurs;


    public Objectif(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
    public abstract boolean verifie(Plateau board);
}
