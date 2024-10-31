package mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StealPlayTest {
    @Test
    public void testGiveAllToKalahaForOwnKalaha() {
        Pit pit1 = new Pit();
        Kalaha kalaha1 = pit1.getKalaha();

        pit1.giveAllToKalaha(kalaha1);

        assertEquals(4, kalaha1.getNumberOfStones());
        assertEquals(0, pit1.getNumberOfStones());
    }

    @Test
    public void testGiveAllToKalahaForOppositeKalaha() {
        Pit pit1 = new Pit();
        Kalaha kalaha2 = (Kalaha) pit1.getNeighbour(13);

        pit1.giveAllToKalaha(kalaha2);

        assertEquals(4, kalaha2.getNumberOfStones());
        assertEquals(0, pit1.getNumberOfStones());
    }

    @Test
    public void testFindOppositePit() {
        Pit pit1 = new Pit();
        Pit pit5 = (Pit) pit1.getNeighbour(4);
        Pit pit8 = (Pit) pit1.getNeighbour(8);
        Pit pit12 = (Pit) pit1.getNeighbour(12);

        assertEquals(pit12, pit1.findOppositePit());
        assertEquals(pit1, pit12.findOppositePit());
        assertEquals(pit8, pit5.findOppositePit());
        assertEquals(pit5, pit8.findOppositePit());
    }

    @Test
    public void stealRemovesStonesFromPitAndOpposite(){
        Pit pit1 = new Pit();
        Pit pit12 = pit1.findOppositePit();

        pit1.handleSteal();

        assertEquals(0, pit1.getNumberOfStones());
        assertEquals(0, pit12.getNumberOfStones());
    }

    @Test
    public void stealAddsStonesToKalaha(){
        Pit pit1 = new Pit();
        Kalaha kalaha = pit1.getKalaha();

        pit1.handleSteal();

        assertEquals(8, kalaha.getNumberOfStones());
    }

    @Test void playEndingInActiveEmptyPitShouldAddStonesToKalaha(){
        Pit pit1 = new Pit();
        Player player1 = pit1.getPlayer();
        Pit pit5 = (Pit) pit1.getNeighbour(4);
        Kalaha kalaha = pit1.getKalaha();

        player1.setActive(true);
        pit5.play();
        player1.setActive(true);
        pit1.play();

        assertEquals(7, kalaha.getNumberOfStones());
    }

    @Test void playEndingInActiveEmptyPitShouldRemoveStonesFromPits(){
        Pit pit1 = new Pit();
        Player player1 = pit1.getPlayer();
        Pit pit5 = (Pit) pit1.getNeighbour(4);
        Pit pit8 = pit5.findOppositePit();

        player1.setActive(true);
        pit5.play();
        player1.setActive(true);
        pit1.play();

        assertEquals(0, pit5.getNumberOfStones());
        assertEquals(0, pit8.getNumberOfStones());
    }
}
