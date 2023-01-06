package ps5.takenoko.Jeu;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Parcelle;

import java.util.ArrayList;
//TODO: Le rendre un STack (es 2 non choisis vont a l'arriere)

public class ParcelleList extends ListDuJeu<Parcelle> {
    public ParcelleList() {
        super();
    }

    @Override
    public void init() {
        for (int i = 0; i < 6; i++) {
            list.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.EMPTY)));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.BASSIN)));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.ENCLOS)));
        }
        list.add(new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.ENGRAIS)));

        for (int i = 0; i < 4; i++) {
            list.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.EMPTY)));
        }
        list.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.BASSIN)));
        list.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.ENCLOS)));
        list.add(new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.ENGRAIS)));

        for (int i = 0; i < 6; i++) {
            list.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.EMPTY)));
        }
        list.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.BASSIN)));
        list.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.ENCLOS)));
        list.add(new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.ENGRAIS)));
    }

    public ArrayList<Parcelle> getRandomParcelles(int nbParcelles){
        ArrayList<Parcelle> parcellesRandoms = new ArrayList<Parcelle>();
        for(int i=0; i<nbParcelles; i++){
            parcellesRandoms.add(list.get(i));
        }
        return parcellesRandoms;
    }
}
