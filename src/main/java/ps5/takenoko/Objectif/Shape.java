package ps5.takenoko.Objectif;

import ps5.takenoko.Plateau.Direction;

import java.util.ArrayList;
public enum Shape {
    LIGNE(),
    COURBE(),
    TRIANGLE(),
    LOSANGE();

    private Direction[][] composition;

    Shape(){
        switch (ordinal()){
            case 0:
                composition = new Direction[][]{
                    {Direction.SUD_OUEST, Direction.NORD_EST}, // LIGNE_SO_NE
                    {Direction.NORD_OUEST, Direction.SUD_EST}, // LIGNE_NO_SE
                    {Direction.OUEST, Direction.EST} // LIGNE_O_E
                };

                break;
            case 1:
                composition = new Direction[][]{
                    {Direction.NORD_EST, Direction.OUEST}, // COURBE_NO
                    {Direction.NORD_OUEST, Direction.EST}, // COURBE_NE
                    {Direction.NORD_EST, Direction.SUD_EST}, // COURBE_E
                    {Direction.SUD_OUEST, Direction.EST}, // COURBE_SE
                    {Direction.SUD_EST, Direction.OUEST}, // COURBE_SO
                    {Direction.NORD_OUEST, Direction.SUD_OUEST}// COURBE_O
                };
                break;
            case 2:
                composition = new Direction[][]{
                        {Direction.SUD_OUEST, Direction.SUD_EST}, // TRIANGLE_UP
                        {Direction.NORD_OUEST, Direction.NORD_EST} // TRIANGLE_DOWN
                };
                break;
            case 3:
                composition = new Direction[][]{
                        {Direction.NORD_EST, Direction.EST, Direction.OUEST, Direction.EST}, // LOSANGE_SO_NE
                        {Direction.OUEST, Direction.EST, Direction.OUEST, Direction.EST}, // LOSANGE_SE_NO
                        {Direction.OUEST, Direction.EST, Direction.OUEST, Direction.EST} // LOSANGE_E_O
                };
                break;
        }
    }

    Direction[][] getDirections(){return composition;}

}
