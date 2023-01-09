package ps5.takenoko.Joueur;

import ps5.takenoko.Jeu.Jeu;

import java.util.ArrayList;

public class ComparateurVictoires {

    public static void main(String... args) throws IllegalAccessException {
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurRandom(2));
        Jeu j = new Jeu(joueurs);
        j.lancer();
        int g = j.calculGagnants().get(0).getId();
    }
}
