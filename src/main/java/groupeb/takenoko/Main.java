package groupeb.takenoko;

import com.beust.jcommander.JCommander;
import groupeb.takenoko.lanceur.JeuLanceur;
import groupeb.takenoko.option.Args;

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
