package mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializationTest {
    @Test
    public void newPitShouldHaveFourStones() {
        Pit pit1 = new Pit();
        assertEquals(4, pit1.getNumberOfStones());
    }

    @Test
    public void getPlayerFirstPitShouldReturnPlayerOne() {
        Pit pit1 = new Pit();
        assertEquals("Player 1", pit1.getPlayer().getName());
    }

    @Test
    public void firstPitCreatesNeighbour() {
        Pit pit1 = new Pit();

        assertNotNull(pit1.getNeighbour());
    }

    @Test
    public void firstFivePitsCreateNeighbourPit() {
        Pit pit1 = new Pit();
        Bowl bowl6 = pit1.getNeighbour(5);

        assertInstanceOf(Pit.class, bowl6);
    }

    @Test
    public void sixthPitCreatesNeighbourKalaha() {
        Pit pit1 = new Pit();
        Bowl kalaha = pit1.getNeighbour(6);

        assertInstanceOf(Kalaha.class, kalaha);
    }

    @Test
    public void pitsSevenToElevenCreateNeighbourPit() {
        Pit pit1 = new Pit();

        Bowl bowl13 = pit1.getNeighbour(12);

        assertInstanceOf(Pit.class, bowl13);
    }

    @Test
    public void twelfthPitCreatesNeighbourKalaha() {
        Pit pit1 = new Pit();

        Bowl kalaha = pit1.getNeighbour(13);

        assertInstanceOf(Kalaha.class, kalaha);
    }

    @Test
    public void getPlayerPitTwelveShouldReturnPlayerTwo() {
        Pit pit1 = new Pit();

        Bowl pit12 = pit1.getNeighbour(11);

        assert pit12 != null;
        assertEquals("Player 2", pit12.getPlayer().getName());
    }

    @Test
    public void getKahalaReturnsRelevantKalaha() {
        Pit pit1 = new Pit();

        Bowl kalaha = pit1.getKalaha();

        assertInstanceOf(Kalaha.class, kalaha);
        assertEquals("Player 1", kalaha.getPlayer().getName());
    }

    @Test
    public void firstKalahaCreatesNeighbourPit() {
        Pit pit1 = new Pit();
        Bowl bowl8 = pit1.getNeighbour(7);

        assertInstanceOf(Pit.class, bowl8);
    }

    @Test
    public void getPlayerFirstKalahaShouldReturnPlayerOne() {
        Pit pit1 = new Pit();
        Bowl kalaha = pit1.getNeighbour(6);

        assert kalaha != null;
        assertEquals("Player 1", kalaha.getPlayer().getName());
    }

    @Test
    public void getPlayerSecondKalahaShouldReturnPlayerTwo() {
        Pit pit1 = new Pit();
        Bowl kalaha = pit1.getNeighbour(13);

        assert kalaha != null;
        assertEquals("Player 2", kalaha.getPlayer().getName());
    }

    @Test
    public void secondKalahaGetsFirstPitAsNeighbour() {
        Pit pit1 = new Pit();
        Bowl kalaha = pit1.getNeighbour(13);

        assert kalaha != null;
        assertEquals(pit1, kalaha.getNeighbour());
    }

    @Test
    public void activePlayerIsActive() {
        Player player1 = new Player();
        player1.setActive(true);

        assertTrue(player1.isActive());
    }

    @Test
    public void getOppositePlayer() {
        Pit pit1 = new Pit();

        assertEquals("Player 2", pit1.getPlayer().getOppositePlayer().getName());
    }

    @Test
    public void testGetNeighbourWithParameter() {
        Pit pit1 = new Pit();

        assertEquals(pit1.getNeighbour(), pit1.getNeighbour(1));
        assertEquals(pit1, pit1.getNeighbour(14));
        assertInstanceOf(Kalaha.class, pit1.getNeighbour(6));
        assertEquals("Player 2", pit1.getNeighbour(9).getPlayer().getName());
    }
}
