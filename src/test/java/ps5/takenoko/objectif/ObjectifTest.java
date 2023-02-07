package ps5.takenoko.objectif;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.plateau.Couleur;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifTest {

    public class ObjectifConcret extends Objectif {
        public ObjectifConcret(String description, int point) {
            super(description, point);
        }

        @Override
        public boolean verifie(Joueur j) {
            return false;
        }
    }

    @Test
    void testEquals() {
        //tests fot Objectif
        ObjectifConcret objectif1 = new ObjectifConcret("objectif1", 1);
        ObjectifConcret objectif2 = new ObjectifConcret("objectif2", 2);
        ObjectifConcret objectif3 = new ObjectifConcret("objectif1", 1);

        assertFalse(objectif1.equals(objectif2));
        assertTrue(objectif1.equals(objectif1));
        assertTrue(objectif1.equals(objectif3));
        assertFalse(objectif1.equals(null));
    }

    @Test
    void testHashCode() {
        //tests fot Objectif HashCode
        ObjectifConcret objectif1 = new ObjectifConcret("objectif1", 1);
        ObjectifConcret objectif2 = new ObjectifConcret("objectif2", 2);
        ObjectifConcret objectif3 = new ObjectifConcret("objectif1", 1);

        assertEquals(objectif1.hashCode(), objectif3.hashCode());
        assertNotEquals(objectif2.hashCode(), objectif3.hashCode());
    }

    @Test
    void testTostring(){
        //tests fot Objectif toString
        ObjectifConcret objectif1 = new ObjectifConcret("objectif1", 1);
        assertEquals("ObjectifConcret de valeur 1", objectif1.toString());
    }
}