package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.*;

import java.security.SecureRandom;
import java.util.*;

public class JoueurMoyen extends JoueurRandom{
    public JoueurMoyen(int id) {
        super(id);
    }

    @Override
    public JoueurMoyen clone(){
        return new JoueurMoyen(this.getId());
    }

    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements, Parcelle p) {
        for(Objectif o: objectifs){
            if(o instanceof ObjectifJardinier) {
                Amenagement res=getSameAmenagements(amenagements, (ObjectifJardinier) o);
                if (res != null)return res;
                for (Amenagement a : amenagements) {
                    if (a.getType() == AmenagementType.ENCLOS && !p.estIrrigue()) {
                        return a;
                    }
                }
            }
            else if(o instanceof ObjectifPanda) {
                if (p.getCouleur().equals(o.getCouleurs())) {
                    for(Amenagement a:amenagements){
                        if(a.getType()== AmenagementType.BASSIN&&!p.estIrrigue()){
                            return a;
                        }
                        if(a.getType()== AmenagementType.ENGRAIS&&p.estIrrigue()){
                            return a;
                        }
                    }
                }
            }
        }
        return super.choisirAmenagement(amenagements);
    }

    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements){
        for (Objectif o : objectifs){
            if(o instanceof ObjectifJardinier) {
                Amenagement a=getSameAmenagements(amenagements, (ObjectifJardinier) o);
                if(a != null)return a;
            }
        }
        return super.choisirAmenagement(amenagements);
    }

    private Amenagement getSameAmenagements(ArrayList<Amenagement> amenagements, ObjectifJardinier o) {
        ObjectifJardinier jar = o;
        for (Amenagement a : amenagements) {
            if (a.getType() == AmenagementType.ENCLOS && jar.getType() == TypeObjJardinier.OBJENCLOS) {
                return a;
            }
            if (a.getType() == AmenagementType.ENGRAIS && jar.getType() == TypeObjJardinier.OBJENGRAIS) {
                return a;
            }
            if (a.getType() == AmenagementType.BASSIN && jar.getType() == TypeObjJardinier.OBJBASSIN) {
                return a;
            }
        }
        Collections.shuffle(amenagements);
        return null;
    }


    @Override
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, ArrayList<Amenagement> amenagements) {
        for (Objectif o : objectifs){
            if(o instanceof ObjectifJardinier){
                ObjectifJardinier jar = (ObjectifJardinier) o;
                for(Position p : positions){
                    if(this.getPlateau().getParcelle(p).estOccupe()){
                        Parcelle par=(Parcelle)this.getPlateau().getParcelle(p);
                        if(par.getCouleur().equals(jar.getCouleurs())&&jar.getType()!= TypeObjJardinier.OBJVIDE){
                            return new ChoixAmenagement(choisirAmenagement(amenagements,par),p);
                        }
                    }
                }
            }
            else if(o instanceof ObjectifPanda){
                ObjectifPanda pan = (ObjectifPanda) o;
                for(Position p : positions){
                    if(this.getPlateau().getParcelle(p).estOccupe()){
                        Parcelle par=(Parcelle)this.getPlateau().getParcelle(p);
                        if(par.getCouleur().equals(pan.getCouleurs())){
                            return new ChoixAmenagement(choisirAmenagement(amenagements,par),p);
                        }
                    }else{
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
        return new ChoixAmenagement(choisirAmenagement(amenagements),getRandomPosition(positions));
    }

    @Override
    public void validerObjectifs() {
        ArrayList<Objectif>validables = objectifsValidable();
        for(Objectif o : validables){
            completerObjectif(o);
        }
    }

    @Override
    public void poserParcelle(Parcelle p) {
        Set<Position> pospos = getPlateau().getEndroitsPosables();
        //foreach pospos
        for(Position pos : pospos){
            for(Direction d : Direction.values()) {
                if (getPlateau().getParcelle(pos.getPositionByDirection(d)) instanceof Parcelle par) {
                    if(par.getCouleur().equals(p.getCouleur())){
                        getPlateau().addParcelle(p, pos);
                        return ;
                    }
                }
            }
        }
        getPlateau().addParcelle(p, getRandomPosition(getPlateau().getEndroitsPosables()));
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
        getPlateau().addBordure(bordure);
        super.placerIrrigation();
    }

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        if(objectifs.size() < MAX_OBJECTIFS && actionsPossibles.contains(Action.OBJECTIFS)){
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
        if(this.getNbIrrigations() <3 && actionsPossibles.contains(Action.PIOCHER_CANAL_DIRRIGATION)){
            return Action.PIOCHER_CANAL_DIRRIGATION;
        }
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }
}
