package ps5.takenoko.Objectif;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifParcelleTest {
    Plateau board = new Plateau();
    Plateau boardLigne = new Plateau();
    Plateau boardCourbe = new Plateau();
    Plateau boardTriangle = new Plateau();
    Plateau boardLosange = new Plateau();
    ObjectifParcelle objLigneR = new ObjectifParcelle(3,Shape.LIGNE,Couleur.ROSE);
    ObjectifParcelle objLigneJ = new ObjectifParcelle(3,Shape.LIGNE,Couleur.JAUNE);
    ObjectifParcelle objLigneV = new ObjectifParcelle(3,Shape.LIGNE,Couleur.VERT);

    ObjectifParcelle objCourbeR = new ObjectifParcelle(3,Shape.COURBE,Couleur.ROSE);
    ObjectifParcelle objCourbeJ = new ObjectifParcelle(3,Shape.COURBE,Couleur.JAUNE);
    ObjectifParcelle objCourbeV = new ObjectifParcelle(3,Shape.COURBE,Couleur.VERT);

    ObjectifParcelle objTriangleR = new ObjectifParcelle(3,Shape.TRIANGLE,Couleur.ROSE);
    ObjectifParcelle objTriangleJ = new ObjectifParcelle(3,Shape.TRIANGLE,Couleur.JAUNE);
    ObjectifParcelle objTriangleV = new ObjectifParcelle(3,Shape.TRIANGLE,Couleur.VERT);

    ObjectifParcelle objLosangeR = new ObjectifParcelle(3,Shape.LOSANGE,Couleur.ROSE,Couleur.VERT);
    ObjectifParcelle objLosangeJ = new ObjectifParcelle(3,Shape.LOSANGE,Couleur.VERT,Couleur.ROSE);
    ObjectifParcelle objLosangeV = new ObjectifParcelle(3,Shape.LOSANGE,Couleur.JAUNE,Couleur.VERT);

    @BeforeEach
    void init() throws IllegalAccessException {
        Parcelle parcelleR = new Parcelle(Couleur.ROSE);
        Parcelle parcelleJ = new Parcelle(Couleur.JAUNE);
        Parcelle parcelleV = new Parcelle(Couleur.VERT);
        initLigneBoard(parcelleR,parcelleJ,parcelleV);
        initCourbeBoard(parcelleR,parcelleJ,parcelleV);
        initTriangleBoard(parcelleR,parcelleJ,parcelleV);
        initLosangeBoard(parcelleR,parcelleJ,parcelleV);
    }
    @Test
    void verifieLigne() {
        assertFalse(objLigneJ.verifie(board));
        assertFalse(objLigneR.verifie(board));
        assertFalse(objLigneV.verifie(board));
        assertTrue(objLigneJ.verifie(boardLigne));
        assertTrue(objLigneR.verifie(boardLigne));
        assertTrue(objLigneV.verifie(boardLigne));

        assertFalse(objLigneJ.verifie(boardCourbe));
        assertFalse(objLigneR.verifie(boardCourbe));
        assertFalse(objLigneV.verifie(boardCourbe));

        assertFalse(objLigneJ.verifie(boardTriangle));
        assertFalse(objLigneR.verifie(boardTriangle));
        assertFalse(objLigneV.verifie(boardTriangle));

        assertFalse(objLigneJ.verifie(boardLosange));
        assertFalse(objLigneR.verifie(boardLosange));
        assertFalse(objLigneV.verifie(boardLosange));
    }
    @Test
    void verifieCourbe() {
        assertFalse(objCourbeJ.verifie(board));
        assertFalse(objCourbeR.verifie(board));
        assertFalse(objCourbeV.verifie(board));
        assertTrue(objCourbeJ.verifie(boardCourbe));
        assertTrue(objCourbeR.verifie(boardCourbe));
        assertTrue(objCourbeV.verifie(boardCourbe));


        assertFalse(objCourbeJ.verifie(boardLigne));
        assertFalse(objCourbeR.verifie(boardLigne));
        assertFalse(objCourbeV.verifie(boardLigne));

        assertFalse(objCourbeJ.verifie(boardTriangle));
        assertFalse(objCourbeR.verifie(boardTriangle));
        assertFalse(objCourbeV.verifie(boardTriangle));

        assertFalse(objCourbeJ.verifie(boardLosange));
        assertFalse(objCourbeR.verifie(boardLosange));
        assertFalse(objCourbeV.verifie(boardLosange));

    }
    @Test
    void verifieTriangle() {
        assertFalse(objTriangleJ.verifie(board));
        assertFalse(objTriangleR.verifie(board));
        assertFalse(objTriangleV.verifie(board));
        assertTrue(objTriangleJ.verifie(boardTriangle));
        assertTrue(objTriangleR.verifie(boardTriangle));
        assertFalse(objTriangleV.verifie(boardTriangle));


        assertFalse(objTriangleJ.verifie(boardLigne));
        assertFalse(objTriangleR.verifie(boardLigne));
        assertFalse(objTriangleV.verifie(boardLigne));

        assertFalse(objTriangleJ.verifie(boardCourbe));
        assertFalse(objTriangleR.verifie(boardCourbe));
        assertFalse(objTriangleV.verifie(boardCourbe));

        assertFalse(objTriangleJ.verifie(boardLosange));
        assertFalse(objTriangleR.verifie(boardLosange));
        assertFalse(objTriangleV.verifie(boardLosange));

    }
    @Test
    void verifieLosange() {
        assertFalse(objLosangeJ.verifie(board));
        assertFalse(objLosangeR.verifie(board));
        assertFalse(objLosangeV.verifie(board));
        assertTrue(objLosangeJ.verifie(boardLosange));
        assertTrue(objLosangeR.verifie(boardLosange));
        assertTrue(objLosangeV.verifie(boardLosange));


        assertFalse(objLosangeJ.verifie(boardLigne));
        assertFalse(objLosangeR.verifie(boardLigne));
        assertFalse(objLosangeV.verifie(boardLigne));

        assertFalse(objLosangeJ.verifie(boardCourbe));
        assertFalse(objLosangeR.verifie(boardCourbe));
        //assertFalse(objLosangeV.verifie(boardCourbe)); //Car il est present dans le plateau
        
        assertFalse(objLosangeJ.verifie(boardTriangle));
        assertFalse(objLosangeR.verifie(boardTriangle));
        assertFalse(objLosangeV.verifie(boardTriangle));
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