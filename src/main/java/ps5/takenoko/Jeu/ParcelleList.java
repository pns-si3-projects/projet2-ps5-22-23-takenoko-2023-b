package ps5.takenoko.Jeu;

import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Plateau.Parcelle;

import java.util.ArrayList;
import java.util.Collections;
//TODO: Le rendre un STack (es 2 non choisis vont a l'arriere)
//TODO: Class Super for ParcelleList and ObjectifList
public class ParcelleList {
    ArrayList<Parcelle> parcelles = new ArrayList<Parcelle>();

    public ParcelleList() {
        init(28);
    }
    //TODO: init all types of Parcelle
    public void init( int nb){
        for(int i=0; i<nb ; i++){
            parcelles.add(new Parcelle());
        }
        Collections.shuffle(parcelles);
    }
    public ArrayList<Parcelle> getRandomParcelles(int nbParcelles){
        ArrayList<Parcelle> parcellesRandoms = new ArrayList<Parcelle>();
        for(int i=0; i<nbParcelles; i++){
            parcellesRandoms.add(parcelles.get(i));
        }
        return parcellesRandoms;
    }

    public void removeParcelle(Parcelle o){
        parcelles.remove(o);
    }
}
