package ps5.takenoko.lanceur;

import org.junit.jupiter.api.Test;
import ps5.takenoko.Bot.Bot;
import ps5.takenoko.Bot.BotMoyen;
import ps5.takenoko.Bot.BotRandom;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    void updateStats() {
        ArrayList<Bot> players = new ArrayList<Bot>();
        ArrayList<Bot> winner = new ArrayList<Bot>();
        BotRandom joueur1 = new BotMoyen(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        winner.add(joueur1);
        Statistics stats = new Statistics(players);

        assertEquals(stats.getScores()[0][0], 0);
        stats.updateStats(winner);
        assertEquals(stats.getScores()[0][0], 1);
        assertEquals(stats.getScores()[1][0], 0);

        assertEquals(stats.getEgalite(),0);
        winner.add(new BotRandom(1));
        stats.updateStats(winner);
        assertEquals(stats.getEgalite(),1);
    }

    @Test
    void getStats(){
        ArrayList<Bot> players = new ArrayList<Bot>();
        ArrayList<Bot> winner = new ArrayList<Bot>();
        BotRandom joueur1 = new BotMoyen(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        winner.add(joueur1);
        Statistics stats = new Statistics(players);

        stats.updateStats(winner);

        winner.add(new BotRandom(1));
        stats.updateStats(winner);

        assertEquals(stats.getStats(joueur1,2)[0], (float)1);
        assertEquals(stats.getStats(joueur1,2)[1], (float) 50);
        assertEquals(stats.getStats(joueur1,2)[2], (float)0);
        assertEquals(stats.getStats(joueur1,2)[3], (float)0);
        assertEquals(stats.getStats(joueur1,2)[4], (float)1);
        assertEquals(stats.getStats(joueur1,2)[5], (float)50);
        assertEquals(stats.getStats(joueur1,2)[6], (float)0);
        assertEquals(stats.getStats(joueur1,2)[7], (float)0);
    }

}