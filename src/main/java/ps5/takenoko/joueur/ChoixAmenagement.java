package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.plateau.Position;

public class ChoixAmenagement {
    private Amenagement amenagement;
    private Position position;

    public ChoixAmenagement(Amenagement amenagement, Position position) {
        this.amenagement = amenagement;
        this.position = position;
    }

    public Amenagement getAmenagement() {
        return amenagement;
    }

    public Position getPosition() {
        return position;
    }
}
