package ps5.takenoko.Plateau;

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

    public Position getAdjacente (Direction d, Position p){
            return switch(d){
                case NordEst -> new Position((p.getY()%2==0 ? x+1 : x ),y-1);
                case Est -> new Position(x+1,y);
                case SudEst -> new Position((p.getY()%2==0 ? x+1 : x ),y+1);
                case SudOuest -> new Position((p.getY()%2==0 ? x : x-1 ),y+1);
                case Ouest -> new Position(x-1,y);
                case NordOuest -> new Position((p.getY()%2==0 ? x : x-1 ),y-1);
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

    public boolean estAdjacente (Position p){
        for(Direction d: Direction.values()){
            if(p.equals(this.getAdjacente(d,p))){
                return true;
            }
        }
        return false;
    }

}