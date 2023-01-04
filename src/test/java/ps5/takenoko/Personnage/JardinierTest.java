package ps5.takenoko.Personnage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.Plateau.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JardinierTest {

    Plateau plateau;
    @BeforeEach
    void CreatePlateau() {
        plateau = new Plateau();
        try{
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,13));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(14,14));
            plateau.addParcelle(new Parcelle(Couleur.VERT),new Position(15,14));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(14,15));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(16,15));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(17,15));

            plateau.addParcelle(new Parcelle(Couleur.VERT),new Position(14,16));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,16));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,17));

            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(13,14));

            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(16,16));

        }catch(Exception e){System.out.println(e);}
        plateau.affichePlateau();
    }


    @Test
    void endroitsDeplacable() {
        Jardinier deplacable = new Jardinier(new Position(15,15));
        Set<Position> endroitsDeplacable = deplacable.posPossibles(plateau);
        for(Direction d : Direction.values()) {
            System.out.println(d);
            assertTrue(endroitsDeplacable.contains(deplacable.getPosition().getPositionByDirection(d)));
        }
        assertTrue(endroitsDeplacable.contains(new Position(17,15)));
        assertFalse(endroitsDeplacable.contains(new Position(17,17)));
    }

    @Test
    void deplacer() {
        Jardinier deplacable = new Jardinier(new Position(15,15));
        deplacable.deplacer(new Position(14,14),plateau);

        assertEquals(1,((Parcelle)plateau.getParcelle(new Position(14,14))).getNbBamboo());
        assertEquals(1,((Parcelle)plateau.getParcelle(new Position(13,14))).getNbBamboo());
        assertEquals(1,((Parcelle)plateau.getParcelle(new Position(15,13))).getNbBamboo());
        assertEquals(0,((Parcelle)plateau.getParcelle(new Position(15,14))).getNbBamboo());
        assertEquals(0,((Parcelle)plateau.getParcelle(new Position(15,16))).getNbBamboo());
    }
}