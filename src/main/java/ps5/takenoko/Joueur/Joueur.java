package ps5.takenoko.Joueur;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Element.Bamboo;
import ps5.takenoko.Jeu.Jeu;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifPanda;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;
import java.util.Objects;

public class Joueur {
    private final int MAX_OBJECTIFS = 5 ;
    
    private final int MAX_PARCELLES =12;
    private final String nom;

    private Plateau plateau;
    private ArrayList<Parcelle> parcelles = new ArrayList<Parcelle>(MAX_PARCELLES);
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>(MAX_OBJECTIFS);
    private ArrayList<Objectif> objectifsObtenus= new ArrayList<Objectif>();

    private ArrayList<Amenagement> amenagements= new ArrayList<Amenagement>();
    private ArrayList<Bamboo> bamboosObtenus = new ArrayList<Bamboo>();
    private int nbIrrigations;

    public int getNombreObjectifsObtenus() {
        return objectifsObtenus.size();
    }

    //TODO: Carte Empereur = Objectif with 2 points?-> just put in objectifObtenus
    //private boolean estDerniere (est le dernier qui valide le dernier object->avoir empereur)
    public Joueur(String nom,Plateau plateau){
        this.nom = nom;
        this.plateau=plateau;
    }
    //TODO: fix later
    public Joueur(String nom, ArrayList<Objectif> objectifs, ArrayList<Objectif> objectifsObtenus, ArrayList<Bamboo> bamboosObtenus, int nbIrrigations) {
        this.nom = nom;
        this.objectifs = objectifs;
        this.objectifsObtenus = objectifsObtenus;
        this.bamboosObtenus = bamboosObtenus;
        this.nbIrrigations = nbIrrigations;
    }

    public String getNom() {
        return nom;
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
    
    public void verifierObjectifs(){
        for(Objectif o: objectifs){
            if(o.verifier()){
                completerObjectif(o);
            }
        }
    }

    public void ajouteIrrigation(){
        nbIrrigations++;
    }

    public Action choisirAction(){
        return null;
    }
    public void placerIrrigation(){
    }

    public void ajouteParcelle(Parcelle p) {
        this.parcelles.add(p);
    }

    public Parcelle donnerParcelle() {
        Parcelle res=this.parcelles.get(0);
        this.parcelles.remove(0);
        return res;
    }

    public Position poserParcelle(Parcelle p){
        for(Position pos: plateau.getEndroitsPosables()){
            return pos;
        }
        return null;
    }

    public int getScore() {
        int score=0;
        for(Objectif o:objectifsObtenus){
            score+=o.getPoint();
        }
        return score;
    }
}