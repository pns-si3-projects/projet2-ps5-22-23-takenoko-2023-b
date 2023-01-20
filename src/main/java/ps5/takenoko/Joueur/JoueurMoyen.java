package ps5.takenoko.Joueur;

import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifJardinier;
import ps5.takenoko.Objectif.ObjectifPanda;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Plateau.*;

import java.util.*;

public class JoueurMoyen extends Joueur{
    public JoueurMoyen(int id) {
        super(id);
    }

    @Override
    public void poserParcelle(Parcelle p) {
        getPlateau().addParcelle(p, getRandomPosition(getPlateau().getEndroitsPosables()));
        Set<Position> pospos = getPlateau().getEndroitsPosables();
        boolean b = false;
        //foreach pospos
        for(Position pos : pospos){
            for(Direction d : Direction.values()) {
                if (getPlateau().getParcelle(pos.getPositionByDirection(d)) instanceof Parcelle) {
                    Parcelle par = (Parcelle) getPlateau().getParcelle(pos.getPositionByDirection(d));
                    if(par.getCouleur().equals(p.getCouleur())){
                        getPlateau().addParcelle(p, pos);
                        b = true;
                        return ;
                    }
                }
            }
        }
        if(!b){
            getPlateau().addParcelle(p, getRandomPosition(getPlateau().getEndroitsPosables()));
        }
    }

    public Position getRandomPosition(Set<Position> positions){
        int R = new Random().nextInt(positions.size());
        Iterator<Position> iterator = positions.iterator(); //iterator is already random by itself
        Position position = iterator.next();
        while(R>0){
            position = iterator.next();
            R--;
        }
        return position;
    }

    /***
     *
     * Choisir 1 parcelle parmi les 3 et puis le poser sur le plateau
     * @return
     */
    @Override
    public Parcelle piocherParcelle(ArrayList<Parcelle> parcelles) {
        Collections.shuffle(parcelles);
        for(Objectif o : this.getObjectifs()) {
            if(o instanceof ObjectifParcelle) {
                for(int i = 0; i < o.getCouleurs().length; i++) {
                    for(Parcelle p : parcelles) {
                        if(o.getCouleurs()[i] == p.getCouleur()) {
                            return p;
                        }
                    }
                }
            }
        }
        return parcelles.get(0);
    }


    public Position deplacerPersonnage(Set<Position> positionsPossibles) {
        return getRandomPosition(positionsPossibles);
    }

    @Override
    public Position deplacerJardinier(Set<Position> positionsPossibles) {
        for(Objectif o : this.getObjectifs()) {
            if(o instanceof ObjectifJardinier) {
                for(Position p : positionsPossibles){
                    for(int i = 0; i < o.getCouleurs().length; i++) {
                        if(!getPlateau().getParcelle(p).estParcelleOriginnelle()) {
                            Parcelle par = (Parcelle) getPlateau().getParcelle(p);
                            if(par.getCouleur() == o.getCouleurs()[i] && par.getNbBamboo() < 4) {
                                return p;
                            }
                        }
                    }
                }
            }
        }
        return deplacerPersonnage(positionsPossibles);
    }
    @Override
    public Position deplacerPanda(Set<Position> positionsPossibles) {
        for(Objectif o : this.getObjectifs()) {
            if(o instanceof ObjectifPanda) {
                for(Position p : positionsPossibles){
                    for(int i = 0; i < o.getCouleurs().length; i++) {
                        if(!getPlateau().getParcelle(p).estParcelleOriginnelle()) {
                            Parcelle par = (Parcelle) getPlateau().getParcelle(p);
                            if(par.getCouleur() == o.getCouleurs()[i] && par.getNbBamboo() > 0) {
                                return p;
                            }
                        }
                    }
                }
            }
        }
        return deplacerPersonnage(positionsPossibles);
    }

    @Override
    public void placerIrrigation(){
        Random Rdm = new Random();
        Set<Bordure> bordures = getPlateau().getBordureDisponible();
        int R = Rdm.nextInt(bordures.size());
        Iterator<Bordure> iterator = bordures.iterator(); //iterator is already random by itself
        Bordure bordure = iterator.next();
        while(R>0){
            bordure = iterator.next();
            R--;
        }
        getPlateau().addBordure(bordure.getPos1(),bordure.getPos2());
    }

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        if(getObjectifs().size() < this.MAX_OBJECTIFS && actionsPossibles.contains(Action.OBJECTIFS)){
            return Action.OBJECTIFS;
        }
        if(actionsPossibles.contains(Action.PIOCHER_PARCELLES)){
            return Action.PIOCHER_PARCELLES;
        }
        if(actionsPossibles.contains(Action.POSER_PARCELLES)){
            return Action.POSER_PARCELLES;
        }
        if(actionsPossibles.contains(Action.PANDA)){
            return Action.PANDA;
        }
        if(actionsPossibles.contains(Action.JARDINIER)){
            return Action.JARDINIER;
        }

        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }
}
