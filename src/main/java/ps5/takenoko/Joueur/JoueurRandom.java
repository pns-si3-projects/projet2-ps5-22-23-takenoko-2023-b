package ps5.takenoko.Joueur;

import ps5.takenoko.Element.Bamboo;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

public class JoueurRandom extends Joueur{

    public JoueurRandom(String nom, Plateau plateau) {
        super(nom, plateau);
    }

    @Override
    public Position poserParcelle(Parcelle p) {
        return null;
    }

    @Override
    public void piocherParcelle(ArrayList<Parcelle> parcelles) {

    }
}
