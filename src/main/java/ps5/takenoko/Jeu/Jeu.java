package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Action;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Objectif.Empereur;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Personnage.Jardinier;
import ps5.takenoko.Personnage.Panda;
import ps5.takenoko.Plateau.*;

import java.util.ArrayList;
import java.util.Collections;


public class Jeu {
    private static final int nbActions = 2;
    private int nbObjectifFin;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Plateau plateau= new Plateau();
    private Jardinier jardinier = new Jardinier();
    private Panda panda = new Panda();
    private ObjectifList objectifList = new ObjectifList();
    private ParcelleList parcellesList = new ParcelleList();

    public Jeu(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        setNbObjectifFin();

    }

    //For tests purpose
    public Jeu(ArrayList<Joueur> joueurs,Plateau plateau, Jardinier pawnJardinier, Panda pawnPanda, ObjectifList objList, ParcelleList parcelleList, int nbObjFin) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        jardinier = pawnJardinier;
        panda = pawnPanda;
        objectifList = objList;
        parcellesList = parcelleList;
        nbObjectifFin = nbObjFin;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void lancer() {
        for(Joueur j: this.joueurs){
            j.setPlateau(this.plateau);
        }
        while (!estTermine()) {
            //TODO: Implementation of Meteo here (except the first round)
            for(Joueur j: joueurs){
                tourJoueur(j,nbActions);
            }
            System.out.println(this.affichePlateau());
        }
        afficheResultat();
        for(Joueur j: joueurs){
            System.out.println("Joueur "+j.getId()+" : "+j.getObjectifsObtenus().toString());
        }
    }

    private boolean tourJoueur(Joueur j, int nbActions){
        ArrayList<Action> actionChoisis = new ArrayList<Action>();
        ArrayList<Action> actionsPossibles = getActionsPossibles(j,actionChoisis);
        boolean stop=false; //for later with more complicated stuff
        if(actionsPossibles.isEmpty()){
            return false;
        }
        while(nbActions>0 && !stop){
            Action actionChoisi = j.jouer(actionsPossibles);
            actionChoisis.add(actionChoisi);
            String msg = "Joueur "+j.getId()+" a choisi action " + actionChoisi.toString();

            switch(actionChoisi){
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
            }
            System.out.println(msg);
            actionsPossibles = getActionsPossibles(j,actionChoisis);
            nbActions--;
            j.validerObjectifs();
        }
        return true;
    }

    private ArrayList<Action> getActionsPossibles(Joueur j, ArrayList<Action> actionsChoisis){
        ArrayList<Action> actionsPossibles = new ArrayList<Action>();
        if(plateau.getParcellePosee().size()>1){
            actionsPossibles.add(Action.PANDA);
            actionsPossibles.add(Action.JARDINIER);
        }
        if(parcellesList.size()>=3){
            actionsPossibles.add(Action.PIOCHER_PARCELLES);
        }
        if(objectifList.size()>0 && j.getObjectifs().size()<5){
            actionsPossibles.add(Action.OBJECTIFS);
        }
        //TODO: implements conditions for irrigation

        //TODO : les actions doivent être différentes sauf si Meteo = VENT
        actionsPossibles.removeAll(actionsChoisis);
        return actionsPossibles;
    }

    private ArrayList<Joueur> calculGagnants() {
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
        return gagnants;
    }



    private void afficheResultat(){
        ArrayList<Joueur> gagnants = calculGagnants();
        if(gagnants.size()>1){
            //TODO: implement the case of a draw >=3 joueurs
            System.out.println("Draw");
        }
        else{
            System.out.println("Joueur " + gagnants.get(0).getId() + " a gagne");
        }
    }

    private boolean estTermine(){
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


    Parcelle piocherParcelles(Joueur j) {
        ArrayList<Parcelle> parcelles = parcellesList.getParcelles(3);
        Parcelle p = j.piocherParcelle(parcelles);
        parcellesList.remove(p);
        parcelles.remove(p);
        parcellesList.addAtEnd(parcelles);
        return p;
    }

    private void piocherObjectifs(Joueur j) {
        Objectif o = objectifList.randomObjectif();
        j.addObjectif(o);
        objectifList.remove(o);
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

        }else if(current.estParcelleOriginnelle())content = CSL_BLEU+" E "+CSL_RESET;
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
                parcelle.estParcelleOriginnelle()
                        || autreParcelle.estParcelleOriginnelle()
                //TODO changer la couleur quand il y a une irrigation : || listeBordure.contains(new Bordure(pos,pos.getPositionByDirection(dir)))
        ) return CSL_BLEU+border+CSL_RESET;
        else return border;

    }

    private String afficheJardinier(Position pos){
        if(pos.getPositionByDirection(Direction.SUD_OUEST).equals(jardinier.getPosition())) return CSL_BLEU+"J"+CSL_RESET;
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
        for (Joueur player : joueurs){
            player.setPlateau(value);
        }
    }
}
