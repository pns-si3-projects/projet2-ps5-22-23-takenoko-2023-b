package ps5.takenoko.Plateau;

public class Parcelle extends ParcelleInactive{
    private Couleur couleur;

    public Parcelle() {
    }

    public Parcelle(Couleur c) {
        this.couleur = c;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

}
