package ps5.takenoko;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

public class Main {


    public static String hello() {
        return "Hello World!";
    }

    public static void main(String... args) throws IllegalAccessException {
        System.out.println(hello());
        Plateau plateau = new Plateau();
        for(Position p: plateau.EndroitsPosables()){
            plateau.addParcelle(new Parcelle(),p);
        }
        System.out.println(plateau.EndroitsPosables());
    }

}
