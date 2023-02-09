package ps5.takenoko.plateau;

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
     * NordEst -> return 0
     * Est -> return 1
     * SudEst -> return 2
     * ...
     */
    public int numDirection() {
        return this.ordinal();
    }
}