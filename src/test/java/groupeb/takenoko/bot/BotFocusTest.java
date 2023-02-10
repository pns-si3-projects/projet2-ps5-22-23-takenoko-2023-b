package groupeb.takenoko.bot;

import groupeb.takenoko.jeu.Jeu;
import groupeb.takenoko.objectif.*;
import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BotFocusTest {

    BotPanda botPanda;
    BotParcelle botParcelle;
    BotJardinier botJardinier;
    Jeu game;
    Plateau board;

    @BeforeEach
    void init(){
        ArrayList<Bot> players = new ArrayList<>();
        botPanda = new BotPanda(0);
        botParcelle = new BotParcelle(1);
        botJardinier = new BotJardinier(2);
        players.add(botParcelle);
        players.add(botJardinier);
        players.add(botPanda);
        game = new Jeu(players);
        board = game.getPlateau();
    }

    @Test
    void BotPandaTest(){
        List<Class<? extends Objectif>> objectifsTypes = new ArrayList<>();
        objectifsTypes.add(ObjectifPanda.class);
        objectifsTypes.add(ObjectifJardinier.class);
        assertEquals(botPanda.choisirObjectif(objectifsTypes),ObjectifPanda.class);
        objectifsTypes.remove(ObjectifPanda.class);
        assertNotEquals(botPanda.choisirObjectif(objectifsTypes),ObjectifPanda.class);
        objectifsTypes.add(ObjectifPanda.class);
        botPanda.addObjectif(new ObjectifPanda(4, Couleur.ROSE,1));
        botPanda.addObjectif(new ObjectifPanda(4, Couleur.ROSE,1));
        botPanda.addObjectif(new ObjectifPanda(4, Couleur.ROSE,1));
        botPanda.addObjectif(new ObjectifPanda(4, Couleur.ROSE,1));
        botJardinier.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        assertNotEquals(botPanda.choisirObjectif(objectifsTypes),ObjectifPanda.class);
    }

    @Test
    void BotJardinierTest(){
        List<Class<? extends Objectif>> objectifsTypes = new ArrayList<>();
        objectifsTypes.add(ObjectifPanda.class);
        objectifsTypes.add(ObjectifJardinier.class);
        assertEquals(botJardinier.choisirObjectif(objectifsTypes),ObjectifJardinier.class);
        objectifsTypes.remove(ObjectifJardinier.class);
        assertNotEquals(botJardinier.choisirObjectif(objectifsTypes),ObjectifJardinier.class);
        objectifsTypes.add(ObjectifJardinier.class);
        botJardinier.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        botJardinier.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        botJardinier.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        botJardinier.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        botPanda.addObjectif(new ObjectifPanda(4, Couleur.ROSE,1));
        assertNotEquals(botJardinier.choisirObjectif(objectifsTypes),ObjectifJardinier.class);
    }

    @Test
    void BotParcelleTest(){
        List<Class<? extends Objectif>> objectifsTypes = new ArrayList<>();
        objectifsTypes.add(ObjectifParcelle.class);
        objectifsTypes.add(ObjectifJardinier.class);
        assertEquals(botParcelle.choisirObjectif(objectifsTypes),ObjectifParcelle.class);
        objectifsTypes.remove(ObjectifParcelle.class);
        assertNotEquals(botParcelle.choisirObjectif(objectifsTypes),ObjectifParcelle.class);
        objectifsTypes.add(ObjectifParcelle.class);
        botParcelle.addObjectif(new ObjectifParcelle(Shape.LIGNE,Couleur.JAUNE));
        botParcelle.addObjectif(new ObjectifParcelle(Shape.LIGNE,Couleur.JAUNE));
        botParcelle.addObjectif(new ObjectifParcelle(Shape.LIGNE,Couleur.JAUNE));
        botParcelle.addObjectif(new ObjectifParcelle(Shape.LIGNE,Couleur.JAUNE));
        botJardinier.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        assertNotEquals(botParcelle.choisirObjectif(objectifsTypes),ObjectifParcelle.class);
    }
}


