package ps5.takenoko.lanceur;

import ps5.takenoko.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final ArrayList<Joueur> joueurs;
    private int[][]scores; //victoire|scoreTotal|objectifs
    private  int egalite;

    public Statistics(ArrayList<Joueur> joueurs) {

        this.joueurs = joueurs;
        scores = new int[joueurs.size()][3];
        for (int i = 0; i < joueurs.size(); i++){
            for (int j = 0; j < 3; j++){
                scores[i][j] = 0;
            }
        }
    }

    public void updateStats(ArrayList<Joueur> gagnants){
        if(gagnants.size()==joueurs.size()){
            egalite++;
        }
        else {
            for (Joueur gagnant : gagnants) {
                scores[joueurs.indexOf(gagnant)][0]+=1;
            }
            updateScores();
            updateNbObjectifs();
        }
    }

    private void updateScores(){
        for(int i = 0; i < joueurs.size(); i++){
            scores[i][1] += joueurs.get(i).calculPoint();
        }
    }
    private void updateNbObjectifs(){
        for(int i = 0; i < joueurs.size(); i++){
            scores[i][2] += joueurs.get(i).getObjectifsObtenus().size();
        }
    }
    public int getGagne(int index){
        return scores[index][0];
    }

    public int getPerdu(int index, int nbParties){
        return nbParties-scores[index][0]-egalite;
    }

    public int getEgalite(){
        return egalite;
    }

    public float getScoreMoyenne(int index, int nbParties){
        return (float)(scores[index][1])/nbParties;
    }

    public float getObjectifMoyenne(Joueur joueur, int nbParties){
        return (float)(scores[joueurs.indexOf(joueur)][2])/nbParties;
    }

    public float getPourcentage(int score, int nbParties){
        return (float)score/nbParties*100;
    }

    public String[] getStats(Joueur joueur, int nbParties){
        int index = joueurs.indexOf(joueur);
        String[] stats = new String[9];
        stats[0] = joueur.getClass().getSimpleName(); //name
        stats[1]= String.valueOf(getGagne(index)); //gagne
        stats[2]= String.valueOf(getPourcentage(getGagne(index),nbParties))+"%"; //pourcentGagne
        stats[3]= String.valueOf(getPerdu(index,nbParties)); //perdu
        stats[4] = String.valueOf(getPourcentage(getPerdu(index,nbParties),nbParties))+"%"; //pourcentPerdu
        stats[5] = String.valueOf(getEgalite()); //nulle
        stats[6] = String.valueOf(getPourcentage(getEgalite(),nbParties))+"%"; //pourcentNulle
        stats[7] = String.valueOf(getScoreMoyenne(index,nbParties)); //scoreMoyen
        stats[8] = String.valueOf(getObjectifMoyenne(joueur,nbParties)); //objectifMoyen
        return stats;
    }

    public int[][] getScores() {
        return scores;
    }
}
