package ps5.takenoko.Objectif;

import ps5.takenoko.Plateau.Plateau;

public class ObjectifPanda extends Objectif{
    public ObjectifPanda(int point) {
        super(point);
    }

    @Override
    public boolean verifie(Plateau board) {
        return false;
    }
}
