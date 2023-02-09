package ps5.takenoko.objectif;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeObjJardinierTest {

    @Test
    void getPoint() {
        assertEquals(6,TypeObjJardinier.OBJVIDE.getPoint());
        assertEquals(5,TypeObjJardinier.OBJBASSIN.getPoint());
        assertEquals(4,TypeObjJardinier.OBJENGRAIS.getPoint());
        assertEquals(5,TypeObjJardinier.OBJENCLOS.getPoint());
        assertEquals(6,TypeObjJardinier.OBJMULTROSE.getPoint());
        assertEquals(7,TypeObjJardinier.OBJMULTJAUNE.getPoint());
        assertEquals(8,TypeObjJardinier.OBJMULTVERT.getPoint());
    }

    @Test
    void getNbBamboo() {
        assertEquals(4,TypeObjJardinier.OBJVIDE.getNbBamboo());
        assertEquals(4,TypeObjJardinier.OBJBASSIN.getNbBamboo());
        assertEquals(4,TypeObjJardinier.OBJENGRAIS.getNbBamboo());
        assertEquals(4,TypeObjJardinier.OBJENCLOS.getNbBamboo());
        assertEquals(3,TypeObjJardinier.OBJMULTROSE.getNbBamboo());
        assertEquals(3,TypeObjJardinier.OBJMULTJAUNE.getNbBamboo());
        assertEquals(3,TypeObjJardinier.OBJMULTVERT.getNbBamboo());

    }

    @Test
    void getDescription() {
        assertEquals("Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif sans ammenagement",TypeObjJardinier.OBJVIDE.getDescription());
        assertEquals("Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement bassin",TypeObjJardinier.OBJBASSIN.getDescription());
        assertEquals("Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement engrais",TypeObjJardinier.OBJENGRAIS.getDescription());
        assertEquals("Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement enclos.",TypeObjJardinier.OBJENCLOS.getDescription());
        assertEquals("Objectif: Avoir sur le plateau 2 parcelles de 3 bambous de la couleur de la carte objectif.",TypeObjJardinier.OBJMULTROSE.getDescription());
        assertEquals("Objectif: Avoir sur le plateau 3 parcelles de 3 bambous de la couleur de la carte objectif.",TypeObjJardinier.OBJMULTJAUNE.getDescription());
        assertEquals("Objectif: Avoir sur le plateau 4 parcelles de 3 bambous de la couleur de la carte objectif.",TypeObjJardinier.OBJMULTVERT.getDescription());

    }

    @Test
    void isMultiple() {
        assertFalse(TypeObjJardinier.OBJVIDE.isMultiple());
        assertFalse(TypeObjJardinier.OBJBASSIN.isMultiple());
        assertFalse(TypeObjJardinier.OBJENGRAIS.isMultiple());
        assertFalse(TypeObjJardinier.OBJENCLOS.isMultiple());
        assertTrue(TypeObjJardinier.OBJMULTROSE.isMultiple());
        assertTrue(TypeObjJardinier.OBJMULTJAUNE.isMultiple());
        assertTrue(TypeObjJardinier.OBJMULTVERT.isMultiple());

    }
}