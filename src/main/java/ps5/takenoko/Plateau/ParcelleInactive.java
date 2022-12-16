package ps5.takenoko.Plateau;

public class ParcelleInactive {

    public boolean estOccupe(){
        return (this instanceof Parcelle || estParcelleOriginnelle());
    }
    public boolean estParcelleOriginnelle(){
        return this instanceof ParcelleOriginelle;
    }
}
