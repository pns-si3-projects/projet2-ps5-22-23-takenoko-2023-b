package ps5.takenoko.Personnage;

import ps5.takenoko.Plateau.Direction;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

public abstract class Deplacable {
    private Position position;

    public ArrayList<Position> posPossibles(Plateau plateau) {
        ArrayList<Position> positionsPosables = new ArrayList<>();
        for(Direction d : Direction.values()) {
            Position temp = position.getPositionByDirection(d);
            while(!plateau.getParcelle(temp).equals(null) && plateau.getParcelle(temp).estOccupe()) {
                positionsPosables.add(position.getPositionByDirection(d));
                position = position.getPositionByDirection(d);
            }
        }
        return positionsPosables;
    }
}
