package ps5.takenoko.Plateau;

public class Parcelle {
    private Couleur couleur;
    private Position position;

    public Parcelle(Couleur couleur, Position position) {
        this.couleur = couleur;
        this.position = position;
    }

    public Parcelle() {
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}