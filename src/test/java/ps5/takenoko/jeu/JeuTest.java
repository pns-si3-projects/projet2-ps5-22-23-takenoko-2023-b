package ps5.takenoko.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.objectif.ObjectifJardinier;
import ps5.takenoko.objectif.TypeObjJardinier;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.security.SecureRandom;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JeuTest {

    Jeu jeu;
    Plateau plateau;
    ArrayList<Joueur> players = new ArrayList<Joueur>();


    @BeforeEach
    void init(){
        players.add(new JoueurRandom(0));
        players.add(new JoueurRandom(1));
        jeu = new Jeu(players);
        plateau = new Plateau();
    }

}