package ps5.takenoko.Jeu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifListTest {
    @Test
    void initList(){
        ObjectifList objList = new ObjectifList();
        assertEquals(3,objList.list.size());
        /*
        //PARCELLE
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.VERT})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.JAUNE})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.ROSE})));

        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.COURBE ,new Couleur[]{Couleur.VERT})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.COURBE , new Couleur[]{Couleur.JAUNE})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.COURBE ,new Couleur[]{Couleur.ROSE})));

        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.TRIANGLE ,new Couleur[]{Couleur.VERT})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.TRIANGLE , new Couleur[]{Couleur.JAUNE})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.TRIANGLE ,new Couleur[]{Couleur.ROSE})));

        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.LOSANGE ,new Couleur[]{Couleur.JAUNE,Couleur.VERT})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.LOSANGE , new Couleur[]{Couleur.JAUNE,Couleur.ROSE})));
        assertTrue(objList.hasObj(new ObjectifParcelle(Shape.LOSANGE ,new Couleur[]{Couleur.ROSE,Couleur.VERT})));

        //JARDINIER
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJVIDE ,Couleur.VERT)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJVIDE ,Couleur.JAUNE)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJVIDE ,Couleur.ROSE)));

        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN ,Couleur.VERT)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN ,Couleur.JAUNE)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN ,Couleur.ROSE)));

        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJENGRAIS ,Couleur.VERT)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJENGRAIS ,Couleur.JAUNE)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJENGRAIS ,Couleur.ROSE)));

        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJENCLOS ,Couleur.VERT)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJENCLOS ,Couleur.JAUNE)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJENCLOS ,Couleur.ROSE)));

        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT ,Couleur.VERT)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE ,Couleur.JAUNE)));
        assertTrue(objList.hasObj(new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE ,Couleur.ROSE)));

        //PANDA
        assertTrue(objList.hasObj(new ObjectifPanda(2,new Couleur[]{Couleur.VERT},2)));
        assertTrue(objList.hasObj(new ObjectifPanda(3,new Couleur[]{Couleur.JAUNE},3)));
        assertTrue(objList.hasObj(new ObjectifPanda(4,new Couleur[]{Couleur.ROSE},4)));
        */
    }
}