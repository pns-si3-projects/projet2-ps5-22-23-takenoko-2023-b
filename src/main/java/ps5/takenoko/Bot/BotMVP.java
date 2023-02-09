package ps5.takenoko.Bot;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.*;

import java.util.*;

public class BotMVP extends BotMoyen
{
    public BotMVP(int id)
    {
        super(id);
    }
    @Override
    public BotMVP clone(){
        return new BotMVP(this.getId());
    }
    
    @Override
    public Action jouer(List<Action> actionsPossibles) {
        if(actionsPossibles.contains(Action.OBJECTIFS)){
            if(objectifs.size()<5){
                return Action.OBJECTIFS;
            }
        }
        if(jeu.getCompteurTour()==1 && actionsPossibles.contains(Action.PIOCHER_CANAL_DIRRIGATION)){
            return Action.PIOCHER_CANAL_DIRRIGATION;
        }

        if(actionsPossibles.contains(Action.PANDA)){
            for(Position p: jeu.getPanda().posPossibles(getPlateau())){
                if(!getPlateau().getParcelle(p).estParcelleOriginelle()){
                    if(((Parcelle)getPlateau().getParcelle(p)).getNbBamboo()>0){
                        return Action.PANDA;
                    }
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
        if(objectifPrincipale()==ObjectifJardinier.class){
            for(Position p: positionsPossibles){
                if(!getPlateau().getParcelle(p).estParcelleOriginelle()){
                    if(((Parcelle)getPlateau().getParcelle(p)).getNbBamboo()>=3){
                        return p;
                    }
                }
            }
        }
        if(objectifPrincipale()==ObjectifPanda.class){
            for(Position p: positionsPossibles){
                if(!getPlateau().getParcelle(p).estParcelleOriginelle()){
                    if(((Parcelle)getPlateau().getParcelle(p)).getNbBamboo()==1){
                        return p;
                    }
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

