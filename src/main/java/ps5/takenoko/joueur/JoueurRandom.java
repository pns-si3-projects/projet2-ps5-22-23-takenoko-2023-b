package ps5.takenoko.joueur;

import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.plateau.Bordure;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Position;

import java.util.*;

public class JoueurRandom extends Joueur{
    Random random = new Random();

    public JoueurRandom(int id) {
        super(id);
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
        Collections.shuffle(objectifs);
        return objectifs.get(0);
    }

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }

    @Override
    public void placerIrrigation(){
        Set<Bordure> bordures = getPlateau().getBordureDisponible();
        int r = random.nextInt(bordures.size());
        Iterator<Bordure> iterator = bordures.iterator(); //iterator is already random by itself
        Bordure bordure = iterator.next();
        while(r>0){
            bordure = iterator.next();
            r--;
        }
        getPlateau().addBordure(bordure.getPos1(),bordure.getPos2());
        super.placerIrrigation();
    }
}