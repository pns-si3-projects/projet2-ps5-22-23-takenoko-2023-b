package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.plateau.Bordure;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Position;

import java.lang.reflect.InaccessibleObjectException;
import java.security.SecureRandom;
import java.util.*;

public class JoueurRandom extends Joueur{

    public JoueurRandom(int id) {
        super(id);
    }

    @Override
    public JoueurRandom clone(){
        return new JoueurRandom(this.getId());
    }

    @Override
    public Position choisirParcelleAPousser(Set<Position> positions) {
        return getRandomPosition(positions);
    }

    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements) {
        return (Amenagement) randomList(amenagements);
    }

    @Override
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, ArrayList<Amenagement> amenagements) {
        return new ChoixAmenagement(choisirAmenagement(amenagements),getRandomPosition(positions));
    }
    @Override
    public Meteo choisirMeteo(ArrayList<Meteo> meteos) {
        return (Meteo)randomList(meteos);
    }
    @Override
    public void validerObjectifs() {
        ArrayList<Objectif>validables = objectifsValidable();
        for(Objectif o : validables){
            if(random.nextInt(2) == 0){//50-50% chance
                completerObjectif(o);
            }
        }
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
     * @return
     */
    @Override
    public Parcelle piocherParcelle(ArrayList<Parcelle> parcelles) {
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
        return (Class<? extends Objectif>)randomList(objectifs);
    }

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        return (Action)randomList(actionsPossibles);
    }

    @Override
    public void placerIrrigation(){
        if(getNbIrrigations()<=0) throw new InaccessibleObjectException();
        Set<Bordure> bordures = getPlateau().getBordureDisponible();
        int r = random.nextInt(bordures.size());
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