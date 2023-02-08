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
    public float getGagne(int index){
        return scores[index][0];
    }

    public float getPerdu(int index, int nbParties){
        return nbParties-scores[index][0]-egalite;
    }

    public float getEgalite(){
        return egalite;
    }

    public float getScoreMoyenne(int index, int nbParties){
        return (float)(scores[index][1])/nbParties;
    }

    public float getObjectifMoyenne(Joueur joueur, int nbParties){
        return (scores[joueurs.indexOf(joueur)][2])/nbParties;
    }

    public float getPourcentage(float score, int nbParties){
        return score/nbParties*100;
    }

    public Float[] getStats(Joueur joueur, int nbParties){
        int index = joueurs.indexOf(joueur);
        Float[] stats = new Float[8];
        stats[0]= getGagne(index); //gagne
        stats[1]= getPourcentage(getGagne(index),nbParties); //pourcentGagne
        stats[2]=getPerdu(index,nbParties); //perdu
        stats[3] =getPourcentage(getPerdu(index,nbParties),nbParties); //pourcentPerdu
        stats[4] = getEgalite(); //nulle
        stats[5] = getPourcentage(getEgalite(),nbParties); //pourcentNulle
        stats[6] = getScoreMoyenne(index,nbParties); //scoreMoyen
        stats[7] = getObjectifMoyenne(joueur,nbParties); //objectifMoyen
        return stats;
    }

    public int[][] getScores() {
        return scores;
    }
}
