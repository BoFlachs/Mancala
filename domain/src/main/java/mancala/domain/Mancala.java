package mancala.domain;

import mancala.domain.exceptions.InvalidMoveException;
import mancala.domain.exceptions.InvalidPlayerNameException;

public class Mancala implements IMancala {
    private final String namePlayer1;
    private final String namePlayer2;
    private final Pit firstPit;

    public Mancala(String namePlayer1, String namePlayer2) {
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.firstPit = new Pit();
    }

    @Override
    public String getNameOfPlayerOne() {
        return namePlayer1;
    }

    @Override
    public String getNameOfPlayerTwo() {
        return namePlayer2;
    }

    @Override
    public boolean isPlayersTurn(String name) {
        if (name == namePlayer1) {
            return firstPit.getPlayer().isActive();
        } else if (name == namePlayer2) {
            return firstPit.getPlayer().getOppositePlayer().isActive();
        } else {
            throw new InvalidPlayerNameException("This player does not exist");
        }
    }

    @Override
    public void playPit(int index) {
        if (index == 6 || index == 13) {
            throw new InvalidMoveException("You cannot play a kalaha");
        } else {
            Pit relevantPit = (Pit) firstPit.getNeighbour(index);
            relevantPit.play();
        }
        if(firstPit.hasGameEnded()){
            firstPit.getKalaha().endGameAndGetScore();
            firstPit.findOppositePit().getKalaha().endGameAndGetScore();
        }
    }

    @Override
    public int getStonesForPit(int index) {
        Bowl relevantBowl = firstPit.getNeighbour(index);
        return relevantBowl.getNumberOfStones();
    }

    @Override
    public boolean isEndOfGame() {
        return firstPit.hasGameEnded();
    }

    @Override
    public Winner getWinner() {
        if (!firstPit.hasGameEnded()) {
            return Winner.NO_ONE;
        }

        int player1Score = firstPit.getKalaha().getNumberOfStones();
        int player2Score = firstPit.findOppositePit().getKalaha().getNumberOfStones();

        if (player1Score > player2Score) {
            return Winner.PLAYER_1;
        } else if (player2Score > player1Score) {
            return Winner.PLAYER_2;
        } else {
            return Winner.DRAW;
        }
    }
}
