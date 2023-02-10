package groupeb.takenoko.lanceur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVStatsTest {
    private CSVStats csvStats;

    @BeforeEach
    void setUp() {
        csvStats = new CSVStats();
    }

    @Test
    void calculateMoyenne() {
        assertEquals("6.0",csvStats.calculateMoyenne("7", "5"));
    }

    @Test
    void calculateSum() {
        assertEquals("12.0",csvStats.calculateSum("7", "5"));
    }

    @Test
    void toStringArray() {
        csvStats.setJoueurType("JoueurType");
        csvStats.update("JoueurType,5,60,7,7,9,7,40,1");
        String[] expected = {"JoueurType", "5", "60", "7", "7", "9", "7", "40", "1"};
        assertArrayEquals(csvStats.toStringArray(), expected);
    }
}