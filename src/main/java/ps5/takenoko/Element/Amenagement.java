package ps5.takenoko.Element;

public class Amenagement {
    private AmenagementType type;
    private int nbBambouAManger=1;
    private int nbBambouAPousser=1;
    public Amenagement(){
        this.type = AmenagementType.EMPTY;
        this.nbBambouAPousser = 1;
        this.nbBambouAManger = 1;
    }
    public Amenagement(AmenagementType type) {
        this.type = type;
        switch (type){
            case ENCLOS:
                this.nbBambouAManger = 0;
                this.nbBambouAPousser = 1;
                break;
            case ENGRAIS:
                this.nbBambouAPousser = 2;
                this.nbBambouAManger = 1;
                break;
            case EMPTY:
                this.nbBambouAManger = 1;
                this.nbBambouAPousser = 1;
                break;
        }
    }

    public AmenagementType getType() {return type;}
    public int getNbBambouAManger() {return nbBambouAManger;}
    public int getNbBambouAPousser() {return nbBambouAPousser;}
    public void setType(AmenagementType type) {this.type = type;}


}
