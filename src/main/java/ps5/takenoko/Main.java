package ps5.takenoko;

import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.joueur.JoueurMoyen;

import java.util.ArrayList;

public class Main {
    public static void main(String... args) {
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurMoyen(2));
        Jeu j = new Jeu(joueurs);
        j.lancer();
        //System.out.println("Le gagnant est le joueur " + j.calculGagnants().get(0).getId());
    }
}
