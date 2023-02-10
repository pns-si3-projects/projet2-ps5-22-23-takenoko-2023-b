package groupeb.takenoko.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import groupeb.takenoko.plateau.Parcelle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleListTest {

    private ParcelleList parcelleList;

    @BeforeEach
    void setUp() {
        parcelleList = new ParcelleList();
    }

    @Test
    void getParcelles() {
        assertEquals(27, parcelleList.size());
        assertEquals(10, parcelleList.getParcelles(10).size());
        List<Parcelle> parcelles = parcelleList.getParcelles(10);
        for(Parcelle p : parcelles){
            assertFalse(parcelleList.contains(p));
        }
        assertEquals(7, parcelleList.size());
    }

    @Test
    void addAtEnd() {
        ParcelleList parcelleList2 = new ParcelleList();
        List<Parcelle> parcelles = parcelleList.getParcelles(10);
        parcelleList2.addAtEnd(parcelles);
        assertEquals(37, parcelleList2.size());
    }
}