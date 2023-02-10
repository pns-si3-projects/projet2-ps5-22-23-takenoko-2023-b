package groupeb.takenoko.personnage;
import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PandaTest {
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

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    void deplacer() {
        Panda panda = new Panda(new Position(15,15));

        Parcelle p1 = (Parcelle) plateau.getParcelle(new Position(14,14));
        Parcelle p2 = (Parcelle) plateau.getParcelle(new Position(13,14));
        Parcelle p3 = (Parcelle) plateau.getParcelle(new Position(15,13));
        p1.setNbBamboo(4);
        p2.setNbBamboo(3);
        assertEquals(4,p1.getNbBamboo());
        assertEquals(3,p2.getNbBamboo());
        assertEquals(0,p3.getNbBamboo());

        panda.deplacer(new Position(14,14),plateau);
        panda.deplacer(new Position(15,13),plateau);
        panda.deplacer(new Position(14,14),plateau);

        assertEquals(2,p1.getNbBamboo());
        assertEquals(3,p2.getNbBamboo());
        assertEquals(0,p3.getNbBamboo());
    }
}
