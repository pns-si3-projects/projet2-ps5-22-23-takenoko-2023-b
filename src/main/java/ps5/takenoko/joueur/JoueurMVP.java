package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Position;

import java.util.*;

public class JoueurMVP extends Joueur
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
    public Joueur clone() {
        return new JoueurMVP(this.getId());
    }
//    Il récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante.
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
        if(actionsPossibles.contains(Action.OBJECTIFS)){
            if(objectifs.size()<5){
                actionChoisie= Action.OBJECTIFS;
            }
        }
        if(actionsPossibles.contains(Action.PANDA)){
            if(){
                actionChoisie= Action.PANDA;
            }
        }

    }

    @Override
    public void validerObjectifs() {

    }

    @Override
    public void poserParcelle(Parcelle p) {

    }

    @Override
    public Position choisirParcelleAPousser(Set<Position> positions) {
        return null;
    }

    @Override
    public Parcelle piocherParcelle(ArrayList<Parcelle> parcelles) {
        return null;
    }

    @Override
    public Position deplacerJardinier(Set<Position> positionsPossibles) {
        return null;
    }

    @Override
    public Position deplacerPanda(Set<Position> positionsPossibles) {
        return null;
    }

    @Override
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifs) {
        return null;
    }

    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements) {
        return null;
    }

    @Override
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, ArrayList<Amenagement> amenagements) {
        return null;
    }

    @Override
    public Meteo choisirMeteo(ArrayList<Meteo> meteos) {
        return null;
    }
}


