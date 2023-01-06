package ps5.takenoko.Objectif;

import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.*;

public class ObjectifParcelle extends Objectif {
    private Shape figure;

    public ObjectifParcelle(Shape figure, Couleur[] couleurs) {
        super(figure.getPoint(), couleurs);
        this.figure = figure;
    }

    @Override
    public int getPoint() {
        if(figure == Shape.LOSANGE){
            if(
                    (couleurs[0] == Couleur.JAUNE && couleurs[1] == Couleur.ROSE)
                    || (couleurs[0] == Couleur.ROSE && couleurs[1] == Couleur.JAUNE)
            ) return 5;
            if(
                    (couleurs[0] == Couleur.VERT && couleurs[1] == Couleur.ROSE)
                            || (couleurs[0] == Couleur.ROSE && couleurs[1] == Couleur.VERT)
            ) return 4;
            if
            (
                    (couleurs[0] == Couleur.JAUNE && couleurs[1] == Couleur.VERT)
                            || (couleurs[0] == Couleur.VERT && couleurs[1] == Couleur.JAUNE)
            ) return 3;
            throw new IllegalArgumentException();
        }switch(couleurs[0]){
            case VERT :
                return super.getPoint() -1;
            case ROSE :
                return super.getPoint() +1;
            default :
                return super.getPoint() ;
        }
    }

    @Override
    public boolean verifie(Joueur j) {
        Plateau plateau = j.getPlateau();
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
                        if(testCouleurOfPos(plateau,pos,couleurs[0])){
                            notreCouleur = couleurs[0];
                            autreCouleur = couleurs[1];
                        }else if (testCouleurOfPos(plateau,pos,couleurs[1])){
                            notreCouleur = couleurs[1];
                            autreCouleur = couleurs[0];
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
                        if(testCouleurOfPos(plateau, pos, couleurs[0])
                                && testCouleurOfPos(plateau, pos.getPositionByDirection(formes[0]), couleurs[0])
                                && testCouleurOfPos(plateau, pos.getPositionByDirection(formes[1]), couleurs[0])
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