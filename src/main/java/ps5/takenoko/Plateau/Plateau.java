package ps5.takenoko.Plateau;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Plateau {
    private static final int MAX_PARCELLES = 28;
    private Set<Parcelle> parcelles; //Set est comme ArrayList mais eviter d'avoir les duplications où parcelle1.equals(parcelle2) = true

    public Plateau() {
        ajouteEtang();
    }

    public void ajouteEtang(){
        if(parcelles.size()>0){
            throw new IllegalStateException("Parcelle d'etrange doit etre le premier parcelle");
        }
        this.parcelles.add(new Parcelle(Couleur.ETANG, new Position(0,0)));
    }

    public boolean contiensPosition(Position p){
        for(Parcelle parcelle : parcelles){
            if(parcelle.getPosition().equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     *La parcelle retenue est ensuite mise en jeu
     * en respectant l’une ET/OU l’autre de ces deux
     * règles :
     * la parcelle est adjacente à la parcelle Spéciale (étang) ;
     * la parcelle est adjacente à au moins deux
     * parcelles déjà en jeu
     */
    public boolean estPosable(Position p){
        return (!contiensPosition(p) && (p.estAdjacente(new Position(0,0)) || nbAdjacente(p)>=2 ));
    }

    //TODO: Tester si ya bug
    public int nbAdjacente(Position p){
        int res=0;
        for(Parcelle pa: this.parcelles){
            for(Direction d: Direction.values()){
                if(pa.getPosition().equals(p.getAdjacente(d,p))){
                    res++;
                    break; 
                }
            }
        }
        return res;
    }

    //TODO: Position est defined dans le Parcelle est mieux ou po?
    public void ajouteParcelle(Parcelle p, Position pos) throws IllegalAccessException {
        if(estPosable(pos) && parcelles.size()< MAX_PARCELLES){
            p.setPosition(pos);
            parcelles.add(p);
        }
        else{
            throw new IllegalAccessException("On ne peux pas ajouter une parcelle ici");
        }
    }

    /**
     * La parcelle retenue est ensuite mise en jeu
     * en respectant l’une ET/OU l’autre de ces deux
     * règles :
     * • la parcelle est adjacente à la parcelle Spéciale (étang) ;
     * • la parcelle est adjacente à au moins deux
     * parcelles déjà en jeu.
     * TODO: Test if there is duplication or not (normally Set would handle them itself)
     *
     */
    public Set<Position> endroitsPosables(){
        Set<Position> posables = new HashSet<Position>();
        for (Parcelle p : parcelles){
            for(Direction d: Direction.values()) {
                if(estPosable(p.getPosition().getAdjacente(d,p.getPosition()))){
                    posables.add(p.getPosition().getAdjacente(d,p.getPosition()));
                }
            }
        }
        return posables;
    }

}