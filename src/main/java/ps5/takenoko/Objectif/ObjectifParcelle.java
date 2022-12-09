package ps5.takenoko.Objectif;

import ps5.takenoko.Plateau.*;

public class ObjectifParcelle extends Objectif implements ObjectifInterf {
    private Shape figure;
    private Couleur principale;
    private Couleur secondaire;

    public ObjectifParcelle(int point, Shape forme, Couleur couleur) {
        super(point);
        figure = forme;
        principale = couleur;
    }

    public ObjectifParcelle(int point, Shape forme, Couleur couleurP, Couleur couleurS) {
        super(point);
        figure = forme;
        principale = couleurP;
        secondaire = couleurS;
    }

    public boolean verifie(Plateau plateau) {


        for (Position pos : plateau.getParcellePosee()) {
            Parcelle parcelle = (Parcelle) plateau.getParcelle(pos);
            boolean valide = true;

            switch (figure) {
                case LOSANGE:

                    for(Direction[] formes: figure.getDirections()){
                        if(testCouleurOfPos(plateau,pos,secondaire)){
                            Couleur tmp = principale;
                            principale = secondaire;
                            secondaire = tmp;
                        }
                        valide = valide && testCouleurOfPos(plateau,pos,principale)
                            && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[0]),principale)
                            && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[1]),secondaire)
                            && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[2]),secondaire);
                    }

                    break;

                default:
                    //pour chaque pattern
                    for(Direction[] formes : figure.getDirections()){
                        if(!testCouleurOfPos(plateau, pos, principale))continue;
                        valide = true;
                        //On regarde chacune de ses directions
                        for(Direction dirs : formes){
                            valide = valide && testCouleurOfPos(plateau, pos.getPositionByDirection(dirs), principale);
                        }
                        if(valide) return valide;

                    }
                    break;
            }
            if (valide) return true;

        }
        return false;
    }

    private boolean testCouleurOfPos(Plateau plat, Position pos, Couleur color) {
        ParcelleInactive parcelle = plat.getParcelle(pos);

        if (parcelle instanceof Parcelle) {
            Parcelle valid = (Parcelle) parcelle;
            return valid.getCouleur() == color;
        } else return false;

    }
}


