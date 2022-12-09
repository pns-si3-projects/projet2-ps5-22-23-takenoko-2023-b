package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Action;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.ParcelleInactive;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.Stack;

public class Jeu {

    public Jeu(){

    }

    private void piocherParcelles(Joueur j){
        j.ajouteParcelle(new Parcelle());
    }

    private void poserParcelles(Joueur j){
        j.poserParcelle(j.donnerParcelle());
    }

    private void piocherObjectifs(Joueur j) {
        j.addObjectif(new ObjectifParcelle(2));
    }

    public void run(){
        Plateau plateau = new Plateau();
        Joueur j1 = new Joueur("joueur 1",plateau);
        int score_p1=0;
        boolean game = true;
        while(game){
            switch(j1.choisirAction()){
                case PIOCHE_PARCELLES -> this.piocherParcelles(j1);
                case OBJECTIFS -> this.piocherObjectifs(j1);
                case POSE_PARCELLES -> this.poserParcelles(j1);
            };
            j1.verifierObjectifs();
            if(j1.getNombreObjectifsObtenus()>0){
                game=false;
            }
        }
        System.out.println(j1.getNom()+" a accompli un objectif, Fin du jeu.");
        System.out.println("Score:");
        System.out.println(j1.getNom()+":"+j1.getScore());
    }
}
