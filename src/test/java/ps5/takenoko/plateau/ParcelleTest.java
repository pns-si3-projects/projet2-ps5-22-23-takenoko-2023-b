package ps5.takenoko.plateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleTest {
    Parcelle parcelle;
    Parcelle bassin;
    Parcelle enclos;
    Parcelle engrais;
    @BeforeEach
    void init(){
        parcelle = new Parcelle(Couleur.VERT);
        bassin = new Parcelle(Couleur.JAUNE, new Amenagement(AmenagementType.BASSIN));
        enclos = new Parcelle(Couleur.ROSE, new Amenagement(AmenagementType.ENCLOS));
        engrais = new Parcelle(Couleur.VERT, new Amenagement(AmenagementType.ENGRAIS));
    }

    @Test
    void irrigue() {
        assertFalse(parcelle.estIrrigue());
        assertTrue(bassin.estIrrigue());
        assertFalse(enclos.estIrrigue());
        assertFalse(engrais.estIrrigue());

        parcelle.irrigue();
        enclos.irrigue();
        engrais.irrigue();
        assertTrue(parcelle.estIrrigue());
        assertTrue(enclos.estIrrigue());
        assertTrue(engrais.estIrrigue());


    }

    @Test
    void getCouleur() {
        assertEquals(Couleur.VERT,parcelle.getCouleur());
        assertEquals(Couleur.JAUNE,bassin.getCouleur());
        assertEquals(Couleur.ROSE,enclos.getCouleur());
    }

    @Test
    void getNbBamboo() {
        assertEquals(0,parcelle.getNbBamboo());
        assertEquals(1,bassin.getNbBamboo());
    }

    @Test
    void setNbBamboo() {
        assertEquals(0,parcelle.getNbBamboo());
        parcelle.setNbBamboo(3);
        assertEquals(3,parcelle.getNbBamboo());
    }

    @Test
    void setCouleur() {

        assertEquals(Couleur.VERT,parcelle.getCouleur());
        parcelle.setCouleur(Couleur.JAUNE);
        assertEquals(Couleur.JAUNE,parcelle.getCouleur());
    }

    @Test
    void setAmenagement() {
        assertEquals(new Amenagement(),parcelle.getAmenagement());
        Amenagement amm = new Amenagement(AmenagementType.BASSIN);
        parcelle.setAmenagement(amm);
        assertEquals(amm,parcelle.getAmenagement());
    }

    @Test
    void augmenteBamboo() {
        irrigue();
        parcelle.augmenteBamboo();
        bassin.augmenteBamboo();
        enclos.augmenteBamboo();
        engrais.augmenteBamboo();
        assertEquals(2,parcelle.getNbBamboo());
        assertEquals(2,bassin.getNbBamboo());
        assertEquals(2,enclos.getNbBamboo());
        assertEquals(4,engrais.getNbBamboo());
    }

    @Test
    void mangerBambou() {
        augmenteBamboo();
        parcelle.mangerBambou();
        bassin.mangerBambou();
        enclos.mangerBambou();
        engrais.mangerBambou();
        assertEquals(1,parcelle.getNbBamboo());
        assertEquals(1,bassin.getNbBamboo());
        assertEquals(2,enclos.getNbBamboo());
        assertEquals(3,engrais.getNbBamboo());

    }

    @Test
    void pouvoirAugmenter() {

        assertFalse(parcelle.pouvoirAugmenter());
        assertFalse(engrais.pouvoirAugmenter());

        parcelle.irrigue();
        engrais.irrigue();
        assertTrue(parcelle.pouvoirAugmenter());
        assertTrue(engrais.pouvoirAugmenter());

        engrais.setNbBamboo(3);
        assertTrue(parcelle.pouvoirAugmenter());

        parcelle.setNbBamboo(4);
        engrais.setNbBamboo(4);
        assertFalse(parcelle.pouvoirAugmenter());
        assertFalse(engrais.pouvoirAugmenter());
    }
}