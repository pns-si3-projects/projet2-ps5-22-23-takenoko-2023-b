package ps5.takenoko.jeu;

import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.Couleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectifList {
    private Map<Class<? extends Objectif>,  ArrayList<Objectif>> list;

    public ObjectifList() {
        init();
    }
    public Map<Class<? extends Objectif>, ArrayList<Objectif>> getList() {
        return list;
    }
    public void init() {
        list= Map.of(
                ObjectifPanda.class, new ArrayList<Objectif>(initPanda()),
                ObjectifParcelle.class, new ArrayList<Objectif>(initParcelle()) ,
                ObjectifJardinier.class, new ArrayList<Objectif>(initJardinier())
        );
    }
    private ArrayList<Objectif> initPanda(){
        ArrayList<Objectif> listPanda = new ArrayList<>();
        for(int i=0; i<5;i++){
            listPanda.add(new ObjectifPanda( 3, new Couleur[]{Couleur.VERT}, 2));
        }
        for(int i=0; i<4;i++){
            listPanda.add(new ObjectifPanda( 4, new Couleur[]{Couleur.JAUNE}, 2));
        }
        for(int i=0; i<3;i++){
            listPanda.add(new ObjectifPanda(5, new Couleur[]{Couleur.ROSE}, 2));
            listPanda.add(new ObjectifPanda( 6, new Couleur[]{Couleur.ROSE,Couleur.JAUNE,Couleur.VERT}, 1));
        }
        return listPanda;
    }

    private ArrayList<Objectif> initParcelle(){
        ArrayList<Objectif> listParcelle = new ArrayList<>();
        for(Couleur couleur : Couleur.values()) for( Shape type : Shape.values()){
            if(type == Shape.LOSANGE) continue;
            listParcelle.add(new ObjectifParcelle(type, new Couleur[]{couleur}));
        }
        listParcelle.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.VERT,Couleur.VERT}));
        listParcelle.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.JAUNE}));
        listParcelle.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.ROSE,Couleur.ROSE}));

        listParcelle.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.ROSE}));
        listParcelle.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.JAUNE,Couleur.VERT}));
        listParcelle.add(new ObjectifParcelle(Shape.LOSANGE,new Couleur[]{Couleur.ROSE,Couleur.VERT}));
        return listParcelle;
    }
    
    private ArrayList<Objectif> initJardinier(){
        ArrayList<Objectif> listJardinier = new ArrayList<>();
        for(Couleur couleur : Couleur.values()) for(TypeObjJardinier type : TypeObjJardinier.values()){
            if(type.isMultiple()) continue;
            listJardinier.add(new ObjectifJardinier(type,couleur));
        }
        listJardinier.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT,Couleur.VERT));
        listJardinier.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE,Couleur.JAUNE));
        listJardinier.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE,Couleur.ROSE));
        return listJardinier;
    }

    public List<Class<? extends Objectif>> objectifTypeDisponible(){
        List<Class<? extends Objectif>> listDispo = new ArrayList<>();
        for(Class<? extends Objectif> type : this.list.keySet()){
            if(!this.list.get(type).isEmpty()) listDispo.add(type);
        }
        return listDispo;
    }
}
