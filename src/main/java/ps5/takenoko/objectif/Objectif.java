package ps5.takenoko.objectif;


import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.plateau.Couleur;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Objectif objectif = (Objectif) obj;
        return this.description.equals(objectif.description)
                && this.couleurs == objectif.couleurs
                && this.point == objectif.point;
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
