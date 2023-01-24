package ps5.takenoko.objectif;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectifParcelleTest {
    Plateau board = new Plateau();
    Plateau boardLigne = new Plateau();
    Plateau boardCourbe = new Plateau();
    Plateau boardTriangle = new Plateau();
    Plateau boardLosange = new Plateau();
    ObjectifParcelle objLigneR = new ObjectifParcelle(Shape.LIGNE, new Couleur[]{Couleur.ROSE});
    ObjectifParcelle objLigneJ = new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.JAUNE});
    ObjectifParcelle objLigneV = new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.VERT});

    ObjectifParcelle objCourbeR = new ObjectifParcelle(Shape.COURBE,new Couleur[]{Couleur.ROSE});
    ObjectifParcelle objCourbeJ = new ObjectifParcelle(Shape.COURBE,new Couleur[]{Couleur.JAUNE});
    ObjectifParcelle objCourbeV = new ObjectifParcelle(Shape.COURBE,new Couleur[]{Couleur.VERT});

    ObjectifParcelle objTriangleR = new ObjectifParcelle(Shape.TRIANGLE,new Couleur[]{Couleur.ROSE});
    ObjectifParcelle objTriangleJ = new ObjectifParcelle(Shape.TRIANGLE,new Couleur[]{Couleur.JAUNE});
    ObjectifParcelle objTriangleV = new ObjectifParcelle(Shape.TRIANGLE,new Couleur[]{Couleur.VERT});

    ObjectifParcelle objLosangeR = new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.ROSE,Couleur.VERT});
    ObjectifParcelle objLosangeJ = new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.VERT,Couleur.ROSE});
    ObjectifParcelle objLosangeV = new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.VERT});
    Jeu game1;
    Jeu game2;
    Jeu game3;
    Jeu game4;
    Jeu game5;
    JoueurRandom player = new JoueurRandom(0);
    JoueurRandom playerLigne = new JoueurRandom(1);
    JoueurRandom playerCourbe = new JoueurRandom(2);
    JoueurRandom playerTriangle = new JoueurRandom(3);
    JoueurRandom playerLosange = new JoueurRandom(4);

    @BeforeEach
    void init() throws IllegalAccessException {
        Parcelle parcelleR = new Parcelle(Couleur.ROSE);
        Parcelle parcelleJ = new Parcelle(Couleur.JAUNE);
        Parcelle parcelleV = new Parcelle(Couleur.VERT);
        parcelleR.irrigue();
        parcelleJ.irrigue();
        parcelleV.irrigue();
        ArrayList<Joueur> players1 = new ArrayList<Joueur>(); players1.add(player);         players1.add(new JoueurRandom(6));
        ArrayList<Joueur> players2 = new ArrayList<Joueur>(); players2.add(playerLigne);    players2.add(new JoueurRandom(6));
        ArrayList<Joueur> players3 = new ArrayList<Joueur>(); players3.add(playerCourbe);   players3.add(new JoueurRandom(6));
        ArrayList<Joueur> players4 = new ArrayList<Joueur>(); players4.add(playerTriangle); players4.add(new JoueurRandom(6));
        ArrayList<Joueur> players5 = new ArrayList<Joueur>(); players5.add(playerLosange);  players5.add(new JoueurRandom(6));
        Jeu game1 = new Jeu(players1);
        Jeu game2 = new Jeu(players2);
        Jeu game3 = new Jeu(players3);
        Jeu game4 = new Jeu(players4);
        Jeu game5 = new Jeu(players5);
        initLigneBoard(parcelleR,parcelleJ,parcelleV);
        initCourbeBoard(parcelleR,parcelleJ,parcelleV);
        initTriangleBoard(parcelleR,parcelleJ,parcelleV);
        initLosangeBoard(parcelleR,parcelleJ,parcelleV);
        game1.setPlateau(board);
        game2.setPlateau(boardLigne);
        game3.setPlateau(boardCourbe);
        game4.setPlateau(boardTriangle);
        game5.setPlateau(boardLosange);

        System.out.println((game1.affichePlateau()));
        System.out.println((game2.affichePlateau()));
        System.out.println((game3.affichePlateau()));
        System.out.println((game4.affichePlateau()));
        System.out.println((game5.affichePlateau()));
    }
    @Test
    void verifieLigne() {
        assertFalse(objLigneJ.verifie(player));
        assertFalse(objLigneR.verifie(player));
        assertFalse(objLigneV.verifie(player));
        assertTrue(objLigneJ.verifie(playerLigne));
        assertTrue(objLigneR.verifie(playerLigne));
        assertTrue(objLigneV.verifie(playerLigne));

        assertFalse(objLigneJ.verifie(playerCourbe));
        assertFalse(objLigneR.verifie(playerCourbe));
        assertFalse(objLigneV.verifie(playerCourbe));

        assertFalse(objLigneJ.verifie(playerTriangle));
        assertFalse(objLigneR.verifie(playerTriangle));
        assertFalse(objLigneV.verifie(playerTriangle));

        assertFalse(objLigneJ.verifie(playerLosange));
        assertFalse(objLigneR.verifie(playerLosange));
        assertFalse(objLigneV.verifie(playerLosange));
    }
    @Test
    void verifieCourbe() {
        assertFalse(objCourbeJ.verifie(player));
        assertFalse(objCourbeR.verifie(player));
        assertFalse(objCourbeV.verifie(player));
        assertTrue(objCourbeJ.verifie(playerCourbe));
        assertTrue(objCourbeR.verifie(playerCourbe));
        assertTrue(objCourbeV.verifie(playerCourbe));


        assertFalse(objCourbeJ.verifie(playerLigne));
        assertFalse(objCourbeR.verifie(playerLigne));
        assertFalse(objCourbeV.verifie(playerLigne));

        assertFalse(objCourbeJ.verifie(playerTriangle));
        assertFalse(objCourbeR.verifie(playerTriangle));
        assertFalse(objCourbeV.verifie(playerTriangle));

        assertFalse(objCourbeJ.verifie(playerLosange));
        assertFalse(objCourbeR.verifie(playerLosange));
        assertFalse(objCourbeV.verifie(playerLosange));

    }
    @Test
    void verifieTriangle() {
        assertFalse(objTriangleJ.verifie(player));
        assertFalse(objTriangleR.verifie(player));
        assertFalse(objTriangleV.verifie(player));
        assertTrue(objTriangleJ.verifie(playerTriangle));
        assertTrue(objTriangleR.verifie(playerTriangle));
        assertFalse(objTriangleV.verifie(playerTriangle));


        assertFalse(objTriangleJ.verifie(playerLigne));
        assertFalse(objTriangleR.verifie(playerLigne));
        assertFalse(objTriangleV.verifie(playerLigne));

        assertFalse(objTriangleJ.verifie(playerCourbe));
        assertFalse(objTriangleR.verifie(playerCourbe));
        assertFalse(objTriangleV.verifie(playerCourbe));

        assertFalse(objTriangleJ.verifie(playerLosange));
        assertFalse(objTriangleR.verifie(playerLosange));
        assertFalse(objTriangleV.verifie(playerLosange));

    }
    @Test
    void verifieLosange() {
        assertFalse(objLosangeJ.verifie(player));
        assertFalse(objLosangeR.verifie(player));
        assertFalse(objLosangeV.verifie(player));
        assertTrue(objLosangeJ.verifie(playerLosange));
        assertTrue(objLosangeR.verifie(playerLosange));
        assertTrue(objLosangeV.verifie(playerLosange));


        assertFalse(objLosangeJ.verifie(playerLigne));
        assertFalse(objLosangeR.verifie(playerLigne));
        assertFalse(objLosangeV.verifie(playerLigne));

        assertFalse(objLosangeJ.verifie(playerCourbe));
        assertFalse(objLosangeR.verifie(playerCourbe));
        //assertFalse(objLosangeV.verifie(playerCourbe)); //Car il est present dans le plateau

        assertFalse(objLosangeJ.verifie(playerTriangle));
        assertFalse(objLosangeR.verifie(playerTriangle));
        assertFalse(objLosangeV.verifie(playerTriangle));
    }



    private void initLigneBoard(Parcelle rose,Parcelle jaune, Parcelle vert) throws IllegalAccessException {

        boardLigne.addParcelle(rose,new Position(16,15));// E
        boardLigne.addParcelle(rose,new Position(15,16));// SE

        boardLigne.addParcelle(jaune,new Position(14,16));// SO
        boardLigne.addParcelle(jaune,new Position(14,15));// O
        boardLigne.addParcelle(vert,new Position(14,14));// NO
        boardLigne.addParcelle(vert,new Position(15,14));// NE

        boardLigne.addParcelle(rose,new Position(15,17));
        boardLigne.addParcelle(jaune,new Position(13,14));
        boardLigne.addParcelle(vert,new Position(16,14));

        System.out.println("Adjacent: "+boardLigne.nextToOrigin(new Position(13,14)));
    }
    private void initCourbeBoard(Parcelle rose,Parcelle jaune, Parcelle vert) throws IllegalAccessException {


        boardCourbe.addParcelle(rose,new Position(15,14));// NE
        boardCourbe.addParcelle(vert,new Position(16,15));// E
        boardCourbe.addParcelle(jaune,new Position(15,16));// SE
        boardCourbe.addParcelle(rose,new Position(14,16));// SO
        boardCourbe.addParcelle(vert,new Position(14,15));// O
        boardCourbe.addParcelle(jaune,new Position(14,14));// NO


        boardCourbe.addParcelle(rose,new Position(16,14));
        boardCourbe.addParcelle(rose,new Position(17,15));
        boardCourbe.addParcelle(rose,new Position(13,16));
        boardCourbe.addParcelle(rose,new Position(13,15));

        boardCourbe.addParcelle(jaune,new Position(15,17));
        boardCourbe.addParcelle(jaune,new Position(14,17));
        boardCourbe.addParcelle(jaune,new Position(15,13));
        boardCourbe.addParcelle(jaune,new Position(16,13));

        boardCourbe.addParcelle(vert,new Position(16,16));
        boardCourbe.addParcelle(vert,new Position(16,17));
        boardCourbe.addParcelle(vert,new Position(13,14));
        boardCourbe.addParcelle(vert,new Position(14,13));
    }

    private void initTriangleBoard(Parcelle rose,Parcelle jaune, Parcelle vert) throws IllegalAccessException {


        boardTriangle.addParcelle(vert,new Position(16,15));// E
        boardTriangle.addParcelle(jaune,new Position(15,16));// SE
        boardTriangle.addParcelle(jaune,new Position(14,16));// SO
        boardTriangle.addParcelle(vert,new Position(14,15));// O
        boardTriangle.addParcelle(rose,new Position(14,14));// NO
        boardTriangle.addParcelle(rose,new Position(15,14));// NE

        boardTriangle.addParcelle(rose,new Position(15,13));// NO
        boardTriangle.addParcelle(jaune,new Position(15,17));// NE
    }

    private void initLosangeBoard(Parcelle rose,Parcelle jaune, Parcelle vert) throws IllegalAccessException {


        boardLosange.addParcelle(vert,new Position(15,14));// NE
        boardLosange.addParcelle(jaune,new Position(16,15));// E
        boardLosange.addParcelle(rose,new Position(15,16));// SE
        boardLosange.addParcelle(vert,new Position(14,16));// SO
        boardLosange.addParcelle(jaune,new Position(14,15));// O
        boardLosange.addParcelle(rose,new Position(14,14));// NO

        boardLosange.addParcelle(vert,new Position(15,13));
        boardLosange.addParcelle(rose,new Position(16,13));
        boardLosange.addParcelle(jaune,new Position(16,14));
        boardLosange.addParcelle(vert,new Position(17,15));
        boardLosange.addParcelle(rose,new Position(16,16));
        boardLosange.addParcelle(jaune,new Position(16,17));
        boardLosange.addParcelle(vert,new Position(15,17));
        boardLosange.addParcelle(rose,new Position(14,17));
        boardLosange.addParcelle(jaune,new Position(13,16));
        boardLosange.addParcelle(vert,new Position(13,15));
        boardLosange.addParcelle(rose,new Position(13,14));
        boardLosange.addParcelle(jaune,new Position(14,13));

        boardLosange.addParcelle(rose,new Position(15,12));
        boardLosange.addParcelle(vert,new Position(17,14));
        boardLosange.addParcelle(jaune,new Position(17,17));
        boardLosange.addParcelle(rose,new Position(14,18));
        boardLosange.addParcelle(vert,new Position(12,16));
        boardLosange.addParcelle(jaune,new Position(13,13));

    }
}