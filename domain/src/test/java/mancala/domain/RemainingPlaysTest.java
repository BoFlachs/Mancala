package mancala.domain;

import mancala.domain.exceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemainingPlaysTest {
    @Test
    public void pitsAlwaysAddAStoneWhenReceived() {
        Pit pit1 = new Pit();

        pit1.receiveStonesAndDecideWhatToDo(2);

        assertEquals(5, pit1.getNumberOfStones());
    }

    @Test
    public void pitPassesStonesAlongIfAnyLeft() {
        Pit pit1 = new Pit();
        Pit pit2 = (Pit) pit1.getNeighbour();

        pit1.receiveStonesAndDecideWhatToDo(2);

        assertEquals(5, pit2.getNumberOfStones());
    }

    @Test
    public void kalahaAlwaysAddAStoneWhenActive() {
        Pit pit1 = new Pit();
        Kalaha kalaha = pit1.getKalaha();

        kalaha.receiveStonesAndDecideWhatToDo(2);

        assertEquals(1, kalaha.getNumberOfStones());
    }

    @Test
    public void kalahaPassesStonesAlongIfAnyLeft() {
        Pit pit1 = new Pit();
        Kalaha kalaha = pit1.getKalaha();
        Pit pit7 = (Pit) kalaha.getNeighbour();

        kalaha.receiveStonesAndDecideWhatToDo(2);

        assertEquals(5, pit7.getNumberOfStones());
    }

    @Test
    public void testChangeTurn() {
        Pit pit1 = new Pit();
        Player player1 = pit1.getPlayer();
        player1.changeTurn();

        assertTrue(player1.getOppositePlayer().isActive());
    }

    @Test
    public void pitLastStoneReceivedShouldChangeTurns() {
        Pit pit1 = new Pit();
        Player player1 = pit1.getPlayer();
        Player player2 = player1.getOppositePlayer();

        pit1.receiveStonesAndDecideWhatToDo(1);

        assertTrue(player2.isActive());
        assertFalse(player1.isActive());
    }

    @Test
    public void kalahaLastStoneReceivedShouldNotChangeTurns() {
        Pit pit1 = new Pit();
        Player player1 = pit1.getPlayer();
        Player player2 = player1.getOppositePlayer();
        Kalaha kalaha1 = pit1.getKalaha();

        kalaha1.receiveStonesAndDecideWhatToDo(1);

        assertTrue(player1.isActive());
        assertFalse(player2.isActive());
    }

    @Test
    public void lastStoneReceivedEmptyButNonActiveShouldHaveNoEffect() {
        Pit pit1 = new Pit(0);
        Player player1 = pit1.getPlayer();
        player1.changeTurn();

        pit1.receiveStonesAndDecideWhatToDo(1);

        assertEquals(1, pit1.getNumberOfStones());
    }

    @Test
    public void inActiveKalahaShouldPassStoneAlong(){
        Pit pit1 = new Pit(13);

        Kalaha kalaha2 = pit1.findOppositePit().getKalaha();

        pit1.play();

        assertEquals(0, kalaha2.getNumberOfStones());
    }

    @Test
    public void playPitAddsOneToIntermediaryPits() {
        Pit pit1 = new Pit();
        Pit pit4 = (Pit) pit1.getNeighbour(3);

        assert pit4 != null;
        Pit pit5 = (Pit) pit4.getNeighbour();
        Pit pit6 = (Pit) pit5.getNeighbour();
        Kalaha kalaha1 = (Kalaha) pit6.getNeighbour();
        Pit pit7 = (Pit) kalaha1.getNeighbour();

        pit4.play();

        assertEquals(5, pit5.getNumberOfStones());
        assertEquals(5, pit6.getNumberOfStones());
        assertEquals(1, kalaha1.getNumberOfStones());
        assertEquals(5, pit7.getNumberOfStones());
    }

    @Test
    public void playPitEmptiesThePit() {
        Pit pit1 = new Pit();

        pit1.play();

        assertEquals(0, pit1.getNumberOfStones());
    }

    @Test
    public void playEmptyPitThrowsInvalidMoveException(){
        Pit pit1 = new Pit(0);

        assertThrows(InvalidMoveException.class, pit1::play);
    }

    @Test
    public void playInactivePitThrowsInvalidMoveException(){
        Pit pit1 = new Pit();
        pit1.getPlayer().setActive(false);

        assertThrows(InvalidMoveException.class, pit1::play);
    }

    @Test
    public void getZeroNeighbourIsPitItself(){
        Pit pit1 = new Pit();
        Bowl bowl = pit1.getNeighbour(0);

        assertEquals(pit1, bowl);
    }
}