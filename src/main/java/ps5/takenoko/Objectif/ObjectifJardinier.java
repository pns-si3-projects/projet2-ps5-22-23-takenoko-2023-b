package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Plateau.*;

public class ObjectifJardinier extends Objectif{
    private TypeObjJardinier type;
    private Couleur couleur;


    public ObjectifJardinier(TypeObjJardinier type, Couleur color) {
        super(type.getPoint());
        this.type = type;
        couleur = color;
    }

    @Override
    public boolean verifie(Plateau board) {
        if(type!=TypeObjJardinier.MULTIPLE){
            for(Position pos : board.getParcellePosee() ){
                if(!(board.getParcelle(pos) instanceof Parcelle parcelle))continue;
                if (
                        couleur == parcelle.getCouleur()
                        && parcelle.getNbBamboo()==type.getNbBamboo()
                        //&& parcelle.getAmmenagement()==type.getAmmenagement()
                )return true;
            }
        }else{
            int restant = 4;
            for(Position pos : board.getParcellePosee() ){
                if(!(board.getParcelle(pos) instanceof Parcelle parcelle))continue;
                if (
                        couleur == parcelle.getCouleur()
                        && parcelle.getNbBamboo()==type.getNbBamboo()

                ) {
                    if (--restant == 0) return true;
                }
            }

        }
        return false;
    }
}
