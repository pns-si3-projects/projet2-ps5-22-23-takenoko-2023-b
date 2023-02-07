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
        System.out.println("\nDraw : " + stats.getEgalite() + "%" + "\n");
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getClass().getSimpleName() + " : " + stats.getScoreMoyenne(joueur,nbparties) + " points");
            System.out.println(joueur.getClass().getSimpleName() + " : " + stats.getPourcentageVictoires(joueur, nbparties) + "% of victories");
        }
        String[] logs = {};
        writeToCsv(logs);


//        String[] logs = {};
//        logs[0]="Joueur, Score Moyen, %Victoires";
//        for (int i = 0; i <= nbJoueurs; i++) {
//            float scoreMoyen = victoires.get(0) / nbparties * 100;
//            float pourcentageVictoires = points.get(1) / nbparties;
//            if(i==0){
//                logs[i]= "Draw,"+ scoreMoyen+","+ pourcentageVictoires;
//            }
//            else{
//                logs[i]= "Joueur "+i+","+ scoreMoyen+","+ pourcentageVictoires;
//            }
//        }
//        bld.append("\nDraw : " + (victoires.get(0) / nbparties * 100)  + "%" + "\n");
//        bld.append("Joueur Random : " + (victoires.get(1) / nbparties * 100)  + "% victoires score moyen: " + (points.get(1) / nbparties)+"\n");
//        bld.append("Joueur Moyen : " + (victoires.get(2) / nbparties * 100)  + "% victoires score moyen: " + (points.get(2) / nbparties)+"\n");
//        LOGGER.info(bld.toString());
//        String[] logs = {};
//        logs[0]="Joueur, Score Moyen, %Victoires";
//        logs[1]="JoueurRandom, "

//        logs[1]=(victoires.get(0) / nbparties * 100)+ "%", (victoires.get(1) / nbparties * 100)+"", (victoires.get(2) / nbparties * 100)+"";
//        writeToCsv(logs);
//
//        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File("./src/main/java/ps5/takenoko/stats/gamestats.csv")));
//        CSVWriter csvWriter = new CSVWriter(fileWriter);
//        String[] logMessage = {LOGGER.getName(), LOGGER.getLevel().toString(), bld.toString()};
//        csvWriter.writeNext(logMessage);
//        csvWriter.close();
//        writeToCsv(logs);
//
//    }
    }
    private void writeToCsv(String[] data) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./src/main/java/ps5/takenoko/stats/gamestats.csv"));
        String[] record = "2,Rahul,Vaidya,India,35555".split(",");
        writer.writeNext(record, false);
        writer.close();
    }

}
