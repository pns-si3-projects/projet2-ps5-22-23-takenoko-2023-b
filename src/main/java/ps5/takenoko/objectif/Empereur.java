package ps5.takenoko.objectif;

import ps5.takenoko.joueur.Joueur;

public class Empereur extends Objectif {
    public Empereur() {
        super("Empereur", 2);
    }
    @Override
    public boolean verifie(Joueur j) {
        return true;
    }
}
