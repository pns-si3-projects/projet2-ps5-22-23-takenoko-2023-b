package groupeb.takenoko.plateau;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Position posPair = new Position(2,2);
    Position posPairNE = new Position(3,1);
    Position posPairE = new Position(3,2);
    Position posPairSE = new Position(3,3);
    Position posPairSO = new Position(2,3);
    Position posPairO = new Position(1,2);
    Position posPairNO = new Position(2,1);

    Position posImpair = new Position(3,3);
    Position posImpairNE = new Position(3,2);
    Position posImpairE = new Position(4,3);
    Position posImpairSE = new Position(3,4);
    Position posImpairSO = new Position(2,4);
    Position posImpairO = new Position(2,3);
    Position posImpairNO = new Position(2,2);

    @Test
    void getPositionByDirection() {
        Position[] tabPair = {
            posPairNE,
            posPairE,
            posPairSE,
            posPairSO,
            posPairO,
            posPairNO
        };
        Position[] tabImpair = {
            posImpairNE,
            posImpairE,
            posImpairSE,
            posImpairSO,
            posImpairO,
            posImpairNO
        };
        for(Direction dir : Direction.values()){
            assertEquals(posPair.getPositionByDirection(dir),tabPair[dir.ordinal()]);
            assertEquals(posImpair.getPositionByDirection(dir),tabImpair[dir.ordinal()]);
        }

    }

    @Test
    void testEquals() {
        assertEquals(new Position(2,2),posPair);
        assertEquals(posPair,posPair);
        assertNotEquals(posPair,posImpair);
        assertNotEquals(new Position(3,2),posPair);
        assertNotEquals(new Position(2,3),posPair);
        assertNotEquals(new Position(3,3),posPair);
    }

    @Test
    void testToString() {
        assertEquals("(2,2)", posPair.toString());
    }
}