package groupeb.takenoko.personnage;

import groupeb.takenoko.plateau.Direction;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class Deplacable {
    private Position position = new Position(15,15);

    protected Deplacable(Position position) {
        this.position = position;
    }

    protected Deplacable() {
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
