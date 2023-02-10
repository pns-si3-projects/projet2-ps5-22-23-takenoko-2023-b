package groupeb.takenoko.objectif;

import groupeb.takenoko.bot.Bot;
import org.junit.jupiter.api.Test;
import groupeb.takenoko.plateau.Couleur;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifTest {

    public class ObjectifConcret extends Objectif {
        public ObjectifConcret(String description, int point) {
            super(description, point);
        }
        public ObjectifConcret(String description, int point,Couleur couleur ) {
            super(description, point, couleur);
        }
        public ObjectifConcret(String description, int point,Couleur[] couleurs) {
            super(description, point, couleurs);
        }

        @Override
        public boolean verifie(Bot j) {
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
        ObjectifConcret objectif1 = new ObjectifConcret("objectif1",1,Couleur.VERT);
        ObjectifConcret objectif2 = new ObjectifConcret("objectif1",1, new Couleur[]{Couleur.VERT, Couleur.JAUNE});

        assertEquals("ObjectifConcret de couleur VERT. Cet objectif vaut 1 points", objectif1.toString());
        assertEquals("ObjectifConcret de couleur VERT et JAUNE. Cet objectif vaut 1 points", objectif2.toString());
    }

    @Test
    void testEmpereur(){
        Empereur empereur = new Empereur();
        assertEquals("Empereur", empereur.toString());
        assertTrue(empereur.verifie(null));
    }
}