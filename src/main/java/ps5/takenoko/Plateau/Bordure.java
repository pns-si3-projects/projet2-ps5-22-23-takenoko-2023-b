package ps5.takenoko.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bordure {
    private enum BordureDir{
        VERTICAL,
        DIAGONALE_SO_NE,
        DIAGONALE_NO_SE,
    }
    private Position pos1;
    private Position pos2;

    public Bordure(Position pos, Direction dir) {
        this(pos,pos.getPositionByDirection(dir));
    }
    public Bordure(Position pos1, Position pos2){
        if(pos1 == null || pos2==null) throw new IllegalArgumentException();
        if(
                pos1.getPositionByDirection(Direction.EST).equals(pos2)
                || pos1.getPositionByDirection(Direction.SUD_OUEST).equals(pos2)
                || pos1.getPositionByDirection(Direction.SUD_EST).equals(pos2)
        ){
            this.pos1 = pos1;
            this.pos2 = pos2;
        }
        else if(
                pos1.getPositionByDirection(Direction.NORD_OUEST).equals(pos2)
                    || pos1.getPositionByDirection(Direction.NORD_EST).equals(pos2)
                    || pos1.getPositionByDirection(Direction.OUEST).equals(pos2)
        ){
            this.pos1 = pos2;
            this.pos2 = pos1;

        }else throw new IllegalArgumentException("Les parcelles ne sont pas a cote");
    }

    public Position getPos1() {
        return pos1;
    }

    public Position getPos2() {
        return pos2;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(
            object ==null
            || this.getClass()!= object.getClass()
        )return false;

        Bordure other = (Bordure) object;
        return (pos1.equals(other.pos1) && pos2.equals(other.pos2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos1, pos2);
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
        throw new NullPointerException();
    }
    public String toString(){
        return "["+pos1.toString()+","+pos2.toString()+"]";
    }
}
