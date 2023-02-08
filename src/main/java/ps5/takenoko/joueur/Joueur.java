package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;

public abstract class Joueur implements Comparable<Joueur>{
    protected SecureRandom random = new SecureRandom();
    protected final static int MAX_OBJECTIFS = 5 ;

    private final int id;

    protected Jeu jeu;
    protected ArrayList<Objectif> objectifs = new ArrayList<>(MAX_OBJECTIFS);
    private ArrayList<Objectif> objectifsObtenus= new ArrayList<>();

    private ArrayList<Amenagement> amenagements= new ArrayList<>();
    private int nbIrrigations;
    private int[] bambousObtenus = new int[]{0,0,0};


    public int getNombreObjectifsObtenus() {
        return objectifsObtenus.size();
    }

    //private boolean estDerniere (est le dernier qui valide le dernier object->avoir empereur)
    public Joueur(int id){
        this.id=id;
    }

    public abstract Joueur clone();

    public int getNbIrrigations() {
        return nbIrrigations;
    }
    public int getId() {return id;}
    public ArrayList<Objectif> getObjectifsObtenus() {return objectifsObtenus;}
    public int[] getBambousObtenus() {return bambousObtenus;}
    public ArrayList<Objectif> getObjectifs() {return objectifs;}
    public ArrayList<Amenagement> getAmenagements() {return amenagements;}

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
            else if (obj instanceof ObjectifPanda) {
                for (int i = 0; i < obj.getCouleurs().length; i++) {
                    enleverBambous(((ObjectifPanda) obj).getNbParcelles(),obj.getCouleurs()[i]);
                }
            }
            objectifs.remove(obj);
            objectifsObtenus.add(obj);
        }
    }

    public abstract void validerObjectifs();

    public ArrayList<Objectif> objectifsValidable(){
        ArrayList<Objectif> objectifsValidable = new ArrayList<>();
        for(int i=0;i<objectifs.size();i++){
            if(objectifs.get(i).verifie(this)) {
                objectifsValidable.add(objectifs.get(i));
            }
        }
        return objectifsValidable;
    }

    public void ajouteIrrigation(){
        nbIrrigations++;
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
    public abstract Position choisirParcelleAPousser(Set<Position> positions);

    /***
     *
     * @return 1 Parcelle choisi
     */
    public abstract Parcelle piocherParcelle(ArrayList<Parcelle> parcelles);
    public abstract Position deplacerJardinier(Set<Position> positionsPossibles);
    public abstract Position deplacerPanda(Set<Position> positionsPossibles);
    public abstract Class<? extends Objectif> choisirObjectif (List<Class<? extends Objectif>> objectifs);
    @Override
    public int compareTo(Joueur other) {
        int result = Integer.compare(this.calculPoint(),other.calculPoint());
        if (result != 0) {
            return result;
        }
        return Integer.compare( this.calculPointPanda(),other.calculPointPanda());
    }

    public void reset(){
        objectifsObtenus.clear();
        objectifs.clear();
        amenagements.clear();
        nbIrrigations=0;
        bambousObtenus = new int[]{0,0,0};
    }
    public abstract Action jouer(ArrayList<Action> actionsPossibles);
    public abstract Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements);
    public void addAmenagement(Amenagement amenagement){
        amenagements.add(amenagement);
    }
    public abstract ChoixAmenagement choisirPositionAmenagement( Set<Position> positions, ArrayList<Amenagement>amenagements);
    public void placerIrrigation(){
        this.nbIrrigations--;
    }
    public abstract Meteo choisirMeteo(ArrayList<Meteo> meteos);


    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}