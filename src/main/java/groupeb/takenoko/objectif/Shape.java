package groupeb.takenoko.objectif;

import groupeb.takenoko.plateau.Direction;

public enum Shape {
    LIGNE(),
    COURBE(),
    TRIANGLE(),
    LOSANGE();

    private final Direction[][] composition;
    private final String description;
    private final int points;

    Shape(){
        switch (ordinal()) {
            case 0 -> { // LINE
                composition = new Direction[][]{
                        {Direction.SUD_OUEST, Direction.NORD_EST}, // LIGNE_SO_NE
                        {Direction.NORD_OUEST, Direction.SUD_EST}, // LIGNE_NO_SE
                        {Direction.OUEST, Direction.EST} // LIGNE_O_E
                };
                description = "Objectif: Une ligne de 3 parcelle de la meme couleur";
                points = 3;
            }
            case 1 -> { // COURBE
                composition = new Direction[][]{
                        {Direction.NORD_EST, Direction.OUEST}, // COURBE_NO
                        {Direction.NORD_OUEST, Direction.EST}, // COURBE_NE
                        {Direction.NORD_EST, Direction.SUD_EST}, // COURBE_E
                        {Direction.SUD_OUEST, Direction.EST}, // COURBE_SE
                        {Direction.SUD_EST, Direction.OUEST}, // COURBE_SO
                        {Direction.NORD_OUEST, Direction.SUD_OUEST}// COURBE_O
                };
                description = "Objectif: Une courbe de 3 parcelle de la meme couleur";
                points = 3;
            }
            case 2 -> { // TRIANGLE
                composition = new Direction[][]{
                        {Direction.SUD_OUEST, Direction.SUD_EST}, // TRIANGLE_UP
                        {Direction.NORD_OUEST, Direction.NORD_EST} // TRIANGLE_DOWN
                };
                description = "Objectif: Un triangle de 3 parcelle de la meme couleur";
                points = 3;
            }
            case 3 -> { // LOSANGE
                composition = new Direction[][]{
                        {Direction.SUD_OUEST, Direction.SUD_EST, Direction.EST}, // LOSANGE_SO_NE
                        {Direction.OUEST, Direction.SUD_OUEST, Direction.SUD_EST}, // LOSANGE_SE_NO
                        {Direction.NORD_EST, Direction.EST, Direction.SUD_OUEST} // LOSANGE_S_N
                };
                description = "Objectif: Un losange de 2 paires de 2 parcelles de la meme couleur";
                points = 4;
            }
            default -> throw new IllegalArgumentException("Shape non valide");
        }
    }

    Direction[][] getDirections(){return composition;}

    public String getDescription() {
        return description;
    }

    public int getPoint() {
        return points;
    }
}
