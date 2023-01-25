package ps5.takenoko.objectif;

import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.plateau.*;

import java.util.Objects;

public class ObjectifParcelle extends Objectif {
    private Shape figure;

    public ObjectifParcelle(Shape figure, Couleur[] couleurs) {
        super(figure.getPoint(), couleurs);
        this.figure = figure;
    }

    @Override
    public int getPoint() {
        if(figure == Shape.LOSANGE){
            switch(couleurs[0]){
                case VERT :
                    if(couleurs[1] == Couleur.VERT) return 3;
                    else throw new IllegalArgumentException();
                case JAUNE :
                    if(couleurs[1] == Couleur.VERT) return 3;
                    else if(couleurs[1] == Couleur.JAUNE) return 4;
                    else if(couleurs[1] == Couleur.ROSE) return 5;
                    else throw new IllegalArgumentException();
                case ROSE :
                    if(couleurs[1] == Couleur.VERT) return 4;
                    else if(couleurs[1] == Couleur.ROSE) return 5;
                    else throw new IllegalArgumentException();
                default : throw new IllegalArgumentException();

            }

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

                        valide = testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[0]),autreCouleur)
                                && testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[1]),autreCouleur)
                                && testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[2]),notreCouleur);

                        valide = valide || (testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[0]),autreCouleur)
                                && testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[1]),notreCouleur)
                                && testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[2]),autreCouleur));

                        valide = valide || (testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[0]),notreCouleur)
                                && testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[1]),autreCouleur)
                                && testCouleurIrrigueOfPos(plateau,pos.getPositionByDirection(formes[2]),autreCouleur));
                        if(valide) return true;
                    }

                    break; // End of Losange

                default:
                    //pour chaque pattern
                    for(Direction[] formes : figure.getDirections()){
                        if(testCouleurOfPos(plateau, pos, couleurs[0])
                                && testCouleurIrrigueOfPos(plateau, pos.getPositionByDirection(formes[0]), couleurs[0])
                                && testCouleurIrrigueOfPos(plateau, pos.getPositionByDirection(formes[1]), couleurs[0])
                        )return true;
                    }
                    break;
                }
            }
        return false;
    }

    private boolean testCouleurIrrigueOfPos(Plateau plat, Position pos, Couleur color){
        if(plat.getParcelle(pos) instanceof Parcelle parcelle) {
            return parcelle.estIrrigue() && parcelle.getCouleur() == color;
        } return false;
    }

    private boolean testCouleurOfPos(Plateau plat, Position pos, Couleur color) {
        if(plat.getParcelle(pos) instanceof Parcelle parcelle) return parcelle.getCouleur() == color;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifParcelle that = (ObjectifParcelle) o;
        return figure == that.figure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), figure);
    }
}