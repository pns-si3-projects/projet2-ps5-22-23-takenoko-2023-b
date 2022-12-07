package ps5.takenoko.Plateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<Position> endroitsPosables = plateau.getEndroitsPosables();

        for(int i=0; i<31; i++) {
            for(int j=0; j<31; j++) {
                if(endroitsPosables.contains(new Position(i,j))) {
                    assertTrue(plateau.isPosable(new Position(i,j)));
                } else {
                    assertFalse(plateau.isPosable(new Position(i,j)));
                }
            }
        }
    }

    @Test
    void getParcelle() {
        Position pos = new Position(5,5);
        Position posorigine = new Position(15,15);
        Parcelle par1 = new Parcelle();
        par1.setCouleur(Color.JAUNE);

        assertTrue(plateau.getParcelle(pos) instanceof ParcelleInactive);
        assertTrue(plateau.getParcelle(posorigine) instanceof ParcelleOriginelle);

        try {
            plateau.addParcelle(par1, pos);
        } catch (IllegalAccessException e) {
        }

        assertTrue(plateau.getParcelle(pos) instanceof Parcelle);
        assertTrue(((Parcelle) plateau.getParcelle(pos)).getCouleur() == Color.JAUNE);
    }

    @Test
    void isPosable() {
        Position pos = new Position(0,0);
        Position posCoteCentre = new Position(14,15);
        assertFalse(plateau.isPosable(pos));
        assertTrue(plateau.isPosable(posCoteCentre));

        Parcelle par1 = new Parcelle();
        Parcelle par2 = new Parcelle();
        Position pos1 = new Position(1,0);
        Position pos2 = new Position(0,1);

        try{
            plateau.addParcelle(par1,pos1);
            plateau.addParcelle(par2,pos2);
            assertTrue(plateau.isPosable(pos));
            assertFalse(plateau.isPosable(pos1));

        } catch (IllegalAccessException e) {
        }
    }
}