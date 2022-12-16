package ps5.takenoko.Plateau;

import ps5.takenoko.Element.AmenagementType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Plateau {

    private ParcelleInactive[][] plateau;
    private static final int TAILLE = 31;

    public ParcelleInactive[][] getPlateau() {
        return plateau;
    }

    private Set<Position> parcellePosee = new HashSet<Position>();

    private Set<Position> parcelleDisponible = new HashSet<Position>();

    public Plateau() {
        this.plateau = new ParcelleInactive[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = new ParcelleInactive();
            }
        }
        ParcelleOriginelle etang = new ParcelleOriginelle();
        Position centre = new Position(TAILLE/2,TAILLE/2);//(15,15)
        plateau[centre.getX()][centre.getY()] = etang;
        for(Direction d : Direction.values()) {
            parcelleDisponible.add(centre.getPositionByDirection(d));
        }
    }

    public Set<Position> getParcellePosee() {
        return parcellePosee;
    }

    public Set<Position> getParcellePosee() {
        return parcellePosee;
    }

    public void addParcelle(Parcelle p, Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (plateau[x][y] instanceof Parcelle || x < 0 || y < 0 || x > 30 || y > 30) {
            throw new IllegalArgumentException("On ne peux pas ajouter une parcelle ici");
        }
        this.plateau[x][y] = p;
        parcellePosee.add(pos);
        miseAJourParcellePosable(pos);
    }

    public void miseAJourParcellePosable(Position pos){
        parcelleDisponible.remove(pos);
        for(Direction d : Direction.values()) {
            if(isPosable(pos.getPositionByDirection(d))) {
                parcelleDisponible.add(pos.getPositionByDirection(d));
            }
        }
    }

    public Set<Position> getEndroitsPosables() {
        Set<Position> posables = new HashSet<Position>(this.parcelleDisponible);
        return this.parcelleDisponible;
    }

    public ParcelleInactive getParcelle(Position p) {
        if (p == null) {
            return null;
        }
        if (p.getX() < 0 || p.getY() < 0 || p.getX() > 30 || p.getY() > 30) {
            return null;
        }
        return this.plateau[p.getX()][p.getY()];
    }

    public Boolean isPosable(Position p) {
        if (p == null) {
            return false;
        }
        if (this.getParcelle(p) instanceof Parcelle) {
            return false;
        }
        int cpt = 0;

        for (Direction d : Direction.values()) {
            if (this.getParcelle(p.getPositionByDirection(d)) == null) {
            } else {
                if (this.getParcelle(p.getPositionByDirection(d)) instanceof ParcelleOriginelle) {
                    return true;
                }
                if (this.getParcelle(p.getPositionByDirection(d)) instanceof Parcelle) {
                    cpt++;
                }
            }
        }
        return cpt > 1;
    }
    public void affichePlateau(){
        int ParcelleGauche=0;
        for(int x=1;x<15 && ParcelleGauche==0;x++) for(int y=1;y<TAILLE-1 && ParcelleGauche==0;y++){
            if(plateau[x][y] instanceof Parcelle)ParcelleGauche=x;
        }if(ParcelleGauche==0)ParcelleGauche=15;

        for(int y=0;y<TAILLE-1;y++) {
            String lignes[] = ligneToString(y);
            if(!lignes[0].matches("^\s+$")) System.out.println(lignes[0]);
            if(!lignes[1].matches("^\s+$")) System.out.println(lignes[1]);


        }
    }

    private String[] ligneToString(int y){
        String result[] = {"",""};
        if(y%2==0)result= new String[]{"  ", "  "};

        for(int x=1;x<TAILLE-1;x++) {
            String retour[] = parcelleToString(new Position(x, y));
            result[0]+=retour[0]; result[1]+=retour[1];
        }
        return result;
    }

    private String[] parcelleToString(Position pos){
        ParcelleInactive current = getParcelle(pos);

        String content = "    ";
        if(current instanceof Parcelle) {
            Parcelle parcelle = (Parcelle) getParcelle(pos);
            //Creation du contenu de la parcelle
            // get nbBambou + Couleur
            switch(parcelle.getCouleur()){
                case ROSE :
                    content=CSL_ROUGE+"x"+CSL_RESET;
                    break;
                case VERT :
                    content=CSL_VERT+"x"+CSL_RESET;
                    break;
                case JAUNE:
                    content=CSL_JAUNE+"x"+CSL_RESET;
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
            content = afficheBordure(pos,Direction.OUEST) + content + afficheBordure(pos,Direction.EST);
        }else if(current.estParcelleOriginnelle())content = CSL_BLEU+"| E "+CSL_RESET;
        String ligneBas = " "+afficheBordure(pos,Direction.SUD_OUEST) + " " + afficheBordure(pos,Direction.SUD_EST);
        return new String[]{content, ligneBas};
    }

    private String afficheBordure(Position pos,Direction dir){
        ParcelleInactive parcelle = getParcelle(pos);
        ParcelleInactive autreParcelle = getParcelle(pos.getPositionByDirection(dir));
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

        }
            if(
                parcelle.estParcelleOriginnelle()
                || autreParcelle.estParcelleOriginnelle()
            ) return CSL_BLEU+border+CSL_RESET;
            else return border;

    }
    public static final String CSL_RESET = "\u001B[0m";
    public static final String CSL_ROUGE = "\u001B[31m";
    public static final String CSL_VERT = "\u001B[32m";
    public static final String CSL_JAUNE = "\u001B[33m";
    public static final String CSL_BLEU = "\u001B[34m";

    public static final String CSL_NOIR = "\u001B[30m";
    public static final String CSL_VIOLET = "\u001B[35m";
    public static final String CSL_CYAN = "\u001B[36m";
    public static final String CSL_BLANC = "\u001B[37m";

    public static int getTaille(){return TAILLE;}
}
