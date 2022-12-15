package ps5.takenoko.Joueur;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Element.Bamboo;
import ps5.takenoko.Jeu.Jeu;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifPanda;

import java.util.ArrayList;
import java.util.Objects;

public class Joueur {
    private final int MAX_OBJECTIFS = 5 ;
    private final String nom;
    private final Jeu jeu;
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>(MAX_OBJECTIFS);
    private ArrayList<Objectif> objectifsObtenus= new ArrayList<Objectif>();

    private ArrayList<Amenagement> amenagements= new ArrayList<Amenagement>();
    private ArrayList<Bamboo> bamboosObtenus = new ArrayList<Bamboo>();
    private int nbIrrigations;
    //TODO: Carte Empereur = Objectif with 2 points?-> just put in objectifObtenus
    //private boolean estDerniere (est le dernier qui valide le dernier object->avoir empereur)

    //TODO: fix later
    public Joueur(String nom, Jeu jeu, ArrayList<Objectif> objectifs, ArrayList<Objectif> objectifsObtenus, ArrayList<Bamboo> bamboosObtenus, int nbIrrigations) {
        this.nom = nom;
        this.jeu = jeu;
        this.objectifs = objectifs;
        this.objectifsObtenus = objectifsObtenus;
        this.bamboosObtenus = bamboosObtenus;
        this.nbIrrigations = nbIrrigations;
    }

    public int calculPoint(){
        int res=0;
        for(Objectif o: objectifsObtenus){
            res+=o.getPoint();
        }
        return res;
    }

    /**
     * En cas d’égalité, le joueur qui a le plus de points sur ses
     * cartes objectif « Panda » remporte la victoire
     */
    public int calculPointPanda(){
        int res=0;
        for(Objectif o: objectifsObtenus){
            if(o instanceof ObjectifPanda){
                res+=o.getPoint();
            }
        }
        return res;
    }

    public void addObjectif(Objectif obj){
        objectifs.add(Objects.requireNonNull(obj,"Objectif ne doit pas etre NULL"));
    }

    /**
     *
     * Depacer objectif de ArrayList objectifs -> ArrayList objectifs obtenus
     */
    public void completerObjectif(Objectif obj){
        Objects.requireNonNull(obj,"Objectif ne doit pas etre NULL");
        if(!objectifs.contains(obj)){ //TODO: Check if not bug
            throw new IllegalArgumentException("Joueur n'a pas de cet objectif");
        }

        objectifs.remove(obj);
        objectifsObtenus.add(obj);
    }
    public void verifieObjectifs(){
        for (Objectif obj : this.objectifs){
            if (obj.verifie(jeu.getPlateau())) completerObjectif(obj);
        }
    }

    public void ajouteIrrigation(){
        nbIrrigations++;
    }

    public void placerIrrigation(){

    }

}