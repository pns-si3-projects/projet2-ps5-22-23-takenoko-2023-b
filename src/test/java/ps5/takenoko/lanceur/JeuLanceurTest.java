package ps5.takenoko.lanceur;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.option.Args;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse("--demo");
        jeuLanceurCsv = new JeuLanceur(joueurs, arguments);
        assertTrue(jeuLanceurCsv.getNbparties() == 1);

    }

    @Test
    void affichageStats() {
        JeuLanceur jeuL = Mockito.spy(new JeuLanceur(new ArrayList<>(), new Args()));
        jeuL.lancer();
        Mockito.verify(jeuL).affichageStats();
    }

}