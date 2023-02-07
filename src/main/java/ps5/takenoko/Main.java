package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.lanceur.JeuLanceur;
import ps5.takenoko.option.Args;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurMoyen(2));
        JeuLanceur jeuLanceurCsv = new JeuLanceur(joueurs, arguments);
        jeuLanceurCsv.lancer();
    }
}
