package ps5.takenoko.objectif;

import ps5.takenoko.bot.Bot;

public class Empereur extends Objectif {
    public Empereur() {
        super("Empereur", 2);
    }
    @Override
    public boolean verifie(Bot j) {
        return true;
    }

    @Override
    public String toString() {
        return "Empereur";
    }
}
