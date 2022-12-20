package ps5.takenoko.Jeu;

import ps5.takenoko.Joueur.Action;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Objectif.Objectif;
import ps5.takenoko.Plateau.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jeu {
    private static final int nbActions = 2;
    private int nbObjectifFin;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Plateau plateau= new Plateau();
    private Position positionJardinier = new Position(15,15);
    private Position positionPanda = new Position(15,15);
    private ObjectifList objectifListe = new ObjectifList();
    ParcelleList parcellesListe = new ParcelleList();

    public Jeu(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        setNbObjectifFin();

    }

    //For tests purpose
    public Jeu(ArrayList<Joueur> joueurs,Plateau plateau, Position Jardinier, Position Panda, ObjectifList objList, ParcelleList parcelleList, int nbObjFin) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        positionJardinier = Jardinier;
        positionPanda = Panda;
        objectifListe = objList;
        parcellesListe = parcelleList;
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
        }

    }

    private boolean tourJoueur(Joueur j, int nbActions){
        ArrayList<Action> actionsPossibles = getActionsPossibles();
        boolean stop=false; //for later with more complicated stuff
        if(actionsPossibles.isEmpty()){
            return false;
        }
        Action actionChoisi = j.jouer(actionsPossibles);
        while(nbActions>0 && !stop){
            switch(actionChoisi){
                case PIOCHER_PARCELLES:
                    Parcelle parellePioche = this.piocherParcelles(j);
                    j.poserParcelle(parellePioche);
                    break;
                case OBJECTIFS:
                    this.piocherObjectifs(j);
                    break;
            }
            nbActions--;
        }
        j.validerObjectifs();
        return true;


    }

    //TODO: methode getActionsPossible (par ex: si ya que 1 parcelle etang-> peut pas deplacer Panda ni Jardinier) ou si ya plus de Parcelle dans parcellesList-> peut pas piocher Parcelle
    private ArrayList<Action> getActionsPossibles(){
        //TODO: implements conditions
        ArrayList<Action> res=  new ArrayList<>(Arrays.asList(Action.values()));
        return res;
    }

    private ArrayList<Joueur> calculGagnants(){
        ArrayList<Joueur> gagnants= new ArrayList<Joueur>();
        Collections.sort(joueurs);
        gagnants.add(joueurs.get(0));
        loopGagnant: for(int i=0; i<joueurs.size()-1;i++){
            if(joueurs.get(i).compareTo(joueurs.get(i+1))==0){
                gagnants.add(joueurs.get(i+1));
            }
            else{
                break loopGagnant;
            }
        }
        afficheResultat();
        return gagnants;
    }

    private void afficheResultat(){
        ArrayList<Joueur> gagnants = calculGagnants();
        if(gagnants.size()>0){
            //TODO
        }
        else{
            System.out.println("Joueur " + gagnants.get(0).getId() + " a gagne");
        }
    }

    //TODO: Good idea to do both at da same time like so?
    private boolean estTermine(){
        for(Joueur j: joueurs){
            if(j.getNombreObjectifsObtenus()>=nbObjectifFin){
                //TODO: Put Emperor objectif to j here
                return true;
            }
        }
        return false;
    }

    private void setNbObjectifFin(){
        switch(joueurs.size()){
            case 2:
                nbObjectifFin=1;
                break;
            case 3:
                nbObjectifFin=1;
                break;
            case 4:
                nbObjectifFin=1;
                break;
            default:
                throw new IllegalArgumentException("Le nombre de Joueur doit etre entre 2 et 4");
        }
    }


    private Parcelle piocherParcelles(Joueur j) {
        ArrayList<Parcelle> parcelles = parcellesListe.getRandomParcelles(3);
        Parcelle p = j.piocherParcelle(parcelles);
        parcellesListe.removeParcelle(p);
        return p;
    }

    private void poserParcelles(Joueur j) {
        j.poserParcelle(j.donnerParcelle());
    }

    private void piocherObjectifs(Joueur j) {
        Objectif o = objectifListe.randomObjectif();
        j.addObjectif(o);
        objectifListe.removeObjectif(o);
    }

    private void mangerBamboo (Parcelle p, Joueur j){
        j.ajouteBambou(p.getCouleur());
        p.mangerBambou();
    }

    public String affichePlateau(){
        int ParcelleGauche=0;
        String result="";

        for(int x=1;x<15 && ParcelleGauche==0;x++) for(int y=1;y<plateau.getTaille()-1 && ParcelleGauche==0;y++){
            if(plateau.getPlateau()[x][y] instanceof Parcelle)ParcelleGauche=x;
        }if(ParcelleGauche==0) ParcelleGauche=15;

        for(int y=0;y<plateau.getTaille()-1;y++) {
            String lignes[] = ligneToString(y);
            if(!lignes[0].matches("^\s+$")) result+=lignes[0]+"\n";
            if(!lignes[1].matches("^\s+$")) result+=lignes[1]+"\n";
        }
        return result;
    }

    private String[] ligneToString(int y){
        String result[] = {"",""};
        if(y%2==0)result= new String[]{"  ", "  "};

        for(int x=1;x<Plateau.getTaille()-1;x++) {
            String retour[] = parcelleToString(new Position(x, y));
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
            switch(parcelle.getCouleur()){
                case ROSE :
                    content=CSL_ROUGE+parcelle.getNbBamboo()+CSL_RESET;
                    break;
                case VERT :
                    content=CSL_VERT+parcelle.getNbBamboo()+CSL_RESET;
                    break;
                case JAUNE:
                    content=CSL_JAUNE+parcelle.getNbBamboo()+CSL_RESET;
                    break;
            }
            // get Ammenagement

            /*
            switch(parcelle.getAmmenagement()){
                case ENCLOS: content+="Ec"; break;
                case ENGRAIS: content+="Eg"; break;
                case BASSIN: content+="Ba"; break;
                default:content = content+" ";
            }
             */
            content=" "+content+" ";
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
        ) return CSL_BLEU+border+CSL_RESET;
        else return border;

    }

    private String afficheJardinier(Position pos){
        if(pos.getPositionByDirection(Direction.SUD_OUEST).equals(positionJardinier)) return CSL_VIOLET+"J"+CSL_RESET;
        return " ";
    }


    private String affichePanda(Position pos){
        if(pos.equals(positionPanda)) return CSL_VIOLET+"P"+CSL_RESET;
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
}
