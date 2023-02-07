package ps5.takenoko.lanceur;

import com.opencsv.CSVWriter;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.option.Args;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class JeuLanceur {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());
    private static final String CSV_FILE_NAME = "./stats/gamestats.csv";
    private int nbparties;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    Args arguments = new Args();
    private Statistics stats;

    public JeuLanceur(ArrayList<Joueur> joueurs, Args arguments) {
        this.joueurs = joueurs;
        this.arguments = arguments;
        if(arguments.isCsv()||arguments.isTwoThousand()){
            nbparties = 1000;
        }
        else if(arguments.isDemo()){
            nbparties = 1;
            LOGGER.setLevel(Level.FINER);
        }
        else{
            throw new IllegalArgumentException("Arguments non valides");
        }
        this.stats = new Statistics(joueurs);
    }

    //TODO: Change the main to a specific method
    public void lancer(){
        for (int i = 0; i < nbparties; i++) {
            //if (i % 100 == 0) System.out.println(i);
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
        affichageStats();
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
    private void affichageStats(){
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new CustomHandler());
        String[] logs = new String[joueurs.size()+1];
        if(arguments.isDemo()){
            logs[0]="JoueurType,Gagne,Perdu,Nulle,Score,NbObjectifs";
            String[] init = logs[0].split(",");
            LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                    init[0], init[1], init[2], init[3], init[4], init[5]));
        }
        else{logs[0]="JoueurType,Gagne,%Gagne,Perdu,%Perdu,Nulle,%Nulle,ScoreMoyen";
            String[] init = logs[0].split(",");
            LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                    init[0], init[1], init[2], init[3], init[4], init[5], init[6], init[7]));
        }
        LOGGER.info("--------------------------------------------------------------------------------------------------------------------------");


        for (int i = 1, j=0; i <= joueurs.size(); i++,j++) {
            String[] statsRes = stats.getStats(joueurs.get(j), nbparties);
            logs[i]="";
            for(int k=0;k<statsRes.length;k++){
                logs[i]+=statsRes[k]+",";
            }
            if(arguments.isDemo()){
                LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                        statsRes[0], statsRes[1], statsRes[3], statsRes[5], statsRes[7], statsRes[8]));
            }
            else{
                LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                        statsRes[0], statsRes[1], statsRes[2], statsRes[3], statsRes[4], statsRes[5], statsRes[6], statsRes[7]));
            }
        }
        if(arguments.isCsv()){
            logs[0]+= ",NbObjectifsMoyen";
            try {
                writeToCsv(logs);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getNbparties() {
        return nbparties;
    }

    public void setNbparties(int nbparties) {
        this.nbparties = nbparties;
    }
}
