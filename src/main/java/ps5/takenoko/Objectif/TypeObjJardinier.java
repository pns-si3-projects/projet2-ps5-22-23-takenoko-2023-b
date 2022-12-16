package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;

public enum TypeObjJardinier {
    AMMENAGEMENT(),
    NOAMMENAGEMENT(),
    MULTIPLE();

    private int points;
    private int nbBamboo;
    private AmenagementType amenagement;

    TypeObjJardinier(){
        switch(ordinal()){
            case 0:
                points=5;
                nbBamboo=4;
                amenagement=AmenagementType.ENGRAIS;
                break;
            case 1:
                points=6;
                nbBamboo=4;
                amenagement=null;
                break;
            case 2:
                points=8;
                nbBamboo=3;
                break;
        }
    }
    public int getPoint() {
        return points;
    }

    public int getNbBamboo() {
        return nbBamboo;
    }
}
