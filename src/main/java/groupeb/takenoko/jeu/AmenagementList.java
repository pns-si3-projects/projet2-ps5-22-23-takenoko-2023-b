package groupeb.takenoko.jeu;

import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;

import java.util.ArrayList;

public class AmenagementList extends ArrayList<Amenagement> {
    public AmenagementList() {
        init();
    }
    private void init(){
        for(int i=0; i<3; i++){
            this.add(new Amenagement(AmenagementType.BASSIN));
            this.add(new Amenagement(AmenagementType.ENCLOS));
            this.add(new Amenagement(AmenagementType.ENGRAIS));
        }
    }
}
