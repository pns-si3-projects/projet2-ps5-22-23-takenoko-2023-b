package groupeb.takenoko.bot;

import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.Meteo;
import groupeb.takenoko.objectif.Objectif;
import groupeb.takenoko.plateau.Bordure;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Position;

import java.lang.reflect.InaccessibleObjectException;
import java.util.*;

public class BotRandom extends Bot {

    public BotRandom(int id) {
        super(id);
    }

    @Override
    public BotRandom clone(){
        return new BotRandom(this.getId());
    }

    @Override
    public Position choisirParcelleAPousser(Set<Position> positions) {
        return getRandomPosition(positions);
    }

    @Override
    public Amenagement choisirAmenagement(List<Amenagement> amenagements) {
        return (Amenagement) randomList(amenagements);
    }

    @Override
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, List<Amenagement> amenagements) {
        return new ChoixAmenagement(choisirAmenagement(amenagements),getRandomPosition(positions));
    }
    @Override
    public Meteo choisirMeteo(List<Meteo> meteos) {
        return (Meteo)randomList(meteos);
    }
    @Override
    public List<Objectif> validerObjectifs() {
        List<Objectif>validables = objectifsValidable();
        List<Objectif> valide = new ArrayList<>();
        for(Objectif o : validables){
            if(random.nextInt(2) == 0){//50-50% chance
                completerObjectif(o);
                valide.add(o);
            }
        }
        return valide;
    }

    @Override
    public void poserParcelle(Parcelle p) {
        getPlateau().addParcelle(p, getRandomPosition(getPlateau().getEndroitsPosables()));
    }


    public Position getRandomPosition(Set<Position> positions){
        return (Position)randomSet(positions);
    }

    /***
     *
     * Choisir 1 parcelle parmi les 3 et puis le poser sur le plateau
     */
    @Override
    public Parcelle piocherParcelle(List<Parcelle> parcelles) {
        Collections.shuffle(parcelles);
        return parcelles.get(0);
    }


    public Position deplacerPersonnage(Set<Position> positionsPossibles) {
        return getRandomPosition(positionsPossibles);
    }

    @Override
    public Position deplacerJardinier(Set<Position> positionsPossibles) {
        return deplacerPersonnage(positionsPossibles);
    }
    @Override
    public Position deplacerPanda(Set<Position> positionsPossibles) {
        return deplacerPersonnage(positionsPossibles);
    }

    @Override
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifs) {
        //return ObjectifParcelle.class;
        return (Class<? extends Objectif>)randomList(objectifs);
    }

    @Override
    public Action jouer(List<Action> actionsPossibles) {
        return (Action)randomList(actionsPossibles);
    }

    @Override
    public void placerIrrigation(){
        if(getNbIrrigations()<=0) throw new InaccessibleObjectException();
        Set<Bordure> bordures = getPlateau().getBordureDisponible();
        Bordure bordure = (Bordure) randomSet(bordures);
        getPlateau().addBordure(bordure.getPos1(),bordure.getPos2());
        super.placerIrrigation();
    }

    public Object randomList(List list){
        Collections.shuffle(list);
        return list.get(0);
    }

    public Object randomSet(Set set){
        int r = random.nextInt(set.size());
        Iterator iterator = set.iterator(); //iterator is already random by itself
        Object o = iterator.next();
        while(r>0){
            o = iterator.next();
            r--;
        }
        return o;
    }
}