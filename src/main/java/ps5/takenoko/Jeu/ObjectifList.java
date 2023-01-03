package ps5.takenoko.Jeu;

import ps5.takenoko.Element.AmenagementType;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Objectif.ObjectifJardinier;
import ps5.takenoko.Objectif.ObjectifParcelle;
import ps5.takenoko.Objectif.Shape;
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
        //TODO: ObjectifParcelle

        list.add(new ObjectifJardinier(1, AmenagementType.BASSIN,Couleur.JAUNE));
        list.add(new ObjectifJardinier(1, AmenagementType.DEFAULT,Couleur.JAUNE));
        list.add(new ObjectifJardinier(2, AmenagementType.DEFAULT,Couleur.ROSE));
        list.add(new ObjectifJardinier(3, AmenagementType.DEFAULT,Couleur.JAUNE));
        list.add(new ObjectifJardinier(4, AmenagementType.DEFAULT,Couleur.VERT));

        list.add(new ObjectifJardinier(1, AmenagementType.ENCLOS,Couleur.VERT));
        list.add(new ObjectifJardinier(1, AmenagementType.BASSIN,Couleur.VERT));
        list.add(new ObjectifJardinier(1, AmenagementType.DEFAULT,Couleur.VERT));
        list.add(new ObjectifJardinier(1, AmenagementType.ENGRAIS,Couleur.JAUNE));
        list.add(new ObjectifJardinier(1, AmenagementType.ENCLOS,Couleur.JAUNE));

        list.add(new ObjectifJardinier(1, AmenagementType.ENGRAIS,Couleur.ROSE));
        list.add(new ObjectifJardinier(1, AmenagementType.BASSIN,Couleur.ROSE));
        list.add(new ObjectifJardinier(1, AmenagementType.ENCLOS,Couleur.ROSE));
        list.add(new ObjectifJardinier(1, AmenagementType.DEFAULT,Couleur.ROSE));
        list.add(new ObjectifJardinier(1, AmenagementType.ENGRAIS,Couleur.VERT));




    }

    public Objectif randomObjectif(){
        Random random_method = new Random();
        return list.get(random_method.nextInt(list.size()));
    }

}
