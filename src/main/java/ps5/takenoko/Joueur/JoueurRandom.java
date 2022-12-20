package ps5.takenoko.Joueur;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

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

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }
}
