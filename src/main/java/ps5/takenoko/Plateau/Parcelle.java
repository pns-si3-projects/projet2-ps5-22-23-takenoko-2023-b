package ps5.takenoko.Plateau;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Element.Bamboo;

public class Parcelle extends ParcelleInactive{
    private Couleur couleur;
    private Amenagement amenagement;

    public Parcelle() {
    }



    public Parcelle(Couleur c) {
        this.couleur = c;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }


}
