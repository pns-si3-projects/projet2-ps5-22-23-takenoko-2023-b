package ps5.takenoko.Plateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        assertFalse(plateau.getParcelle(pos) instanceof Parcelle);
        plateau.addParcelle(par,pos);
        assertTrue(plateau.getParcelle(pos) instanceof Parcelle);
    }

    @Test
    void endroitsPosables() {
        Set<Position> endroitsPosables = plateau.getEndroitsPosables();
        for(int i=0; i<31; i++) {
            for(int j=0; j<31; j++) {
                if(endroitsPosables.contains(new Position(i,j))) {
                    assertTrue(plateau.positionPosable(new Position(i,j)));
                } else {
                    assertFalse(plateau.positionPosable(new Position(i,j)));
                }
            }
        }
    }

    @Test
    void getParcelle() {
        Position pos = new Position(5,5);
        Position posorigine = new Position(15,15);
        Parcelle par1 = new Parcelle();
        par1.setCouleur(Couleur.JAUNE);

        assertTrue(plateau.getParcelle(pos) instanceof ParcelleInactive);
        assertTrue(plateau.getParcelle(posorigine) instanceof ParcelleOriginelle);

        plateau.addParcelle(par1, pos);

        assertTrue(plateau.getParcelle(pos) instanceof Parcelle);
        assertTrue(((Parcelle) plateau.getParcelle(pos)).getCouleur() == Couleur.JAUNE);
    }

    @Test
    void isPosable() {
        Position pos = new Position(0,0);
        Position posCoteCentre = new Position(14,15);
        assertFalse(plateau.positionPosable(pos));
        assertTrue(plateau.positionPosable(posCoteCentre));

        Parcelle par1 = new Parcelle();
        Parcelle par2 = new Parcelle();
        Position pos1 = new Position(1,0);
        Position pos2 = new Position(0,1);
        plateau.addParcelle(par1,pos1);
        plateau.addParcelle(par2,pos2);
        assertTrue(plateau.positionPosable(pos));
        assertFalse(plateau.positionPosable(pos1));
    }

}