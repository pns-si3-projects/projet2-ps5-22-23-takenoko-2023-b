package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Action;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Joueur.JoueurRandom;
import ps5.takenoko.Objectif.Empereur;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jeu {
    private static final int nbActions = 2;
    private int nbObjectifFin;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Plateau plateau= new Plateau();
    private Position positionJardinier = new Position(15,15);
    private Position positionPanda = new Position(15,15);
    private ObjectifList objectifList = new ObjectifList();
    ParcelleList parcellesList = new ParcelleList();

    public Jeu(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        setNbObjectifFin();

    }

    public void lancer() {
        Joueur j1 = new JoueurRandom(1);
        Joueur j2 = new JoueurRandom(2);
        int score_p1 = 0;
        while (!estTermine()) {
            //TODO: Implementation of Meteo here (except the first round)

            for(Joueur j: joueurs){
                tourJoueur(j,nbActions);
                j.validerObjectifs();
            }
        }

//        System.out.println("Joueur"+ j1.getI() + " a accompli un objectif, Fin du jeu.");
//        System.out.println("Score:");
//        System.out.println(j1.getNom() + ":" + j1.getScore());
    }

    private boolean tourJoueur(Joueur j, int nbActions){
        ArrayList<Action> actionsPossibles = getActionsPossibles();
        boolean stop=false;
        if(actionsPossibles.isEmpty()){
            return false;
        }
        Action actionChoisi = j.jouer(actionsPossibles);
        while(nbActions>0 && !stop){
            switch(actionChoisi){
                case PIOCHER_PARCELLES: 
                    this.piocherParcelles(j);

                    break;
                case OBJECTIFS:
                    this.piocherObjectifs(j);

                    break;
                case POSER_PARCELLES:
                    this.poserParcelles(j);

                    break;
            }
        }


    }

    //TODO: methode getActionsPossible (par ex: si ya que 1 parcelle etang-> peut pas deplacer Panda ni Jardinier) ou si ya plus de Parcelle dans parcellesList-> peut pas piocher Parcelle
    private ArrayList<Action> getActionsPossibles(){
        //TODO: implements conditions
        ArrayList<Action> res=  new ArrayList<>(Arrays.asList(Action.values()));
        return res;
    }

    private ArrayList<Joueur> calculGagnants(){
        ArrayList<Joueur> gagnants= new ArrayList<Joueur>();
        Collections.sort(joueurs);
        gagnants.add(joueurs.get(0));
        loopGagnant: for(int i=0; i<joueurs.size()-1;i++){
            if(joueurs.get(i).compareTo(joueurs.get(i+1))==0){
                gagnants.add(joueurs.get(i+1));
            }
            else{
                break loopGagnant;
            }
        }
        return gagnants;
    }

    //TODO: Good idea to do both at da same time like so?
    private boolean estTermine(){
        for(Joueur j: joueurs){
            if(j.getNombreObjectifsObtenus()>=nbObjectifFin){
                j.completerObjectif(new Empereur(2));
                return true;
            }
        }
        return false;
    }

    private void setNbObjectifFin(){
        switch(joueurs.size()){
            case 2:
                nbObjectifFin=9;
                break;
            case 3:
                nbObjectifFin=8;
                break;
            case 4:
                nbObjectifFin=7;
                break;
            default:
                throw new IllegalArgumentException("Le nombre de Joueur doit etre entre 2 et 4");
        }
    }


    private void piocherParcelles(Joueur j) {
        ArrayList<Parcelle> parcelles = parcellesList.getRandomParcelles(3);
        Parcelle p = j.piocherParcelle(parcelles);
        parcellesList.removeParcelle(p);
    }

    private void poserParcelles(Joueur j) {
        j.poserParcelle(j.donnerParcelle());
    }

    private void piocherObjectifs(Joueur j) {
        Objectif o = objectifList.randomObjectif();
        j.addObjectif(new ObjectifParcelle(2));
        objectifList.removeObjectif(o);
    }


}
