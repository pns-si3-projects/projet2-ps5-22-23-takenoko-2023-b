package ps5.takenoko.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.util.ArrayList;

class JeuTest {

    Jeu jeu;
    Plateau plateau;
    ArrayList<Joueur> players = new ArrayList<Joueur>();


    @BeforeEach
    void init(){
        players.add(new JoueurRandom(0));
        players.add(new JoueurRandom(1));
        players.add(new JoueurRandom(2));
        jeu = new Jeu(players);
        plateau = new Plateau();
    }

    @Test
    void affichePlateauTest() {
        try{
            plateau.addParcelle(new Parcelle(Couleur.ROSE,2),new Position(15,13));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(14,14));
            plateau.addParcelle(new Parcelle(Couleur.VERT),new Position(15,14));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE,3),new Position(14,15));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(16,15));
            plateau.addParcelle(new Parcelle(Couleur.VERT),new Position(14,16));

            plateau.addParcelle(new Parcelle(Couleur.ROSE,2),new Position(17,15));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,17));

            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(13,14));

            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(16,16));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE,1),new Position(17,16));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE,1),new Position(17,17));

        }catch(Exception e){System.out.println(e);}
        jeu = new Jeu(players);
        jeu.setPlateau(plateau);
        System.out.println(jeu.affichePlateau());
    }


}