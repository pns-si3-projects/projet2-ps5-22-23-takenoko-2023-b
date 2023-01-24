package ps5.takenoko.personnage;

import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

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
                if(plateau.getParcelle(p) instanceof Parcelle temp) {
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
