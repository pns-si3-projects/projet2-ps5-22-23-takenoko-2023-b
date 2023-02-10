package groupeb.takenoko.element;

import java.util.Objects;

public class Amenagement {
    private AmenagementType type = null;
    private int nbBambouAManger=1;
    private int nbBambouAPousser=1;
    public Amenagement(){
        this.type = AmenagementType.EMPTY;
    }
    public Amenagement(AmenagementType type) {
        this.type = type;
        switch (type){
            case ENCLOS:
                this.nbBambouAManger = 0;
                break;
            case ENGRAIS:
                this.nbBambouAPousser = 2;
                break;
            case EMPTY:
                break;
            case BASSIN:
                break;
            default:
                throw new IllegalArgumentException("AmenagementType inconnu");
        }
    }

    public AmenagementType getType() {return type;}
    public int getNbBambouAManger() {return nbBambouAManger;}
    public int getNbBambouAPousser() {return nbBambouAPousser;}
    public void setType(AmenagementType type) {this.type = type;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenagement that = (Amenagement) o;
        return nbBambouAManger == that.nbBambouAManger
                && nbBambouAPousser == that.nbBambouAPousser
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, nbBambouAManger, nbBambouAPousser);
    }
}
