package ps5.takenoko.Joueur;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

public class JoueurRandom extends Joueur{

    public JoueurRandom(int id) {
        super(id);
    }

    @Override
    public Position poserParcelle(Parcelle p) {
        return null;
    }

    /***
     *
     * Choisir 1 parcelle parmi les 3 et puis le poser sur le plateau
     * @return
     */
    @Override
    public Parcelle piocherParcelle(ArrayList<Parcelle> parcelles) {
        return null;
    }
}
