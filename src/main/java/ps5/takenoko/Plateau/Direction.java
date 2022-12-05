package ps5.takenoko.Plateau;

/**
 *
 *                ■■
 *  NordOuest  ■■    ■■  NordEst
 *           ■■        ■■
 *         ■■            ■■
 * Ouest   ■■            ■■  Est
 *         ■■            ■■
 *           ■■        ■■
 SudOuest     ■■    ■■    SudEst
 *                ■■
 */
public enum Direction {
    NORD_EST,
    EST,
    SUD_EST,
    SUD_OUEST,
    OUEST,
    NORD_OUEST;

    /**
     * 0-> return NordEst
     * 1 -> return Est
     * 2-> return SudEst
     * ...
     */
    private static Direction directionDeNum(int i) {
        if (i < 0 || 5 < i) {
            throw new RuntimeException("Le numero de la direction doit etre entre 0 et 5");
        }
        return Direction.values()[i];
    }

    public static Position getPositionByDirection(Position p, Direction d) {
        if(p.getY() % 2 == 0) {
            switch (d) {
                case NORD_EST -> {
                    return new Position(p.getX()+1,p.getY()-1);
                }
                case EST -> {
                    return new Position(p.getX()+1,p.getY());
                }
                case SUD_EST -> {
                    return new Position(p.getX()+1,p.getY()+1);
                }
                case SUD_OUEST -> {
                    return new Position(p.getX(),p.getY()+1);
                }
                case OUEST -> {
                    return new Position(p.getX()-1,p.getY());
                }
                case NORD_OUEST -> {
                    return new Position(p.getX()-1,p.getY());
                }
                default -> throw new IllegalArgumentException();
            }
        } else {
            switch (d) {
                case NORD_EST -> {
                    return new Position(p.getX(),p.getY()-1);
                }
                case EST -> {
                    return new Position(p.getX()+1,p.getY());
                }
                case SUD_EST -> {
                    return new Position(p.getX(),p.getY()+1);
                }
                case SUD_OUEST -> {
                    return new Position(p.getX()-1,p.getY()+1);
                }
                case OUEST -> {
                    return new Position(p.getX()-1,p.getY());
                }
                case NORD_OUEST -> {
                    return new Position(p.getX()-1,p.getY()-1);
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }
    /**
     * NordEst -> return 0
     * Est -> return 1
     * SudEst -> return 2
     * ...
     */
    public int numDirection() {
        return this.ordinal();
    }
}