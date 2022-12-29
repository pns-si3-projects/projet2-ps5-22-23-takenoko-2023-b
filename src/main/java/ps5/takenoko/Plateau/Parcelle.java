package ps5.takenoko.Plateau;

import ps5.takenoko.Element.Amenagement;
import java.util.Random;

public class Parcelle extends ParcelleInactive{
    private static final int maxNbBamboo = 4;
    private Couleur couleur;
    private Amenagement amenagement = null;
    private int nbBamboo = 0;

    public Parcelle() {
        Random R = new Random();
        this.couleur = Couleur.values()[R.nextInt(3)];
    }



    public Parcelle(Couleur c) {
        this.couleur = c;
    }

    public Parcelle(Couleur couleur, Amenagement amenagement) {
        this.couleur = couleur;
        this.amenagement = amenagement;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return ""; //TODO
    }
}
