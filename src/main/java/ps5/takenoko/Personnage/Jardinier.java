package ps5.takenoko.Personnage;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

public class Jardinier extends Deplacable {
    //constructor

    public Jardinier(Position position) {
        super(position);
    }
    public Jardinier(){super();}

    public boolean deplacer(Position position, Plateau plateau) {
        if(posPossibles(plateau).contains(position)){
            this.setPosition(position);
            ArrayList<Position> positions = plateau.getConnectedParcelleSameColor(position);
            for(Position p : positions) {
                Parcelle temp = (Parcelle) plateau.getParcelle(p);
                if (temp.estIrrigue()) {
                    temp.augmenteBamboo();
                }
            }
            return true;
        }
        else{
            throw new IllegalArgumentException("Position impossible a poser");
        }
    }
}
