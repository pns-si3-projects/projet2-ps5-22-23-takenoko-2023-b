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
                    {Direction.SudOuest, Direction.NordEst}, // LIGNE_SO_NE
                    {Direction.NordOuest, Direction.SudEst}, // LIGNE_NO_SE
                    {Direction.Ouest, Direction.Est} // LIGNE_O_E
                };

                break;
            case 1:
                composition = new Direction[][]{
                    {Direction.NordEst, Direction.Ouest}, // COURBE_NO
                    {Direction.NordOuest, Direction.Est}, // COURBE_NE
                    {Direction.NordEst, Direction.SudEst}, // COURBE_E
                    {Direction.SudOuest, Direction.Est}, // COURBE_SE
                    {Direction.SudEst, Direction.Ouest}, // COURBE_SO
                    {Direction.NordOuest, Direction.SudOuest}// COURBE_O
                };
                break;
            case 2:
                composition = new Direction[][]{
                        {Direction.SudOuest, Direction.SudEst}, // TRIANGLE_UP
                        {Direction.NordOuest, Direction.NordEst} // TRIANGLE_DOWN
                };
                break;
            case 3:
                composition = new Direction[][]{
                        {Direction.NordEst, Direction.Est, Direction.Ouest, Direction.Est}, // LOSANGE_SO_NE
                        {Direction.Ouest, Direction.Est, Direction.Ouest, Direction.Est}, // LOSANGE_SE_NO
                        {Direction.Ouest, Direction.Est, Direction.Ouest, Direction.Est} // LOSANGE_E_O
                };
                break;
        }
    }

    Direction[][] getDirections(){return composition;}

}
