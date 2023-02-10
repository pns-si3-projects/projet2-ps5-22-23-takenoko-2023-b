package groupeb.takenoko.bot;

import groupeb.takenoko.objectif.ObjectifParcelle;
import groupeb.takenoko.objectif.Objectif;

import java.util.List;

public class BotParcelle extends BotMoyen {

    public BotParcelle(int id) {
        super(id);
    }

    @Override
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifsTypes) {
        int cptPar=0;
        for(Objectif o: this.objectifs){
            if(o instanceof ObjectifParcelle){
                cptPar++;
            }
        }
        if (cptPar<4 && objectifsTypes.contains(ObjectifParcelle.class)) return ObjectifParcelle.class;
        return super.choisirObjectif(objectifsTypes);
    }
}
