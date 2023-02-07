package ps5.takenoko.joueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifPanda;
import ps5.takenoko.objectif.ObjectifParcelle;
import ps5.takenoko.objectif.Shape;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JoueurMoyenTest {
    JoueurMoyen player;
    JoueurMoyen player2;
    Jeu game;
    Plateau board;
    @BeforeEach
    void init(){
        ArrayList<Joueur> players = new ArrayList<>();
        player = new JoueurMoyen(0);
        player2 = new JoueurMoyen(1);
        players.add(player);
        players.add(player2);
        game = new Jeu(players);
        board = game.getPlateau();
        player.setPlateau(board);
        player2.setPlateau(board);
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
        assertEquals(1,player.getNombreObjectifsObtenus());

    }

    @Test
    void getRandomPositionTests() {

    }

    @Test
    void deplacerJardinierTests() {
    }

    @Test
    void placerIrrigationTests() {
    }

    @Test
    void deplacerPandaTests() {
        Parcelle green0 = new Parcelle(Couleur.VERT);
        Parcelle pink0 = new Parcelle(Couleur.ROSE);
        Parcelle yellow0 = new Parcelle(Couleur.JAUNE);
        Parcelle green1 = new Parcelle(Couleur.VERT);
        Parcelle pink1 = new Parcelle(Couleur.ROSE);
        Parcelle yellow1 = new Parcelle(Couleur.JAUNE);
        Position posG = new Position(15, 14);
        Position posY = new Position(16, 15);
        Position posP = new Position(15, 16);
        Position pos1 = new Position(14, 16);
        Position pos2 = new Position(14, 15);
        Position pos3 = new Position(14, 14);

        board.addParcelle(green1, posG);
        board.addParcelle(pink1, posP);
        board.addParcelle(yellow1, posY);
        board.addParcelle(green0, pos1);
        board.addParcelle(pink0, pos2);
        board.addParcelle(yellow0, pos3);
        green0.setNbBamboo(0);
        pink0.setNbBamboo(0);
        yellow0.setNbBamboo(0);
        Objectif objG = new ObjectifPanda(1,new Couleur[]{Couleur.VERT},3);
        Objectif objP = new ObjectifPanda(1,new Couleur[]{Couleur.ROSE},3);
        Objectif objY = new ObjectifPanda(1,new Couleur[]{Couleur.JAUNE},3);
        Objectif objM = new ObjectifPanda(1,Couleur.values(),3);
        Objectif[] obj = {objG,objP,objY,objM};
        Position[] pos = {posG,posP,posY};

        for(int i=0;i<4;i++){
            player.objectifs = new ArrayList<>();
            player.objectifs.add(obj[i]);
            Position selected = player.deplacerPanda(Set.of(pos1, posG, pos2, posP, pos3, posY));
            if(i<3){
                assertEquals(pos[i],selected);
            }else{
                boolean found = false;
                for(Position possible: pos){
                    if(possible == selected) found = true;
                }
                assertTrue(found);
            }
        }

    }
    @Test
    void piocherPoserParcelle(){
        Objectif objG= new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.VERT});
        Parcelle parcelleG = new Parcelle(Couleur.VERT);
        Parcelle parcelleR = new Parcelle(Couleur.ROSE);
        ArrayList<Parcelle> pioche = new ArrayList<>();
        pioche.add(parcelleG);
        pioche.add(parcelleR);
        player.objectifs.add(objG);
        Parcelle found;

        assertEquals(2,pioche.size());

        found = player.piocherParcelle(pioche);
        pioche.remove(found);
        player.poserParcelle(found);

        assertEquals(1,pioche.size());
        assertTrue(pioche.contains(parcelleR));
        assertEquals(2,board.getParcellePosee().size());

        Boolean ok = false;
        for(Position pos : board.getParcellePosee()){
            if (board.getParcelle(pos).equals(parcelleG)){
                ok=true;
            }
        }
        assertTrue(ok);

        found = player.piocherParcelle(pioche);
        pioche.remove(found);
        player.poserParcelle(found);
        assertEquals(0,pioche.size());
        assertEquals(3,board.getParcellePosee().size());

        ok = false;
        for(Position pos : board.getParcellePosee()){
            if (board.getParcelle(pos).equals(parcelleR)){
                ok=true;
            }
        }
        assertTrue(ok);
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