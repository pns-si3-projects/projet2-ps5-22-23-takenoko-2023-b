package ps5.takenoko.lanceur;

import com.opencsv.CSVWriter;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class ComparateurVictoires {
    private static final Logger LOGGER = Logger.getLogger(ComparateurVictoires.class.getSimpleName());
    private static int nbparties = 10000;
    private ArrayList<Joueur> joueurs = new ArrayList<>();

    public ComparateurVictoires(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    //TODO: Change the main to a specific method
    public void lancer() throws IOException {
        int egalite = 0;
        Statistics stats = new Statistics(joueurs);
        for (int i = 0; i < nbparties; i++) {
            if (i % 100 == 0) System.out.println(i);
            Jeu jeu = new Jeu(joueurs);
            jeu.setAffichage(false);
            jeu.lancer();
            ArrayList<Joueur> gagnants = jeu.calculGagnants();
            if (gagnants.size() != 1) {//Case where the game surpass the number of turns
                i--;
            } else {
                stats.updateStats(gagnants);
            }
            for (Joueur joueur : joueurs) {
                joueur.reset();
            }
        }
        StringBuilder sb = new StringBuilder();
        String[] logs = new String[joueurs.size()+1];
        logs[0]="   JoueurType   ,   Gagne   ,   %Gagne   ,   Perdu   ,   %Perdu   ,   Nulle   ,   %Nulle   ,   ScoreMoyen   ";
        sb.append(logs[0].replace(",", "|")+"\n");
        sb.append("-------------------------------------------------------------------------------------------------------------------------------\n");
        for (int i = 1; i <= joueurs.size(); i++) {
            logs[i]= joueurs.get(i-1).getClass().getSimpleName()+"   ,   "
                    + stats.getGagne(joueurs.get(i-1))+"   ,   "
                    +stats.getPourcentage(stats.getGagne(joueurs.get(i-1)),nbparties)+"%   ,   "
                    +stats.getPerdu(joueurs.get(i-1),nbparties)+" , "
                    +stats.getPourcentage(stats.getPerdu(joueurs.get(i-1),nbparties),nbparties)+"%   ,   "
                    +stats.getEgalite()+"   ,   "
                    +stats.getPourcentage(stats.getEgalite(),nbparties)+"%   ,   "
                    +stats.getScoreMoyenne(joueurs.get(i-1),nbparties);
            sb.append(logs[i].replace(",", "|")+"\n");
        }
        LOGGER.info(sb.toString());
        writeToCsv(logs);
    }
    private void writeToCsv(String[] data) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./src/main/java/ps5/takenoko/stats/gamestats.csv"));
        for(String line : data){
            writer.writeNext(line.split(","));
        }
//       String[] record = "2,Rahul,Vaidya,India,35555".split(",");
//        writer.writeNext(record, false);
        writer.close();
    }

}
