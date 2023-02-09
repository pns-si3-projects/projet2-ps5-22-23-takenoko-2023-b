package ps5.takenoko.joueur;

import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifPanda;

import java.util.List;

public class JoueurPanda extends JoueurMoyen{

    public JoueurPanda(int id) {
        super(id);
    }

    @Override
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifsTypes) {
        int cptPan=0;
        for(Objectif o: this.objectifs){
            if(o instanceof ObjectifPanda){
                cptPan++;
            }
        }
        if (cptPan<4 && objectifsTypes.contains(ObjectifPanda.class)) return ObjectifPanda.class;
        return super.choisirObjectif(objectifsTypes);
    }
}
