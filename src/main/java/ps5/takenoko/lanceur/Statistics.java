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
        updateGagnants(gagnants);
    }

    private void updateGagnants(ArrayList<Joueur> gagnants){
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
    public int getGagne(Joueur joueur){
        return scores[joueurs.indexOf(joueur)][0];
    }

    public int getPerdu(Joueur joueur, int nbParties){
        return nbParties-scores[joueurs.indexOf(joueur)][0]-egalite;
    }

    public int getEgalite(){
        return egalite;
    }

    public float getScoreMoyenne(Joueur joueur, int nbParties){
        return (float)(scores[joueurs.indexOf(joueur)][1])/nbParties;
    }

    public float getObjectifMoyenne(Joueur joueur, int nbParties){
        return (float)(scores[joueurs.indexOf(joueur)][2])/nbParties;
    }

    public float getPourcentage(int score, int nbParties){
        return (float)score/nbParties*100;
    }

}
