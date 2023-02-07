package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.lanceur.ComparateurVictoires;
import ps5.takenoko.option.Args;


import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Args arguments = new Args();
//        JCommander.newBuilder()
//                .addObject(arguments)
//                .build()
//                .parse(args);
//
//        if (arguments.isDemo()) {
//            ArrayList<Joueur> joueurs = new ArrayList<>();
//            joueurs.add(new JoueurRandom(1));
//            joueurs.add(new JoueurMoyen(2));
//            Jeu j = new Jeu(joueurs);
//            j.lancer();
//            System.out.println("Le gagnant est le joueur " + j.calculGagnants().get(0).getId());
//        }
//        if (arguments.isTwoThousand()) {
//            ArrayList<Joueur> joueurs = new ArrayList<>();
//            joueurs.add(new JoueurRandom(1));
//            joueurs.add(new JoueurMoyen(2));
//            ComparateurVictoires comparateurVictoires = new ComparateurVictoires(joueurs);
//            try {
//                comparateurVictoires.lancer();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if (arguments.isCsv()) {
//            // TODO : lancer des parties de bots vs bots et enregistrer les résultats dans un fichier csv
//        }
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurMoyen(2));
        ComparateurVictoires comparateurVictoires = new ComparateurVictoires(joueurs);
        try {
            comparateurVictoires.lancer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
