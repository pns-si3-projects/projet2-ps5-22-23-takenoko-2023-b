package ps5.takenoko.Objectif;

import ps5.takenoko.Joueur.Joueur;

public class Empereur extends Objectif {
    public Empereur() {
        super("Empereur", 2);
    }
    @Override
    public boolean verifie(Joueur j) {
        return true;
    }
}
