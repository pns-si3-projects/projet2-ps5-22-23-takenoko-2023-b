package ps5.takenoko.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.joueur.Joueur;
import ps5.takenoko.joueur.JoueurMoyen;
import ps5.takenoko.joueur.JoueurRandom;
import ps5.takenoko.objectif.Objectif;
import ps5.takenoko.objectif.ObjectifJardinier;
import ps5.takenoko.objectif.TypeObjJardinier;
import ps5.takenoko.personnage.Jardinier;
import ps5.takenoko.personnage.Panda;
import ps5.takenoko.plateau.Couleur;
import ps5.takenoko.plateau.Parcelle;
import ps5.takenoko.plateau.Plateau;
import ps5.takenoko.plateau.Position;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testEstTermine(){
        assertFalse(jeu.estTermine());
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurRandom(0);
        ArrayList<Objectif> objectifs = new ArrayList<>();
        for(int i=0; i<10; i++){
            objectifs.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE, Couleur.ROSE));
        }
        joueur1.setObjectifsObtenus(objectifs);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        assertTrue(jeu2.estTermine());
    }

    @Test
    void CalculGagnats(){
        assertEquals(jeu.calculGagnants().size(), 2);
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurRandom(0);
        ArrayList<Objectif> objectifs = new ArrayList<>();
        for(int i=0; i<10; i++){
            objectifs.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE, Couleur.ROSE));
        }
        joueur1.setObjectifsObtenus(objectifs);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        assertEquals(jeu2.calculGagnants().size(), 1);
        assertTrue(jeu2.calculGagnants().contains(joueur1));
    }

    @Test
    void testpiocherObjectifs(){
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurRandom(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        jeu2.piocherObjectifs(joueur1);
        assertEquals(joueur1.getObjectifs().size(), 1);
        jeu2.piocherObjectifs(joueur1);
        assertEquals(joueur1.getObjectifs().size(), 2);
    }

    @Test
    void testpiocherParcelles() {
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurRandom(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        assertEquals(jeu2.getParcellesList().size(),27);
        Parcelle p = jeu2.piocherParcelles(joueur1);
        assertFalse(jeu2.getParcellesList().contains(p));
        assertEquals(jeu2.getParcellesList().size(),26);
    }

    @Test
    void testchoisirMeteo() {
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurRandom(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        Meteo m = jeu2.choisirMeteo(joueur1);
        assertTrue(m == Meteo.SOLEIL || m == Meteo.PLUIE || m == Meteo.ORAGE || m == Meteo.VENT || m == Meteo.NUAGES);

    }

    @Test
    void testtourJoueur(){
        Logger LOGGER = Logger.getLogger(Jeu.class.getSimpleName());
        LOGGER.setLevel(Level.OFF);
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurMoyen(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        jeu2.tourJoueur(joueur1,false);
        assertTrue(joueur1.getObjectifs().size() == 1);
    }

    @Test
    void executerNuage(){
        Jeu jeu2;
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        JoueurRandom joueur1 = new JoueurMoyen(0);
        players.add(joueur1);
        players.add(new JoueurRandom(1));
        jeu2 = new Jeu(players);
        assertEquals(jeu2.getAmenagementList().size(), 9);
        assertEquals(joueur1.getAmenagements().size(), 0);
        jeu2.executerNuage(joueur1);
        assertEquals(jeu2.getAmenagementList().size(), 8);
        assertEquals(joueur1.getAmenagements().size(), 1);
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
        assertEquals("                                                         \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m                                                          \n" +
                "                                                        \u001B[30m|\u001B[0m \u001B[31m2 \u001B[0m\u001B[30m|\u001B[0m                                                       \n" +
                "                                                   \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m                                                      \n" +
                "                                                  \u001B[30m|\u001B[0m \u001B[31m0 \u001B[0m\u001B[30m|\u001B[0m \u001B[31m1 \u001B[0m| \u001B[32m1 \u001B[0m\u001B[30m|\u001B[0m                                                       \n" +
                "                                                   \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \\ \u001B[34m/\u001B[0m\u001B[35mJ\u001B[0m\u001B[34m\\\u001B[0m / \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m                                                  \n" +
                "                                                    \u001B[30m|\u001B[0m \u001B[33m4 \u001B[0m\u001B[34m|\u001B[0m\u001B[34m E \u001B[0m\u001B[34m|\u001B[0m \u001B[33m1 \u001B[0m\u001B[30m|\u001B[0m \u001B[31m2 \u001B[0m\u001B[30m|\u001B[0m                                               \n" +
                "                                                     \u001B[30m\\\u001B[0m / \u001B[34m\\\u001B[0m\u001B[35mP\u001B[0m\u001B[34m/\u001B[0m \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m                                              \n" +
                "                                                      \u001B[30m|\u001B[0m \u001B[32m1 \u001B[0m\u001B[30m|\u001B[0m   \u001B[30m|\u001B[0m \u001B[31m0 \u001B[0m\u001B[30m|\u001B[0m \u001B[33m1 \u001B[0m\u001B[30m|\u001B[0m                                               \n" +
                "                                                       \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m   \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m                                                \n" +
                "                                                        \u001B[30m|\u001B[0m \u001B[31m0 \u001B[0m\u001B[30m|\u001B[0m   \u001B[30m|\u001B[0m \u001B[33m1 \u001B[0m\u001B[30m|\u001B[0m                                               \n" +
                "                                                         \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m     \u001B[30m\\\u001B[0m \u001B[30m/\u001B[0m                                                \n",jeu.affichePlateau());
    }


    @Test
    void pluie(){
        Parcelle farAway= new Parcelle(Couleur.VERT);

        jeu.getPlateau().addParcelle(farAway,new Position(18,18));
        farAway.setAmenagement(new Amenagement(AmenagementType.BASSIN));

        assertEquals(1,farAway.getNbBamboo());
        jeu.executerPluie(players.get(0));
        assertEquals(2,farAway.getNbBamboo());
    }

}