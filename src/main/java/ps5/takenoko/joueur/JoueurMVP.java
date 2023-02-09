package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.*;

import java.util.*;

public class JoueurMVP extends JoueurMoyen

{
    public JoueurMVP(int id)
    {
        super(id);
    }
    @Override
    public JoueurMVP clone(){
        return new JoueurMVP(this.getId());
    }
    
    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        if(actionsPossibles.contains(Action.OBJECTIFS)||jeu.getCompteurTour()==1){
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
        return super.jouer(actionsPossibles);
    }

    @Override
    public Meteo choisirMeteo(ArrayList<Meteo> meteos) {
        if(meteos.contains(Meteo.NUAGES) && jeu.getCompteurTour()==1){
            return Meteo.NUAGES;
        }
       return super.choisirMeteo(meteos);
    }
    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements) {
        if(jeu.getCompteurTour()==1){
            for(Amenagement a: amenagements){
                if(a.getType()== AmenagementType.BASSIN){
                    return new Amenagement(AmenagementType.BASSIN);
                }
            }
        }
        return super.choisirAmenagement(amenagements);
    }
}

