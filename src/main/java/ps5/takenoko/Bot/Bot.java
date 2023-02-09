package ps5.takenoko.Bot;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.security.SecureRandom;
import java.util.*;

public abstract class Bot implements Comparable<Bot> {
    protected SecureRandom random = new SecureRandom();
    protected static final int MAX_OBJECTIFS = 5;

    private final int id;

    protected Jeu jeu;
    protected ArrayList<Objectif> objectifs = new ArrayList<>(MAX_OBJECTIFS);
    private ArrayList<Objectif> objectifsObtenus = new ArrayList<>();

    private final ArrayList<Amenagement> amenagements = new ArrayList<>();
    private int nbIrrigations;
    private int[] bambousObtenus = new int[] { 0, 0, 0 };

    public int getNombreObjectifsObtenus() {
        return objectifsObtenus.size();
    }

    // private boolean estDerniere (est le dernier qui valide le dernier
    // object->avoir empereur)
    public Bot(int id) {
        this.id = id;
    }

    public abstract Bot clone();

    public int getNbIrrigations() {
        return nbIrrigations;
    }

    public int getId() {
        return id;
    }

    public List<Objectif> getObjectifsObtenus() {
        return objectifsObtenus;
    }

    public int[] getBambousObtenus() {
        return bambousObtenus;
    }

    public List<Objectif> getObjectifs() {
        return objectifs;
    }

    public List<Amenagement> getAmenagements() {
        return amenagements;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Plateau getPlateau() {
        return jeu.getPlateau();
    }

    public int calculPoint() {
        int res = 0;
        for (Objectif o : objectifsObtenus) {
            res += o.getPoint();
        }
        return res;
    }

    /**
     * En cas d’égalité, le Bot qui a le plus de points sur ses
     * cartes objectif « Panda » remporte la victoire
     */
    public int calculPointPanda() {
        int res = 0;
        for (Objectif o : objectifsObtenus) {
            if (o instanceof ObjectifPanda) {
                res += o.getPoint();
            }
        }
        return res;
    }

    public void addObjectif(Objectif obj) {
        objectifs.add(Objects.requireNonNull(obj, "Objectif ne doit pas etre NULL"));
    }

    /**
     *
     * Depacer objectif de ArrayList objectifs -> ArrayList objectifs obtenus
     */
    public void completerObjectif(Objectif o) {
        Objects.requireNonNull(o, "Objectif ne doit pas etre NULL");
        if (o instanceof Empereur obj) {
            objectifsObtenus.add(obj);
        } else {
            if (!(objectifs.contains(o))) {
                throw new IllegalArgumentException("Bot n'a pas de cet objectif");
            } else if (o instanceof ObjectifPanda obj) {
                for (int i = 0; i < obj.getCouleurs().length; i++) {
                    enleverBambous(obj.getNbParcelles(), obj.getCouleurs()[i]);
                }
            }
            objectifs.remove(o);
            objectifsObtenus.add(o);
        }
    }

    public abstract void validerObjectifs();

    public List<Objectif> objectifsValidable() {
        ArrayList<Objectif> objectifsValidable = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if (objectif.verifie(this)) {
                objectifsValidable.add(objectif);
            }
        }
        return objectifsValidable;
    }

    public void ajouteIrrigation() {
        nbIrrigations++;
    }

    public abstract void poserParcelle(Parcelle p);

    public void ajouteBambou(Couleur c) {
        bambousObtenus[c.ordinal()]++;
    }

    public void enleverBambous(int nb, Couleur c) {
        bambousObtenus[c.ordinal()] -= nb;
    }

    public int nbBambousParCouleur(Couleur c) {
        return bambousObtenus[c.ordinal()];
    }

    public abstract Position choisirParcelleAPousser(Set<Position> positions);

    /***
     *
     * @return 1 Parcelle choisi
     */
    public abstract Parcelle piocherParcelle(List<Parcelle> parcelles);

    public abstract Position deplacerJardinier(Set<Position> positionsPossibles);

    public abstract Position deplacerPanda(Set<Position> positionsPossibles);

    public abstract Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifs);

    @Override
    public int compareTo(Bot other) {
        int result = Integer.compare(this.calculPoint(), other.calculPoint());
        if (result != 0) {
            return result;
        }
        return Integer.compare(this.calculPointPanda(), other.calculPointPanda());
    }

    public void reset() {
        objectifsObtenus.clear();
        objectifs.clear();
        amenagements.clear();
        nbIrrigations = 0;
        bambousObtenus = new int[] { 0, 0, 0 };
    }

    public abstract Action jouer(List<Action> actionsPossibles);

    public abstract Amenagement choisirAmenagement(List<Amenagement> amenagements);

    public void addAmenagement(Amenagement amenagement) {
        amenagements.add(amenagement);
    }
    
    public abstract ChoixAmenagement choisirPositionAmenagement( Set<Position> positions, List<Amenagement>amenagements);
    public void placerIrrigation(){
        useIrrigation();
    }

    public void useIrrigation(){
        if(this.nbIrrigations>0) {
            this.nbIrrigations--;
        }else{
            throw new IllegalArgumentException("Le Bot n'a pas d'irrigations");
        }
    }

    public abstract Meteo choisirMeteo(List<Meteo> meteos);

    public void setObjectifsObtenus(List<Objectif> objectifsObtenus) {
        this.objectifsObtenus = new ArrayList<>(objectifsObtenus);
    }

    public List<Class<? extends Objectif>> getObjectifsTypes(){
        ArrayList<Class<? extends Objectif>> objectifsTypes = new ArrayList<>();
        for(Objectif o : objectifs){
            objectifsTypes.add(o.getClass());
        }
        return objectifsTypes;
    }
}