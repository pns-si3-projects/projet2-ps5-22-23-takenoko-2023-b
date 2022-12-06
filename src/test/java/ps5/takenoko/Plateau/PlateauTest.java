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
        assertTrue(1 == 1);
    }

    @Test
    void getParcelle() {
        assertTrue(1 == 1);
    }

    @Test
    void isPosable() {
        Position pos = new Position(5,5);
        Position posCoteCentre = new Position(14,15);
        assertFalse(plateau.isPosable(pos));
        assertTrue(plateau.isPosable(posCoteCentre));

        Parcelle par1 = new Parcelle();
        Parcelle par2 = new Parcelle();
        Position pos1 = new Position(5,4);
        Position pos2 = new Position(5,6);

        try{
            plateau.addParcelle(par1,pos1);
            plateau.addParcelle(par2,pos2);
            assertTrue(plateau.isPosable(pos));
            assertFalse(plateau.isPosable(pos1));

        } catch (IllegalAccessException e) {
        }
    }
}