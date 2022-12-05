package ps5.takenoko.Joueur;

import ps5.takenoko.Element.Bamboo;
import ps5.takenoko.Jeu.Jeu;
import ps5.takenoko.Objectif.Objectif;

import java.util.ArrayList;

public class Joueur {
    private final int MAX_OBJECTIFS = 5 ;
    private final String nom;
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>(MAX_OBJECTIFS);
    private ArrayList<Objectif> objectifsObtenus= new ArrayList<Objectif>();
    private final Jeu jeu;
    private ArrayList<Bamboo> bamboosObtenus = new ArrayList<Bamboo>();
    private int nbIrrigations;

    public Joueur(String nom, ArrayList<Objectif> objectifs, ArrayList<Objectif> objectifsObtenus, Jeu jeu, ArrayList<Bamboo> bamboosObtenus, int nbIrrigations) {
        this.nom = nom;
        this.objectifs = objectifs;
        this.objectifsObtenus = objectifsObtenus;
        this.jeu = jeu;
        this.bamboosObtenus = bamboosObtenus;
        this.nbIrrigations = nbIrrigations;
    }


}
