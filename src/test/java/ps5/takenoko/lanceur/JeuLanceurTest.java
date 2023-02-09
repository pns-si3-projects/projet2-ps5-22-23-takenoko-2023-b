package ps5.takenoko.lanceur;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ps5.takenoko.Bot.Bot;
import ps5.takenoko.Bot.BotMoyen;
import ps5.takenoko.Bot.BotRandom;
import ps5.takenoko.option.Args;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JeuLanceurTest {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());

    @Test
    void create() {
        LOGGER.setLevel(Level.OFF);

        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse("--csv");
        ArrayList<Bot> bots = new ArrayList<>();
        bots.add(new BotRandom(1));
        bots.add(new BotMoyen(2));
        JeuLanceur jeuLanceurCsv = new JeuLanceur(bots, arguments);
        assertTrue(jeuLanceurCsv.getNbparties() == 1000);

        arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse("--demo");
        jeuLanceurCsv = new JeuLanceur(bots, arguments);
        assertTrue(jeuLanceurCsv.getNbparties() == 1);

    }

    @Test
    void affichageStats() {
        JeuLanceur jeuL = Mockito.spy(new JeuLanceur(new ArrayList<>(), new Args()));
        jeuL.lancer();
        Mockito.verify(jeuL).affichageStats();
    }

    @Test
    void twoThousandsPartTwoActive() {
        LOGGER.setLevel(Level.OFF);
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse("--2thousands");
        ArrayList<Bot> bots = new ArrayList<>();
        bots.add(new BotRandom(1));
        bots.add(new BotMoyen(2));
        JeuLanceur jeuLanceur = Mockito.spy(new JeuLanceur(bots, arguments));
        jeuLanceur.setNbparties(0);
        when(jeuLanceur.twoThousandPartTwo()).thenReturn(new JeuLanceur(new ArrayList<>(), new Args()));
        jeuLanceur.lancer();
        Mockito.verify(jeuLanceur).twoThousandPartTwo();
    }

    @Test
    void twoThousandsPartTwo() {
        JeuLanceur jeuLanceur = new JeuLanceur(new ArrayList<>(), new Args());
        JeuLanceur jeuLanceur2 = jeuLanceur.twoThousandPartTwo();
        assertEquals(1000, jeuLanceur2.getNbparties());
        assertEquals(4, jeuLanceur2.getJoueurs().size());
    }

    }