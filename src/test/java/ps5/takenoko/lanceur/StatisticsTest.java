package ps5.takenoko.lanceur;

import org.junit.jupiter.api.Test;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.joueur.JoueurRandom;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    void updateStats() {
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        ArrayList<Joueur> winner = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurMoyen(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        winner.add(joueur1);
        Statistics stats = new Statistics(players);

        assertEquals(stats.getScores()[0][0], 0);
        stats.updateStats(winner);
        assertEquals(stats.getScores()[0][0], 1);
        assertEquals(stats.getScores()[1][0], 0);

        assertEquals(stats.getEgalite(),0);
        winner.add(new JoueurRandom(1));
        stats.updateStats(winner);
        assertEquals(stats.getEgalite(),1);
    }

    @Test
    void getStats(){
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        ArrayList<Joueur> winner = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurMoyen(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        winner.add(joueur1);
        Statistics stats = new Statistics(players);

        stats.updateStats(winner);

        winner.add(new JoueurRandom(1));
        stats.updateStats(winner);

        assertEquals(stats.getStats(joueur1,2)[0], "JoueurMoyen");
        assertEquals(stats.getStats(joueur1,2)[1], "1");
        assertEquals(stats.getStats(joueur1,2)[2], "50.0%");
        assertEquals(stats.getStats(joueur1,2)[3], "0");
        assertEquals(stats.getStats(joueur1,2)[4], "0.0%");
        assertEquals(stats.getStats(joueur1,2)[5], "1");
        assertEquals(stats.getStats(joueur1,2)[6], "50.0%");
        assertEquals(stats.getStats(joueur1,2)[7], "0.0");
        assertEquals(stats.getStats(joueur1,2)[8], "0.0");
    }

}