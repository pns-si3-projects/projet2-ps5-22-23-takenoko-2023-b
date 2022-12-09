package ps5.takenoko.Jeu;

import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifParcelle;

import java.util.ArrayList;
import java.util.Random;

public class ObjectifList {
    ArrayList<Objectif> objectifs;


    public ObjectifList() {
        init(46);
    }

    //TODO: In the future, will do 3 types of Objectifs -> no need for nb
    private void init(int nb){
        for(int i=0; i<nb ; i++){
            objectifs.add(new ObjectifParcelle(2));
        }
    }

    public Objectif randomObjectif(){
        Random random_method = new Random();
        return objectifs.get(random_method.nextInt(objectifs.size()));
    }

    public void removeObjectif(Objectif o){
        objectifs.remove(o);
    }
}
