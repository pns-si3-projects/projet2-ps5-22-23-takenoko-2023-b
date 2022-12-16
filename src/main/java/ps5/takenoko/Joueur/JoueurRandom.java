package ps5.takenoko.Joueur;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;
import java.util.Collections;

public class JoueurRandom extends Joueur{

    public JoueurRandom(int id) {
        super(id);
    }

    @Override
    public void poserParcelle(Parcelle p) {
        Position position = getPlateau().getEndroitsPosables().iterator().next(); //interator is already random by itself
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

    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }
}
