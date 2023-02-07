package ps5.takenoko.lanceur;

import ps5.takenoko.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final ArrayList<Joueur> joueurs;
    private float[][]scores; //victoire|scoreTotal
    private  int egalite;

    public Statistics(ArrayList<Joueur> joueurs) {

        this.joueurs = joueurs;
        scores = new float[joueurs.size()][2];
        for (int i = 0; i < joueurs.size(); i++){
            for (int j = 0; j < 2; j++){
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
        }
    }

    private void updateScores(){
        for(int i = 0; i < joueurs.size(); i++){
            scores[i][1] += joueurs.get(i).calculPoint();
        }
    }

    public float getScoreMoyenne(Joueur joueur, int nbParties){
        return (scores[joueurs.indexOf(joueur)][1])/nbParties;
    }

    public int getEgalite(){
        return egalite;
    }

    public float getPourcentageVictoires(Joueur joueur, int nbParties){
        return scores[joueurs.indexOf(joueur)][0]/nbParties*100;
    }

}
