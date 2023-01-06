package ps5.takenoko.Objectif;


import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public abstract class Objectif {
    private String description;
    private final int point;
    Couleur[] couleurs;

    public Objectif(int point, Couleur[] couleurs) {
        this.description = this.getClass().getSimpleName();
        this.point = point;
        this.couleurs = couleurs;
    }

    public Objectif(String description, int point) {
        this.description = description;
        this.point = point;
    }

    public Couleur[] getCouleurs() {return couleurs;}

    public boolean equals(Objectif obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        return this.description.equals(obj.description)
            && this.couleurs == obj.couleurs
            && this.point == obj.point;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(description, point);
        result = 31 * result + Arrays.hashCode(couleurs);
        return result;
    }

    public int getPoint() {
        return point;
    }

    public abstract boolean verifie(Joueur j);

    public String toString() {
        return getClass().getSimpleName() + " de valeur " + point;
    }
}
