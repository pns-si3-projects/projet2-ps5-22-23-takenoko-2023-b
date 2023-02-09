package ps5.takenoko.objectif;


import ps5.takenoko.Bot.Bot;
import ps5.takenoko.plateau.Couleur;

import java.util.Arrays;
import java.util.Objects;

public abstract class Objectif {
    private String description;
    private final int point;
    Couleur[] couleurs;

    public Objectif(String description, int point, Couleur couleur){
        this(description, point, new Couleur[]{couleur});
        this.description = description;
    }
    public Objectif(String description, int point, Couleur[] couleurs){
        this(point,couleurs);
        this.description = description;
    }
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

    public abstract boolean verifie(Bot j);
    public String toString() {
        String stringCouleur;
        if(couleurs.length==1) stringCouleur = ""+couleurs[0];
        else stringCouleur = couleurs[0]+" et "+couleurs[1];
        return getClass().getSimpleName() + " de couleur " + stringCouleur +". Cet objectif vaut " + point +" points" ;
    }
}
