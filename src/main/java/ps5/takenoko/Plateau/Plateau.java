package ps5.takenoko.Plateau;

import java.util.ArrayList;

public class Plateau {
    private ParcelleInactive[][] plateau;
    private static final int TAILLE = 31;

    public Plateau() {
        this.plateau = new Parcelle[TAILLE][TAILLE];
        for(int i=0; i<TAILLE; i++) {
            for(int j=0; i<TAILLE; j++) {
                plateau[i][j] = new ParcelleInactive();
            }
        }
        Parcelle centre = new Parcelle();
        centre.setDebut(true);
        plateau[15][15] = centre;
    }

    public void addParcelle(Parcelle p, int x, int y) throws IllegalAccessException {
        if(plateau[x][y] instanceof Parcelle) {
            throw new IllegalAccessException("On ne peux pas ajouter une parcelle ici");
        }
        this.plateau[x][y] = p;
    }

    public ArrayList<Position> EndroitsPosables() {
        ArrayList<Position> posables = new ArrayList<>();
        for(int i=0; i<TAILLE; i++) {
            for(int j=0; i<TAILLE; j++) {
                if(isPosable(new Position(i,j))) {
                    posables.add(new Position(i,j));
                }
            }
        }
        return posables;
    }

    public Boolean isPosable(Position p) {
        int x = p.getX();
        int y = p.getY();
        if(plateau[x][y] instanceof Parcelle) {
            return false;
        }
        int cpt =0;

        if(plateau[x-1][y] instanceof Parcelle) {
            cpt++;
        }
        if(plateau[x+1][y] instanceof Parcelle) {
            cpt++;
        }
        if(plateau[x][y-1] instanceof Parcelle) {
            cpt++;
        }
        if(plateau[x][y+1] instanceof Parcelle) {
            cpt++;
        }

        if(y % 2 == 0) {
            if(plateau[x+1][y+1] instanceof Parcelle) {
                cpt++;
            }
            if(plateau[x+1][y-1] instanceof Parcelle) {
                cpt++;
            }
        } else {
            if(plateau[x-1][y-1] instanceof Parcelle) {
                cpt++;
            }
            if(plateau[x-1][y+1] instanceof Parcelle) {
                cpt++;
            }
        }
        return cpt > 1;
    }
}
