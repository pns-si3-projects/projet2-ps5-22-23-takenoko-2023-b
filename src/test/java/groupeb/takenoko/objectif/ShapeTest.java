package groupeb.takenoko.objectif;

import groupeb.takenoko.plateau.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void getDirections() {
        Direction[][] ligneDir = {
                {Direction.SUD_OUEST, Direction.NORD_EST}, // LIGNE_SO_NE
                {Direction.NORD_OUEST, Direction.SUD_EST}, // LIGNE_NO_SE
                {Direction.OUEST, Direction.EST} // LIGNE_O_E
        };
        Shape.LIGNE.getDirections();

        Direction[][] courbeDir = {
                {Direction.NORD_EST, Direction.OUEST}, // COURBE_NO
                {Direction.NORD_OUEST, Direction.EST}, // COURBE_NE
                {Direction.NORD_EST, Direction.SUD_EST}, // COURBE_E
                {Direction.SUD_OUEST, Direction.EST}, // COURBE_SE
                {Direction.SUD_EST, Direction.OUEST}, // COURBE_SO
                {Direction.NORD_OUEST, Direction.SUD_OUEST}// COURBE_O
        };
        Shape.COURBE.getDirections();

        Direction[][] triangleDir = {
                {Direction.SUD_OUEST, Direction.SUD_EST}, // TRIANGLE_UP
                {Direction.NORD_OUEST, Direction.NORD_EST}
        };
        Shape.TRIANGLE.getDirections();

        Direction[][] losangeDir = {
            {Direction.SUD_OUEST, Direction.SUD_EST, Direction.EST}, // LOSANGE_SO_NE
            {Direction.OUEST, Direction.SUD_OUEST, Direction.SUD_EST}, // LOSANGE_SE_NO
            {Direction.NORD_EST, Direction.EST, Direction.SUD_OUEST}
        };
        for(int i=0;i<6;i++)for(int y=0;y<3;y++) {
            if (y < 2) {
                if (i < 2) Assertions.assertEquals(triangleDir[i][y], Shape.TRIANGLE.getDirections()[i][y]);
                if (i < 3) Assertions.assertEquals(ligneDir[i][y], Shape.LIGNE.getDirections()[i][y]);
                Assertions.assertEquals(courbeDir[i][y], Shape.COURBE.getDirections()[i][y]);


            }
            if (i < 3) {
                Assertions.assertEquals(losangeDir[i][y], Shape.LOSANGE.getDirections()[i][y]);
            }
        }
    }

    @Test
    void getDescription() {
        assertEquals("Objectif: Une ligne de 3 parcelle de la meme couleur",Shape.LIGNE.getDescription());
        assertEquals("Objectif: Une courbe de 3 parcelle de la meme couleur",Shape.COURBE.getDescription());
        assertEquals("Objectif: Un triangle de 3 parcelle de la meme couleur",Shape.TRIANGLE.getDescription());
        assertEquals("Objectif: Un losange de 2 paires de 2 parcelles de la meme couleur",Shape.LOSANGE.getDescription());

    }

    @Test
    void getPoint() {
        assertEquals(3,Shape.LIGNE.getPoint());
        assertEquals(3,Shape.COURBE.getPoint());
        assertEquals(3,Shape.TRIANGLE.getPoint());
        assertEquals(4,Shape.LOSANGE.getPoint());

    }

}