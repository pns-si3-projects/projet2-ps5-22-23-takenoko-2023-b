package ps5.takenoko.Plateau;

import java.util.ArrayList;
import java.util.List;

public class Bordure {
    private enum BordureDir{
        VERTICAL,
        DIAGONALE_SO_NE,
        DIAGONALE_NO_SE,
    }
    private Position pos1;
    private Position pos2;
    public Bordure(Position pos1, Position pos2){
        if(pos1 == null || pos2==null) throw new IllegalArgumentException();

        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public boolean equals(Bordure other){
        if(this == other) return true;
        if(
            other ==null
            || this.getClass()!= other.getClass()
        )return false;

        return (pos1.equals(other.pos1) && pos2.equals(other.pos2)) || (pos1.equals(other.pos2) && pos2.equals(other.pos1));
    }

    public List<Bordure> adjacentBorder(){
        ArrayList<Bordure> result = new ArrayList<>();
        switch (borderOrientation()) {
            case VERTICAL -> {
                result.add(new Bordure(pos1, pos1.getPositionByDirection(Direction.NORD_EST)));
                result.add(new Bordure(pos1, pos1.getPositionByDirection(Direction.SUD_EST)));
                result.add(new Bordure(pos2, pos2.getPositionByDirection(Direction.NORD_OUEST)));
                result.add(new Bordure(pos2, pos2.getPositionByDirection(Direction.SUD_OUEST)));
            }
            case DIAGONALE_SO_NE -> {
                result.add(new Bordure(pos1, pos1.getPositionByDirection(Direction.EST)));
                result.add(new Bordure(pos1, pos1.getPositionByDirection(Direction.SUD_OUEST)));
                result.add(new Bordure(pos2, pos2.getPositionByDirection(Direction.NORD_EST)));
                result.add(new Bordure(pos2, pos2.getPositionByDirection(Direction.OUEST)));
            }
            case DIAGONALE_NO_SE -> {
                result.add(new Bordure(pos1, pos1.getPositionByDirection(Direction.OUEST)));
                result.add(new Bordure(pos1, pos1.getPositionByDirection(Direction.SUD_EST)));
                result.add(new Bordure(pos2, pos2.getPositionByDirection(Direction.EST)));
                result.add(new Bordure(pos2, pos2.getPositionByDirection(Direction.NORD_OUEST)));
            }
            default -> {
                throw new Exception ;
            }
        }
        return result;
    }

    private BordureDir borderOrientation() {
        // Bordure Sud-Nord
        if(pos1.getPositionByDirection(Direction.EST).equals(pos2)){
            return BordureDir.VERTICAL;
        }
        else if(pos1.getPositionByDirection(Direction.SUD_EST).equals(pos2)){
            return BordureDir.DIAGONALE_SO_NE;
        }
        else if(pos1.getPositionByDirection(Direction.SUD_OUEST).equals(pos2)){
            return BordureDir.DIAGONALE_NO_SE;
        }
        return null;
    }
}
