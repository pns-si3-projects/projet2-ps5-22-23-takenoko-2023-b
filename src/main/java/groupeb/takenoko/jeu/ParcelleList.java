package groupeb.takenoko.jeu;

import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Parcelle;

import java.util.ArrayList;
import java.util.List;

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

    public List<Parcelle> getParcelles(int nbParcelles){
        ArrayList<Parcelle> parcellesRandoms = new ArrayList<>();
        for(int i=0; i<nbParcelles; i++){
            parcellesRandoms.add(this.get(i));
        }
        this.removeAll(parcellesRandoms);
        return parcellesRandoms;
    }

    public void addAtEnd(List<Parcelle> parcelles){
        this.addAll(parcelles);
    }


}
