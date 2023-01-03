package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

public class ObjectifJardinier extends Objectif{
    private AmenagementType amenagementType;
    private Couleur couleur;

    public ObjectifJardinier(int point, AmenagementType amenagementType, Couleur couleur) {
        super(point);
        this.amenagementType = amenagementType;
        this.couleur = couleur;
    }

    @Override
    public boolean verifie(Plateau board) {
        return false;
    }
}
