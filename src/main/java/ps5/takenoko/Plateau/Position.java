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
        if(this.y % 2 == 0) {
            switch (d) {
                case NORD_EST -> {
                    if(this.x<30 && this.y > 0) {
                        return new Position(this.x + 1, this.y - 1);
                    }
                }
                case EST -> {
                    if(this.x<30) {
                        return new Position(this.x + 1, this.y);
                    }
                }
                case SUD_EST -> {
                    if(this.x<30 && this.y < 30) {
                        return new Position(this.x + 1, this.y + 1);
                    }
                }
                case SUD_OUEST -> {
                    if(this.y < 30) {
                        return new Position(this.x, this.y + 1);
                    }
                }
                case OUEST -> {
                    if(this.x>0) {
                        return new Position(this.x - 1, this.y);
                    }
                }
                case NORD_OUEST -> {
                    if(this.x>0) {
                        return new Position(this.x, this.y -1);
                    }
                }
                default -> {
                    return null;
                }
            }
        } else {
            switch (d) {
                case NORD_EST -> {
                    if(this.y > 0) {
                        return new Position(this.x, this.y - 1);
                    }
                }
                case EST -> {
                    if(this.x<30) {
                        return new Position(this.x + 1, this.y);
                    }
                }
                case SUD_EST -> {
                    if(this.y < 30) {
                        return new Position(this.x, this.y + 1);
                    }
                }
                case SUD_OUEST -> {
                    if(this.x>0 && this.y < 30) {
                        return new Position(this.x - 1, this.y + 1);
                    }
                }
                case OUEST -> {
                    if(this.x > 0) {
                        return new Position(this.x - 1, this.y);
                    }
                }
                case NORD_OUEST -> {
                    if(this.x > 0 && this.y > 0) {
                        return new Position(this.x - 1, this.y - 1);
                    }
                }
                default -> {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object p) {
        if(p instanceof Position) {
            return (((Position) p).getX() == this.x && ((Position) p).getY() == this.y);
        }
        return false;
    }
}
