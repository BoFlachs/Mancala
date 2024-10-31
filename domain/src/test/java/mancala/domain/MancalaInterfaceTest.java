package mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import mancala.domain.exceptions.InvalidPlayerNameException;

public class MancalaInterfaceTest {
   @Test
   public void isPlayersTurnForPlayerOne(){
       Mancala mancala = new Mancala("player1", "player2");
       assert(mancala.isPlayersTurn("player1"));
   }

    @Test
    public void isPlayersTurnForInactivePlayerTwo(){
        Mancala mancala = new Mancala("player1", "player2");
        assertFalse(mancala.isPlayersTurn("player2"));
    }

    @Test
    public void isPlayersTurnThrowsErrorForWrongName(){
        Mancala mancala = new Mancala("player1", "player2");
        assertThrows(InvalidPlayerNameException.class,
                () -> mancala.isPlayersTurn("player3"));
    }

    @Test
    public void playPitIndexesCorrectly(){
        Mancala mancala = new Mancala("player1", "player2");
        mancala.playPit(0);
        assertEquals(0, mancala.getStonesForPit(0));
        assertEquals(5, mancala.getStonesForPit(3));
    }

    @Test
    public void testGetWinner(){
        Mancala mancala = new Mancala("player1", "player2");
        mancala.playPit(2);
        mancala.playPit(5);
        mancala.playPit(8);
        mancala.playPit(9);
        mancala.playPit(0);
        mancala.playPit(10);
        mancala.playPit(0);
        mancala.playPit(11);
        mancala.playPit(0);
        mancala.playPit(12);
        mancala.playPit(0);
        assert(mancala.isEndOfGame());
        assertEquals(IMancala.Winner.PLAYER_1, mancala.getWinner());
    }
}
