package ps5.takenoko.Objectif;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Plateau.*;

public class ObjectifJardinier extends Objectif{
    private TypeObjJardinier type;
    private Couleur couleur;


    public ObjectifJardinier(TypeObjJardinier type, Couleur color) {
        super(type.getPoint());
        couleur = color;
    }

    @Override
    public boolean verifie(Plateau board) {
        if(type!=TypeObjJardinier.MULTIPLE){
            for(Position pos : board.getParcellePosee() ){
                if(!(board.getParcelle(pos)instanceof Parcelle))continue;
                Parcelle parcelle = (Parcelle) board.getParcelle(pos);
                if (
                        couleur == parcelle.getCouleur()
                        && parcelle.getNbBamboo()==type.getNbBamboo()
                        //&& parcelle.getAmmenagement()==type.getAmmenagement()
                )return true;
            }
        }else{
            int restant = 4;
            for(Position pos : board.getParcellePosee() ){
                if(!(board.getParcelle(pos)instanceof Parcelle))continue;
                Parcelle parcelle = (Parcelle) board.getParcelle(pos);
                if (
                        couleur == parcelle.getCouleur()
                        && parcelle.getNbBamboo()==type.getNbBamboo()

                )if(--restant==4)return true;
            }

        }
        return false;
    }
}
