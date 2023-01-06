package ps5.takenoko.Objectif;

import ps5.takenoko.Element.AmenagementType;

public enum TypeObjJardinier {
    OBJVIDE(),
    OBJBASSIN(),
    OBJENGRAIS(),
    OBJENCLOS(),
    OBJMULTROSE(),
    OBJMULTJAUNE(),
    OBJMULTVERT();

    private int points;
    private int nbBamboo;
    private String description;
    private AmenagementType amenagementType;

    TypeObjJardinier(){
        switch(ordinal()){
            case 0 :
                points=6;
                nbBamboo=4;
                amenagementType = AmenagementType.EMPTY;
                description="Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif sans ammenagement";
                break;
            case 1:
                points=5;
                nbBamboo=4;
                amenagementType = AmenagementType.BASSIN;
                description="Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement bassin";
                break;
            case 2:
                points=4;
                nbBamboo=4;
                amenagementType = AmenagementType.ENGRAIS;
                description="Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement engrais";
                break;
            case 3:
                points=5;
                nbBamboo=4;
                amenagementType = AmenagementType.ENCLOS;
                description="Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement enclos.";
                break;
            case 4:
                points=6;
                nbBamboo=3;
                description="Objectif: Avoir sur le plateau 2 parcelles de 3 bambous de la couleur de la carte objectif.";
                break;
            case 5:
                points=7;
                nbBamboo=3;
                description="Objectif: Avoir sur le plateau 3 parcelles de 3 bambous de la couleur de la carte objectif.";
                break;
            case 6:
                points=8;
                nbBamboo=3;
                description="Objectif: Avoir sur le plateau 4 parcelles de 3 bambous de la couleur de la carte objectif.";
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

    public boolean isMultiple(){
        return ordinal() == OBJMULTROSE.ordinal()
                || ordinal() == OBJMULTJAUNE.ordinal()
                || ordinal() == OBJMULTVERT.ordinal();
    }
}
