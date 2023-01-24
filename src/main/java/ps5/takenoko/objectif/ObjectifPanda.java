package ps5.takenoko.objectif;

import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.plateau.Couleur;

public class ObjectifPanda extends Objectif{
    private int nbParcelles;

    public ObjectifPanda(int point, Couleur[] couleurs, int nbParcelles) {
        super( point, couleurs);
        this.nbParcelles = nbParcelles;
    }
    public int getNbParcelles() {return nbParcelles;}
    @Override
    public boolean verifie(Joueur j) {
        for(int i=0; i<couleurs.length;i++){
            if(j.nbBambousParCouleur(couleurs[i]) < nbParcelles){
                return false;
            }
        }
        return true;
    }

    public boolean equals(ObjectifPanda obj) {
        return super.equals(obj)
            && this.nbParcelles == obj.nbParcelles;
    }
}
