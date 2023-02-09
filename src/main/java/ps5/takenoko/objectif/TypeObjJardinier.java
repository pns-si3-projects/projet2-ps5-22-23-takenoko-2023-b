package ps5.takenoko.objectif;

import ps5.takenoko.element.AmenagementType;

public enum TypeObjJardinier {
    OBJVIDE(),
    OBJBASSIN(),
    OBJENGRAIS(),
    OBJENCLOS(),
    OBJMULTROSE(),
    OBJMULTJAUNE(),
    OBJMULTVERT();

    private final int points;
    private final int nbBamboo;
    private final String description;
    private AmenagementType amenagementType = null;

    TypeObjJardinier(){
        switch (ordinal()) {
            case 0 -> {
                points = 6;
                nbBamboo = 4;
                amenagementType = AmenagementType.EMPTY;
                description = "Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif sans ammenagement";
            }
            case 1 -> {
                points = 5;
                nbBamboo = 4;
                amenagementType = AmenagementType.BASSIN;
                description = "Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement bassin";
            }
            case 2 -> {
                points = 4;
                nbBamboo = 4;
                amenagementType = AmenagementType.ENGRAIS;
                description = "Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement engrais";
            }
            case 3 -> {
                points = 5;
                nbBamboo = 4;
                amenagementType = AmenagementType.ENCLOS;
                description = "Objectif: Avoir sur le plateau une parcelle avec 4 bambous de la couleur de la carte objectif et avec un amenagement enclos.";
            }
            case 4 -> {
                points = 6;
                nbBamboo = 3;
                description = "Objectif: Avoir sur le plateau 2 parcelles de 3 bambous de la couleur de la carte objectif.";
            }
            case 5 -> {
                points = 7;
                nbBamboo = 3;
                description = "Objectif: Avoir sur le plateau 3 parcelles de 3 bambous de la couleur de la carte objectif.";
            }
            case 6 -> {
                points = 8;
                nbBamboo = 3;
                description = "Objectif: Avoir sur le plateau 4 parcelles de 3 bambous de la couleur de la carte objectif.";
            }
            default -> throw new IllegalArgumentException("TypeObjJardinier non valide");
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

    public AmenagementType getAmenagementType() {
        return amenagementType;
    }

    public boolean isMultiple(){
        return ordinal() == OBJMULTROSE.ordinal()
                || ordinal() == OBJMULTJAUNE.ordinal()
                || ordinal() == OBJMULTVERT.ordinal();
    }
}
