package groupeb.takenoko.bot;

import groupeb.takenoko.objectif.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.jeu.Jeu;
import groupeb.takenoko.plateau.Couleur;
import groupeb.takenoko.plateau.Parcelle;
import groupeb.takenoko.plateau.Plateau;
import groupeb.takenoko.plateau.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    BotRandom player0;
    BotMoyen player1;
    Jeu game;
    ArrayList<Bot> players;
    Plateau board;


    @BeforeEach
    void init(){
        ArrayList<Bot> players = new ArrayList<>();
        player0 = new BotRandom(0);
        player1 = new BotMoyen(1);
        players.add(player0);
        players.add(player1);
        game = new Jeu(players);
        board = game.getPlateau();

    }
    /* Pas possible tant que c'est aleatoire l'obtention des objectifs
    @Test
    void getNombreObjectifsObtenus() {
    }
     */

    @Test
    void testClone() {
        BotRandom fake = player0.clone();
        assertEquals(fake.getClass(),player0.getClass());
        assertEquals(fake.getId(),player0.getId());
        /* Element qui ne sont pas copie
        assertEquals(fake.getPlateau(),player0.getPlateau());
        assertEquals(fake.getParcelles(),player0.getParcelles());
        assertEquals(fake.getNbIrrigations(),player0.getNbIrrigations());
        assertEquals(fake.getAmenagements(),player0.getAmenagements());
        assertEquals(fake.getObjectifs(),player0.getObjectifs());
        assertEquals(fake.getObjectifsObtenus(),player0.getObjectifsObtenus());
        assertEquals(fake.getBambousObtenus(),player0.getBambousObtenus());
        */
        assertNotEquals(fake,player0);
    }

    @Test
    void calculPoint() {
        assertEquals(0,player0.calculPoint());
    }

    @Test
    void calculPointPanda() {
        assertEquals(0,player0.calculPointPanda());
    }

    @Test
    void addObjectif() {
        Objectif objPrG= new ObjectifParcelle(Shape.LIGNE, Couleur.VERT);
        Objectif objPnY= new ObjectifPanda(1,Couleur.JAUNE,3);
        Objectif objJR= new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.ROSE);
        Objectif[] objs = {objPrG,objPnY,objJR};
        assertEquals(0,player0.getObjectifs().size());
        for( int i=0;i<3;i++){
            player0.addObjectif(objs[i]);
            assertEquals(i+1,player0.getObjectifs().size());
        }

    }

    /* 1 chance sur deux de completer ducoup on peut pas prevoir les assert
    @Test
    void completerObjectif() {

        addObjectif()
        Parcelle ParR = new Parcelle(Couleur.ROSE);
        ParR.setNbBamboo(4);
        System.out.println(objJR.verifie(player0));
        board.addParcelle(ParR, new Position(15,14));
        player0.validerObjectifs();
        assertEquals(1,player0.getNombreObjectifsObtenus());
        assertTrue(player0.getObjectifsObtenus().contains(objJR));

        board.addParcelle(new Parcelle(Couleur.VERT),new Position(14,15));
        board.addParcelle(new Parcelle(Couleur.VERT),new Position(14,14));
        board.addParcelle(new Parcelle(Couleur.VERT),new Position(15,13));
        player0.validerObjectifs();
        assertEquals(2,player0.getNombreObjectifsObtenus());

        player0.ajouteBambou(Couleur.JAUNE);
        player0.ajouteBambou(Couleur.JAUNE);
        player0.ajouteBambou(Couleur.JAUNE);
        player0.validerObjectifs();
        assertEquals(3,player0.getNombreObjectifsObtenus());

    }*/

    @Test
    void objectifsValidable() {
        Objectif objR = new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.ROSE);
        Objectif objG = new ObjectifPanda(1,Couleur.VERT,3);
        player0.addObjectif(objR);
        player0.addObjectif(objG);

        Parcelle parcelleRose = new Parcelle(Couleur.ROSE);
        board.addParcelle(parcelleRose, new Position(16,15));

        parcelleRose.setNbBamboo(4);
        List<Objectif> validable = player0.objectifsValidable();
        assertEquals(1,validable.size());
        assertTrue(validable.contains(objR));

    }

    @Test
    void ajouteIrrigation() {
        assertEquals(0,player0.getNbIrrigations());
        player0.ajouteIrrigation();
        assertEquals(1,player0.getNbIrrigations());
    }

    @Test
    void ajouteBambou() {
        assertEquals(0, player0.getBambousObtenus()[0]);
        assertEquals(0, player0.getBambousObtenus()[1]);
        assertEquals(0, player0.getBambousObtenus()[2]);

        player0.ajouteBambou(Couleur.VERT);
        assertEquals(1, player0.getBambousObtenus()[0]);
        assertEquals(0, player0.getBambousObtenus()[1]);
        assertEquals(0, player0.getBambousObtenus()[2]);
        player0.ajouteBambou(Couleur.JAUNE);
        assertEquals(1, player0.getBambousObtenus()[0]);
        assertEquals(1, player0.getBambousObtenus()[1]);
        assertEquals(0, player0.getBambousObtenus()[2]);
        player0.ajouteBambou(Couleur.ROSE);
        assertEquals(1, player0.getBambousObtenus()[0]);
        assertEquals(1, player0.getBambousObtenus()[1]);
        assertEquals(1, player0.getBambousObtenus()[2]);
    }

    @Test
    void enleverBambous() {
        ajouteBambou();
        player0.ajouteBambou(Couleur.ROSE);
        player0.enleverBambous(0,Couleur.VERT);
        player0.enleverBambous(1,Couleur.JAUNE);
        player0.enleverBambous(2,Couleur.ROSE);

        assertEquals(1, player0.getBambousObtenus()[0]);
        assertEquals(0, player0.getBambousObtenus()[1]);
        assertEquals(0, player0.getBambousObtenus()[2]);
    }

    @Test
    void nbBambousParCouleur() {
        ajouteBambou();
        player0.ajouteBambou(Couleur.ROSE);
        assertEquals(1, player0.nbBambousParCouleur(Couleur.VERT));
        assertEquals(1, player0.nbBambousParCouleur(Couleur.JAUNE));
        assertEquals(2, player0.nbBambousParCouleur(Couleur.ROSE));
    }
    /* Peux pas tant que 50% chance de gagner un evenement
    @Test
    void compareTo() {

    }
    */


    @Test
    void reset() {
        addObjectif();
        player0.addAmenagement(new Amenagement(AmenagementType.BASSIN));
        player0.ajouteIrrigation();
        player0.reset();
        assertEquals(0,player0.getObjectifs().size());
        assertEquals(0,player0.getNombreObjectifsObtenus());
        assertEquals(0,player0.getAmenagements().size());
        assertEquals(0,player0.nbBambousParCouleur(Couleur.VERT));
        assertEquals(0,player0.nbBambousParCouleur(Couleur.JAUNE));
        assertEquals(0,player0.nbBambousParCouleur(Couleur.ROSE));
        assertEquals(0,player0.getNbIrrigations());
    }

    @Test
    void addAmenagement() {
        Amenagement bassin = new Amenagement(AmenagementType.BASSIN);
        Amenagement enclos = new Amenagement(AmenagementType.ENCLOS);
        Amenagement engrais = new Amenagement(AmenagementType.ENGRAIS);
        player0.addAmenagement(bassin);
        assertTrue(player0.getAmenagements().contains(bassin));
        player0.addAmenagement(enclos);
        assertTrue(player0.getAmenagements().contains(enclos));
        player0.addAmenagement(engrais);
        assertTrue(player0.getAmenagements().contains(engrais));
    }

    @Test
    void placerIrrigation() {
        board.addParcelle(new Parcelle(Couleur.VERT),new Position(14,14));
        board.addParcelle(new Parcelle(Couleur.JAUNE),new Position(15,14));
        board.addParcelle(new Parcelle(Couleur.ROSE),new Position(16,15));
        player0.ajouteIrrigation();
        assertEquals(1,player0.getNbIrrigations());
        player0.placerIrrigation();
        assertEquals(0,player0.getNbIrrigations());
        try{
            player0.placerIrrigation();
        }catch(Exception e){
            // Must throw excpetion because nb of irrigation == 0, can't be lower
        }
        assertEquals(0,player0.getNbIrrigations());

    }
}