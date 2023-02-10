package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.lanceur.JeuLanceur;
import ps5.takenoko.option.Args;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        JeuLanceur jeuLanceur = new JeuLanceur(arguments);
        jeuLanceur.lancer();
    }
}
