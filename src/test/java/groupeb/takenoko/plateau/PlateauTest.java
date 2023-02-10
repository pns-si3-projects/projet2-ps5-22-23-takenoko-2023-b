package groupeb.takenoko.plateau;

import groupeb.takenoko.bot.Bot;
import groupeb.takenoko.bot.BotRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import groupeb.takenoko.element.Amenagement;
import groupeb.takenoko.element.AmenagementType;
import groupeb.takenoko.jeu.Jeu;
import java.util.ArrayList;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    Plateau plateau;
    @BeforeEach
    void CreatePlateau() {
        plateau = new Plateau();
    }

    @Test
    void addParcelle() {
        Parcelle par = new Parcelle();
        Position pos = new Position(5,5);
        assertFalse(plateau.getParcelle(pos) instanceof Parcelle);
        plateau.addParcelle(par,pos);
        assertTrue(plateau.getParcelle(pos) instanceof Parcelle);
    }

    @Test
    void endroitsPosables() {
        Set<Position> endroitsPosables = plateau.getEndroitsPosables();
        for(int i=0; i<31; i++) {
            for(int j=0; j<31; j++) {
                if(endroitsPosables.contains(new Position(i,j))) {
                    assertTrue(plateau.positionPosable(new Position(i,j)));
                } else {
                    assertFalse(plateau.positionPosable(new Position(i,j)));
                }
            }
        }
    }

    @Test
    void getParcelle() {
        Position pos = new Position(5,5);
        Position posorigine = new Position(15,15);
        Parcelle par1 = new Parcelle();
        par1.setCouleur(Couleur.JAUNE);

        assertTrue(plateau.getParcelle(pos) instanceof ParcelleInactive);
        assertTrue(plateau.getParcelle(posorigine) instanceof ParcelleOriginelle);

        plateau.addParcelle(par1, pos);

        assertTrue(plateau.getParcelle(pos) instanceof Parcelle);
        assertTrue(((Parcelle) plateau.getParcelle(pos)).getCouleur() == Couleur.JAUNE);
    }

    @Test
    void PositionsPosable() {
        Position pos = new Position(0,0);
        Position posCoteCentre = new Position(14,15);
        assertFalse(plateau.positionPosable(pos));
        assertTrue(plateau.positionPosable(posCoteCentre));

        Parcelle par1 = new Parcelle();
        Parcelle par2 = new Parcelle();
        Position pos1 = new Position(1,0);
        Position pos2 = new Position(0,1);
        plateau.addParcelle(par1,pos1);
        plateau.addParcelle(par2,pos2);
        assertTrue(plateau.positionPosable(pos));
        assertFalse(plateau.positionPosable(pos1));
    }

    @Test
    void getConnectedParcelleSameColor() {
        try{
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,13));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(14,14));
            plateau.addParcelle(new Parcelle(Couleur.VERT),new Position(15,14));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(14,15));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(16,15));
            plateau.addParcelle(new Parcelle(Couleur.JAUNE),new Position(17,15));
            plateau.addParcelle(new Parcelle(Couleur.VERT),new Position(14,16));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,16));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(15,17));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(13,14));
            plateau.addParcelle(new Parcelle(Couleur.ROSE),new Position(16,16));

            ArrayList<Position> connectedParcelleSameColor = plateau.getConnectedParcelleSameColor(new Position(14,14));
            assertEquals(3,connectedParcelleSameColor.size());
            assertTrue(connectedParcelleSameColor.contains(new Position(15,13)));
            assertTrue(connectedParcelleSameColor.contains(new Position(14,14)));
            assertTrue(connectedParcelleSameColor.contains(new Position(13,14)));
            for(Position pos : connectedParcelleSameColor) {
                Parcelle par = (Parcelle) plateau.getParcelle(pos);
                assertTrue(par.getCouleur().equals(Couleur.ROSE));
            }
        }catch(Exception e){System.out.println(e);
        }
    }

    @Test
    void initialBorders(){
        BotRandom rdm0 = new BotRandom(0);
        BotRandom rdm1 = new BotRandom(11);
        ArrayList<Bot> players = new ArrayList<>();
        players.add(rdm0); players.add(rdm1);
        Jeu game = new Jeu(players);
        Plateau plateau = game.getPlateau();




        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(16,15));
        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(15,14));
        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(15,16));

        Position East = new Position(16,15);
        assertTrue(plateau.addBordure(East,East.getPositionByDirection(Direction.NORD_OUEST)));
        assertTrue(plateau.addBordure(East,East.getPositionByDirection(Direction.SUD_OUEST)));

        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(14,14));
        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(14,15));
        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(14,16));
        //System.out.println(game.affichePlateau());

        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(16,14));
        //System.out.println(game.affichePlateau());

        plateau.addParcelle(new Parcelle(Couleur.ROSE,1),new Position(17,15));
        //System.out.println(game.affichePlateau());

        assertTrue(plateau.addBordure(new Position(16,15),new Position(16,14)));
        //System.out.println(game.affichePlateau());
        int sum=0;
        for(Position p : plateau.getParcellePosee()){
            if(plateau.getParcelle(p) instanceof Parcelle parc) if (parc.estIrrigue()) sum++;

        }
        assertEquals(7,sum);
    }

    @Test
    void nextToOriginTest(){
        Position center = new Position(Plateau.getTaille()/2,Plateau.getTaille()/2);
        ArrayList<Position> adjacentCenter = new ArrayList<>();
        for (Direction dir : Direction.values()) adjacentCenter.add(center.getPositionByDirection(dir));

        for(int y=1;y<Plateau.getTaille()-1;y++) for(int x=1;x<Plateau.getTaille()-1;x++){
            Position current = new Position(x,y);
            if(adjacentCenter.contains(current)) assertTrue(plateau.nextToOrigin(current));
            else assertFalse(plateau.nextToOrigin(current));
        }
    }

    @Test
    void parcellesIrrigue(){
        plateau.addParcelle(new Parcelle(),new Position(14,14));
        plateau.addParcelle(new Parcelle(),new Position(15,14));
        plateau.addParcelle(new Parcelle(),new Position(16,15));
        plateau.addParcelle(new Parcelle(),new Position(15,13));
        plateau.addParcelle(new Parcelle(),new Position(16,14));

        plateau.addBordure(new Position(14,14), new Position(15,14));
        plateau.addBordure(new Position(15,14), Direction.NORD_OUEST);
        plateau.addBordure(new Position(15,14), Direction.SUD_EST);

        Set<Position> pos = plateau.getParcellesIrriguees();

        assertTrue(pos.contains(new Position(14,14)));
        assertTrue(pos.contains(new Position(15,14)));
        assertTrue(pos.contains(new Position(16,15)));
        assertTrue(pos.contains(new Position(15,13)));

        assertFalse(pos.contains(new Position(15,15)));
        assertFalse(pos.contains(new Position(16,14)));
        assertFalse(pos.contains(new Position(17,17)));

    }

    @Test
    void adjacentIrrigue(){
        plateau.addParcelle(new Parcelle(),new Position(14,14));
        plateau.addParcelle(new Parcelle(),new Position(15,14));
        plateau.addParcelle(new Parcelle(),new Position(16,15));
        plateau.addParcelle(new Parcelle(),new Position(15,13));
        plateau.addParcelle(new Parcelle(),new Position(16,13));
        Bordure borderClose = new Bordure(new Position(14,14),new Position(15,14));
        Bordure borderFar = new Bordure(new Position(15,14),Direction.NORD_OUEST);

        assertTrue(plateau.adjacentIrrigue(borderClose));
        assertFalse(plateau.adjacentIrrigue(borderFar));

        plateau.addBordure(borderClose);
        plateau.addBordure(borderFar);

        assertFalse(plateau.adjacentIrrigue(new Bordure(new Position(16,17),new Position(17,17))));
        assertTrue(plateau.adjacentIrrigue(borderFar));

    }

    @Test
    void parcellesPosable(){
        plateau.addParcelle(new Parcelle(), new Position(15,14));
        plateau.addParcelle(new Parcelle(), new Position(14,14));

        assertFalse(plateau.positionPosable(new Position(14,14)));
        assertFalse(plateau.positionPosable(new Position(15,14)));
        assertFalse(plateau.positionPosable(new Position(15,15)));
        assertFalse(plateau.positionPosable(new Position(17,17)));

        assertTrue(plateau.positionPosable(new Position(14,15)));
        assertTrue(plateau.positionPosable(new Position(16,15)));
        assertTrue(plateau.positionPosable(new Position(14,16)));
        assertTrue(plateau.positionPosable(new Position(15,16)));
        assertTrue(plateau.positionPosable(new Position(15,13)));
    }
    @Test
    void parcellesAmenageable() {
        Parcelle parcelle = new Parcelle(Couleur.JAUNE);
        Parcelle parcelleVide = new Parcelle(Couleur.ROSE);
        parcelle.setAmenagement(new Amenagement(AmenagementType.BASSIN));
        plateau.addParcelle(parcelle, new Position(16, 15));//Bassin
        parcelle.setAmenagement(new Amenagement(AmenagementType.ENCLOS));
        plateau.addParcelle(parcelle, new Position(15, 14));//Enclos
        parcelle.setAmenagement(new Amenagement(AmenagementType.ENGRAIS));
        plateau.addParcelle(parcelle, new Position(14, 14));//Engrais
        Position pos1 = new Position(16, 13);
        Position pos2 = new Position(15, 13);
        plateau.addParcelle(parcelleVide, pos1);//Vide non-Irrigue
        plateau.addParcelle(parcelleVide, pos2);//Vide Irrigue

        Set<Position> got = plateau.getParcellesAmenageables();
        assertTrue(got.contains(pos1));
        assertTrue(got.contains(pos2));
    }

    @Test
    void testDirection() {
        Direction dir = Direction.NORD_EST;
        assertEquals(dir.numDirection(), 0);
    }
}
