package ps5.takenoko.Objectif;

import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

public class ObjectifPanda extends Objectif{
    private int nbBambous;

    public ObjectifPanda(String description, int point, Couleur[] couleurs, int nbBambous) {
        super(description, point, couleurs);
        this.nbBambous = nbBambous;
    }
    public int getNbBambous() {return nbBambous;}
    @Override
    public boolean verifie(Joueur j) {
        for(int i=0; i<couleurs.length;i++){
            if(j.nbBambousParCouleur(couleurs[i])<nbBambous){
                return false;
            }
        }
        return true;
    }

}
