package groupeb.takenoko.lanceur;

import com.beust.jcommander.JCommander;
import groupeb.takenoko.option.Args;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

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
        JeuLanceur jeuLanceurCsv = new JeuLanceur(arguments);
        assertEquals(1000,jeuLanceurCsv.getNbparties());

        arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse("--demo");
        jeuLanceurCsv = new JeuLanceur(arguments);
        assertEquals(1,jeuLanceurCsv.getNbparties());

    }

    @Test
    void affichageStats() {
        JeuLanceur jeuL = Mockito.spy(new JeuLanceur(new Args()));
        jeuL.lancer();
        Mockito.verify(jeuL).affichageStats();
    }

    }