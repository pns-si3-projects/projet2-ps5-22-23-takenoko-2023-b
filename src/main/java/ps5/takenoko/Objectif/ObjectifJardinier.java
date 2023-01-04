package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

public class ObjectifJardinier extends Objectif{
    private AmenagementType amenagementType;
    private int nbBambous;

    public ObjectifJardinier(String description, int point, Couleur[] couleurs, AmenagementType amenagementType) {
        super(description, point, couleurs);
        this.amenagementType = amenagementType;
    }

    @Override
    public boolean verifie(Joueur j) {
        return false;
    }
}
