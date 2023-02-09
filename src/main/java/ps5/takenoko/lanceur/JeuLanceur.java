package ps5.takenoko.lanceur;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.option.Args;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class JeuLanceur {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());
    private static final String CSV_FILE_NAME = "./stats/gamestats.csv";
    private int nbparties=0;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    Args arguments = new Args();
    private Statistics stats;
    public int getNbparties() {return nbparties;}
    public void setNbparties(int nbparties) {this.nbparties = nbparties;}

    public JeuLanceur(ArrayList<Joueur> joueurs, Args arguments) {
        this.joueurs = joueurs;
        this.arguments = arguments;
        if(arguments.isCsv()||arguments.isTwoThousand()){
            nbparties = 1000;
        }
        else if(arguments.isDemo()){
            nbparties = 1;
        }
        this.stats = new Statistics(joueurs);
    }

    public void lancer(){
        for (int i = 0; i < nbparties; i++) {
            Jeu jeu = new Jeu(joueurs);
            if(!arguments.isDemo()){
                jeu.setAffichage(false);
            }
            jeu.lancer();
            ArrayList<Joueur> gagnants = jeu.calculGagnants();
            if (gagnants.size() == 0) {//Case where the game surpass the number of turns
                i--;
            } else {
                stats.updateStats(gagnants);
            }
            for (Joueur joueur : joueurs) {
                joueur.reset();
            }
        }
        affichageStats();
        if(arguments.isTwoThousand()){
            LOGGER.log(Level.INFO, String.format("\n Second set de 1000 parties :"));
            ArrayList<Joueur> joueurs = new ArrayList<>();
            joueurs.add(new JoueurMoyen(1));
            joueurs.add(new JoueurMoyen(2));
            joueurs.add(new JoueurMoyen(3));
            joueurs.add(new JoueurMoyen(4));
            JeuLanceur jeuLanceur = new JeuLanceur(joueurs, new Args());
            jeuLanceur.setNbparties(1000);
            jeuLanceur.lancer();
        }
    }
    private ColumnPositionMappingStrategy setColumMapping(String[] data) {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(CSVStats.class);
        String[] columns = data[0].split(",");
        strategy.setColumnMapping(columns);
        return strategy;
    }
    private void writeToCsv(String[] data) throws IOException {
        if(fichierExists()){
            CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_NAME));
            CsvToBean csv = new CsvToBean();
            csv.setCsvReader(csvReader);
            csv.setMappingStrategy(setColumMapping(data));
            List list = csv.parse();
            CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_NAME, false));
            writer.writeNext(((CSVStats) list.get(0)).toStringArray());
            for (int i= 1; i < list.size(); i++) {
                if(list.size()!=data.length && !list.isEmpty()){
                    throw new IllegalArgumentException("Fichier CSV n'est pas conforme");
                }
                CSVStats stat = (CSVStats) list.get(i);
                stat.update(data[i]);
                writer.writeNext(stat.toStringArray());
            }
            writer.close();
            csvReader.close();
        }
        else{
            CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_NAME, false));
            for(String line : data){
                writer.writeNext(line.split(","));
            }
            writer.close();
        }
    }

    private boolean fichierExists(){
        File file = new File(CSV_FILE_NAME);
        return file.exists()&&file.isFile()&&file.length()!=0;
    }

    private void affichageStats(){
        //remove all in logger
        Handler[] handlers = LOGGER.getHandlers();
        for (Handler handler : handlers) {
            LOGGER.removeHandler(handler);
        }
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new CustomHandler());
        String[] logs = new String[joueurs.size()+1];
        if(arguments.isDemo()){
            logs[0]="JoueurType,Gagne,Perdu,Null,Score,NbObjectifs";
            String[] init = logs[0].split(",");
            LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ", init));
        }
        else{
            logs[0]="JoueurType,Gagne,%Gagne,Perdu,%Perdu,Null,%Null,ScoreMoyen";
            String[] init = logs[0].split(",");
            LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ", init));
        }
        LOGGER.info("--------------------------------------------------------------------------------------------------------------------------");


        for (int i = 1, j=0; i <= joueurs.size(); i++,j++) {
            Float[] statsRes = stats.getStats(joueurs.get(j), nbparties);
            logs[i]=joueurs.get(j).getClass().getSimpleName()+",";
            for(int k=0;k<statsRes.length;k++){
                logs[i]+=statsRes[k]+",";
            }
            if(arguments.isDemo()){
                LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                        joueurs.get(j).getClass().getSimpleName(), statsRes[0], statsRes[2], statsRes[4], statsRes[6], statsRes[7]));
            }
            else{
                LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                        joueurs.get(j).getClass().getSimpleName(), statsRes[0], statsRes[1]+"%", statsRes[2], statsRes[3]+"%", statsRes[4], statsRes[5]+"%", statsRes[6]));
            }
        }
        if(arguments.isCsv()){
            logs[0]="JoueurType,Gagne,PourcentageGagne,Perdu,PourcentagePerdu,Null,PourcentageNull,ScoreMoyen,NbObjectifsMoyen";
            try {
                writeToCsv(logs);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

