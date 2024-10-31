package mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EndGameTest {
    @Test
    public void testGameHasEndedOnPit1EmptyBoard() {
        Pit pit1 = new Pit(0);

        assertTrue(pit1.hasGameEnded());
    }

    @Test
    public void testGameHasEndedOnPit5EmptyBoard() {
        Pit pit1 = new Pit(0);
        Pit pit5 = (Pit) pit1.getNeighbour(4);

        assertTrue(pit5.hasGameEnded());
    }

    @Test
    public void testGameHasEndedOnPit10EmptyBoard() {
        Pit pit1 = new Pit(0);
        Pit pit10 = (Pit) pit1.getNeighbour(10);

        assertTrue(pit10.hasGameEnded());
    }

    @Test
    public void testGameHasEndedOnPit5NonEmptyOppositeSide() {
        Pit pit1 = new Pit(2);
        Player player1 = pit1.getPlayer();
        pit1.play();
        for (int i = 1; i < 6; i++) {
            player1.setActive(true);
            Pit nextPit = (Pit) pit1.getNeighbour(i);
            nextPit.play();
        }
        player1.setActive(true);
        Pit pit5 = (Pit) pit1.getNeighbour(4);

        assertTrue(pit5.hasGameEnded());
    }

    @Test
    public void testGameHasNotEndedWhenEmptySideIsNonActive() {
        Pit pit1 = new Pit();
        Player player1 = pit1.getPlayer();
        pit1.play();
        for (int i = 1; i < 6; i++) {
            player1.setActive(true);
            Pit nextPit = (Pit) pit1.getNeighbour(i);
            nextPit.play();
        }
        Pit pit5 = (Pit) pit1.getNeighbour(4);

        player1.setActive(false);

        assertFalse(pit5.hasGameEnded());
    }

    @Test
    public void testEndGameAndGetScoreGetsAllRemainingStones() {
        Pit pit1 = new Pit(2);
        Player player1 = pit1.getPlayer();
        pit1.play();
        for (int i = 1; i < 6; i++) {
            player1.setActive(true);
            Pit nextPit = (Pit) pit1.getNeighbour(i);
            nextPit.play();
        }
        player1.setActive(false);
        Kalaha kalaha2 = pit1.findOppositePit().getKalaha();

        assertEquals(20, kalaha2.endGameAndGetScore());
    }

}
