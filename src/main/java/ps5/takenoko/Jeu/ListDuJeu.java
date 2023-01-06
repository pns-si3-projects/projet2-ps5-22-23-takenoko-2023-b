package ps5.takenoko.Jeu;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ListDuJeu <T> {
    ArrayList<T> list = new ArrayList<T>();
    public ListDuJeu() {
        init();
    }
    public abstract void init();
    public int size(){
        return list.size();
    }

    public ArrayList<T> getRandom(int nb){
        ArrayList<T> randoms = new ArrayList<T>();
        for(int i=0; i<nb; i++){
            randoms.add(list.get(i));
        }
        return randoms;
    }
    public void remove(T o){
        list.remove(o);
    }

}
