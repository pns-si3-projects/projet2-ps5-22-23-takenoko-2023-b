package ps5.takenoko.Joueur;

import ps5.takenoko.Jeu.Jeu;

import java.util.ArrayList;

public class ComparateurVictoires {

    public static void main(String... args) throws IllegalAccessException {
        ArrayList<Float> points = new ArrayList<>();
        points.add((float) 0);
        points.add((float) 0);
        for (int i = 0; i < 1000; i++) {
            ArrayList<Joueur> joueurs = new ArrayList<>();
            joueurs.add(new JoueurRandom(1));
            joueurs.add(new JoueurRandom(2));
            Jeu j = new Jeu(joueurs);
            j.lancer();
            int g = j.calculGagnants().get(0).getId();
            points.set(g - 1, points.get(g - 1) + 1);
        }
        System.out.println("Joueur 1 : " + (points.get(0) / 1000 * 100)  + "%");
        System.out.println("Joueur 2 : " + (points.get(1) / 1000 * 100) + "%");
    }
}
