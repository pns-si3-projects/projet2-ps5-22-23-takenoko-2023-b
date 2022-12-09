package ps5.takenoko.Objectif;

import ps5.takenoko.Plateau.*;


public class ObjectifParcelle extends Objectif implements ObjectifInterf{
    private Shape figure;
    private Couleur primary;
    private Couleur secondary;
    public ObjectifParcelle(int point, Shape forme, Couleur couleur) {
        super(point);
        figure = forme;
        primary=couleur;
    }
    public ObjectifParcelle(int point, Shape forme, Couleur couleurP,Couleur couleurS) {
        super(point);
        figure = forme;
        primary=couleurP;
        secondary=couleurS;
    }
    public int check(Plateau board){
        for(int x=1;x< ;x++) for(int y = 1; y< ; y++){
            ParcelleInactive currentParcelle = board.getParcelle(x,y);
            if (currentParcelle instanceof Parcelle){
                Parcelle valid = (Parcelle) currentParcelle;
                switch(figure){
                    case LOSANGE:

                        break;
                    default:
                        for(Direction[] dir : figure.getDirections())
                            if(valid.getCouleur()!=primary) break;

                            break;
                }
            }
        }

    }
}
