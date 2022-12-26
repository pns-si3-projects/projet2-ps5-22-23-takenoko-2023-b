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

    public void deplacer(Position position, Plateau plateau) {
        this.setPosition(position);
        ArrayList<Position> positions = plateau.getConnectedParcelleSameColor(position);
            for(Position p : positions) {
                Parcelle temp = (Parcelle) plateau.getParcelle(p);
                if (temp.estIrrigue()) {
                    temp.augmenteBamboo();
                }
            }
    }
}
