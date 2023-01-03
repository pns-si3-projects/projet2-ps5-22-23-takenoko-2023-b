package ps5.takenoko.Plateau;

import ps5.takenoko.Element.Amenagement;
import java.util.Random;

public class Parcelle extends ParcelleInactive{
    private static final int MAX_BAMBOU = 4;
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

    // For test purpose
    public Parcelle(Couleur c,int nbBamboo) {
        this.couleur = c;
        this.nbBamboo = nbBamboo;
    }

    public Parcelle(Couleur couleur, Amenagement amenagement) {
        this.couleur = couleur;
        this.amenagement = amenagement;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    public int getNbBamboo() {return nbBamboo;}

    public void setNbBamboo(int nbBamboo) {
        this.nbBamboo = nbBamboo;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }


    public boolean augmenteBamboo(){
        if(nbBamboo<MAX_BAMBOU && estIrrigue()){
            nbBamboo++;
            return true;
        }
        //TODO: Grow double bamboos when there is ENGRAS and nbBamboo<MAX_BAMBOU
        return false;
    }

    public boolean mangerBambou(){
        if(nbBamboo==0){
            return false;
        }
        nbBamboo--;
        return true;
    }

    public boolean estIrrigue(){
        //TODO
        return true;
    }


}
