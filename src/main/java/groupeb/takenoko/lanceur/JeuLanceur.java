package groupeb.takenoko.lanceur;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import groupeb.takenoko.bot.*;
import groupeb.takenoko.option.Args;
import groupeb.takenoko.jeu.Jeu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class JeuLanceur {
    private static final Logger LOGGER = Logger.getLogger(JeuLanceur.class.getSimpleName());
    private static final String CSV_FILE_NAME = "./stats/gamestats.csv";
    private int nbparties=0;
    private ArrayList<Bot> bots = new ArrayList<>();
    Args arguments = new Args();
    private Statistics stats;
    public int getNbparties() {return nbparties;}
    public void setNbparties(int nbparties) {this.nbparties = nbparties;}
    public void setBots(ArrayList<Bot> bots) {this.bots = bots; this.stats = new Statistics(bots);}

    public JeuLanceur(Args arguments) {
        this.arguments = arguments;
        if(arguments.isCsv()||arguments.isTwoThousand()){
            nbparties = 1000;
            bots.add(new BotMVP(1));
            bots.add(new BotMoyen(2));
        }
        else if(arguments.isFocus()){
            nbparties = 1000;
            bots.add(new BotJardinier(1));
            bots.add(new BotParcelle(2));
            bots.add(new BotPanda(3));
        }
        else if(arguments.isDemo()){
            nbparties = 1;
            bots.add(new BotMVP(1));
            bots.add(new BotParcelle(2));
            bots.add(new BotMoyen(3));
            bots.add(new BotRandom(4));
        }
        this.stats = new Statistics(bots);
    }

    public void lancer(){
        for (int i = 0; i < nbparties; i++) {
            Jeu jeu = new Jeu(bots);
            if(!arguments.isDemo()){
                jeu.desactiverLogger();
            }
            jeu.lancer();
            ArrayList<Bot> gagnants = new ArrayList<>(jeu.calculGagnants());
            if (gagnants.isEmpty()) {//Case where the game surpass the number of turns
                i--;
            } else {
                stats.updateStats(gagnants);
            }
            for (Bot bot : bots) {
                bot.reset();
            }
        }
        affichageStats();
        if(arguments.isTwoThousand()){
            twoThousandPartTwo();
        }
    }

    public void twoThousandPartTwo() {
        LOGGER.info("\n Second set de 1000 parties :");
        ArrayList<Bot> bots2 = new ArrayList<>();
        bots2.add(new BotMVP(1));
        bots2.add(new BotMVP(2));
        bots2.add(new BotMVP(3));
        bots2.add(new BotMVP(4));
        this.setBots(bots2);
        this.arguments=new Args();
        this.lancer();
    }

    private ColumnPositionMappingStrategy setColumMapping(String[] data) {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(CSVStats.class);
        String[] columns = data[0].split(",");
        strategy.setColumnMapping(columns);
        return strategy;
    }
    private void writeToCsv(String[] data) throws IOException {
        Files.createDirectories(Paths.get(CSV_FILE_NAME.replace("/gamestats.csv", "")));
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

    public void affichageStats(){
        //remove all in logger
        Handler[] handlers = LOGGER.getHandlers();
        for (Handler handler : handlers) {
            LOGGER.removeHandler(handler);
        }
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new CustomHandler());
        String[] logs = new String[bots.size()+1];
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


        for (int i = 1, j = 0; i <= bots.size(); i++,j++) {
            Float[] statsRes = stats.getStats(bots.get(j), nbparties);
            logs[i]= bots.get(j).getClass().getSimpleName()+",";
            for(int k=0;k<statsRes.length;k++){
                logs[i]+=statsRes[k]+",";
            }
            if(arguments.isDemo()){
                LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                        bots.get(j).getClass().getSimpleName(), statsRes[0], statsRes[2], statsRes[4], statsRes[6], statsRes[7]));
            }
            else{
                LOGGER.log(Level.INFO, String.format(" %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s ",
                        bots.get(j).getClass().getSimpleName(), statsRes[0], statsRes[1]+"%", statsRes[2], statsRes[3]+"%", statsRes[4], statsRes[5]+"%", statsRes[6]));
            }
        }
        if(arguments.isCsv()){
            logs[0]="JoueurType,Gagne,PourcentageGagne,Perdu,PourcentagePerdu,Null,PourcentageNull,ScoreMoyen,NbObjectifsMoyen";
            try {
                writeToCsv(logs);
            } catch (IOException e) {
                throw new IllegalArgumentException("Erreur lors de l'écriture du fichier CSV");
            }
        }
    }

    public ArrayList<Bot> getJoueurs() {
        return bots;
    }

}

