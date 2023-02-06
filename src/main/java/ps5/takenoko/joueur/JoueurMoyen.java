package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifJardinier;
import ps5.takenoko.objectif.ObjectifPanda;
import ps5.takenoko.objectif.ObjectifParcelle;
import ps5.takenoko.plateau.*;

import java.security.SecureRandom;
import java.util.*;

public class JoueurMoyen extends Joueur{
    public JoueurMoyen(int id) {
        super(id);
    }
    @Override
    public Position choisirParcelleAPousser(Set<Position> positions) {
        //TODO
        return getRandomPosition(positions);
    }
    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements) {
        //TODO
        Collections.shuffle(amenagements);
        return amenagements.get(0);
    }

    @Override
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, ArrayList<Amenagement> amenagements) {
        //TODO
        return new ChoixAmenagement(choisirAmenagement(amenagements),getRandomPosition(positions));
    }
    @Override
    public Meteo choisirMeteo(ArrayList<Meteo> meteos) {
        //TODO
        Collections.shuffle(meteos);
        return meteos.get(0);
    }
    @Override
    public void validerObjectifs() {
        //TODO
        ArrayList<Objectif>validables = objectifsValidable();
        for(Objectif o : validables){
            if(random.nextInt(2) == 0){//50-50% chance
                completerObjectif(o);
            }
        }
    }
    @Override
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifs) {
        //TODO
        Collections.shuffle(objectifs);
        return objectifs.get(0);
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
        int r = random.nextInt(positions.size());
        Iterator<Position> iterator = positions.iterator(); //iterator is already random by itself
        Position position = iterator.next();
        while(r>0){
            position = iterator.next();
            r--;
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
        for(Objectif o : objectifs) {
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
        for(Objectif o : objectifs) {
            if(o instanceof ObjectifJardinier) {
                for(Position p : positionsPossibles){
                    for(int i = 0; i < o.getCouleurs().length; i++) {
                        if(!getPlateau().getParcelle(p).estParcelleOriginelle()) {
                            Parcelle par = (Parcelle) getPlateau().getParcelle(p);
                            if(par.getCouleur() == o.getCouleurs()[i] && par.getNbBamboo() < 4 && par.estIrrigue()) {
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
        for(Objectif o : objectifs) {
            if(o instanceof ObjectifPanda) {
                for(Position p : positionsPossibles){
                    for(int i = 0; i < o.getCouleurs().length; i++) {
                        if(!getPlateau().getParcelle(p).estParcelleOriginelle()) {
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
        Set<Bordure> bordures = getPlateau().getBordureDisponible();
        int R = random.nextInt(bordures.size());
        Iterator<Bordure> iterator = bordures.iterator(); //iterator is already random by itself
        Bordure bordure = iterator.next();
        while(R>0){
            bordure = iterator.next();
            R--;
        }
        getPlateau().addBordure(bordure.getPos1(),bordure.getPos2());
        super.placerIrrigation();
    }

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        if(objectifs.size() < this.MAX_OBJECTIFS && actionsPossibles.contains(Action.OBJECTIFS)){
            return Action.OBJECTIFS;
        }
        if(actionsPossibles.contains(Action.PIOCHER_PARCELLES)){
            return Action.PIOCHER_PARCELLES;
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
