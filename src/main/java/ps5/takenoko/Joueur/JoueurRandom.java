package ps5.takenoko.Joueur;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Position;

import java.util.*;

public class JoueurRandom extends Joueur{

    public JoueurRandom(int id) {
        super(id);
    }

    @Override
    public void poserParcelle(Parcelle p) {
        int R = new Random().nextInt(getPlateau().getEndroitsPosables().size());
        Iterator<Position> iterator = getPlateau().getEndroitsPosables().iterator(); //iterator is already random by itself
        Position position = iterator.next();
        while(R>0){
            position = iterator.next();
            R--;
        }
        getPlateau().addParcelle(p, position);
        System.out.println("------------------------------");
        getPlateau().affichePlateau();
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
        int rand = new Random().nextInt(positionsPossibles.size());
        int index=0;
        Position res = null;
        Iterator<Position> iterator = positionsPossibles.iterator();
        while(iterator.hasNext()){
            res = iterator.next();
            if(index==rand){
                return res;
            }
            index++;
        }
        return res;
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
    public Action jouer(ArrayList<Action> actionsPossibles) {
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }
}
