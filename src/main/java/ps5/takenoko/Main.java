package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.lanceur.JeuLanceur;
import ps5.takenoko.option.Args;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurMoyen(2));

        if (arguments.isDemo()) {
            JeuLanceur jeuLanceurDemo = new JeuLanceur(1, joueurs);
            try {
                jeuLanceurDemo.lancer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (arguments.isTwoThousand()) {
            JeuLanceur jeuLanceurTwoThousand = new JeuLanceur(1000, joueurs);
            try {
                jeuLanceurTwoThousand.lancer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (arguments.isCsv()) {
            JeuLanceur jeuLanceurCsv = new JeuLanceur(1000, joueurs, true);
            try {
                jeuLanceurCsv.lancer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
