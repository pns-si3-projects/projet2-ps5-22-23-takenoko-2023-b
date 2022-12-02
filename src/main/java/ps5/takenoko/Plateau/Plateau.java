package ps5.takenoko.Plateau;

import java.util.ArrayList;

public class Plateau {
    private ParcelleInactive[][] plateau;
    private static final int TAILLE = 20;

    public Plateau() {
        this.plateau = new Parcelle[TAILLE][TAILLE];
        for(int i=0; i<TAILLE; i++) {
            for(int j=0; i<TAILLE; j++) {
                plateau[i][j] = new ParcelleInactive();
            }
        }
    }

    public void addParcelle(Parcelle p, int x, int y) throws IllegalAccessException {
        if(plateau[x][y] instanceof Parcelle) {
            throw new IllegalAccessException("On ne peux pas ajouter une parcelle ici");
        }
        this.plateau[x][y] = p;
    }

    public ArrayList<ParcelleInactive> EndroitsPosables() {
        ArrayList<ParcelleInactive> posables = new ArrayList<>();
        return posables;
    }


}
