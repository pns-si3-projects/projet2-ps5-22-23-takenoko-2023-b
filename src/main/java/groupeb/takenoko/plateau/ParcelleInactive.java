package groupeb.takenoko.plateau;

public class ParcelleInactive {

    public boolean estOccupe(){
        return (this instanceof Parcelle || estParcelleOriginelle());
    }
    public boolean estParcelleOriginelle(){
        return this instanceof ParcelleOriginelle;
    }
}
