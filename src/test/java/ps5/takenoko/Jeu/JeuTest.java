package ps5.takenoko.Jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.Joueur.Joueur;
import ps5.takenoko.Joueur.JoueurRandom;
import ps5.takenoko.Plateau.Couleur;
import ps5.takenoko.Plateau.Parcelle;
import ps5.takenoko.Plateau.Plateau;
import ps5.takenoko.Plateau.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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