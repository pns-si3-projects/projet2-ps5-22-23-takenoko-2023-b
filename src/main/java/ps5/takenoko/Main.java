package ps5.takenoko;

import ps5.takenoko.Jeu.Jeu;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Joueur.JoueurRandom;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

public class Main {
    public static void main(String... args) throws IllegalAccessException {
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new JoueurRandom(1));
        joueurs.add(new JoueurRandom(2));
        Jeu j = new Jeu(joueurs);
        j.lancer();

    }
}
