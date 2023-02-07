package ps5.takenoko.lanceur;

import com.opencsv.CSVWriter;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class JeuLanceur {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());
    private static final String CSV_FILE_NAME = "./src/main/java/ps5/takenoko/stats/gamestats.csv";
    private int nbparties = 1000;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private boolean csv = false;

    public JeuLanceur(int nbparties, ArrayList<Joueur> joueurs, boolean csv) {
        this.nbparties = nbparties;
        this.joueurs = joueurs;
        this.csv = csv;
    }

    public JeuLanceur(int nbparties, ArrayList<Joueur> joueurs) {
        this.nbparties = nbparties;
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
        LOGGER.setUseParentHandlers(false);
        CustomHandler customHandler = new CustomHandler();
        LOGGER.addHandler(customHandler);
        String[] logs = new String[joueurs.size()+1];
        logs[0]="JoueurType,Gagne,%Gagne,Perdu,%Perdu,Nulle,%Nulle,ScoreMoyen";
        String[] init = logs[0].split(",");
        LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                init[0], init[1], init[2], init[3], init[4], init[5], init[6], init[7]));
        LOGGER.info("--------------------------------------------------------------------------------------------------------------------------");
        for (int i = 1, j=0; i <= joueurs.size(); i++,j++) {
            String[] statsRes = stats.getStats(joueurs.get(j), nbparties);
            logs[i]="";
            for(int k=0;k<statsRes.length;k++){
                logs[i]+=statsRes[k]+",";
            }
            LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                    statsRes[0], statsRes[1], statsRes[2], statsRes[3], statsRes[4], statsRes[5], statsRes[6], statsRes[7]));
            if(csv){
                logs[i]+=stats.getObjectifMoyenne(joueurs.get(j),nbparties);
            }
        }

        if(csv){
            logs[0]+= ",ObjectifMoyen";
            writeToCsv(logs);
        }
    }
    private void writeToCsv(String[] data) throws IOException {
        String fileName = CSV_FILE_NAME;
        int fileNo=0;
        while(new File(fileName).exists()){
            if(fileName.equals(CSV_FILE_NAME)){
                fileName= fileName.replaceAll(".csv",fileNo+".csv");
            }
            else{
                fileName= fileName.replaceAll((fileNo-1)+".csv",fileNo+".csv");
            }
            fileNo++;
        }
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));

        for(String line : data){
            writer.writeNext(line.split(","));
        }
        writer.close();
    }
}
