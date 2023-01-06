package ps5.takenoko.Jeu;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Objectif.*;
import ps5.takenoko.Plateau.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ObjectifList extends ListDuJeu<Objectif> {
    public ObjectifList() {
        super();
    }

    @Override
    public void init() {
        list = new ArrayList<>();
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

        System.out.println(list.size());

        for(Couleur couleur : Couleur.values()) for(TypeObjJardinier type : TypeObjJardinier.values()){
            if(type.isMultiple()) continue;
            list.add(new ObjectifJardinier(type,couleur));
        }
        list.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT,Couleur.VERT));
        list.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE,Couleur.JAUNE));
        list.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE,Couleur.ROSE));

        System.out.println(list.size());
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
