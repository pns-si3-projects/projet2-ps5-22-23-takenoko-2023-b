package groupeb.takenoko.bot;
import groupeb.takenoko.objectif.*;
import groupeb.takenoko.plateau.*;
import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.element.Meteo;
import java.util.*;

public class BotMoyen extends BotRandom {
    public BotMoyen(int id) {
        super(id);
    }

    @Override
    public Class<? extends Objectif> choisirObjectif(List<Class<? extends Objectif>> objectifsTypes) {
        int cptPan=0;
        int cptPar=0;
        int cptJar=0;
        for(Objectif o: this.objectifs){
            if(o instanceof ObjectifPanda){
                cptPan++;
            }
            if(o instanceof ObjectifJardinier){
                cptJar++;
            }
            if(o instanceof ObjectifParcelle){
                cptPar++;
            }
        }
        if (getPlateau().getParcellePosee().size() <= 7 && cptPar<3
            && objectifsTypes.contains(ObjectifParcelle.class)) return ObjectifParcelle.class;
        else if (cptPan<2 && objectifsTypes.contains(ObjectifPanda.class)) return ObjectifPanda.class;
        else if (cptJar<2 && objectifsTypes.contains(ObjectifJardinier.class)) return ObjectifJardinier.class;
        else if (cptPar<1 && objectifsTypes.contains(ObjectifParcelle.class)) return ObjectifParcelle.class;

        return super.choisirObjectif(objectifsTypes);

    }

    @Override
    public Meteo choisirMeteo(List<Meteo> meteos) {
        if (objectifs.isEmpty()){
            return Meteo.VENT;
        }
        int objPan = 0;
        int objJar = 0;
        for(Objectif o: objectifs) {
            if (o instanceof ObjectifJardinier jar) {
                objJar +=1;
                if(((jar.getType() == TypeObjJardinier.OBJENCLOS)||(jar.getType() == TypeObjJardinier.OBJENGRAIS)||(jar.getType() == TypeObjJardinier.OBJBASSIN))&& meteos.contains(Meteo.NUAGES)){
                        return Meteo.NUAGES;
                }
            }
            if( o instanceof ObjectifPanda) {
                objPan +=1;
            }
        }
        if (objPan>0){
            return Meteo.ORAGE;
        }
        if (objJar>0){
                return Meteo.PLUIE;
        }
        return Meteo.SOLEIL;
    }

    @Override
    public Position choisirParcelleAPousser(Set<Position> positions) {
        int max=0;
        List<Position> goodPos = new ArrayList<>();
        for(Position p:positions){
            int cpt=compteurCouleur(p);
            if (cpt>max){
                max=cpt;
                goodPos.clear();
                goodPos.add(p);
            }
            if (cpt==max) {
                goodPos.add(p);
            }

        }
        Collections.shuffle(goodPos);
        return goodPos.get(0);
    }

    public int compteurCouleur(Position p){
        int cpt=0;
        if(getPlateau().getParcelle(p)instanceof Parcelle par){
            for(Objectif o:objectifs){
                for(Couleur c:o.getCouleurs()){
                    if(par.getCouleur().equals(c)){
                        cpt++;
                    }
                }
            }
        }
        return cpt;
    }

    public Amenagement choisirAmenagement(List<Amenagement> amenagements, Parcelle p) {
        for(Objectif o: objectifs){
            if(o instanceof ObjectifJardinier obj) {
                Amenagement res=getSameAmenagements(amenagements, obj);
                if (res != null)return res;
                for (Amenagement a : amenagements) {
                    if (a.getType() == AmenagementType.ENCLOS && !p.estIrrigue()) {
                        return a;
                    }
                }
            }
            else if(o instanceof ObjectifPanda obj) {
                for(Couleur c : obj.getCouleurs()) {
                    if (p.getCouleur().equals(c)) {
                        for (Amenagement a : amenagements) {
                            if (a.getType() == AmenagementType.BASSIN && !p.estIrrigue()) {
                                return a;
                            }
                            if (a.getType() == AmenagementType.ENGRAIS && p.estIrrigue()) {
                                return a;
                            }
                        }
                    }
                }
            }
        }
        return super.choisirAmenagement(amenagements);
    }

    @Override
    public Amenagement choisirAmenagement(List<Amenagement> amenagements){
        for (Objectif o : objectifs){
            if(o instanceof ObjectifJardinier obj) {
                Amenagement a=getSameAmenagements(amenagements, obj);
                if(a != null) return a;
            }
        }
        return super.choisirAmenagement(amenagements);
    }

    private Amenagement getSameAmenagements(List<Amenagement> amenagements, ObjectifJardinier o) {
        for (Amenagement a : amenagements) {
            if ((a.getType() == AmenagementType.ENCLOS) && (o.getType() == TypeObjJardinier.OBJENCLOS)) {
                return a;
            }
            if ((a.getType() == AmenagementType.ENGRAIS) && (o.getType() == TypeObjJardinier.OBJENGRAIS)) {
                return a;
            }
            if ((a.getType() == AmenagementType.BASSIN) && (o.getType() == TypeObjJardinier.OBJBASSIN)) {
                return a;
            }
        }
        return null;
    }
    @Override
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, List<Amenagement> amenagements) {
        for (Objectif o : objectifs){
            if(o instanceof ObjectifJardinier jar){
                for(Position p : positions){
                    if(this.getPlateau().getParcelle(p).estOccupe()){
                        Parcelle par=(Parcelle)this.getPlateau().getParcelle(p);
                        for(Couleur c:jar.getCouleurs()){
                            if(par.getCouleur().equals(c)&&jar.getType()!= TypeObjJardinier.OBJVIDE){
                                return new ChoixAmenagement(choisirAmenagement(amenagements,par),p);
                            }
                        }
                    }
                }
            }
            else if(o instanceof ObjectifPanda pan){
                for(Position p : positions){
                    if(this.getPlateau().getParcelle(p).estOccupe()){
                        Parcelle par=(Parcelle)this.getPlateau().getParcelle(p);
                        for(Couleur c:pan.getCouleurs()){
                            if(par.getCouleur().equals(c)) {
                                return new ChoixAmenagement(choisirAmenagement(amenagements, par), p);
                            }
                        }
                    }else{
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
        return new ChoixAmenagement(choisirAmenagement(amenagements),getRandomPosition(positions));
    }

    @Override
    public List<Objectif> validerObjectifs() {
        List<Objectif>validables = objectifsValidable();
        List<Objectif> valide = new ArrayList<>();
        for(Objectif o : validables){
            completerObjectif(o);
            valide.add(o);
        }
        return valide;
    }

    @Override
    public void poserParcelle(Parcelle p) {
        Set<Position> pospos = getPlateau().getEndroitsPosables();
        List<Position> goodPos = new ArrayList<>();
        int max=0;
        for(Position pos : pospos){
            int cpt=0;
            for(Direction d : Direction.values()) {
                if (getPlateau().getParcelle(pos.getPositionByDirection(d)) instanceof Parcelle par && par.getCouleur().equals(p.getCouleur())) {
                    cpt++;
                }
            }
            if (cpt>max){
                max=cpt;
                goodPos.clear();
                goodPos.add(pos);
            } else if (cpt==max && max>1) {
                goodPos.add(pos);
            }
        }
        if(!goodPos.isEmpty()){
            Collections.shuffle(goodPos);
            getPlateau().addParcelle(p,goodPos.get(0));
            return ;
        }
        getPlateau().addParcelle(p, getRandomPosition(getPlateau().getEndroitsPosables()));
    }

    /***
     * Choisir 1 parcelle parmi les 3 et puis le poser sur le plateau
     */
    @Override
    public Parcelle piocherParcelle(List<Parcelle> parcelles) {
        Collections.shuffle(parcelles);
        for(Objectif o : objectifs) {
            if(o instanceof ObjectifParcelle) {
                for(int i = 0; i < o.getCouleurs().length; i++) {
                    for(Parcelle p : parcelles) {
                        if(o.getCouleurs()[i] == p.getCouleur()) {
                            return p;
                        }
                    }
                }
            }
        }
        return parcelles.get(0);
    }

    @Override
    public Position deplacerPersonnage(Set<Position> positionsPossibles) {
        return getRandomPosition(positionsPossibles);
    }

    @Override
    public Position deplacerJardinier(Set<Position> positionsPossibles) {
        for(Objectif o : objectifs) {
            if(o instanceof ObjectifJardinier) {
                for(Position p : positionsPossibles){
                    for(int i = 0; i < o.getCouleurs().length; i++) {
                        if(!getPlateau().getParcelle(p).estParcelleOriginelle()) {
                            Parcelle par = (Parcelle) getPlateau().getParcelle(p);
                            if(par.getCouleur() == o.getCouleurs()[i] && par.getNbBamboo() < 4 && par.estIrrigue()) {
                                return p;
                            }
                        }
                    }
                }
            }
        }
        return deplacerPersonnage(positionsPossibles);
    }
    @Override
    public Position deplacerPanda(Set<Position> positionsPossibles) {
        for(Objectif o : objectifs) {
            if(o instanceof ObjectifPanda) {
                for(Position p : positionsPossibles){
                    for(int i = 0; i < o.getCouleurs().length; i++) {
                        if(!getPlateau().getParcelle(p).estParcelleOriginelle()) {
                            Parcelle par = (Parcelle) getPlateau().getParcelle(p);
                            if(par.getCouleur() == o.getCouleurs()[i] && par.getNbBamboo() > 0) {
                                return p;
                            }
                        }
                    }
                }
            }
        }
        return deplacerPersonnage(positionsPossibles);
    }

    @Override
    public void placerIrrigation(){
        if(this.getNbIrrigations()==0) return;
        Set<Bordure> bordureDisponibles = this.getPlateau().getBordureDisponible();
        ArrayList<Bordure> meilleurChoix = new ArrayList<>();
        int max = 0;
        for(Bordure b : bordureDisponibles){
            int cpt = evalueIrrigation(b);

            if(cpt>=3) {
                if (cpt > max) {
                    max = cpt;
                    meilleurChoix = new ArrayList<>();
                }
                meilleurChoix.add(b);
            }
        }
        if(meilleurChoix.isEmpty()) {
            super.placerIrrigation();
            return;
        }
        getPlateau().addBordure(meilleurChoix.get(0));
        this.useIrrigation();
        placerIrrigation();
    }

    public int evalueIrrigation(Bordure b){
        int cpt = 0;
        if(this.getPlateau().getParcelle(b.getPos1()).estOccupe()&&this.getPlateau().getParcelle(b.getPos2()).estOccupe()){
            Parcelle p1 = (Parcelle) this.getPlateau().getParcelle(b.getPos1());
            Parcelle p2 = (Parcelle) this.getPlateau().getParcelle(b.getPos2());
            for(Objectif o: objectifs){
                for (Couleur c : o.getCouleurs()){
                    if(p1.getCouleur()==c && !p1.estIrrigue()){
                        cpt++;
                    }
                    if(p2.getCouleur()==c && !p2.estIrrigue()){
                        cpt++;
                    }
                }
            }
        }
        return cpt;
    }

    private boolean irrigationInteressante() {
        if(this.getPlateau().getBordurePosee().size()<=10)return true;
        Set<Bordure> bordureDisponibles = this.getPlateau().getBordureDisponible();
        for(Bordure b : bordureDisponibles)if(evalueIrrigation(b)>=3)return true;
        return false;
    }

    public Action choisirActionBasique(List<Action> actionsPossibles){
        int cptPan=0;
        int cptPar=0;
        int cptJar=0;
        for(Objectif o: objectifs){
            if(o instanceof ObjectifPanda){
                cptPan++;
            }
            if(o instanceof ObjectifJardinier){
                cptJar++;
            }
            if(o instanceof ObjectifParcelle){
                cptPar++;
            }
        }
        if(cptPar>=cptJar&&cptPar>=cptPan&&actionsPossibles.contains(Action.PIOCHER_PARCELLES)){
            return Action.PIOCHER_PARCELLES;
        }
        else if(cptPan>=cptJar&&actionsPossibles.contains(Action.PANDA)){
            return Action.PANDA;
        }
        return Action.JARDINIER;
    }

    @Override
    public Action jouer(List<Action> actionsPossibles) {
        if(objectifs.size() < MAX_OBJECTIFS && actionsPossibles.contains(Action.OBJECTIFS)){
            return Action.OBJECTIFS;
        }
        if(this.getNbIrrigations() <2 && actionsPossibles.contains(Action.PIOCHER_CANAL_DIRRIGATION)){
            return Action.PIOCHER_CANAL_DIRRIGATION;
        }
        if(!this.getAmenagements().isEmpty() && actionsPossibles.contains(Action.POSER_AMENAGEMENT)){
            return Action.POSER_AMENAGEMENT;
        }
        if(actionsPossibles.contains(Action.POSER_CANAL_DIRRIGATION) && irrigationInteressante()){
            return Action.POSER_CANAL_DIRRIGATION;
        }
        if(actionsPossibles.contains(choisirActionBasique(actionsPossibles))){
            return choisirActionBasique(actionsPossibles);
        }
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }

}
