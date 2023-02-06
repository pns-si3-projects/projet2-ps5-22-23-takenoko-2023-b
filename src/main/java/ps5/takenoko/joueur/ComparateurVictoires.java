package ps5.takenoko.joueur;

import ps5.takenoko.jeu.Jeu;

import java.util.ArrayList;

public class ComparateurVictoires {

    private static int nbparties = 10000;
    private static int nbJoueurs = 2;

    public static void main(String... args) {
        ArrayList<Float> points = new ArrayList<>();
        ArrayList<Float> victoires = new ArrayList<>();
        for (int i = 0; i <= nbJoueurs; i++) {
            victoires.add((float) 0);
            points.add((float)0);
        }
        for (int i = 0; i < nbparties; i++) {
            if(i%100==0) System.out.println(i);
            ArrayList<Joueur> joueurs = new ArrayList<>();
            joueurs.add(new JoueurRandom(1));
            joueurs.add(new JoueurMoyen(2));
            Jeu jeu = new Jeu(joueurs);
            jeu.setAffichage(false);
            jeu.lancer();
            ArrayList<Joueur> g= jeu.calculGagnants();
            if(g.size()!=1) {//Case where the game surpass the number of turns
                i--;
            }else{
                for(Joueur gagnants : g){
                    victoires.set(gagnants.getId(), victoires.get(gagnants.getId())+1);
                }
                for(Joueur j : joueurs){
                    points.set(j.getId(),points.get(j.getId())+j.calculPoint());
                }
            }
        }
        System.out.println("Draw : " + (victoires.get(0) / nbparties * 100)  + "%");
        System.out.println("Joueur Random : " + (victoires.get(1) / nbparties * 100)  + "% victoires score moyen: " + (points.get(1) / nbparties));
        System.out.println("Joueur Moyen : " + (victoires.get(2) / nbparties * 100) + "% victoires score moyen: " + (points.get(2) / nbparties));
    }
}
