package ps5.takenoko.Objectif;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifParcelleTest {
    Plateau board = new Plateau();
    Plateau boardLine = new Plateau();
    Plateau boardCurve = new Plateau();
    Plateau boardTriangle = new Plateau();
    Plateau boardLosange = new Plateau();

    @BeforeAll
    void init() throws IllegalAccessException {
        Parcelle parcelleR = new Parcelle(Couleur.ROSE);
        Parcelle parcelleJ = new Parcelle(Couleur.JAUNE);
        Parcelle parcelleV = new Parcelle(Couleur.VERT);
        boardLine.addParcelle(parcelleR,new Position(16,15));
        boardLine.addParcelle(parcelleR,new Position(15,14));
        boardLine.addParcelle(parcelleR,new Position(15,13));

        boardLine.addParcelle(parcelleJ,new Position(13,16));
        boardLine.addParcelle(parcelleJ,new Position(14,15));
        boardLine.addParcelle(parcelleJ,new Position(14,14));

        boardLine.addParcelle(parcelleV,new Position(16,16));
        boardLine.addParcelle(parcelleV,new Position(16,16));
        boardLine.addParcelle(parcelleV,new Position(16,16));

    }
    @Test
    void verifie() {
    }
}