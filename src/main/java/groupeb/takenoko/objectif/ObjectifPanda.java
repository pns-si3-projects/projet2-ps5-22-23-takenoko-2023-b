package groupeb.takenoko.objectif;

import groupeb.takenoko.bot.Bot;
import groupeb.takenoko.plateau.Couleur;

import java.util.Objects;

public class ObjectifPanda extends Objectif{
    private final int nbParcelles;

    public ObjectifPanda(int point, Couleur couleurs, int nbParcelles){this(point, new Couleur[]{couleurs},nbParcelles);}
    public ObjectifPanda(int point, Couleur[] couleurs, int nbParcelles) {
        super( point, couleurs);
        this.nbParcelles = nbParcelles;
    }
    public int getNbParcelles() {return nbParcelles;}
    @Override
    public boolean verifie(Bot j) {
        for(int i=0; i<couleurs.length;i++){
            if(j.nbBambousParCouleur(couleurs[i]) < nbParcelles){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifPanda that = (ObjectifPanda) o;
        return nbParcelles == that.nbParcelles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nbParcelles);
    }
}
