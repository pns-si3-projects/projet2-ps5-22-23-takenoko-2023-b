package groupeb.takenoko.bot;

import groupeb.takenoko.objectif.Objectif;
import groupeb.takenoko.objectif.ObjectifJardinier;
import groupeb.takenoko.objectif.ObjectifPanda;
import groupeb.takenoko.objectif.ObjectifParcelle;
import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.element.Meteo;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Position;
import java.util.*;

public class BotMVP extends BotMoyen
{
    public BotMVP(int id)
    {
        super(id);
    }
    @Override
    public Action jouer(List<Action> actionsPossibles) {
        if(actionsPossibles.contains(Action.OBJECTIFS) && objectifs.size() < 5) {
            return Action.OBJECTIFS;
        }
        if(jeu.getCompteurTour()==1 && actionsPossibles.contains(Action.PIOCHER_CANAL_DIRRIGATION)){
            return Action.PIOCHER_CANAL_DIRRIGATION;
        }
        if(actionsPossibles.contains(Action.PANDA)) {
            for(Position p : jeu.getPanda().posPossibles(getPlateau())) {
                if(!getPlateau().getParcelle(p).estParcelleOriginelle() &&
                        ((Parcelle)getPlateau().getParcelle(p)).getNbBamboo() > 0) {
                    return Action.PANDA;
                }
            }
        }
        if(actionsPossibles.contains(Action.PIOCHER_PARCELLES) && objectifPrincipale()== ObjectifParcelle.class){
            return Action.PIOCHER_PARCELLES;
        }
        return super.jouer(actionsPossibles);
    }

    @Override
    public Meteo choisirMeteo(List<Meteo> meteos) {
        if(meteos.contains(Meteo.NUAGES) && jeu.getCompteurTour()<=5){
            return Meteo.NUAGES;
        }
       return super.choisirMeteo(meteos);
    }
    @Override
    public Amenagement choisirAmenagement(List<Amenagement> amenagements) {
        if(jeu.getCompteurTour()<=5){
            for(Amenagement a: amenagements){
                if(a.getType()== AmenagementType.BASSIN){
                    return a;
                }
            }
        }
        return super.choisirAmenagement(amenagements);
    }
    @Override
    public Position deplacerPanda(Set<Position> positionsPossibles) {
        Class objectifPrincipale = objectifPrincipale();
        if(objectifPrincipale== ObjectifJardinier.class || objectifPrincipale()== ObjectifPanda.class){
            for(Position p: positionsPossibles){
                if(!getPlateau().getParcelle(p).estParcelleOriginelle() && ((Parcelle)getPlateau().getParcelle(p)).getNbBamboo()>=3 && objectifPrincipale== ObjectifJardinier.class){
                        return p;
                }
                if(!getPlateau().getParcelle(p).estParcelleOriginelle() && ((Parcelle)getPlateau().getParcelle(p)).getNbBamboo()==1 && objectifPrincipale== ObjectifPanda.class){
                    return p;
                }
            }
        }
        return super.deplacerPanda(positionsPossibles);
    }


    public Class<? extends Objectif> objectifPrincipale(){
        List<Bot> bots = jeu.getJoueurs();
        ArrayList<Class<? extends Objectif>> objectifs = new ArrayList<>();
        for(Bot j : bots){
            objectifs.addAll(j.getObjectifsTypes());
        }
        int max=0;
        Class<? extends Objectif> res = null;
        for(Class<? extends Objectif> o : objectifs){
            if(Collections.frequency(objectifs,o)>max){
                max=Collections.frequency(objectifs,o);
                res=o;
            }
        }
        return res;
    }

}

