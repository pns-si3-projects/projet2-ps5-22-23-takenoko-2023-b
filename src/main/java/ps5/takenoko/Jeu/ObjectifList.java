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
        for(int i=0; i<5;i++){
            list.add(new ObjectifPanda( 3, new Couleur[]{Couleur.VERT}, 2));
        }
        for(int i=0; i<4;i++){
            list.add(new ObjectifPanda( 4, new Couleur[]{Couleur.JAUNE}, 2));
        }
        for(int i=0; i<5;i++){
            list.add(new ObjectifPanda(5, new Couleur[]{Couleur.ROSE}, 2));
            list.add(new ObjectifPanda( 6, new Couleur[]{Couleur.ROSE,Couleur.JAUNE,Couleur.VERT}, 1));
        }


        //TODO: The whole stuff

//        list.add(new ObjectifJardinier(1, AmenagementType.BASSIN,Couleur.JAUNE));
//        list.add(new ObjectifJardinier(1, AmenagementType.DEFAULT,Couleur.JAUNE));
//        list.add(new ObjectifJardinier(2, AmenagementType.DEFAULT,Couleur.ROSE));
//        list.add(new ObjectifJardinier(3, AmenagementType.DEFAULT,Couleur.JAUNE));
//        list.add(new ObjectifJardinier(4, AmenagementType.DEFAULT,Couleur.VERT));
//
//        list.add(new ObjectifJardinier(1, AmenagementType.ENCLOS,Couleur.VERT));
//        list.add(new ObjectifJardinier(1, AmenagementType.BASSIN,Couleur.VERT));
//        list.add(new ObjectifJardinier(1, AmenagementType.DEFAULT,Couleur.VERT));
//        list.add(new ObjectifJardinier(1, AmenagementType.ENGRAIS,Couleur.JAUNE));
//        list.add(new ObjectifJardinier(1, AmenagementType.ENCLOS,Couleur.JAUNE));
//
//        list.add(new ObjectifJardinier(1, AmenagementType.ENGRAIS,Couleur.ROSE));
//        list.add(new ObjectifJardinier(1, AmenagementType.BASSIN,Couleur.ROSE));
//        list.add(new ObjectifJardinier(1, AmenagementType.ENCLOS,Couleur.ROSE));
//        list.add(new ObjectifJardinier(1, AmenagementType.DEFAULT,Couleur.ROSE));
//        list.add(new ObjectifJardinier(1, AmenagementType.ENGRAIS,Couleur.VERT));




    }

    public Objectif randomObjectif(){
        Random random_method = new Random();
        return list.get(random_method.nextInt(list.size()));
    }

}
