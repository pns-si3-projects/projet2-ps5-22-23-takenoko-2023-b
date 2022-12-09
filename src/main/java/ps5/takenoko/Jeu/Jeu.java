package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Joueur.JoueurRandom;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

public class Jeu {
    private static final int nbActions = 2;
    private final int nbObjectifFin;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Plateau plateau;
    private Position positionJardinier = new Position(15,15);
    private Position positionPanda = new Position(15,15);
    private ObjectifList objectifList = new ObjectifList();
    ParcelleList parcellesList = new ParcelleList();

    public Jeu(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
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

    public void run() {
        Plateau plateau = new Plateau();
        Joueur j1 = new JoueurRandom("joueur 1", plateau);
        int score_p1 = 0;
        boolean game = true;
        while (game) {
            switch (j1.choisirAction()) {
                case PIOCHER_PARCELLES -> this.piocherParcelles(j1);
                case OBJECTIFS -> this.piocherObjectifs(j1);
                case POSER_PARCELLES -> this.poserParcelles(j1);
            }
            ;
            j1.validerObjectifs();
            if (j1.getNombreObjectifsObtenus() > 0) {
                game = false;
            }
        }
        System.out.println(j1.getNom() + " a accompli un objectif, Fin du jeu.");
        System.out.println("Score:");
        System.out.println(j1.getNom() + ":" + j1.getScore());
    }
}
