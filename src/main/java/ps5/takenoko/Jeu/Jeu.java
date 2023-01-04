package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Action;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Joueur.JoueurRandom;
import ps5.takenoko.Objectif.Empereur;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Personnage.Jardinier;
import ps5.takenoko.Personnage.Panda;
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
    private Jardinier jardinier = new Jardinier();
    private Panda panda = new Panda();

    private ObjectifList objectifList = new ObjectifList();
    private ParcelleList parcellesList = new ParcelleList();

    public Jeu(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        setNbObjectifFin();

    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void lancer() {
        for(Joueur j: this.joueurs){
            j.setPlateau(this.plateau);
        }
        while (!estTermine()) {
            //TODO: Implementation of Meteo here (except the first round)
            for(Joueur j: joueurs){
                tourJoueur(j,nbActions);
            }
            getPlateau().affichePlateau();
        }
        afficheResultat();
        System.out.println(joueurs.get(0).getObjectifsObtenus());
        System.out.println(joueurs.get(1).getObjectifsObtenus());
        // System.out.println(joueurs.get(2).getObjectifsObtenus());

    }

    private boolean tourJoueur(Joueur j, int nbActions){
        ArrayList<Action> actionChoisis = new ArrayList<Action>();
        ArrayList<Action> actionsPossibles = getActionsPossibles(j,actionChoisis);
        boolean stop=false; //for later with more complicated stuff
        if(actionsPossibles.isEmpty()){
            return false;
        }
        while(nbActions>0 && !stop){
            Action actionChoisi = j.jouer(actionsPossibles);
            String msg = "Joueur "+j.getId()+" a choisi action " + actionChoisi.toString();

            switch(actionChoisi){
                case PIOCHER_PARCELLES:
                    Parcelle parcellePioche = this.piocherParcelles(j);
                    msg += " et a pioché une " + parcellePioche + "puis l'a placé sur le plateau";
                    j.poserParcelle(parcellePioche);
                    parcellesList.remove(parcellePioche);
                    //affichage plateau
                    break;
                case OBJECTIFS:
                    msg += " et a pioché un objectif";
                    this.piocherObjectifs(j);
                    break;
                case JARDINIER:
                    Position posJardinier = j.deplacerJardinier(jardinier.posPossibles(plateau));
                    msg += " et a déplacé le jardinier en " + posJardinier;
                    jardinier.deplacer(posJardinier,plateau);
                    break;
                    case PANDA:
                        Position p = j.deplacerPanda(panda.posPossibles(plateau));
                        msg += " et a déplacé le panda en " + p;
                        if(panda.deplacer(p,plateau)){
                            j.ajouteBambou(((Parcelle)plateau.getParcelle(p)).getCouleur());
                        }
                        break;
            }
            System.out.println(msg);
            actionsPossibles = getActionsPossibles(j,actionChoisis);

            nbActions--;
            j.validerObjectifs();
        }
        return true;
    }

    private ArrayList<Action> getActionsPossibles(Joueur j, ArrayList<Action> actionsChoisis){
        ArrayList<Action> actionsPossibles = new ArrayList<Action>();
        if(plateau.getParcellePosee().size()>1){
            actionsPossibles.add(Action.PANDA);
            actionsPossibles.add(Action.JARDINIER);
        }
        if(parcellesList.size()>=3){
            actionsPossibles.add(Action.PIOCHER_PARCELLES);
        }
        if(objectifList.size()>0 && j.getObjectifs().size()<5){
            actionsPossibles.add(Action.OBJECTIFS);
        }
        //TODO: implements conditions for irrigation

        //TODO : les actions doivent être différentes sauf si Meteo = VENT
        actionsPossibles.removeAll(actionsChoisis);
        return actionsPossibles;
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

    private void afficheResultat(){
        ArrayList<Joueur> gagnants = calculGagnants();
        if(gagnants.size()>1){
            //TODO: implement the case of a draw >=3 joueurs
            System.out.println("Draw");
        }
        else{
            System.out.println("Joueur " + gagnants.get(0).getId() + " a gagne");
        }
    }

    private boolean estTermine(){
        for(Joueur j: joueurs){
            if(j.getNombreObjectifsObtenus()>=nbObjectifFin){
                j.completerObjectif(new Empereur());
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


    private Parcelle piocherParcelles(Joueur j) {
        ArrayList<Parcelle> parcelles = parcellesList.getRandomParcelles(3);
        Parcelle p = j.piocherParcelle(parcelles);
        parcellesList.remove(p);
        return p;
    }

    private void piocherObjectifs(Joueur j) {
        Objectif o = objectifList.randomObjectif();
        j.addObjectif(o);
        objectifList.remove(o);
    }

}
