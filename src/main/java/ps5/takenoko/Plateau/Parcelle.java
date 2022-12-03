package ps5.takenoko.Plateau;

public class Parcelle extends ParcelleInactive{
    private Color couleur;
    private Boolean debut = false;

    public Parcelle() {
    }

    public Parcelle(Color c) {
        this.couleur = c;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Boolean isDebut() {
        return debut;
    }

    public void setDebut(Boolean debut) {
        this.debut = debut;
    }
}
