package ps5.takenoko.Objectif;

import org.junit.jupiter.api.Test;
import ps5.takenoko.Jeu.Jeu;
import ps5.takenoko.Jeu.ObjectifList;
import ps5.takenoko.Jeu.ParcelleList;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifJardinierTest {



    @Test
    void verifie() {
        Plateau board = new Plateau();
        Jeu game = new Jeu(new ArrayList<Joueur>(),board,new Position(15,15),new Position(15,15),new ObjectifList(), new ParcelleList(),3);

        Parcelle tileRed3 = new Parcelle(Couleur.ROSE);
        tileRed3.setNbBamboo(3);
        Parcelle tileYellow3 = new Parcelle(Couleur.JAUNE);
        tileYellow3.setNbBamboo(3);
        Parcelle tileGreen3 = new Parcelle(Couleur.VERT);
        tileGreen3.setNbBamboo(3);
        Parcelle tileRed4 = new Parcelle(Couleur.ROSE);
        tileRed4.setNbBamboo(4);
        Parcelle tileYellow4 = new Parcelle(Couleur.JAUNE);
        tileYellow4.setNbBamboo(4);
        Parcelle tileGreen4 = new Parcelle(Couleur.VERT);
        tileGreen4.setNbBamboo(4);
        board.addParcelle(tileRed4,new Position(16,15));
        board.addParcelle(tileYellow3,new Position(14,15));
        board.addParcelle(tileYellow3,new Position(15,16));
        board.addParcelle(tileGreen3,new Position(14,16));
        board.addParcelle(tileGreen4,new Position(15,14));
        board.addParcelle(tileYellow3,new Position(14,14));
        board.addParcelle(tileYellow3,new Position(16,16));

        Objectif objGreenNoA= new ObjectifJardinier(TypeObjJardinier.NOAMMENAGEMENT,Couleur.VERT);
        Objectif objRedNoA= new ObjectifJardinier(TypeObjJardinier.NOAMMENAGEMENT,Couleur.ROSE);
        Objectif objYellowNoA= new ObjectifJardinier(TypeObjJardinier.NOAMMENAGEMENT,Couleur.JAUNE);
        Objectif objGreenMult= new ObjectifJardinier(TypeObjJardinier.MULTIPLE,Couleur.VERT);
        Objectif objRedMult= new ObjectifJardinier(TypeObjJardinier.MULTIPLE,Couleur.ROSE);
        Objectif objYellowMult= new ObjectifJardinier(TypeObjJardinier.MULTIPLE,Couleur.JAUNE);
        System.out.println(game.affichePlateau());

        assertTrue(objRedNoA.verifie(board));
        assertTrue(objGreenNoA.verifie(board));
        assertFalse(objYellowNoA.verifie(board));

        assertFalse(objRedMult.verifie(board));
        assertFalse(objGreenMult.verifie(board));
        assertTrue(objYellowMult.verifie(board));



    }
}