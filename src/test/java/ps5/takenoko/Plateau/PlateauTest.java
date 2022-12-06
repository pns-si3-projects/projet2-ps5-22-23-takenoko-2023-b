package ps5.takenoko.Plateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    Plateau plateau;
    @BeforeEach
    void CreatePlateau() {
        plateau = new Plateau();
    }

    @Test
    void addParcelle() {
        Parcelle par = new Parcelle();
        Position pos = new Position(5,5);
        try {
            assertFalse(plateau.getParcelle(pos) instanceof Parcelle);
            plateau.addParcelle(par,pos);
            assertTrue(plateau.getParcelle(pos) instanceof Parcelle);
        } catch (IllegalAccessException e) {
            System.out.println("Out of bound exeption");
        }
    }

    @Test
    void endroitsPosables() {
    }

    @Test
    void getParcelle() {
    }

    @Test
    void isPosable() {
    }
}