package ps5.takenoko.jeu;

import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.joueur.Action;
import ps5.takenoko.joueur.ChoixAmenagement;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.lanceur.CustomHandler;
import ps5.takenoko.objectif.Empereur;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.personnage.Jardinier;
import ps5.takenoko.personnage.Panda;
import ps5.takenoko.plateau.*;

import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Logger;


public class Jeu {

    public static final int NB_TOUR_MAX = 5000;
    private int cpt = 0;
    private static final int NB_ACTIONS = 2;
    private int nbObjectifFin;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private Plateau plateau= new Plateau();
    private Jardinier jardinier = new Jardinier();
    private Panda panda = new Panda();
    private ObjectifList objectifList = new ObjectifList();
    private ParcelleList parcellesList = new ParcelleList();
    private AmenagementList amenagementList = new AmenagementList();

    private static final Logger LOGGER = Logger.getLogger(Jeu.class.getSimpleName());

    private Boolean affichage = true;

    public Jeu(ArrayList<Joueur> joueurs) {
        for(Joueur player : joueurs) player.setJeu(this);
        this.joueurs = joueurs;
        setNbObjectifFin();

    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void lancer() {
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new CustomHandler());
        while (!estTermine()) {
            cpt++;
            for(Joueur j: joueurs){
                if(cpt!=1){
                    tourJoueur(j, true);
                }
                else{
                    tourJoueur(j, false);
                }
                if(this.affichage) {
                    LOGGER.info(this.affichePlateau());
                }
            }
        }
        if(this.affichage) {
            for (Joueur j : joueurs) {
                LOGGER.info("Joueur " + j.getId() + " : " + j.getObjectifsObtenus().toString());
            }
        }
    }

    public boolean tourJoueur(Joueur j, boolean lanceMeteo) {
        Meteo meteoTour = null;
        int nbActions = NB_ACTIONS;
        if(lanceMeteo){
            meteoTour = getRandomMeteo();
            if(this.affichage) {
                LOGGER.info("Tour " + cpt + " : Meteo " + meteoTour);
            }
            if (meteoTour == Meteo.CHOIX_LIBRE){
                meteoTour= choisirMeteo(j);
                if(this.affichage) {
                    LOGGER.info("Le joueur a choisit la météo : " + meteoTour);
                }
            }
            if (meteoTour == Meteo.NUAGES){
                if(amenagementList.isEmpty()){
                    meteoTour= choisirMeteo(j);
                    if(this.affichage) {
                        LOGGER.info("Plus d'aménagements ! Le joueur choisit la météo : " + meteoTour);
                    }
                }
                else{
                    executerNuage(j);
                }
            }
            if(meteoTour == Meteo.SOLEIL){
                nbActions++;
            }
            if(meteoTour == Meteo.PLUIE){
                executerPluie(j);
            }
            if(meteoTour == Meteo.ORAGE){
                executerOrage(j);
            }
        }
        ArrayList<Action> actionChoisis = new ArrayList<Action>();
        ArrayList<Action> actionsPossibles = getActionsPossibles(j);
        boolean stop=false; //for later with more complicated stuff
        if(actionsPossibles.isEmpty()){
            return false;
        }
        while(nbActions>0 && !stop){
            Action actionChoisi = j.jouer(actionsPossibles);
            actionChoisis.add(actionChoisi);

            String msg = "Joueur "+j.getId()+" a choisi action " + actionChoisi.toString();

            switch(actionChoisi){
                case PIOCHER_CANAL_DIRRIGATION:
                    j.ajouteIrrigation();
                    break;
                case POSER_CANAL_DIRRIGATION:
                    nbActions++;
                    j.placerIrrigation();
                    break;
                case PIOCHER_PARCELLES:
                    Parcelle parcellePioche = this.piocherParcelles(j);
                    msg += " et a pioché une " + parcellePioche + " puis l'a placé sur le plateau";
                    j.poserParcelle(parcellePioche);
                    parcellesList.remove(parcellePioche);
                    //affichage plateau
                    break;
                case OBJECTIFS:
                    msg += " et a pioché un objectif";
                    this.piocherObjectifs(j);
                    break;
                case JARDINIER:
                    Position posJardinier = j.deplacerJardinier(jardinier.posPossibles(plateau));
                    msg += " et a déplacé le jardinier en " + posJardinier;
                    jardinier.deplacer(posJardinier,plateau);
                    break;
                case PANDA:
                    Position p = j.deplacerPanda(panda.posPossibles(plateau));

                    msg += " et a déplacé le panda en " + p;
                    if(panda.deplacer(p,plateau)){
                        j.ajouteBambou(((Parcelle)plateau.getParcelle(p)).getCouleur());
                    }
                    break;
                case POSER_AMENAGEMENT:
                    Set<Position> parcellesAmenageables = plateau.getParcellesAmenageables();
                    if(!parcellesAmenageables.isEmpty()) {
                        ChoixAmenagement choixAmenagement = j.choisirPositionAmenagement(parcellesAmenageables, j.getAmenagements());
                        if (parcellesAmenageables.contains(choixAmenagement.getPosition())) {
                            ((Parcelle) plateau.getParcelle(choixAmenagement.getPosition())).setAmenagement(choixAmenagement.getAmenagement());
                            j.getAmenagements().remove(choixAmenagement.getAmenagement());
                            nbActions++;
                        } else {
                            throw new IllegalArgumentException("La position choisie n'est pas amenageable");
                        }
                    }
                    else{
                        throw new IllegalArgumentException("Il n'y a pas de parcelle amenageable");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Action non valide");

            }
            if(this.affichage){
                LOGGER.info(msg);
            }
            actionsPossibles = getActionsPossibles(j);
            if(meteoTour!=Meteo.VENT){
                actionsPossibles.removeAll(actionChoisis);
            }
            nbActions--;
            j.validerObjectifs();
        }
        return true;
    }
    private void executerOrage(Joueur j) {
        Position p = j.deplacerPanda(plateau.getParcellePosee());
        panda.deplacer(p,this.plateau);

    }

    public Meteo choisirMeteo(Joueur j){
        ArrayList<Meteo> meteoList = new ArrayList<>(Arrays.asList(Meteo.values()));
        meteoList.remove(Meteo.CHOIX_LIBRE);
        if(amenagementList.isEmpty()){
            meteoList.remove(Meteo.NUAGES);
        }
        Meteo res = j.choisirMeteo(meteoList);
        if(!meteoList.contains(res)){
            throw new IllegalArgumentException("Météo non valide");
        }
        return res;
    }

    public void executerPluie(Joueur j){
        Set<Position> parcellesIrriguees = plateau.getParcellesAugmentables();
        if(!parcellesIrriguees.isEmpty()){
            Position p = j.choisirParcelleAPousser(parcellesIrriguees);
            if(parcellesIrriguees.contains(p)){
                ((Parcelle)plateau.getParcelle(p)).augmenteBamboo();
            }
            else{
                throw new IllegalArgumentException("La parcelle choisie n'est pas irriguée");
            }
        }
    }

    public void executerNuage(Joueur j){
        Amenagement amenagement = j.choisirAmenagement(amenagementList);
        if(amenagementList.contains(amenagement)){
            j.addAmenagement(amenagement);
            amenagementList.remove(amenagement);
        }
        else{
            throw new IllegalArgumentException("L'amenagement choisi n'est pas dans la liste");
        }
    }


    private Meteo getRandomMeteo(){
        SecureRandom random = new SecureRandom();
        return Meteo.values()[random.nextInt(Meteo.values().length)];
    }

        private ArrayList<Action> getActionsPossibles(Joueur j){
            ArrayList<Action> actionsPossibles = new ArrayList<Action>();
            if(plateau.getParcellePosee().size()>1){
                actionsPossibles.add(Action.PANDA);
                actionsPossibles.add(Action.JARDINIER);
            }
            if(parcellesList.size()>=3){
                actionsPossibles.add(Action.PIOCHER_PARCELLES);
            }
            if(objectifList.objectifTypeDisponible().size()>0 && j.getObjectifs().size()<5){
                actionsPossibles.add(Action.OBJECTIFS);
            }
            actionsPossibles.add(Action.PIOCHER_CANAL_DIRRIGATION);
            if(j.getNbIrrigations() > 0 && this.plateau.getBordureDisponible().size() > 0) {
                actionsPossibles.add(Action.POSER_CANAL_DIRRIGATION);
            }
            if(!j.getAmenagements().isEmpty() && !this.plateau.getParcellesAmenageables().isEmpty()){
                actionsPossibles.add(Action.POSER_AMENAGEMENT);
            }
            return actionsPossibles;
        }

        public ArrayList<Joueur> calculGagnants() {
            ArrayList<Joueur> js = new ArrayList<Joueur>();
            js.addAll(joueurs);
            js.sort(Collections.reverseOrder());
            Joueur maxRanking = js.get(0);
            ArrayList<Joueur> gagnants = new ArrayList<>();
            for (Joueur joueur : js) {
                if (joueur.compareTo(maxRanking) == 0) {
                    gagnants.add(joueur);
                } else {
                    break;
                }
            }
            if(cpt > NB_TOUR_MAX){
                ArrayList<Joueur> error = new ArrayList<Joueur>();
                return error;
            }
            return gagnants;
        }

        public boolean estTermine(){
            for(Joueur j: joueurs){
                if(j.getNombreObjectifsObtenus()>=nbObjectifFin){
                    j.completerObjectif(new Empereur());
                    return true;
                }
            }
            return false;
        }

        private void setNbObjectifFin(){
            switch(joueurs.size()){
                case 2:
                    nbObjectifFin=9;
                    break;
                case 3:
                    nbObjectifFin=8;
                    break;
                case 4:
                    nbObjectifFin=7;
                    break;
                default:
                    throw new IllegalArgumentException("Le nombre de Joueur doit etre entre 2 et 4");
            }
        }


        public Parcelle piocherParcelles(Joueur j) {
            ArrayList<Parcelle> parcelles = parcellesList.getParcelles(3);
            Parcelle p = j.piocherParcelle(parcelles);
            parcellesList.remove(p);
            parcelles.remove(p);
            parcellesList.addAtEnd(parcelles);
            return p;
        }

        public void piocherObjectifs(Joueur j) {
            List<Class<?extends Objectif>> objectifs = objectifList.objectifTypeDisponible();
            Class<? extends Objectif> o = j.choisirObjectif(objectifs);
            if(!objectifs.contains(o)){
                throw new IllegalArgumentException("Le joueur a choisi un objectif qui n'est pas disponible");
            }
            j.addObjectif(objectifList.getList().get(o).remove(0));
        }

        public ParcelleList getParcellesList() {
            return parcellesList;
        }

        public AmenagementList getAmenagementList() {
            return amenagementList;
        }

        public String affichePlateau(){
            String result="";

            for(int y=0;y<plateau.getTaille()-1;y++) {
                String[] lignes = ligneToString(y);
                if(!lignes[0].matches("^\s+$")) result+=lignes[0]+"\n";
                if(!lignes[1].matches("^\s+$")) result+=lignes[1]+"\n";
            }
            return result;
        }

        private String[] ligneToString(int y){
            String[] result = {"",""};
            if(y%2==0)result= new String[]{"  ", "  "};

            for(int x=1;x<Plateau.getTaille()-1;x++) {
                String[] retour = parcelleToString(new Position(x, y));
                result[0]+=retour[0]; result[1]+=retour[1];
            }
            return result;
        }

        private String[] parcelleToString(Position pos){
            ParcelleInactive current = plateau.getParcelle(pos);

            String content = "   ";
            if(current instanceof Parcelle) {
                Parcelle parcelle = (Parcelle) plateau.getParcelle(pos);
                //Creation du contenu de la parcelle
                // get nbBambou + Couleur
                content = switch (parcelle.getCouleur()) {
                    case ROSE -> CSL_ROUGE + parcelle.getNbBamboo();
                    case VERT -> CSL_VERT + parcelle.getNbBamboo();
                    case JAUNE -> CSL_JAUNE + parcelle.getNbBamboo();
                };
                // get Ammenagement
                switch (parcelle.getAmenagement().getType()) {
                    case ENCLOS -> content += "Ec" + CSL_RESET;
                    case ENGRAIS -> content += "Eg" + CSL_RESET;
                    case BASSIN -> content += "Ba" + CSL_RESET;
                    default -> content = " " + content + " " + CSL_RESET;
                }

            }else if(current.estParcelleOriginelle())content = CSL_BLEU+" E "+CSL_RESET;
            content = afficheBordure(pos, Direction.OUEST) + content;
            String ligneBas = afficheJardinier(pos)+afficheBordure(pos,Direction.SUD_OUEST) + affichePanda(pos) + afficheBordure(pos,Direction.SUD_EST);
            return new String[]{content, ligneBas};
        }

        private String afficheBordure(Position pos,Direction dir) throws IllegalArgumentException {
            ParcelleInactive parcelle = plateau.getParcelle(pos);
            ParcelleInactive autreParcelle = plateau.getParcelle(pos.getPositionByDirection(dir));
            if( //si les deux passerelle sont des parcelleinactive alors on retourne " "
                    !parcelle.estOccupe() && !autreParcelle.estOccupe()
            )return " ";
            String border="";
            switch(dir){
                case OUEST:
                    border = "|";
                    break;
                case EST:
                    if(autreParcelle.estOccupe())border = "";
                    else border =  "|";
                    break;
                case SUD_OUEST :
                    border="\\";
                    break;
                case SUD_EST :
                    border="/";
                    break;
                default : throw new IllegalArgumentException();

            }
            if(
                    parcelle.estParcelleOriginelle()
                            || autreParcelle.estParcelleOriginelle()
                            || plateau.getBordurePosee().contains(new Bordure(pos,dir))
            ) return CSL_BLEU+border+CSL_RESET;
            else if(plateau.getBordureDisponible().contains(new Bordure(pos,dir))) return border;
            else return CSL_NOIR+border+CSL_RESET;

        }

        private String afficheJardinier(Position pos){
            if(pos.getPositionByDirection(Direction.SUD_OUEST).equals(jardinier.getPosition())) return CSL_VIOLET+"J"+CSL_RESET;
            return " ";
        }


        private String affichePanda(Position pos){
            if(pos.equals(panda.getPosition())) return CSL_VIOLET+"P"+CSL_RESET;
            return " ";
        }
        public static final String CSL_RESET = "\u001B[0m";
        public static final String CSL_ROUGE = "\u001B[31m";
        public static final String CSL_VERT = "\u001B[32m";
        public static final String CSL_JAUNE = "\u001B[33m";
        public static final String CSL_BLEU = "\u001B[34m";
        public static final String CSL_VIOLET = "\u001B[35m";

        public static final String CSL_NOIR = "\u001B[30m";
        public static final String CSL_CYAN = "\u001B[36m";
        public static final String CSL_BLANC = "\u001B[37m";

        public void setPlateau(Plateau value) {
            plateau = value;
        }

        public void setAffichage(Boolean value) {
            affichage = value;
        }

    public Panda getPanda() {
        return panda;
    }
}
