package ps5.takenoko.joueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifParcelle;
import ps5.takenoko.objectif.Shape;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JoueurMoyenTest {
    Joueur player;
    Joueur player2;
    Jeu game;
    Plateau board;
    @BeforeEach
    void init(){
        ArrayList<Joueur> players = new ArrayList<>();
        player = new JoueurMoyen(0);
        player = new JoueurMoyen(1);
        players.add(player);
        players.add(player2);
        game = new Jeu(players);
        board = game.getPlateau();
    }

    @Test
    void validerObjectifsTests() {
        Parcelle Green3 = new Parcelle(Couleur.VERT);
        Objectif objCourbeVerte= new ObjectifParcelle(Shape.COURBE,new Couleur[]{Couleur.VERT});
        board.addParcelle(Green3,new Position(16,15));
        board.addParcelle(Green3,new Position(15,14));
        board.addParcelle(Green3,new Position(14,14));
        board.addParcelle(Green3,new Position(15,17));
        player2.objectifs.add(objCourbeVerte);
        player.validerObjectifs();
        assertEquals(0,player.objectifs.size());
        player.objectifs.add(objCourbeVerte);
        player.validerObjectifs();
        assertEquals(1,player.objectifs.size());

    }

    @Test
    void getRandomPositionTests() {
    }

    @Test
    void piocherParcelleTests() {
    }

    @Test
    void deplacerJardinierTests() {
    }

    @Test
    void deplacerPandaTests() {
    }

    @Test
    void placerIrrigationTests() {

    }
    @Test
    void piocherPoserParcelle(){
        Parcelle parcelle = new Parcelle();
        ArrayList<Parcelle> pioche = new ArrayList<>();
        pioche.add(parcelle);

        assertEquals(0,player.getParcelles().size());
        player.piocherParcelle(pioche);
        assertEquals(1,player.getParcelles().size());
        assertEquals(parcelle,player.getParcelles().get(0));
        player.poserParcelle(parcelle);
        assertEquals(0,player.getParcelles().size());
    }
    @Test
    void jouerTests() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.addAll(Arrays.asList(Action.values()));

        assertEquals(Action.OBJECTIFS ,player.jouer(actions));
        actions.remove(Action.OBJECTIFS);
        assertEquals(Action.PIOCHER_PARCELLES ,player.jouer(actions));
        actions.remove(Action.PIOCHER_PARCELLES);
        assertEquals(Action.PANDA ,player.jouer(actions));
        actions.remove(Action.PANDA);
        assertEquals(Action.JARDINIER ,player.jouer(actions));
        actions.remove(Action.JARDINIER);
        for(int i=0;i<actions.size();i++){
            Action act = player.jouer(actions);
            assertTrue(actions.contains(act));
            actions.remove(act);
        }
    }
}