package ps5.takenoko.joueur;

import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifJardinier;
import ps5.takenoko.objectif.ObjectifPanda;
import ps5.takenoko.objectif.ObjectifParcelle;

import java.util.ArrayList;

public class ComparateurVictoires {

    private static int nbparties = 10000;
    private static int nbJoueurs = 2;

    public static void main(String... args) {
        ArrayList<Float> points = new ArrayList<>();
        ArrayList<Float> victoires = new ArrayList<>();
        ArrayList<ArrayList<Float>> objectifs = new ArrayList<>();

        for (int i = 0; i <= nbJoueurs; i++) {
            victoires.add((float) 0);
            points.add((float)0);
            objectifs.add(new ArrayList<>());
            objectifs.get(i).add((float) 0);
            objectifs.get(i).add((float) 0);
            objectifs.get(i).add((float) 0);
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
            if(g.size()!=1) {//Case where the game surpass the number of turns or there is a draw
                i--;
            }else{
                for(Joueur gagnants : g){
                    victoires.set(gagnants.getId(), victoires.get(gagnants.getId())+1);
                }
                for(Joueur j : joueurs){
                    points.set(j.getId(),points.get(j.getId())+j.calculPoint());
                    for(Objectif o : j.getObjectifsObtenus()){
                        if(o instanceof ObjectifPanda) {
                            objectifs.get(j.getId()).set(0,objectifs.get(j.getId()).get(0)+1);
                        }
                        if(o instanceof ObjectifJardinier) {
                            objectifs.get(j.getId()).set(1,objectifs.get(j.getId()).get(1)+1);
                        }
                        if(o instanceof ObjectifParcelle) {
                            objectifs.get(j.getId()).set(2,objectifs.get(j.getId()).get(2)+1);
                        }
                    }
                }
            }
        }
        System.out.println("Draw : " + (victoires.get(0) / nbparties * 100)  + "%");
        System.out.println("Joueur Random : " + (victoires.get(1) / nbparties * 100)  + "% victoires \nscore moyen: " + (points.get(1) / nbparties)+ "\n Moyenne des objectifs occomplis: " + (objectifs.get(1).get(0) /nbparties) + " Objectifs Panda, " + (objectifs.get(1).get(1) /nbparties) + " Objectifs Jardinier, " + (objectifs.get(1).get(2) /nbparties) + " Objectifs Parcelle, ");
        System.out.println("Joueur Moyen : " + (victoires.get(2) / nbparties * 100)  + "% victoires \nscore moyen: " + (points.get(2) / nbparties)+ "\n Moyenne des objectifs occomplis: " + (objectifs.get(2).get(0) /nbparties) + " Objectifs Panda, " + (objectifs.get(2).get(1) /nbparties) + " Objectifs Jardinier, " + (objectifs.get(2).get(2) /nbparties) + " Objectifs Parcelle, ");

    }
}
