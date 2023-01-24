package ps5.takenoko.plateau;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getPositionByDirection(Direction d) {
        return switch(d){
            case NORD_EST -> new Position((getY() %2 == 0 ? x+1 : x), y-1);
            case EST -> new Position(x+1,y);
            case SUD_EST -> new Position((getY()%2==0 ? x+1:x), y+1);
            case SUD_OUEST -> new Position((getY()%2==0 ? x:x-1),y+1);
            case OUEST -> new Position(x-1,y);
            case NORD_OUEST -> new Position((getY()%2==0 ? x : x-1),y-1);
            default -> null;
        };
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Position){
            return (this.x == ((Position)obj).getX() && this.y == ((Position)obj).getY());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString(){
        return "("+x+","+y+")";
    }
}
