package ps5.takenoko.Bot;

import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifJardinier;

import java.util.List;

public class BotJardinier extends BotMoyen {
    public BotJardinier(int id) {
        super(id);
    }
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifsTypes) {
        int cptJar=0;
        for(Objectif o: this.objectifs){
            if(o instanceof ObjectifJardinier){
                cptJar++;
            }
        }
        if (cptJar<4 && objectifsTypes.contains(ObjectifJardinier.class)) return ObjectifJardinier.class;
        return super.choisirObjectif(objectifsTypes);
    }
}
