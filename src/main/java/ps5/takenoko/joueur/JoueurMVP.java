package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.*;

import java.util.*;

public class JoueurMVP extends JoueurRandom

{
    private List<Map<Action,Object>> actionMap = new ArrayList<>();

    public JoueurMVP(int id)
    {
        super(id);
    }
    public void ajoutAction(Action action, Object object)
    {
        Map<Action,Object> map = new HashMap<>();
        map.put(action,object);
        actionMap.add(map);
    }
    public void clearAction()
    {
        actionMap.clear();
    }
    public void reset()
    {
        super.reset();
        clearAction();
    }
    @Override
    public JoueurMVP clone(){
        return new JoueurMVP(this.getId());
    }

//        Il récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante.
//    • Il essaie d’avoir 5 cartes objectif en main tout le temps. Les deux premiers mouvements du bot
//    devraient donc être de prendre une carte objectif et de prendre un canal d’irrigation.
//• Quand il tire une la météo « ? » dans les premiers tours, il prend une irrigation.
//• Il essaie de se focaliser sur plusieurs (pas toutes) cartes à la fois. S’il a deux cartes Panda, il va se
//    focaliser sur les deux en même temps.
//• Il surveille les mouvements de ses adversaires (ou de l’adversaire potentiellement en tête), et s’il
//    détecte qu’il essaie de réaliser un objectif particulier, il essaie de saboter ou de ralentir la
//    réalisation.
    @Override
    public Action jouer(ArrayList<Action> actionsPossibles) {
        Action actionChoisie = null;
        Object object = null;

        if(actionMap.size()==1 && actionsPossibles.contains(Action.PIOCHER_CANAL_DIRRIGATION)){
            actionChoisie= Action.PIOCHER_CANAL_DIRRIGATION;
            ajoutAction(actionChoisie,object);
            return actionChoisie;
        }
        if(actionsPossibles.contains(Action.OBJECTIFS)||actionMap.isEmpty()){
            if(objectifs.size()<5){
                actionChoisie= Action.OBJECTIFS;
                ajoutAction(actionChoisie,object);
                return actionChoisie;
            }
        }

        if(actionsPossibles.contains(Action.PANDA)){
            for(Position p: jeu.getPanda().posPossibles(getPlateau())){
                if(!getPlateau().getParcelle(p).estParcelleOriginelle()){
                    if(((Parcelle)getPlateau().getParcelle(p)).getNbBamboo()>0){
                        actionChoisie= Action.PANDA;
                        object = p;
                        ajoutAction(actionChoisie,object);
                        return actionChoisie;
                    }
                }
            }
        }
        return (Action) randomList(actionsPossibles);


    }
    @Override
    public Meteo choisirMeteo(ArrayList<Meteo> meteos) {
        if(meteos.contains(Meteo.NUAGES)){
            return Meteo.NUAGES;
        }
       return (Meteo) randomList(meteos);
    }
    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements) {
        for(Amenagement a: amenagements){
            if(a.getType()== AmenagementType.BASSIN){
                return new Amenagement(AmenagementType.BASSIN);
            }
        }
        return super.choisirAmenagement(amenagements);
    }
}

