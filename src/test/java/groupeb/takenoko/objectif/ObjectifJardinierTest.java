package groupeb.takenoko.objectif;

import groupeb.takenoko.bot.Bot;
import groupeb.takenoko.bot.BotRandom;
import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;
import org.junit.jupiter.api.Test;
import groupeb.takenoko.jeu.Jeu;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifJardinierTest {



    @Test
    void verifie() {
        Plateau board = new Plateau();
        ArrayList<Bot> players = new ArrayList<Bot>();
        Bot player = new BotRandom(0);
        players.add(player);players.add(player);
        Jeu game = new Jeu(players);
        game.setPlateau(board);

        Parcelle tileRed3 = new Parcelle(Couleur.ROSE);
        tileRed3.setNbBamboo(2);
        Parcelle tileYellow3 = new Parcelle(Couleur.JAUNE);
        tileYellow3.setNbBamboo(2);
        Parcelle tileGreen3 = new Parcelle(Couleur.VERT);
        tileGreen3.setNbBamboo(2);
        Parcelle tileRed4 = new Parcelle(Couleur.ROSE);
        tileRed4.setNbBamboo(3);
        Parcelle tileYellow4 = new Parcelle(Couleur.JAUNE);
        tileYellow4.setNbBamboo(3);
        Parcelle tileGreen4 = new Parcelle(Couleur.VERT);
        tileGreen4.setNbBamboo(3);
        board.addParcelle(tileRed4,new Position(16,15));
        board.addParcelle(tileYellow3,new Position(14,15));
        board.addParcelle(tileYellow3,new Position(15,16));
        board.addParcelle(tileGreen3,new Position(14,16));
        board.addParcelle(tileGreen4,new Position(15,14));
        board.addParcelle(tileYellow3,new Position(14,14));
        board.addParcelle(tileYellow3,new Position(16,16));

        Objectif objGreenNoA= new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.VERT);
        Objectif objRedNoA= new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.ROSE);
        Objectif objYellowNoA= new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.JAUNE);
        Objectif objGreenMult= new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT,Couleur.VERT);
        Objectif objRedMult= new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE,Couleur.ROSE);
        Objectif objYellowMult= new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE,Couleur.JAUNE);

        assertTrue(objRedNoA.verifie(player));
        assertTrue(objGreenNoA.verifie(player));
        assertFalse(objYellowNoA.verifie(player));

        assertFalse(objRedMult.verifie(player));
        assertFalse(objGreenMult.verifie(player));
        assertTrue(objYellowMult.verifie(player));



    }

    @Test
    void objVert(){
        Couleur vert = Couleur.VERT;
        assertEquals(5,new ObjectifJardinier(TypeObjJardinier.OBJVIDE,vert).getPoint());
        assertEquals(4,new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,vert).getPoint());
        assertEquals(4,new ObjectifJardinier(TypeObjJardinier.OBJENCLOS,vert).getPoint());
        assertEquals(3,new ObjectifJardinier(TypeObjJardinier.OBJENGRAIS,vert).getPoint());

        assertEquals(8,new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT,vert).getPoint());
    }

    @Test
    void objJaune(){
        Couleur jaune = Couleur.JAUNE;
        assertEquals(6,new ObjectifJardinier(TypeObjJardinier.OBJVIDE,jaune).getPoint());
        assertEquals(5,new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,jaune).getPoint());
        assertEquals(5,new ObjectifJardinier(TypeObjJardinier.OBJENCLOS,jaune).getPoint());
        assertEquals(4,new ObjectifJardinier(TypeObjJardinier.OBJENGRAIS,jaune).getPoint());

        assertEquals(7,new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE,jaune).getPoint());
    }

    @Test
    void objRouge(){

        Couleur rose = Couleur.ROSE;
        assertEquals(7,new ObjectifJardinier(TypeObjJardinier.OBJVIDE,rose).getPoint());
        assertEquals(6,new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,rose).getPoint());
        assertEquals(6,new ObjectifJardinier(TypeObjJardinier.OBJENCLOS,rose).getPoint());
        assertEquals(5,new ObjectifJardinier(TypeObjJardinier.OBJENGRAIS,rose).getPoint());

        assertEquals(6,new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE,rose).getPoint());
    }
}