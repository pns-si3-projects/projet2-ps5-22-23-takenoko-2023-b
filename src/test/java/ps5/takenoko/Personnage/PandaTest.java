package ps5.takenoko.Personnage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.Plateau.*;

import java.util.Set;

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
}
