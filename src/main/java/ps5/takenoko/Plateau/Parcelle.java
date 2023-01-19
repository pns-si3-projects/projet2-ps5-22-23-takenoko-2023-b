package ps5.takenoko.Plateau;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Element.AmenagementType;

import java.util.Random;

public class Parcelle extends ParcelleInactive{
    private static final int MAX_BAMBOU = 4;
    private Couleur couleur;
    private Amenagement amenagement = new Amenagement();
    private int nbBamboo = 0;
    private boolean irrigue = false;


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

    public void irrigue() {
        if(irrigue) return;
        this.irrigue = true;
        augmenteBamboo();
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
            nbBamboo=nbBamboo+amenagement.getNbBambouAPousser()>MAX_BAMBOU ? MAX_BAMBOU : nbBamboo+amenagement.getNbBambouAPousser();
            return true;
        }
        return false;
    }

    public boolean mangerBambou(){
        if(nbBamboo>0 && estIrrigue() && amenagement.getNbBambouAManger()>0){
            nbBamboo= nbBamboo-amenagement.getNbBambouAManger()<0 ? 0 : nbBamboo-amenagement.getNbBambouAManger();
            return true;
        }
        return false;
    }


    public boolean estIrrigue(){
        return irrigue;
    }

    public Amenagement getAmenagement() {
        return amenagement;
    }

    public void setAmenagement(Amenagement amenagement) {
        this.amenagement = amenagement;
    }

    public String toString(){
        return "parcelle de couleur "+couleur;
    }
}
