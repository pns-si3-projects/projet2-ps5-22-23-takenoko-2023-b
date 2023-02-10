package groupeb.takenoko.bot;

import groupeb.takenoko.objectif.ObjectifPanda;
import groupeb.takenoko.objectif.Objectif;

import java.util.List;

public class BotPanda extends BotMoyen {

    public BotPanda(int id) {
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
