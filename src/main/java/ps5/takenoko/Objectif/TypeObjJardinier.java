package ps5.takenoko.Objectif;

public enum TypeObjJardinier {
    AMMENAGEMENT(),
    NOAMMENAGEMENT(),
    MULTIPLE();

    private int points;

    TypeObjJardinier(){
        switch(ordinal()){
            case 0:
                points=5;
                break;
            case 1:
                points=6;
                break;
            case 2:
                points=8;
                break;
        }
    }
    public int getPoint() {
        return points;
    }
}
