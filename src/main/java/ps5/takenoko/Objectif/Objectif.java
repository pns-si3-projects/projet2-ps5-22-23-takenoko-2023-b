package ps5.takenoko.Objectif;


import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Plateau;

import java.util.ArrayList;
import java.util.Random;

public abstract class Objectif {
    private String description;
    private final int point;
    Couleur[] couleurs;

    public Objectif(String description, int point, Couleur[] couleurs) {
        this.description = description;
        this.point = point;
        this.couleurs = couleurs;
    }

    public Objectif(String description, int point) {
        this.description = description;
        this.point = point;
    }

    public Couleur[] getCouleurs() {return couleurs;}

    public int getPoint() {
        return point;
    }

    public abstract boolean verifie(Joueur j);

    public ArrayList<Objectif> initListeObjectif(){
        ArrayList<Objectif> res = new ArrayList<>();
        //Init Obj Jardnier
        for(TypeObjJardinier type : TypeObjJardinier.values()) for(Couleur couleur : Couleur.values()){
            if((type == TypeObjJardinier.OBJMULTVERT && couleur!=Couleur.VERT)
                || (type == TypeObjJardinier.OBJMULTJAUNE && couleur!=Couleur.JAUNE)
                || (type == TypeObjJardinier.OBJMULTROSE && couleur!=Couleur.ROSE)
            ) continue;
            res.add( new ObjectifJardinier(type,couleur));
        }

        // init Obj Parcelle
        for(Shape forme : Shape.values()) for(Couleur couleur: Couleur.values()){
            if(forme == Shape.LOSANGE)continue;
            res.add(new ObjectifParcelle(forme , new Couleur[]{couleur}));
        }
        res.add(new ObjectifParcelle(Shape.LOSANGE, new Couleur[]{Couleur.VERT,Couleur.JAUNE}));
        res.add(new ObjectifParcelle(Shape.LOSANGE, new Couleur[]{Couleur.VERT,Couleur.ROSE}));
        res.add(new ObjectifParcelle(Shape.LOSANGE, new Couleur[]{Couleur.JAUNE,Couleur.ROSE}));

        // init Obj Panda
        int[] nbCards = {5,4,3,3};
        Objectif[] objs = {new ObjectifPanda(),new ObjectifPanda(),new ObjectifPanda(),new ObjectifPanda()}
        for (int i=0; i<nbCards.length;i++){
            for(nbCards)
        }
        return res;
    }

}
