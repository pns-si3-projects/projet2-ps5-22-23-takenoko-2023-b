package ps5.takenoko;

import com.beust.jcommander.JCommander;
import ps5.takenoko.Bot.*;
import ps5.takenoko.Bot.BotMoyen;
import ps5.takenoko.lanceur.JeuLanceur;
import ps5.takenoko.option.Args;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        ArrayList<Bot> bots = new ArrayList<>();
        if(arguments.isDemo()){
            bots.add(new BotMVP(1));
            bots.add(new BotParcelle(2));
            bots.add(new BotMoyen(3));
            bots.add(new BotRandom(4));
        }
        else{
            bots.add(new BotMVP(1));
            bots.add(new BotMoyen(2));
        }
        JeuLanceur jeuLanceur = new JeuLanceur(bots, arguments);
        jeuLanceur.lancer();
    }
}
