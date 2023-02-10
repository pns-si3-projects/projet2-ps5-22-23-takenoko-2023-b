package groupeb.takenoko.jeu;

import groupeb.takenoko.bot.*;
import groupeb.takenoko.objectif.ObjectifJardinier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.element.Meteo;
import groupeb.takenoko.objectif.Objectif;
import groupeb.takenoko.objectif.TypeObjJardinier;
import groupeb.takenoko.personnage.Jardinier;
import groupeb.takenoko.personnage.Panda;
import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JeuTest {

    Jeu jeu;
    Plateau plateau;
    ArrayList<Bot> players = new ArrayList<Bot>();

    @BeforeEach
    void init(){
        players.add(new BotRandom(0));
        players.add(new BotRandom(1));
        jeu = new Jeu(players);
        plateau = new Plateau();
    }

    @Test
    void testEstTermine(){
        assertFalse(jeu.estTermine());
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotRandom joueur1 = new BotRandom(0);
        ArrayList<Objectif> objectifs = new ArrayList<>();
        for(int i=0; i<10; i++){
            objectifs.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE, Couleur.ROSE));
        }
        joueur1.setObjectifsObtenus(objectifs);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        assertTrue(jeu2.estTermine());
    }

    @Test
    void CalculGagnats(){
        assertEquals(2,jeu.calculGagnants().size());
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotRandom joueur1 = new BotRandom(0);
        ArrayList<Objectif> objectifs = new ArrayList<>();
        for(int i=0; i<10; i++){
            objectifs.add(new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE, Couleur.ROSE));
        }
        joueur1.setObjectifsObtenus(objectifs);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        assertEquals(1,jeu2.calculGagnants().size());
        assertTrue(jeu2.calculGagnants().contains(joueur1));
    }

    @Test
    void testpiocherObjectifs(){
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotRandom joueur1 = new BotRandom(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        jeu2.piocherObjectifs(joueur1);
        assertEquals(1,joueur1.getObjectifs().size());
        jeu2.piocherObjectifs(joueur1);
        assertEquals(2,joueur1.getObjectifs().size());
    }

    @Test
    void testpiocherParcelles() {
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotRandom joueur1 = new BotRandom(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        assertEquals(27,jeu2.getParcellesList().size());
        Parcelle p = jeu2.piocherParcelles(joueur1);
        assertFalse(jeu2.getParcellesList().contains(p));
        assertEquals(26,jeu2.getParcellesList().size());
    }

    @Test
    void testchoisirMeteo() {
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotRandom joueur1 = new BotRandom(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        Meteo m = jeu2.choisirMeteo(joueur1);
        assertTrue(m == Meteo.SOLEIL || m == Meteo.PLUIE || m == Meteo.ORAGE || m == Meteo.VENT || m == Meteo.NUAGES);

    }

    @Test
    void testtourJoueur(){
        Logger LOGGER = Logger.getLogger(Jeu.class.getSimpleName());
        LOGGER.setLevel(Level.OFF);
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotMVP joueur1 = new BotMVP(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        jeu2.tourJoueur(joueur1,false);
        assertEquals(1,joueur1.getObjectifs().size());

        Jeu jeuM = Mockito.spy(new Jeu(players));
        when(jeuM.getRandomMeteo()).thenReturn(Meteo.CHOIX_LIBRE);
        when(jeuM.choisirMeteo(joueur1)).thenReturn(Meteo.PLUIE);
        jeuM.tourJoueur(joueur1,true);
        Mockito.verify(jeuM, Mockito.times(1)).choisirMeteo(joueur1);
        Mockito.verify(jeuM).executerPluie(joueur1);

        Jeu jeuM2 = Mockito.spy(new Jeu(players));
        when(jeuM2.getRandomMeteo()).thenReturn(Meteo.ORAGE);
        jeuM2.tourJoueur(joueur1,true);
        Mockito.verify(jeuM2).executerOrage(joueur1);

        ArrayList<Bot> players2 = new ArrayList<Bot>();
        BotRandom joueurRandom = mock(BotRandom.class);
        when(joueurRandom.jouer(any())).thenReturn(Action.POSER_AMENAGEMENT);
        players2.add(joueurRandom);
        players2.add(new BotRandom(1));
        Jeu jeu3 = new Jeu(players2);
        String exeption = "";
        try {
            jeu3.tourJoueur(joueurRandom, false);
        } catch (Exception e) {
            exeption = e.getMessage();
        }
        assertEquals("Il n'y a pas de parcelle amenageable",exeption);

        Jeu jeuM3 = Mockito.spy(new Jeu(players));
        when(jeuM3.getRandomMeteo()).thenReturn(Meteo.NUAGES);
        jeuM3.tourJoueur(joueur1,true);
        Mockito.verify(jeuM3).executerNuage(joueur1);
    }

    @Test
    void Jardinier() {
        ArrayList<Bot> players3 = new ArrayList<Bot>();
        BotRandom joueurRandom2 = mock(BotRandom.class);
        players3.add(joueurRandom2);
        players3.add(new BotRandom(1));
        Jeu jeuM4 = new Jeu(players3);
        Jardinier jardiner = mock(Jardinier.class);
        when(jardiner.deplacer(any(), any())).thenReturn(true);
        jeuM4.setJardinier(jardiner);
        when(joueurRandom2.jouer(any())).thenReturn(Action.JARDINIER);
        when(joueurRandom2.deplacerJardinier(any())).thenReturn(new Position(0,0));
        jeuM4.tourJoueur(joueurRandom2,false);
        //check if deplacerJardinier is called twice
        Mockito.verify(joueurRandom2, Mockito.times(2)).deplacerJardinier(any());
    }

    @Test
    void Panda() {
        ArrayList<Bot> players3 = new ArrayList<Bot>();
        BotRandom joueurRandom2 = mock(BotRandom.class);
        players3.add(joueurRandom2);
        players3.add(new BotRandom(1));
        Jeu jeuM4 = new Jeu(players3);
        Panda panda = mock(Panda.class);
        when(panda.deplacer(any(), any())).thenReturn(false);
        jeuM4.setPanda(panda);
        when(joueurRandom2.jouer(any())).thenReturn(Action.PANDA);
        when(joueurRandom2.deplacerPanda(any())).thenReturn(new Position(0,0));
        jeuM4.tourJoueur(joueurRandom2,false);
        //check if deplacerJardinier is called twice
        Mockito.verify(joueurRandom2, Mockito.times(2)).deplacerPanda(any());
    }

    @Test
    void executerNuage(){
        Jeu jeu2;
        ArrayList<Bot> players = new ArrayList<Bot>();
        BotRandom joueur1 = new BotMoyen(0);
        players.add(joueur1);
        players.add(new BotRandom(1));
        jeu2 = new Jeu(players);
        assertEquals(9,jeu2.getAmenagementList().size());
        assertEquals(0,joueur1.getAmenagements().size());
        jeu2.executerNuage(joueur1);
        assertEquals(8,jeu2.getAmenagementList().size());
        assertEquals(1,joueur1.getAmenagements().size());
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