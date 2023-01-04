package ps5.takenoko.Element;

import ps5.takenoko.Plateau.Parcelle;

import java.util.Objects;

public class Irrigation {
    private Parcelle premier;
    private Parcelle deuxieme;

    public Parcelle getPremier() {return premier;}

    public Parcelle getDeuxieme() {return deuxieme;}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Irrigation){
            Irrigation irrigation = (Irrigation) obj;
            return (irrigation.getPremier().equals(premier) && irrigation.getDeuxieme().equals(deuxieme)) || (irrigation.getPremier().equals(deuxieme) && irrigation.getDeuxieme().equals(premier));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return premier.hashCode() + deuxieme.hashCode();
    }
}