package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.joueur.*;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.lanceur.JeuLanceur;
import ps5.takenoko.option.Args;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurMoyen(1));
        joueurs.add(new JoueurMoyen(2));
        JeuLanceur jeuLanceur = new JeuLanceur(joueurs, arguments);
        jeuLanceur.lancer();
    }
}
