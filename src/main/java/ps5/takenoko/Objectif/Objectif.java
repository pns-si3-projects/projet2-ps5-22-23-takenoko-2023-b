package ps5.takenoko.Objectif;

import ps5.takenoko.Objectif.Condition.Condition;

import java.util.ArrayList;

public abstract class Objectif {
    private ArrayList<Condition> conditions;
    private final int point;

    public Objectif(int point) {
        this.point = point;
    }
<<<<<<< HEAD
    public int claim(){
        return point;
    }
=======
    public boolean checkConditions(){

    }
    public void claim(){}
>>>>>>> e24a0cda5a4497b81d68758a954662819aa17a74
}
