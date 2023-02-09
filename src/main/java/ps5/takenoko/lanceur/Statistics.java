package ps5.takenoko.lanceur;

import ps5.takenoko.Bot.Bot;

import java.util.ArrayList;

public class Statistics {
    private final ArrayList<Bot> bots;
    private int[][]scores; //victoire|scoreTotal|objectifs
    private  int egalite;

    public Statistics(ArrayList<Bot> bots) {

        this.bots = bots;
        scores = new int[bots.size()][3];
        for (int i = 0; i < bots.size(); i++){
            for (int j = 0; j < 3; j++){
                scores[i][j] = 0;
            }
        }
    }

    public void updateStats(ArrayList<Bot> gagnants){
        if(gagnants.size()== bots.size()){
            egalite++;
        }
        else {
            for (Bot gagnant : gagnants) {
                scores[bots.indexOf(gagnant)][0]+=1;
            }
            updateScores();
            updateNbObjectifs();
        }
    }

    private void updateScores(){
        for(int i = 0; i < bots.size(); i++){
            scores[i][1] += bots.get(i).calculPoint();
        }
    }
    private void updateNbObjectifs(){
        for(int i = 0; i < bots.size(); i++){
            scores[i][2] += bots.get(i).getObjectifsObtenus().size();
        }
    }
    public float getGagne(int index){
        return scores[index][0];
    }

    public float getPerdu(int index, int nbParties){
        return nbParties-(float)(scores[index][0])-egalite;
    }

    public float getEgalite(){
        return egalite;
    }

    public float getScoreMoyenne(int index, int nbParties){
        return (float)(scores[index][1])/nbParties;
    }

    public float getObjectifMoyenne(Bot bot, int nbParties){
        return (float)(scores[bots.indexOf(bot)][2])/nbParties;
    }

    public float getPourcentage(float score, int nbParties){
        return score/nbParties*100;
    }

    public Float[] getStats(Bot bot, int nbParties){
        int index = bots.indexOf(bot);
        Float[] stats = new Float[8];
        stats[0]= getGagne(index); //gagne
        stats[1]= getPourcentage(getGagne(index),nbParties); //pourcentGagne
        stats[2]=getPerdu(index,nbParties); //perdu
        stats[3] =getPourcentage(getPerdu(index,nbParties),nbParties); //pourcentPerdu
        stats[4] = getEgalite(); //nulle
        stats[5] = getPourcentage(getEgalite(),nbParties); //pourcentNulle
        stats[6] = getScoreMoyenne(index,nbParties); //scoreMoyen
        stats[7] = getObjectifMoyenne(bot,nbParties); //objectifMoyen
        return stats;
    }

    public int[][] getScores() {
        return scores;
    }
}
