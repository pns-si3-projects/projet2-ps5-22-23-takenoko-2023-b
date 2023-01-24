package ps5.takenoko.jeu;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;

import java.util.ArrayList;

public class ParcelleList extends ArrayList<Parcelle> {
    public ParcelleList() {
        init();
    }

    public void init() {
        for (int i = 0; i < 6; i++) {
            this.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.EMPTY)));
        }
        for (int i = 0; i < 2; i++) {
            this.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.BASSIN)));
        }
        for (int i = 0; i < 2; i++) {
            this.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.ENCLOS)));
        }
        this.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.ENGRAIS)));

        for (int i = 0; i < 4; i++) {
            this.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.EMPTY)));
        }
        this.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.BASSIN)));
        this.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.ENCLOS)));
        this.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.ENGRAIS)));

        for (int i = 0; i < 6; i++) {
            this.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.EMPTY)));
        }
        this.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.BASSIN)));
        this.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.ENCLOS)));
        this.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.ENGRAIS)));
    }

    public ArrayList<Parcelle> getParcelles(int nbParcelles){
        ArrayList<Parcelle> parcellesRandoms = new ArrayList<Parcelle>();
        for(int i=0; i<3; i++){
            parcellesRandoms.add(this.get(i));
        }
        this.removeAll(parcellesRandoms);
        return parcellesRandoms;
    }

    public void addAtEnd(ArrayList<Parcelle> parcelles){
        for(Parcelle p : parcelles){
            this.add(p);
        }
    }


}
