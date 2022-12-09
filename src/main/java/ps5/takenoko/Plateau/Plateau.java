package ps5.takenoko.Plateau;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Plateau {
    private ParcelleInactive[][] plateau;
    private static final int TAILLE = 31;

    public Plateau() {
        this.plateau = new ParcelleInactive[TAILLE][TAILLE];
        for(int i=0; i<TAILLE; i++) {
            for(int j=0; j<TAILLE; j++) {
                plateau[i][j] = new ParcelleInactive();
            }
        }
        ParcelleOriginelle centre = new ParcelleOriginelle();
        plateau[15][15] = centre;
    }

    public void addParcelle(Parcelle p, Position pos) throws IllegalAccessException {
        int x = pos.getX();
        int y = pos.getY();
        if(plateau[x][y] instanceof Parcelle || x < 0 || y < 0 || x > 30 || y > 30) {
            throw new IllegalAccessException("On ne peux pas ajouter une parcelle ici");
        }
        this.plateau[x][y] = p;
    }

    public ArrayList<Position> getEndroitsPosables() {
        ArrayList<Position> posables = new ArrayList<>();
        int cpt=1;
        for(int k=1; k<TAILLE/2-1 && cpt>0; k++,cpt=0) {
            for(int i=-k; i<=k; i++) {
                if(i==k || i==-k){
                    for(int j=-k; j<=k; j++) {
                        if (isPosable(new Position(TAILLE/2+i,TAILLE/2+j))) {
                            posables.add(new Position(TAILLE/2+i,TAILLE/2+j));
                        }
                        if(plateau[TAILLE/2+i][TAILLE/2+j] instanceof Parcelle) cpt++;
                    }
                }else{
                    if (isPosable(new Position(TAILLE/2+i,TAILLE/2+k))) {
                        posables.add(new Position(TAILLE/2+i,TAILLE/2+k));
                    }
                    if(plateau[TAILLE/2+i][TAILLE/2+k] instanceof Parcelle) cpt++;
                    if (isPosable(new Position(TAILLE/2+i,TAILLE/2-k))) {
                        posables.add(new Position(TAILLE/2+i,TAILLE/2-k));
                    }
                    if(plateau[TAILLE/2+i][TAILLE/2-k] instanceof Parcelle) cpt++;
                }
            }
        }
        return posables;
    }

    public ParcelleInactive getParcelle(int x,int y) {
        return getParcelle(new Position(x, y));
    }
    public ParcelleInactive getParcelle(Position p) {
        if(p == null) {
            return null;
        }
        if(p.getX() < 0 || p.getY() < 0 || p.getX() > 30 || p.getY() > 30) {
            return null;
        }
        return this.plateau[p.getX()][p.getY()];
    }

    public Boolean isPosable(Position p) {
        if(p == null) {
            return false;
        }
        if(this.getParcelle(p) instanceof Parcelle) {
            return false;
        }
        int cpt =0;

        for(Direction d : Direction.values()) {
            if (this.getParcelle(p.getPositionByDirection(d)) == null) {
            } else {
                if (this.getParcelle(p.getPositionByDirection(d)) instanceof ParcelleOriginelle) {
                    return true;
                }
                if (this.getParcelle(p.getPositionByDirection(d)) instanceof Parcelle) {
                    cpt++;
                }
            }
        }
        return cpt > 1;
    }
    public static int getTaille(){return TAILLE;}
}
