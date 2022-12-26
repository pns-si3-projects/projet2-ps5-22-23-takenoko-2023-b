package ps5.takenoko.Personnage;

import ps5.takenoko.Plateau.Direction;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Deplacable {
    private Position position = new Position(15,15);

    public Deplacable(Position position) {
        this.position = position;
    }

    public Set<Position> posPossibles(Plateau plateau) {
        Set<Position> positionsPosables = new HashSet<>();
        for(Direction d : Direction.values()) {
            Position temp = this.position.getPositionByDirection(d);
            while(plateau.getParcelle(temp) != null && plateau.getParcelle(temp).estOccupe()) {
                positionsPosables.add(temp);
                temp = temp.getPositionByDirection(d);
            }
        }
        return positionsPosables;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
