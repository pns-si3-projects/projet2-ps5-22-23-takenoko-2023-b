package ps5.takenoko.Jeu;

import ps5.takenoko.Objectif.*;
import ps5.takenoko.Plateau.Couleur;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ObjectifList {
    public Map<Class<? extends Objectif>,  ListDuJeu<Objectif>> list;

    public ObjectifList() {
        init();
    }
    public Map<Class<? extends Objectif>, ListDuJeu<Objectif>> getList() {
        return list;
    }
    public void init() {
        list= Map.of(
                ObjectifPanda.class, new ListDuJeu<Objectif>(initPanda()),
                ObjectifParcelle.class, new ListDuJeu<Objectif>(initParcelle()) ,
                ObjectifJardinier.class, new ListDuJeu<Objectif>(initJardinier())
        );
    }
    private ArrayList<Objectif> initPanda(){
        ArrayList<Objectif> list = new ArrayList<>();
        for(int i=0; i<5;i++){
            list.add(new ObjectifPanda( 3, new Couleur[]{Couleur.VERT}, 2));
        }
        for(int i=0; i<4;i++){
            list.add(new ObjectifPanda( 4, new Couleur[]{Couleur.JAUNE}, 2));
        }
        for(int i=0; i<3;i++){
            list.add(new ObjectifPanda(5, new Couleur[]{Couleur.ROSE}, 2));
            list.add(new ObjectifPanda( 6, new Couleur[]{Couleur.ROSE,Couleur.JAUNE,Couleur.VERT}, 1));
        }
        return list;
    }

    private ArrayList<Objectif> initParcelle(){
        ArrayList<Objectif> list = new ArrayList<>();
        for(Couleur couleur : Couleur.values()) for( Shape type : Shape.values()){
            if(type == Shape.LOSANGE) continue;
            list.add(new ObjectifParcelle(type, new Couleur[]{couleur}));
        }
        list.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.VERT,Couleur.VERT}));
        list.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.JAUNE}));
        list.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.ROSE,Couleur.ROSE}));

        list.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.ROSE}));
        list.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.VERT}));
        list.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.ROSE,Couleur.VERT}));
        return list;
    }

    private ArrayList<Objectif> initJardinier(){
        ArrayList<Objectif> list = new ArrayList<>();
        for(Couleur couleur : Couleur.values()) for(TypeObjJardinier type : TypeObjJardinier.values()){
            if(type.isMultiple()) continue;
            list.add(new ObjectifJardinier(type,couleur));
        }
        list.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT,Couleur.VERT));
        list.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE,Couleur.JAUNE));
        list.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE,Couleur.ROSE));
        return list;
    }




    public Objectif randomObjectif(){
        Random random_method = new Random();
        return list.get(random_method.nextInt(list.size()));
    }

    public boolean hasObj(Objectif out){
        for(Objectif in : this.list){
            if(in.equals(out))return true;
        }
        return false;
    }


}
