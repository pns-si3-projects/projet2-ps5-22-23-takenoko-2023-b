package groupeb.takenoko.bot;

import org.junit.jupiter.api.Test;
import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.plateau.Position;

import static org.junit.jupiter.api.Assertions.*;

class ChoixAmenagementTest {

    @Test
    void getAmenagement() {
        for(AmenagementType type : AmenagementType.values()){
            Amenagement amenagement = new Amenagement(type);
            ChoixAmenagement choice = new ChoixAmenagement(amenagement,new Position(15,15));
            assertEquals(amenagement,choice.getAmenagement());

        }
    }

    @Test
    void getPosition() {
        for(int i=0;i<5;i++){
            ChoixAmenagement choice = new ChoixAmenagement(new Amenagement(AmenagementType.BASSIN),new Position(15+i,15-i));
            assertEquals(new Position(15+i,15-i),choice.getPosition());
        }
    }
}