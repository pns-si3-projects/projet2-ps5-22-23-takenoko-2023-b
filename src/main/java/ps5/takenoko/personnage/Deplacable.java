package ps5.takenoko.personnage;

import ps5.takenoko.plateau.Direction;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class Deplacable {
    private Position position = new Position(15,15);

    public Deplacable(Position position) {
        this.position = position;
    }

    public Deplacable() {
        this.position = new Position(15,15);
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

    public abstract boolean deplacer(Position position, Plateau plateau);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
