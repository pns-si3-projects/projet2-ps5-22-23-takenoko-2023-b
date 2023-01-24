package ps5.takenoko.joueur;

import ps5.takenoko.jeu.Jeu;

import java.util.ArrayList;

public class ComparateurVictoires {

    private static int nbparties = 10000;

    public static void main(String... args) {
        ArrayList<Float> points = new ArrayList<>();
        points.add((float) 0);
        points.add((float) 0);
        points.add((float) 0);
        for (int i = 0; i < nbparties; i++) {
            if(i%100==0) System.out.println(i);
            ArrayList<Joueur> joueurs = new ArrayList<>();
            joueurs.add(new JoueurRandom(1));
            joueurs.add(new JoueurMoyen(2));
            Jeu j = new Jeu(joueurs);
            j.setAffichage(false);
            j.lancer();
            ArrayList<Joueur> g= j.calculGagnants();
            if(g.size()>1 || g.isEmpty()) {
                points.set(0, points.get(0) + 1);
            }
            if(g.size()==1) {
                    points.set(g.get(0).getId(), points.get(g.get(0).getId()) + 1);
            }
        }
        System.out.println("Draw : " + (points.get(0) / nbparties * 100)  + "%");
        System.out.println("Joueur 1 : " + (points.get(1) / nbparties * 100)  + "%");
        System.out.println("Joueur 2 : " + (points.get(2) / nbparties * 100) + "%");
    }
}
