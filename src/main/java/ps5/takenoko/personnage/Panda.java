package ps5.takenoko.personnage;

import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.ParcelleOriginelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

public class Panda extends Deplacable{
    public Panda() {super();}

    @Override
    public boolean deplacer(Position position, Plateau plateau) {
        if(posPossibles(plateau).contains(position)){
            this.setPosition(position);
            if (!(plateau.getParcelle(position) instanceof ParcelleOriginelle)){
                Parcelle temp = (Parcelle) plateau.getParcelle(position);
                return temp.mangerBambou();
            }
        }
        else{
            throw new IllegalArgumentException("Position impossible a poser");
        }
        return false;
    }

    public Panda(Position position) {super(position);}
}
