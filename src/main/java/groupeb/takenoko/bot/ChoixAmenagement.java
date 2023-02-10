package groupeb.takenoko.bot;

import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.plateau.Position;

import java.util.Objects;

public class ChoixAmenagement {
    private final Amenagement amenagement;
    private final Position position;

    public ChoixAmenagement(Amenagement amenagement, Position position) {
        this.amenagement = amenagement;
        this.position = position;
    }

    public Amenagement getAmenagement() {
        return amenagement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoixAmenagement that = (ChoixAmenagement) o;
        return Objects.equals(amenagement, that.amenagement) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenagement, position);
    }

    public Position getPosition() {
        return position;
    }
}
