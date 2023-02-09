package ps5.takenoko.objectif;

import ps5.takenoko.Bot.Bot;
import ps5.takenoko.plateau.*;

import java.util.Objects;

public class ObjectifJardinier extends Objectif {
    private final TypeObjJardinier type;

    public ObjectifJardinier(TypeObjJardinier type, Couleur color) {
        super(type.getPoint(), new Couleur[]{color});
        switch (type) {
            case OBJMULTROSE -> super.couleurs = new Couleur[]{Couleur.ROSE};
            case OBJMULTJAUNE -> super.couleurs = new Couleur[]{Couleur.JAUNE};
            case OBJMULTVERT -> super.couleurs = new Couleur[]{Couleur.VERT};
            default -> {
            }
        }
        this.type = type;

    }

    @Override
    public boolean verifie(Bot player) {
        Plateau board = player.getPlateau();
        if (!type.isMultiple()) {
            for (Position pos : board.getParcellePosee()) {
                if (!(board.getParcelle(pos) instanceof Parcelle parcelle)) continue;
                if (
                        couleurs[0] == parcelle.getCouleur()
                                && parcelle.getNbBamboo() == type.getNbBamboo()
                    && (type.getAmenagementType()==null || parcelle.getAmenagement().getType() == type.getAmenagementType())
                ) return true;
            }
        } else {
            int restant = switch (couleurs[0]) {
                case ROSE -> 2;
                case JAUNE -> 3;
                default -> 4;
            };
            for (Position pos : board.getParcellePosee()) {
                if (!(board.getParcelle(pos) instanceof Parcelle parcelle)) continue;
                if (
                        couleurs[0] == parcelle.getCouleur()
                        && parcelle.getNbBamboo() == type.getNbBamboo()
                ) {
                    if (--restant == 0) return true;
                }
            }

        }
        return false;
    }

    public TypeObjJardinier getType() {
        return type;
    }

    @Override
    public int getPoint(){
        if(type.isMultiple()) return super.getPoint();
        return switch (couleurs[0]) {
            case ROSE -> super.getPoint() + 1;
            case VERT -> super.getPoint() - 1;
            default -> super.getPoint();
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifJardinier that = (ObjectifJardinier) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

}