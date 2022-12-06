package ps5.takenoko.Objectif;

import ps5.takenoko.Objectif.Condition.Condition;

import java.util.ArrayList;

public abstract class Objectif {
    private ArrayList<Condition> conditions;
    private final int point;

    public Objectif(int point) {
        this.point = point;
    }
    public boolean checkConditions(){

    }
    public void claim(){}
}
