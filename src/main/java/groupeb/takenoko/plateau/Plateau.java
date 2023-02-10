package groupeb.takenoko.plateau;

import groupeb.takenoko.element.AmenagementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Plateau {

    private ParcelleInactive[][] plateauJeu;
    private static final int TAILLE = 31;

    public ParcelleInactive[][] getPlateau() {
        return plateauJeu;
    }

    private Set<Position> parcellePosee = new HashSet<>();
    private Set<Position> parcelleDisponible = new HashSet<>();

    private Set<Bordure> bordurePosee = new HashSet<>();
    private Set<Bordure> bordureDisponible = new HashSet<>();

    public Plateau() {
        this.plateauJeu = new ParcelleInactive[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateauJeu[i][j] = new ParcelleInactive();
            }
        }
        ParcelleOriginelle etang = new ParcelleOriginelle();
        Position centre = new Position(TAILLE/2,TAILLE/2);//(15,15)
        plateauJeu[centre.getX()][centre.getY()] = etang;
        parcellePosee.add(centre);
        for(Direction d : Direction.values()) {
            parcelleDisponible.add(centre.getPositionByDirection(d));
            Bordure initial = new Bordure (centre,centre.getPositionByDirection(d));
            bordurePosee.add(initial);
            miseAJourBordurePosable(initial);

        }
    }

    public Set<Position> getParcellePosee() {
        return parcellePosee;
    }

    public Set<Bordure> getBordurePosee() {
        return bordurePosee;
    }

    public Set<Bordure> getBordureDisponible() {
        return bordureDisponible;
    }

    public void addParcelle(Parcelle p, Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (plateauJeu[x][y] instanceof Parcelle || x < 0 || y < 0 || x > 30 || y > 30) {
            throw new IllegalArgumentException("On ne peux pas ajouter une parcelle ici");
        }
        this.plateauJeu[x][y] = p;
        parcellePosee.add(pos);
        if(nextToOrigin(pos)) p.irrigue();
        miseAJourParcellePosable(pos);
        miseAJourBordurePosable(pos);
    }

    public boolean nextToOrigin(Position pos){
        Position center = new Position(TAILLE/2,TAILLE/2);
        for(Direction dir : Direction.values()){
            if(center.getPositionByDirection(dir).equals(pos))return true;
        }
        return false;
    }

    private void miseAJourBordurePosable(Position pos) {
        for(Direction dir : Direction.values()){
            Bordure current = new Bordure(pos,pos.getPositionByDirection(dir));
            if(!(entreParcelles(current)))continue;
            for(Bordure voisin : current.adjacentBorder()){
                if(bordurePosee.contains(voisin)){
                    bordureDisponible.add(current);
                    break;
                }
            }

        }
    }
    private void miseAJourBordurePosable(Bordure border) {
        List<Bordure> adjacents = border.adjacentBorder();
        for(Bordure voisin : adjacents){
            if(entreParcelles(voisin))bordureDisponible.add(voisin);
        }
    }

    public boolean entreParcelles(Bordure border){
        return (getParcelle(border.getPos1()) instanceof Parcelle) && (getParcelle(border.getPos2()) instanceof Parcelle);
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
        return this.parcelleDisponible;
    }

    public ParcelleInactive getParcelle(Position p) {
        if (p == null) {
            return null;
        }
        if (p.getX() < 0 || p.getY() < 0 || p.getX() > 30 || p.getY() > 30) {
            return null;
        }
        return this.plateauJeu[p.getX()][p.getY()];
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
            if (this.getParcelle(p.getPositionByDirection(d)) != null) {
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
        Set<Position> connectedParcelle = new HashSet<>();
        Parcelle parcelle;
        Couleur couleur;
        if(!(this.getParcelle(p) instanceof Parcelle)) {
            return new ArrayList<>();
        } else {
            parcelle = (Parcelle) this.getParcelle(p);
            couleur = parcelle.getCouleur();
        }
        connectedParcelle.add(p);
        int size;
        do {
            size = connectedParcelle.size();
            // Create a new list from the Set
            List<Position> tempList = new ArrayList<>(connectedParcelle);
            for (Position pos : tempList) {
                for (Direction d : Direction.values()) {
                    ParcelleInactive tmp = this.getParcelle(pos.getPositionByDirection(d));
                    if (tmp instanceof Parcelle valid && valid.getCouleur() == couleur) {
                            connectedParcelle.add(pos.getPositionByDirection(d));
                    }
                }
            }
        } while (size < connectedParcelle.size());
        return new ArrayList<>(connectedParcelle);
    }

    /**
     * Essaye d'ajouter une irrigation sur la position cible, renvois true si elle a reussis sinon renvois faux
     * @param bordure bordure que l'on veut tester
     * @return boolean si l'irrigation a ete pose.
     */
    public boolean addBordure(Bordure bordure){ return addBordure(bordure.getPos1(),bordure.getPos2());}
    /**
     * Essaye d'ajouter une irrigation sur la position cible, renvois true si elle a reussis sinon renvois faux
     * @param pos1 position d'une parcelle existante
     * @param dir emplacement de l'irrigation par rapport au centre de la parcelle
     * @return boolean si l'irrigation a ete pose.
     */
    public boolean addBordure(Position pos1,Direction dir){ return addBordure(pos1,pos1.getPositionByDirection(dir));}

    /**
     * Essaye d'ajouter une irrigation sur la position cible, renvois true si elle a reussis sinon renvois faux
     * @param pos1 position d'une parcelle existante
     * @param pos2 emplacement de la seconde parcelle
     * @return renvois "true" si l'irrigation a ete pose.
     */
    public boolean addBordure(Position pos1,Position pos2) {
        boolean valid = false;
        for (Direction dir : Direction.values()) if(pos1.getPositionByDirection(dir).equals(pos2)) valid = true;
        if(!valid)throw new IllegalArgumentException("les positions ne sont pas adjacentes");

        Bordure border = new Bordure(pos1,pos2);

        if (!bordureDisponible.contains(border)) return false;

        bordureDisponible.remove(border);
        bordurePosee.add(border);
        miseAJourBordurePosable(border);
        ((Parcelle) getParcelle(pos1)).irrigue();
        ((Parcelle) getParcelle(pos2)).irrigue();
        return true;

    }

    public boolean adjacentIrrigue(Bordure border){
        List<Bordure> adjacent = border.adjacentBorder();
        for(Bordure unit : adjacent){
            if(bordurePosee.contains(unit)) return true;
        }
        return false;
    }

    public Set<Position> getParcellesIrriguees() {
        Set<Position> res = new HashSet<>();
        for(Position p : parcellePosee){
            if(!getParcelle(p).estParcelleOriginelle()&&((Parcelle)getParcelle(p)).estIrrigue()) {
                res.add(p);
            }
        }
        return res;
    }

    public Set<Position> getParcellesAugmentables(){
        Set<Position> res = new HashSet<>();
        for(Position p : parcellePosee){
            if(!getParcelle(p).estParcelleOriginelle()&&((Parcelle) getParcelle(p)).pouvoirAugmenter()){
                res.add(p);
            }
        }
        return res;
    }

    public Set<Position> getParcellesAmenageables(){
        Set<Position> res = new HashSet<>();
        for(Position p : parcellePosee){
            if(!getParcelle(p).estParcelleOriginelle()&&((Parcelle) getParcelle(p)).getNbBamboo()==0 && ((Parcelle) getParcelle(p)).getAmenagement().getType()== AmenagementType.EMPTY){
                res.add(p);
            }
        }
        return res;
    }

    public static int getTaille(){return TAILLE;}

    public void setBordureDisponible(Set<Bordure> bordureDisponible) {
        this.bordureDisponible = bordureDisponible;
    }
}
