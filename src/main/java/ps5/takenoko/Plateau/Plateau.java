package ps5.takenoko.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Plateau {

    private ParcelleInactive[][] plateau;
    private static final int TAILLE = 31;

    public ParcelleInactive[][] getPlateau() {
        return plateau;
    }

    private Set<Position> parcellePosee = new HashSet<Position>();

    private Set<Position> parcelleDisponible = new HashSet<Position>();

    public Plateau() {
        this.plateau = new ParcelleInactive[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = new ParcelleInactive();
            }
        }
        ParcelleOriginelle etang = new ParcelleOriginelle();
        Position centre = new Position(TAILLE/2,TAILLE/2);//(15,15)
        plateau[centre.getX()][centre.getY()] = etang;
        parcellePosee.add(centre);
        for(Direction d : Direction.values()) {
            parcelleDisponible.add(centre.getPositionByDirection(d));
        }
    }

    public Set<Position> getParcellePosee() {
        return parcellePosee;
    }

    public void addParcelle(Parcelle p, Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (plateau[x][y] instanceof Parcelle || x < 0 || y < 0 || x > 30 || y > 30) {
            throw new IllegalArgumentException("On ne peux pas ajouter une parcelle ici");
        }
        this.plateau[x][y] = p;
        parcellePosee.add(pos);
        miseAJourParcellePosable(pos);
    }

    public void miseAJourParcellePosable(Position pos){
        parcelleDisponible.remove(pos);
        for(Direction d : Direction.values()) {
            if(positionPosable(pos.getPositionByDirection(d))) {
                parcelleDisponible.add(pos.getPositionByDirection(d));
            }
        }
    }

    public Set<Position> getEndroitsPosables() {
        Set<Position> posables = new HashSet<Position>(this.parcelleDisponible);
        return this.parcelleDisponible;
    }

    public ParcelleInactive getParcelle(Position p) {
        if (p == null) {
            return null;
        }
        if (p.getX() < 0 || p.getY() < 0 || p.getX() > 30 || p.getY() > 30) {
            return null;
        }
        return this.plateau[p.getX()][p.getY()];
    }

    public Boolean positionPosable(Position p) {
        if (p == null) {
            return false;
        }
        if (this.getParcelle(p) instanceof Parcelle) {
            return false;
        }
        if (this.getParcelle(p) instanceof ParcelleOriginelle) {
            return false;
        }
        int cpt = 0;

        for (Direction d : Direction.values()) {
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



    // return an array of all the positions of the parcelles that are adjacent to the parcelle at the given position
    public ArrayList<Position> getConnectedParcelleSameColor(Position p) {
        Set<Position> connectedParcelle = new HashSet<Position>();
        Parcelle parcelle;
        Couleur couleur;
        if(!(this.getParcelle(p) instanceof Parcelle)) {
            return new ArrayList<Position>();
        } else {
            parcelle = (Parcelle) this.getParcelle(p);
            couleur = parcelle.getCouleur();
        }
        connectedParcelle.add(p);
        int size;
        do {
            size = connectedParcelle.size();
            // Create a new list from the Set
            List<Position> tempList = new ArrayList<Position>(connectedParcelle);
            for (Position pos : tempList) {
                for (Direction d : Direction.values()) {
                    ParcelleInactive tmp = this.getParcelle(pos.getPositionByDirection(d));
                    if (tmp != null) {
                        if (tmp instanceof Parcelle) {
                            if(((Parcelle) tmp).getCouleur() == couleur) {
                                connectedParcelle.add(pos.getPositionByDirection(d));
                            }
                        }
                    }
                }
            }
        } while (size < connectedParcelle.size());
        return new ArrayList<Position>(connectedParcelle);
    }

    public static int getTaille(){return TAILLE;}

}
