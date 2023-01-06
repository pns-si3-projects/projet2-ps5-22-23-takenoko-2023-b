package ps5.takenoko.Objectif;


import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

import java.util.ArrayList;
import java.util.Random;

public abstract class Objectif {
    private String description;
    private final int point;
    Couleur[] couleurs;

    public Objectif(String description, int point, Couleur[] couleurs) {
        this.description = description;
        this.point = point;
        this.couleurs = couleurs;
    }

    public Objectif(String description, int point) {
        this.description = description;
        this.point = point;
    }

    public Couleur[] getCouleurs() {return couleurs;}

    public int getPoint() {
        return point;
    }

    public abstract boolean verifie(Joueur j);

    public String toString() {
        return getClass().getSimpleName() + " de valeur " + point;
    }
}
