package ps5.takenoko.plateau;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BordureTest {

    @Test
    void testEquals() {
        Bordure border0 = new Bordure(new Position(15,16), new Position(15,15));
        Bordure border1 = new Bordure(new Position(15,15), new Position(15,16));
        Bordure border2 = new Bordure(new Position(15,15), new Position(15,16));
        assertTrue(border0.equals(border1));
        assertTrue(border0.equals(border2));
    }
}