package ps5.takenoko.lanceur;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.Test;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.option.Args;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class JeuLanceurTest {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());

    @Test
    void lancer() {
        LOGGER.setLevel(Level.OFF);

        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse("--csv");
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurMoyen(2));
        JeuLanceur jeuLanceurCsv = new JeuLanceur(joueurs, arguments);

        assertTrue(jeuLanceurCsv.getNbparties() == 1000);


        jeuLanceurCsv.setNbparties(1);
        jeuLanceurCsv.lancer();

    }

}