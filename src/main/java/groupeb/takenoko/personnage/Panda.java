package groupeb.takenoko.personnage;

import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;
import groupeb.takenoko.plateau.ParcelleOriginelle;

public class Panda extends Deplacable{
    public Panda() {super();}

    @Override
    public boolean deplacer(Position position, Plateau plateau) {
            this.setPosition(position);
            if (!(plateau.getParcelle(position) instanceof ParcelleOriginelle)){
                Parcelle temp = (Parcelle) plateau.getParcelle(position);
                return temp.mangerBambou();
            }
        return false;
    }

    public Panda(Position position) {super(position);}
}
