package ps5.takenoko.Element;

public class Amenagement {
    private AmenagementType type;
    private int nbBambou;
    public Amenagement(){
        this.type = AmenagementType.DEFAULT;
        this.nbBambou = 1;
    }
    public Amenagement(AmenagementType type) {
        this.type = type;
        switch (type){
            case ENCLOS:
                this.nbBambou = 0;
                break;
            case DEFAULT:
                this.nbBambou = 1;
                break;
        }
        this.nbBambou = 0;
    }

    public AmenagementType getType() {return type;}

    public int getNbBambou() {return nbBambou;}

    public void setType(AmenagementType type) {this.type = type;}


}
