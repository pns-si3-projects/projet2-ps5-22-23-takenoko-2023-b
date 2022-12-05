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