package ps5.takenoko.joueur;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.*;

import java.security.SecureRandom;
import java.util.*;

public class JoueurMoyen extends JoueurRandom{
    public JoueurMoyen(int id) {
        super(id);
    }

    @Override
    public JoueurMoyen clone(){
        return new JoueurMoyen(this.getId());
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
        if (getPlateau().getParcellePosee().size() <= 7 && cptPar<3) {
            if (objectifsTypes.contains(ObjectifParcelle.class)) {
                return ObjectifParcelle.class;
            }
        }else if (cptPan<2) {
            if (objectifsTypes.contains(ObjectifPanda.class)) {
                return ObjectifPanda.class;
            }
        }else if (cptJar<2) {
            if (objectifsTypes.contains(ObjectifJardinier.class)) {
                    return ObjectifJardinier.class;
            }
        }else if (cptPar<1) {
        if (objectifsTypes.contains(ObjectifParcelle.class)) {
            return ObjectifParcelle.class;
        }
    }
        return super.choisirObjectif(objectifsTypes);

    }

    @Override
    public Meteo choisirMeteo(ArrayList<Meteo> meteos) {
        if (objectifs.size()==0){
            return Meteo.VENT;
        }
        int objPan = 0;
        int objJar = 0;
        for(Objectif o: objectifs) {
            if (o instanceof ObjectifJardinier jar) {
                objJar +=1;
                if((jar.getType() == TypeObjJardinier.OBJENCLOS)||(jar.getType() == TypeObjJardinier.OBJENGRAIS)||(jar.getType() == TypeObjJardinier.OBJBASSIN)){
                    if (meteos.contains(Meteo.NUAGES)){
                        return Meteo.NUAGES;
                    }
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

    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements, Parcelle p) {
        for(Objectif o: objectifs){
            if(o instanceof ObjectifJardinier) {
                Amenagement res=getSameAmenagements(amenagements, (ObjectifJardinier) o);
                if (res != null)return res;
                for (Amenagement a : amenagements) {
                    if (a.getType() == AmenagementType.ENCLOS && !p.estIrrigue()) {
                        return a;
                    }
                }
            }
            else if(o instanceof ObjectifPanda) {
                for(Couleur c:o.getCouleurs())
                if (p.getCouleur().equals(c)) {
                    for(Amenagement a:amenagements){
                        if(a.getType()== AmenagementType.BASSIN&&!p.estIrrigue()){
                            return a;
                        }
                        if(a.getType()== AmenagementType.ENGRAIS&&p.estIrrigue()){
                            return a;
                        }
                    }
                }
            }
        }
        return super.choisirAmenagement(amenagements);
    }

    @Override
    public Amenagement choisirAmenagement(ArrayList<Amenagement> amenagements){
        for (Objectif o : objectifs){
            if(o instanceof ObjectifJardinier) {
                Amenagement a=getSameAmenagements(amenagements, (ObjectifJardinier) o);
                if(a != null)return a;
            }
        }
        return super.choisirAmenagement(amenagements);
    }

    private Amenagement getSameAmenagements(ArrayList<Amenagement> amenagements, ObjectifJardinier o) {
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
    public ChoixAmenagement choisirPositionAmenagement(Set<Position> positions, ArrayList<Amenagement> amenagements) {
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
    public void validerObjectifs() {
        ArrayList<Objectif>validables = objectifsValidable();
        for(Objectif o : validables){
            completerObjectif(o);
        }
    }

    @Override
    public void poserParcelle(Parcelle p) {
        Set<Position> pospos = getPlateau().getEndroitsPosables();
        List<Position> goodPos = new ArrayList<>();
        int max=0;
        for(Position pos : pospos){
            int cpt=0;
            for(Direction d : Direction.values()) {
                if (getPlateau().getParcelle(pos.getPositionByDirection(d)) instanceof Parcelle par) {
                    if(par.getCouleur().equals(p.getCouleur())){
                        cpt++;
                    }
                }
            }
            if (cpt>max){
                max=cpt;
                goodPos.clear();
                goodPos.add(pos);
            }
            if (cpt==max) {
                goodPos.add(pos);
            }
        }
        if(goodPos.size()>=0){
            Collections.shuffle(goodPos);
            getPlateau().addParcelle(p,goodPos.get(0));
            return ;
        }
        getPlateau().addParcelle(p, getRandomPosition(getPlateau().getEndroitsPosables()));
    }

    public Position getRandomPosition(Set<Position> positions){
        int r = random.nextInt(positions.size());
        Iterator<Position> iterator = positions.iterator(); //iterator is already random by itself
        Position position = iterator.next();
        while(r>0){
            position = iterator.next();
            r--;
        }
        return position;
    }

    /***
     *
     * Choisir 1 parcelle parmi les 3 et puis le poser sur le plateau
     * @return
     */
    @Override
    public Parcelle piocherParcelle(ArrayList<Parcelle> parcelles) {
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
        Set<Bordure> bordureDisponibles = this.getPlateau().getBordureDisponible();
        Bordure meilleurChoix = null;
        int max = 0;
        for(Bordure b : bordureDisponibles){
            int cpt =0;
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
            if (cpt>max){
                max=cpt;
                meilleurChoix = b;
            }
        }
        if(meilleurChoix!=null) {
            getPlateau().addBordure(meilleurChoix);
            this.useIrrigation();
        }else{
            super.placerIrrigation();
        }
    }

    public boolean BonneIrrigation(){
        Set<Bordure> bordureDisponibles = this.getPlateau().getBordureDisponible();
        int max = 0;
        for(Bordure b : bordureDisponibles){
            int cpt =0;
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
            if (cpt>max){
                max=cpt;
            }
        }
        return max>=4;

    }
    public Action choisirActionBasique(ArrayList<Action> actionsPossibles){
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
    public Action jouer(ArrayList<Action> actionsPossibles) {
        if(objectifs.size() < this.MAX_OBJECTIFS && actionsPossibles.contains(Action.OBJECTIFS)){
            return Action.OBJECTIFS;
        }
        if(this.getNbIrrigations() <2 && actionsPossibles.contains(Action.PIOCHER_CANAL_DIRRIGATION)){
            return Action.PIOCHER_CANAL_DIRRIGATION;
        }
        if(this.getAmenagements().size()>0 && actionsPossibles.contains(Action.POSER_AMENAGEMENT)){
            return Action.POSER_AMENAGEMENT;
        }
        if(this.getPlateau().getBordurePosee().size()>=12&&BonneIrrigation()&&actionsPossibles.contains(Action.POSER_CANAL_DIRRIGATION)){
            return Action.POSER_CANAL_DIRRIGATION;
        }
        if(actionsPossibles.contains(choisirActionBasique(actionsPossibles))){
            return choisirActionBasique(actionsPossibles);
        }
        Collections.shuffle(actionsPossibles);
        return actionsPossibles.get(0);
    }
}
