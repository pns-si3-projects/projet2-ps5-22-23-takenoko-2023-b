package ps5.takenoko.Objectif;

import ps5.takenoko.Objectif.Condition.Condition;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

public class ParcelleCondition implements Condition {
    private Shape forme;
    private Couleur[] couleur;

    public ParcelleCondition(Shape shape,Couleur[] color){
        forme=shape;
        couleur=color;
    }

    @Override
    public boolean check(Plateau board) {
        for(int i=1;;i++){
            for(int y=1;;y++){
                if(board)
            }
        }
    }

    @Override
    public void claim() {

    }

    @Override
    public boolean checkAndClaim() {
        boolean valid = check();
        if(valid)claim();
        return valid;
    }
}
