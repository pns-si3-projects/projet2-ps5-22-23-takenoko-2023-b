package ps5.takenoko;

import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

public class Main {


    public static String hello() {
        return "Hello World!";
    }

    public static void main(String... args) throws IllegalAccessException {

        long startTime = System.nanoTime();
        for(int i=0; i<1000; i++){
            Plateau plateau = new Plateau();
            System.out.println(plateau.isPosable(new Position(14,14)));
            for(Position p: plateau.getEndroitsPosables()){
                plateau.addParcelle(new Parcelle(),p);
                plateau.getEndroitsPosables();
                plateau.getEndroitsPosables();
                plateau.getEndroitsPosables();
                plateau.getEndroitsPosables();
                plateau.getEndroitsPosables();
                break;
            }
        }
        long elapsedTime = System.nanoTime()-startTime;
        System.out.println(elapsedTime/10000);
    }

}
