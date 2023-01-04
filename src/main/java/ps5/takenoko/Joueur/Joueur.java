package ps5.takenoko.Joueur;

import ps5.takenoko.Element.Amenagement;
import ps5.takenoko.Objectif.*;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.*;

public abstract class Joueur implements Comparable<Joueur>{
    private final int MAX_OBJECTIFS = 5 ;
    
    private final int MAX_PARCELLES =12;
    private final int id;
    private Plateau plateau;
    private ArrayList<Parcelle> parcelles = new ArrayList<Parcelle>(MAX_PARCELLES);
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>(MAX_OBJECTIFS);
    private ArrayList<Objectif> objectifsObtenus= new ArrayList<Objectif>();

    private ArrayList<Amenagement> amenagements= new ArrayList<Amenagement>();
    private int nbIrrigations;
    private int[] bambousObtenus = new int[]{0,0,0};


    public int getNombreObjectifsObtenus() {
        return objectifsObtenus.size();
    }

    //private boolean estDerniere (est le dernier qui valide le dernier object->avoir empereur)
    public Joueur(int id){
        this.id=id;
    }
    //TODO: fix later
    public Joueur(int id, ArrayList<Objectif> objectifs, ArrayList<Objectif> objectifsObtenus, int nbIrrigations) {
        this.id=id;
        this.objectifs = objectifs;
        this.objectifsObtenus = objectifsObtenus;
        this.nbIrrigations = nbIrrigations;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getNbIrrigations() {
        return nbIrrigations;
    }
    public int getId() {return id;}
    public ArrayList<Objectif> getObjectifsObtenus() {return objectifsObtenus;}
    public ArrayList<Parcelle> getParcelles() {return parcelles;}
    public int[] getBambousObtenus() {return bambousObtenus;}
    public ArrayList<Objectif> getObjectifs() {return objectifs;}
    public void setParcelles(ArrayList<Parcelle> parcelles) {this.parcelles = parcelles;}

    public Plateau getPlateau() {return plateau;}

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
        if(obj instanceof Empereur){
            objectifsObtenus.add(obj);
        }
        else{
            if(!(objectifs.contains(obj))){
                throw new IllegalArgumentException("Joueur n'a pas de cet objectif");
            }
            if (obj instanceof ObjectifJardinier){
                //TODO
            }
            else if (obj instanceof ObjectifPanda) {
                for (int i = 0; i < obj.getCouleurs().length; i++) {
                    enleverBambous(((ObjectifPanda) obj).getNbBambous(),obj.getCouleurs()[i]);
                }
            }
            else if (obj instanceof ObjectifParcelle) {
                //TODO
            }
            else{
                throw new IllegalArgumentException("Type d'objectif inconnu");
            }
            objectifs.remove(obj);
            objectifsObtenus.add(obj);
        }
    }

    public void validerObjectifs(){
        for(int i=0;i<objectifs.size();i++){
            if(objectifs.get(i).verifie(this)) {
                completerObjectif(objectifs.get(i));
                i--;
            }
        }
    }

    public void ajouteIrrigation(){
        nbIrrigations++;
    }

    public Parcelle donnerParcelle() {
        Parcelle res=this.parcelles.get(0);
        this.parcelles.remove(0);
        return res;
    }

    public abstract void poserParcelle(Parcelle p);
    public void ajouteBambou(Couleur c){
        bambousObtenus[c.ordinal()]++;
    }
    public void enleverBambous(int nb, Couleur c){
        bambousObtenus[c.ordinal()]-=nb;
    }

    public int nbBambousParCouleur(Couleur c){
        return bambousObtenus[c.ordinal()];
    }


    /***
     *
     * @return 1 Parcelle choisi
     */
    public abstract Parcelle piocherParcelle(ArrayList<Parcelle> parcelles);
    public abstract Position deplacerJardinier(Set<Position> positionsPossibles);
    public abstract Position deplacerPanda(Set<Position> positionsPossibles);
    @Override
    public int compareTo(Joueur other) {
        int result = Integer.compare(this.calculPoint(),other.calculPoint());
        if (result != 0) {
            return result;
        }
        return Integer.compare( this.calculPointPanda(),other.calculPointPanda());
    }

    public abstract Action jouer(ArrayList<Action> actionsPossibles);

    //TODO:
    public void placerIrrigation(){

    }



}