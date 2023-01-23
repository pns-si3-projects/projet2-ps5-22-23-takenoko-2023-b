package ps5.takenoko.Jeu;

import java.util.ArrayList;

public class ListDuJeu<T> extends ArrayList<T> {

    public ListDuJeu(ArrayList<T> initList) {
    }

    public int size(){
        return this.size();
    }

    public ArrayList<T> getRandom(int nb){
        ArrayList<T> randoms = new ArrayList<T>();
        for(int i=0; i<nb; i++){
            randoms.add(this.get(i));
        }
        return randoms;
    }
    public boolean remove(Object o){
        return this.remove(o);
    }

}
