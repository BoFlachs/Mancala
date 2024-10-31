package mancala.domain;

import java.util.stream.Stream;

public class Kalaha extends Bowl {
    Kalaha(int bowlNumber, Player thisPlayer, Pit firstPit, int numberOfFirstStones) {
        super(thisPlayer);
        switch (bowlNumber) {
            case 7:
                setNeighbour(new Pit(++bowlNumber, thisPlayer.getOppositePlayer(), firstPit, numberOfFirstStones));
                break;
            case 14:
                setNeighbour(firstPit);
                break;
        }
    }

    @Override
    void receiveStonesAndDecideWhatToDo(int numberOfStones) {
        if (getPlayer().isActive()) {
            addStones(1);

            if (numberOfStones > 1) {
                getNeighbour().receiveStonesAndDecideWhatToDo(--numberOfStones);
            }
        } else{
            getNeighbour().receiveStonesAndDecideWhatToDo(numberOfStones);
        }
    }

    public int endGameAndGetScore() {
        Pit firstOwnPit = (Pit) Stream.iterate(this, Bowl::getNeighbour)
                .skip(8)
                .findFirst()
                .orElse(null);

        Stream.iterate(firstOwnPit, Bowl::getNeighbour)
                .limit(6)
                .forEach(pit -> ((Pit) pit).giveAllToKalaha(this));

        return getNumberOfStones();
    }
}
