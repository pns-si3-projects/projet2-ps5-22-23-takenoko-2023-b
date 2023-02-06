package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.option.Args;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        System.out.println("2thousands: " + arguments.isTwoThousand());
        System.out.println("demo: " + arguments.isDemo());
        System.out.println("csv: " + arguments.isCsv());

        if (arguments.isDemo()) {
            ArrayList<Joueur> joueurs = new ArrayList<>();
            joueurs.add(new JoueurRandom(1));
            joueurs.add(new JoueurRandom(2));
            Jeu j = new Jeu(joueurs);
            j.lancer();
            System.out.println("Le gagnant est le joueur " + j.calculGagnants().get(0).getId());
        }
    }
}
