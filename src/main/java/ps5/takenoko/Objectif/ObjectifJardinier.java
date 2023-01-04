package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.*;

public class ObjectifJardinier extends Objectif {
    private TypeObjJardinier type;


    public ObjectifJardinier(TypeObjJardinier type, Couleur color) {
        super(type.getDescription(), type.getPoint(), new Couleur[]{color});
        this.type = type;
    }

    @Override
    public boolean verifie(Joueur player) {
        Plateau board = player.getPlateau();
        if (type != TypeObjJardinier.MULTIPLE) {
            for (Position pos : board.getParcellePosee()) {
                if (!(board.getParcelle(pos) instanceof Parcelle parcelle)) continue;
                if (
                        couleurs[0] == parcelle.getCouleur()
                                && parcelle.getNbBamboo() == type.getNbBamboo()
                    //&& parcelle.getAmmenagement()==type.getAmmenagement()
                ) return true;
            }
        } else {
            int restant = 4;
            for (Position pos : board.getParcellePosee()) {
                if (!(board.getParcelle(pos) instanceof Parcelle parcelle)) continue;
                if (
                        couleurs[0] == parcelle.getCouleur()
                                && parcelle.getNbBamboo() == type.getNbBamboo()

                ) {
                    if (--restant == 0) return true;
                }
            }

        }
        return false;
    }
}