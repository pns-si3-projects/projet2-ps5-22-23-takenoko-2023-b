package ps5.takenoko.Objectif;

import ps5.takenoko.Plateau.*;

public class ObjectifParcelle extends Objectif {
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

        //Pour chaque position du tableau avec une parcelle dessus
        for (Position pos : plateau.getParcellePosee()) {
            ParcelleInactive parcelle = plateau.getParcelle(pos);
            //En fonction de la figure demande sur la carte objectif
            switch (figure) {
                //Si c'est une carte objectif losange
                case LOSANGE: //On doit verifie que le losange n'est pas retourne
                    for(Direction[] formes: figure.getDirections()){
                        boolean valide = true;
                        //Si il est retourne on inverse les couleurs de la carte objectif
                        Couleur notreCouleur;//Couleur de la case actuel et d'une autre case
                        Couleur autreCouleur;//Couleur des deux autre cases du losange
                        if(testCouleurOfPos(plateau,pos,principale)){
                            notreCouleur = principale;
                            autreCouleur = secondaire;
                        }else if (testCouleurOfPos(plateau,pos,secondaire)){
                            notreCouleur = secondaire;
                            autreCouleur = principale;
                        }else break;

                        valide = testCouleurOfPos(plateau,pos.getPositionByDirection(formes[0]),autreCouleur)
                            && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[1]),autreCouleur)
                            && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[2]),notreCouleur);

                        valide = valide || (testCouleurOfPos(plateau,pos.getPositionByDirection(formes[0]),autreCouleur)
                                && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[1]),notreCouleur)
                                && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[2]),autreCouleur));

                        valide = valide || (testCouleurOfPos(plateau,pos.getPositionByDirection(formes[0]),notreCouleur)
                                && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[1]),autreCouleur)
                                && testCouleurOfPos(plateau,pos.getPositionByDirection(formes[2]),autreCouleur));
                        if(valide) return true;
                    }

                    break; // End of Losange

                default:
                    //pour chaque pattern
                    for(Direction[] formes : figure.getDirections()){
                        if(testCouleurOfPos(plateau, pos, principale)
                            && testCouleurOfPos(plateau, pos.getPositionByDirection(formes[0]), principale)
                            && testCouleurOfPos(plateau, pos.getPositionByDirection(formes[1]), principale)
                        )return true;

                    }
                    break;
            }

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


