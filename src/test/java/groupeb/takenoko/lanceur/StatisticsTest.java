package groupeb.takenoko.lanceur;

import org.junit.jupiter.api.Test;
import groupeb.takenoko.bot.Bot;
import groupeb.takenoko.bot.BotMoyen;
import groupeb.takenoko.bot.BotRandom;

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

        assertEquals(0,stats.getScores()[0][0]);
        stats.updateStats(winner);
        assertEquals(1,stats.getScores()[0][0]);
        assertEquals(0,stats.getScores()[1][0]);

        assertEquals(0,stats.getEgalite());
        winner.add(new BotRandom(1));
        stats.updateStats(winner);
        assertEquals(1,stats.getEgalite());
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

        assertEquals((float)1,stats.getStats(joueur1,2)[0]);
        assertEquals((float) 50,stats.getStats(joueur1,2)[1]);
        assertEquals((float)0,stats.getStats(joueur1,2)[2]);
        assertEquals((float)0,stats.getStats(joueur1,2)[3]);
        assertEquals((float)1,stats.getStats(joueur1,2)[4]);
        assertEquals((float)50,stats.getStats(joueur1,2)[5]);
        assertEquals((float)0,stats.getStats(joueur1,2)[6]);
        assertEquals((float)0,stats.getStats(joueur1,2)[7]);
    }

}