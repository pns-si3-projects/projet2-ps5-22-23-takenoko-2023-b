package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.*;

public class ObjectifJardinier extends Objectif {
    private TypeObjJardinier type;


    public ObjectifJardinier(TypeObjJardinier type, Couleur color) {
        super(type.getDescription(), type.getPoint(), new Couleur[]{color});
        switch(type){
            case OBJMULTROSE :
                super.couleurs = new Couleur[]{Couleur.ROSE};
                break;
            case OBJMULTJAUNE :
                super.couleurs = new Couleur[]{Couleur.JAUNE};
                break;
            case OBJMULTVERT :
                super.couleurs = new Couleur[]{Couleur.VERT};
                break;
            default: break;
        }
        this.type = type;

    }

    @Override
    public boolean verifie(Joueur player) {
        Plateau board = player.getPlateau();
        if (!type.isMultiple()) {
            for (Position pos : board.getParcellePosee()) {
                if (!(board.getParcelle(pos) instanceof Parcelle parcelle)) continue;
                if (
                        couleurs[0] == parcelle.getCouleur()
                                && parcelle.getNbBamboo() == type.getNbBamboo()
                    //&& parcelle.getAmmenagement()==type.getAmmenagement()
                ) return true;
            }
        } else {
            int restant;
            switch (couleurs[0]) {
                case ROSE:
                    restant = 2;
                    break;
                case JAUNE:
                    restant = 3;
                    break;
                default:
                    restant = 4;
            }
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
    @Override
    public int getPoint(){
        if(type.isMultiple()) return super.getPoint();
        switch(couleurs[0]){
            case ROSE :
                return super.getPoint()+1;
            case VERT:
                return super.getPoint()-1;
            default:
                return super.getPoint();
        }
    }
}