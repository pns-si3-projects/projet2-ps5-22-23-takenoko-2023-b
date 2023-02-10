package groupeb.takenoko.personnage;

import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
            for(Position pos : plateau.getParcellePosee()){
                if(plateau.getParcelle(pos) instanceof Parcelle parcelle)parcelle.irrigue();
            }

        }catch(Exception e){System.out.println(e);}
    }


    @Test
    void deplacer() {
        Jardinier deplacable = new Jardinier(new Position(15,15));
        deplacable.deplacer(new Position(14,14),plateau);

        assertEquals(2,((Parcelle)plateau.getParcelle(new Position(14,14))).getNbBamboo());
        assertEquals(2,((Parcelle)plateau.getParcelle(new Position(13,14))).getNbBamboo());
        assertEquals(2,((Parcelle)plateau.getParcelle(new Position(15,13))).getNbBamboo());
        assertEquals(1,((Parcelle)plateau.getParcelle(new Position(15,14))).getNbBamboo());
        assertEquals(1,((Parcelle)plateau.getParcelle(new Position(15,16))).getNbBamboo());
    }
}