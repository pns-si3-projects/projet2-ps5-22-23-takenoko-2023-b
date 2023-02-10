package ps5.takenoko.bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.takenoko.element.Amenagement;
import ps5.takenoko.element.AmenagementType;
import ps5.takenoko.element.Meteo;
import ps5.takenoko.jeu.Jeu;
import ps5.takenoko.objectif.*;
import ps5.takenoko.plateau.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BotMoyenTest {
    BotMoyen player;
    BotMoyen player2;
    Jeu game;
    Plateau board;

    @BeforeEach
    void init(){
        ArrayList<Bot> players = new ArrayList<>();
        player = new BotMoyen(0);
        player2 = new BotMoyen(1);
        players.add(player);
        players.add(player2);
        game = new Jeu(players);
        board = game.getPlateau();
    }

    @Test
    void validerObjectifsTests() {
        Parcelle Green3 = new Parcelle(Couleur.VERT);
        Objectif objCourbeVerte= new ObjectifParcelle(Shape.COURBE,new Couleur[]{Couleur.VERT});
        board.addParcelle(Green3,new Position(16,15));
        board.addParcelle(Green3,new Position(15,14));
        board.addParcelle(Green3,new Position(14,14));
        board.addParcelle(Green3,new Position(15,17));

        player2.objectifs.add(objCourbeVerte);
        player.validerObjectifs();
        assertEquals(0,player.objectifs.size());
        player.objectifs.add(objCourbeVerte);

        player.validerObjectifs();
        assertEquals(1,player.getNombreObjectifsObtenus());

    }

    @Test
    void getRandomPositionTests() {
        Position pos = new Position(14,14);
        Set<Position> positions = new HashSet<>();
        positions.addAll(List.of(new Position[]{pos, new Position(14, 15), new Position(16, 15), new Position(15, 16), new Position(15, 14)}));
        for(int i=0;i<positions.size();i++){
            Position choosen = player.getRandomPosition(positions);
            assertTrue(positions.contains(choosen));
            positions.remove(choosen);
        }

    }

    @Test
    void deplacerJardinierTests() {
        Parcelle green0 = new Parcelle(Couleur.VERT);
        Parcelle pink0 = new Parcelle(Couleur.ROSE);
        Parcelle yellow0 = new Parcelle(Couleur.JAUNE);
        Parcelle green1 = new Parcelle(Couleur.VERT);
        Parcelle pink1 = new Parcelle(Couleur.ROSE);
        Parcelle yellow1 = new Parcelle(Couleur.JAUNE);
        Position posG = new Position(15, 14);
        Position posY = new Position(16, 15);
        Position posP = new Position(15, 16);
        Position pos1 = new Position(14, 16);
        Position pos2 = new Position(14, 15);
        Position pos3 = new Position(14, 14);

        board.addParcelle(green1, posG);
        board.addParcelle(pink1, posP);
        board.addParcelle(yellow1, posY);
        board.addParcelle(green0, pos1);
        board.addParcelle(pink0, pos2);
        board.addParcelle(yellow0, pos3);
        green0.setNbBamboo(4);
        pink0.setNbBamboo(4);
        yellow0.setNbBamboo(4);
        Objectif objG = new ObjectifJardinier(TypeObjJardinier.OBJMULTVERT,Couleur.JAUNE);
        Objectif objP = new ObjectifJardinier(TypeObjJardinier.OBJMULTROSE,Couleur.JAUNE);
        Objectif objY = new ObjectifJardinier(TypeObjJardinier.OBJMULTJAUNE,Couleur.JAUNE);
        Objectif[] obj = {objG,objP,objY};
        Position[] pos = {posG,posP,posY};

        for(int i=0;i<3;i++){
            player.objectifs = new ArrayList<>();
            player.objectifs.add(obj[i]);
            Position selected = player.deplacerJardinier(Set.of(pos1, posG, pos2, posP, pos3, posY));
            assertEquals(pos[i],selected);
        }
    }

    @Test
    void placerIrrigationTests() {
        //TODO quand le bot Moyen ne les posera plus au hasard
    }

    @Test
    void deplacerPandaTests() {
        Parcelle green0 = new Parcelle(Couleur.VERT);
        Parcelle pink0 = new Parcelle(Couleur.ROSE);
        Parcelle yellow0 = new Parcelle(Couleur.JAUNE);
        Parcelle green1 = new Parcelle(Couleur.VERT);
        Parcelle pink1 = new Parcelle(Couleur.ROSE);
        Parcelle yellow1 = new Parcelle(Couleur.JAUNE);
        Position posG = new Position(15, 14);
        Position posY = new Position(16, 15);
        Position posP = new Position(15, 16);
        Position pos1 = new Position(14, 16);
        Position pos2 = new Position(14, 15);
        Position pos3 = new Position(14, 14);

        board.addParcelle(green1, posG);
        board.addParcelle(pink1, posP);
        board.addParcelle(yellow1, posY);
        board.addParcelle(green0, pos1);
        board.addParcelle(pink0, pos2);
        board.addParcelle(yellow0, pos3);
        green0.setNbBamboo(0);
        pink0.setNbBamboo(0);
        yellow0.setNbBamboo(0);
        Objectif objG = new ObjectifPanda(1,new Couleur[]{Couleur.VERT},3);
        Objectif objP = new ObjectifPanda(1,new Couleur[]{Couleur.ROSE},3);
        Objectif objY = new ObjectifPanda(1,new Couleur[]{Couleur.JAUNE},3);
        Objectif objM = new ObjectifPanda(1,Couleur.values(),3);
        Objectif[] obj = {objG,objP,objY,objM};
        Position[] pos = {posG,posP,posY};

        for(int i=0;i<4;i++){
            player.objectifs = new ArrayList<>();
            player.objectifs.add(obj[i]);
            Position selected = player.deplacerPanda(Set.of(pos1, posG, pos2, posP, pos3, posY));
            if(i<3){
                assertEquals(pos[i],selected);
            }else{
                boolean found = false;
                for(Position possible: pos){
                    if(possible == selected) found = true;
                }
                assertTrue(found);
            }
        }

    }

    @Test
    void piocherPoserParcelle(){
        Objectif objG= new ObjectifParcelle(Shape.LIGNE,new Couleur[]{Couleur.VERT});
        Parcelle parcelleG = new Parcelle(Couleur.VERT);
        Parcelle parcelleR = new Parcelle(Couleur.ROSE);
        ArrayList<Parcelle> pioche = new ArrayList<>();
        pioche.add(parcelleG);
        pioche.add(parcelleR);
        player.objectifs.add(objG);
        Parcelle found;

        assertEquals(2,pioche.size());

        found = player.piocherParcelle(pioche);
        pioche.remove(found);
        player.poserParcelle(found);

        assertEquals(1,pioche.size());
        assertTrue(pioche.contains(parcelleR));
        assertEquals(2,board.getParcellePosee().size());

        Boolean ok = false;
        for(Position pos : board.getParcellePosee()){
            if (board.getParcelle(pos).equals(parcelleG)){
                ok=true;
            }
        }
        assertTrue(ok);

        found = player.piocherParcelle(pioche);
        pioche.remove(found);
        player.poserParcelle(found);
        assertEquals(0,pioche.size());
        assertEquals(3,board.getParcellePosee().size());

        ok = false;
        for(Position pos : board.getParcellePosee()){
            if (board.getParcelle(pos).equals(parcelleR)){
                ok=true;
            }
        }
        assertTrue(ok);
    }

    @Test
    void jouerTests() {
        ArrayList<Action> actions = new ArrayList<>(Arrays.asList(Action.values()));

        assertEquals(Action.OBJECTIFS ,player.jouer(actions));
        actions.remove(Action.OBJECTIFS);
        assertEquals(Action.PIOCHER_CANAL_DIRRIGATION ,player.jouer(actions));
        actions.remove(Action.PIOCHER_CANAL_DIRRIGATION);
        assertEquals(Action.POSER_CANAL_DIRRIGATION ,player.jouer(actions));
        actions.remove(Action.POSER_CANAL_DIRRIGATION);
        assertEquals(Action.PIOCHER_PARCELLES ,player.jouer(actions));
        actions.remove(Action.PIOCHER_PARCELLES);

        for(int i=0;i<actions.size();i++){
            Action act = player.jouer(actions);
            assertTrue(actions.contains(act));
            actions.remove(act);
        }
    }


    @Test
    void choisirMeteoTests(){
        ArrayList<Meteo> meteos = new ArrayList<>(Arrays.asList(Meteo.values()));
        meteos.remove(Meteo.CHOIX_LIBRE);
        player.addObjectif(new ObjectifPanda(4,Couleur.ROSE,3));
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        assertEquals(player.choisirMeteo(meteos),Meteo.NUAGES);
        meteos.remove(Meteo.NUAGES);
        assertEquals(player.choisirMeteo(meteos),Meteo.ORAGE);
        player.objectifs.remove(0);
        assertEquals(player.choisirMeteo(meteos),Meteo.PLUIE);
    }

    @Test
    void choisirObj(){
        List<Class<? extends Objectif>> ordre = List.of(ObjectifPanda.class, ObjectifPanda.class, ObjectifJardinier.class, ObjectifJardinier.class, ObjectifParcelle.class);
        Objectif[] objToAdd = {new ObjectifPanda(1,Couleur.VERT,1), new ObjectifPanda(2,Couleur.ROSE,1), new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.JAUNE), new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.JAUNE), new ObjectifParcelle(Shape.LIGNE,Couleur.ROSE)};

        for(int i=0;i<3;i++){
            assertEquals(ObjectifParcelle.class,player.choisirObjectif(List.of(ObjectifParcelle.class,ObjectifJardinier.class,ObjectifPanda.class)));
            player.addObjectif(new ObjectifParcelle(Shape.LIGNE,Couleur.ROSE));
        }
        assertEquals(ObjectifPanda.class,player.choisirObjectif(List.of(ObjectifParcelle.class,ObjectifJardinier.class,ObjectifPanda.class)));

        for(int i=0;i<8;i++) board.addParcelle(new Parcelle(),new Position(15-i,15+i));
        player.objectifs = new ArrayList<>();

        for(int i=0;i<5;i++){
            assertEquals(ordre.get(i),player.choisirObjectif(List.of(ObjectifParcelle.class,ObjectifJardinier.class,ObjectifPanda.class)));
            player.addObjectif(objToAdd[i]);
        }

        assertEquals(ObjectifParcelle.class,player.choisirObjectif(List.of(ObjectifParcelle.class)));
        assertEquals(ObjectifJardinier.class,player.choisirObjectif(List.of(ObjectifJardinier.class)));
        assertEquals(ObjectifPanda.class,player.choisirObjectif(List.of(ObjectifPanda.class)));
    }

    @Test
    void choisirAmenagement(){
        Parcelle parcelleIrrigue = new Parcelle(Couleur.VERT); parcelleIrrigue.irrigue();
        Parcelle parcelleVide = new Parcelle(Couleur.JAUNE);
        Amenagement bassin = new Amenagement(AmenagementType.BASSIN);
        Amenagement enclos = new Amenagement(AmenagementType.ENCLOS);
        Amenagement engrais = new Amenagement(AmenagementType.ENGRAIS);
        ArrayList<Amenagement> amenagementBassin = new ArrayList<>();
        ArrayList<Amenagement> amenagementEnclos = new ArrayList<>();
        ArrayList<Amenagement> amenagementEngrais = new ArrayList<>();
        ArrayList<Amenagement> tousAmenagement = new ArrayList<>();
        amenagementBassin.add(bassin); tousAmenagement.add(bassin);
        amenagementEnclos.add(enclos); tousAmenagement.add(enclos);
        amenagementEngrais.add(engrais); tousAmenagement.add(engrais);




        //obj Panda
        player.addObjectif(new ObjectifPanda(1,Couleur.ROSE,1));
        assertEquals(enclos,player.choisirAmenagement(amenagementEnclos,parcelleVide));
        player.addObjectif(new ObjectifPanda(1,Couleur.JAUNE,1));
        assertEquals(bassin,player.choisirAmenagement(tousAmenagement,parcelleVide));
        player.addObjectif(new ObjectifPanda(1,Couleur.VERT,1));
        assertEquals(engrais,player.choisirAmenagement(tousAmenagement,parcelleIrrigue));

        player.objectifs = new ArrayList<>();
        //obj Jardinier
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJVIDE,Couleur.ROSE));
        assertEquals(enclos,player.choisirAmenagement(tousAmenagement,parcelleVide));
        assertEquals(engrais,player.choisirAmenagement(amenagementEngrais,parcelleIrrigue));
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        assertEquals(bassin,player.choisirAmenagement(tousAmenagement,parcelleIrrigue));
    }

    @Test
    void choisirParcelleAPousserTests(){
        Set<Position> positions = new HashSet<>();
        Parcelle pV = new Parcelle(Couleur.VERT);
        Parcelle pR = new Parcelle(Couleur.ROSE);
        Position p1 = new Position(15,16);
        Position p2 = new Position(15,14);
        positions.add(p1);
        board.addParcelle(pV, p1);
        assertEquals(player.choisirParcelleAPousser(positions),new Position(15,16));
        player.addObjectif(new ObjectifParcelle(Shape.LIGNE,Couleur.ROSE));
        positions.add(p2);
        board.addParcelle(pR, p2);
        assertEquals(player.choisirParcelleAPousser(positions),new Position(15,14));
    }

    @Test
    void evalueIrrigationTest(){
        setupBoardIrrigation();
        Bordure borderWeak = new Bordure(new Position(15,5),new Position(16,5));
        Bordure borderStrong = new Bordure(new Position(15,7),new Position(16,7));
        assertEquals(1,player.evalueIrrigation(borderWeak));
        assertEquals(4,player.evalueIrrigation(borderStrong));
    }

    @Test
    void placerIrrigation(){
        setupBoardIrrigation();
        Bordure borderWeak = new Bordure(new Position(15,5),new Position(16,5));
        Bordure borderStrong = new Bordure(new Position(15,7),new Position(16,7));

        HashSet<Bordure> dispo = new HashSet<>();
        dispo.add(borderWeak);
        dispo.add(borderStrong);
        board.setBordureDisponible(dispo);

        player.ajouteIrrigation();
        player.placerIrrigation();
        assertTrue(board.getBordurePosee().contains(borderStrong));
        assertFalse(board.getBordurePosee().contains(borderWeak));

    }
    @Test
    void placerMoulteIrrigation(){
        setupBoardIrrigation();
        Bordure borderWeak = new Bordure(new Position(15,5),new Position(16,5));
        Bordure borderStrong1 = new Bordure(new Position(15,7),new Position(16,7));
        Bordure borderStrong2 = new Bordure(new Position(15,9),new Position(16,9));
        HashSet<Bordure> dispo = new HashSet<>();
        dispo.add(borderWeak);
        dispo.add(borderStrong1);
        dispo.add(borderStrong2);
        board.setBordureDisponible(dispo);

        player.ajouteIrrigation();
        player.ajouteIrrigation();
        Parcelle jaune = new Parcelle(Couleur.JAUNE);
        Parcelle rose = new Parcelle(Couleur.ROSE); rose.irrigue();
        board.addParcelle(jaune, new Position(15,9));
        board.addParcelle(rose, new Position(16,9));

        player.placerIrrigation();
        assertTrue(board.getBordurePosee().contains(borderStrong1));
        assertTrue(board.getBordurePosee().contains(borderStrong2));
        assertFalse(board.getBordurePosee().contains(borderWeak));
    }

    void setupBoardIrrigation(){

        Parcelle vert = new Parcelle(Couleur.VERT);
        Parcelle jauneNI = new Parcelle(Couleur.JAUNE);
        Parcelle jauneIr = new Parcelle(Couleur.JAUNE);
        Parcelle rose = new Parcelle(Couleur.ROSE);
        jauneIr.irrigue();

        board.addParcelle(vert, new Position(15,5));
        board.addParcelle(jauneIr, new Position(16,5));

        board.addParcelle(jauneNI, new Position(15,7));
        board.addParcelle(rose, new Position(16,7));


        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJVIDE, Couleur.JAUNE));
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJVIDE, Couleur.JAUNE));
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJVIDE, Couleur.JAUNE));
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJVIDE, Couleur.JAUNE));
        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJVIDE, Couleur.VERT));

    }


    @Test
    void choisirPositionAmenagement(){
        Position[] positions = new Position[]{
                new Position(15,14),
                new Position(16,15),
                new Position(15,16),
                new Position(14,16),
                new Position(14,15),
                new Position(14,14)
        };
        Couleur[] couleurs = new Couleur[]{
                Couleur.ROSE,
                Couleur.ROSE,
                Couleur.JAUNE,
                Couleur.ROSE,
                Couleur.JAUNE,
                Couleur.ROSE
        };
        for(int i=0;i<positions.length;i++)board.addParcelle(new Parcelle(couleurs[i]),positions[i]);
        /*
        player.addObjectif(new ObjectifPanda(1,Couleur.ROSE,1));
        assertEquals(new ChoixAmenagement(new Amenagement(Amenagement)), player.choisirPositionAmenagement(posSet,));


        player.addObjectif(new ObjectifJardinier(TypeObjJardinier.OBJBASSIN,Couleur.ROSE));
        player.choisirPositionAmenagement(posSet,);

        Set<Position> posSet = Set.of(positions);

         */
    }

}