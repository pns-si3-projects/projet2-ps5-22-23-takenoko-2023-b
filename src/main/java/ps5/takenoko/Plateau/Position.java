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

    public Position getPositionByDirection(Direction d) {
        if(p.getY() % 2 == 0) {
            switch (d) {
                case NORD_EST -> {
                    return new Position(this.x+1,this.y-1);
                }
                case EST -> {
                    return new Position(this.x+1,this.y);
                }
                case SUD_EST -> {
                    return new Position(this.x+1,this.y+1);
                }
                case SUD_OUEST -> {
                    return new Position(this.x,this.y+1);
                }
                case OUEST -> {
                    return new Position(this.x-1,this.y);
                }
                case NORD_OUEST -> {
                    return new Position(this.x-1,this.y);
                }
                default -> throw new IllegalArgumentException();
            }
        } else {
            switch (d) {
                case NORD_EST -> {
                    return new Position(this.x,this.y-1);
                }
                case EST -> {
                    return new Position(this.x+1,this.y);
                }
                case SUD_EST -> {
                    return new Position(this.x,this.y+1);
                }
                case SUD_OUEST -> {
                    return new Position(this.x-1,this.y+1);
                }
                case OUEST -> {
                    return new Position(this.x-1,this.y);
                }
                case NORD_OUEST -> {
                    return new Position(this.x-1,this.y-1);
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }
}
