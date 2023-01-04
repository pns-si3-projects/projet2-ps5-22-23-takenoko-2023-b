package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;

public enum TypeObjJardinier {
    AMMENAGEMENT(),
    NOAMMENAGEMENT(),
    MULTIPLE();

    private int points;
    private int nbBamboo;
    private String description;
    private AmenagementType amenagement;

    TypeObjJardinier(){
        switch(ordinal()){
            case 0:
                points=5;
                nbBamboo=4;
                amenagement=AmenagementType.ENGRAIS;
                description="Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif avec un ammenagement engrais";
                break;
            case 1:
                points=6;
                nbBamboo=4;
                amenagement=null;
                description="Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et sans amenagement";
                break;
            case 2:
                points=8;
                nbBamboo=3;
                description="Objectif: Avoir sur le plateau 3 parcelles de 3 bambous de la couleur de la carte objectif.";
                break;
        }
    }
    public int getPoint() {
        return points;
    }

    public int getNbBamboo() {
        return nbBamboo;
    }

    public String getDescription() {
        return description;
    }
}
