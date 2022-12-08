package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Action;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.ParcelleInactive;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.Stack;

public class Jeu {

    public Jeu(){

    }

    private void drawParcelles(Joueur j){
        j.addParcelle(new Parcelle());
    }

    private void putParcelles(Joueur j){
        j.putParcelle();
    }

    private void drawObjectifs(Joueur j) {
        j.addObjectifs();
    }

    public void run(){
        Plateau plateau = new Plateau();
        Joueur j1 = new Joueur("joueur 1");
        int score_p1=0;
        Joueur winner =null;
        while(winner==null){
            switch(j1.chooseAction()){
                case Action.DRAW_PARCELLES -> this.drawParcelles(j1);
                case Action.OBJECTIFS -> this.drawObjectifs(j1);
                case Action.PUT_PARCELLES -> this.putParcelles(j1);
            };
            score_p1+=j1.checkObjectifs();
            if(score_p1>=1)winner=j1;
        }

    }
}
