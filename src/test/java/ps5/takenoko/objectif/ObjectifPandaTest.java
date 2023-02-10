package ps5.takenoko.objectif;

import org.junit.jupiter.api.Test;
import ps5.takenoko.bot.Bot;
import ps5.takenoko.bot.BotRandom;
import ps5.takenoko.plateau.Couleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectifPandaTest {
    @Test
    void verifie() {
        Bot player = new BotRandom(0);
        player.ajouteBambou(Couleur.ROSE);
        player.ajouteBambou(Couleur.ROSE);
        player.ajouteBambou(Couleur.ROSE);

        player.ajouteBambou(Couleur.VERT);
        player.ajouteBambou(Couleur.VERT);

        player.ajouteBambou(Couleur.JAUNE);
        player.ajouteBambou(Couleur.JAUNE);
        player.ajouteBambou(Couleur.JAUNE);
        player.ajouteBambou(Couleur.JAUNE);

        Objectif pandaVert2 = new ObjectifPanda( 3, new Couleur[]{Couleur.VERT}, 2);
        Objectif pandaRose2 = new ObjectifPanda( 3, new Couleur[]{Couleur.ROSE}, 2);
        Objectif pandaJaune3 = new ObjectifPanda( 4, new Couleur[]{Couleur.JAUNE}, 1);

        Objectif pandaVert3 = new ObjectifPanda( 5, new Couleur[]{Couleur.VERT}, 3);
        Objectif pandaRose5 = new ObjectifPanda( 4, new Couleur[]{Couleur.ROSE}, 5);
        Objectif pandaJaune5 = new ObjectifPanda( 6, new Couleur[]{Couleur.JAUNE}, 5);

        Objectif panda3Couleur1 = new ObjectifPanda( 6, new Couleur[]{Couleur.ROSE,Couleur.JAUNE,Couleur.VERT}, 1);
        Objectif panda3Couleur3 = new ObjectifPanda( 6, new Couleur[]{Couleur.ROSE,Couleur.JAUNE,Couleur.VERT}, 3);

        assertTrue(pandaVert2.verifie(player));
        assertTrue(pandaRose2.verifie(player));
        assertTrue(pandaJaune3.verifie(player));

        assertFalse(pandaVert3.verifie(player));
        assertFalse(pandaRose5.verifie(player));
        assertFalse(pandaJaune5.verifie(player));

        assertTrue(panda3Couleur1.verifie(player));
        assertFalse(panda3Couleur3.verifie(player));

    }
}
