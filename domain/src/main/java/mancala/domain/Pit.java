package mancala.domain;

import mancala.domain.exceptions.InvalidMoveException;

import java.util.stream.Stream;

public class Pit extends Bowl {
    public Pit() {
        this(4);
    }

    // This constructor exists for testing purposes but should not be removed
    Pit(int numberOfFirstStones) {
        super(new Player());
        addStones(numberOfFirstStones);
        setNeighbour(new Pit(2, this.getPlayer(), this, numberOfFirstStones));
    }

    Pit(int bowlNumber, Player thisPlayer, Pit firstPit, int numberOfFirstStones) {
        super(thisPlayer);
        addStones(numberOfFirstStones);
        switch (bowlNumber) {
            case 2, 3, 4, 5, 8, 9, 10, 11, 12:
                setNeighbour(new Pit(++bowlNumber, thisPlayer, firstPit, numberOfFirstStones));
                break;
            case 6, 13:
                setNeighbour(new Kalaha(++bowlNumber, thisPlayer, firstPit, numberOfFirstStones));
                break;
        }
    }

    public void play() {
        validateMove();

        int oldNumberOfStones = getNumberOfStones();
        removeAllStones();

        getNeighbour().receiveStonesAndDecideWhatToDo(oldNumberOfStones);
    }

    private void validateMove() {
        if (!getPlayer().isActive()) {
            throw new InvalidMoveException("Only pits from the active player can be played");
        } else if (getNumberOfStones() == 0) {
            throw new InvalidMoveException("A pit needs stones to be played");
        }
    }

    @Override
    void receiveStonesAndDecideWhatToDo(int numberOfStones) {
        addStones(1);
        boolean shouldSteal = getNumberOfStones() == 1
                && numberOfStones == 1
                && getPlayer().isActive()
                && this.findOppositePit().getNumberOfStones() > 0;

        if (numberOfStones > 1) {
            getNeighbour().receiveStonesAndDecideWhatToDo(--numberOfStones);
        } else if (shouldSteal) {
            handleSteal();
            getPlayer().changeTurn();
        } else {
            getPlayer().changeTurn();
        }
    }

    void handleSteal() {
        Kalaha activeKalaha = getKalaha();
        Pit oppositePit = findOppositePit();

        this.giveAllToKalaha(activeKalaha);
        oppositePit.giveAllToKalaha(activeKalaha);
    }

    Kalaha getKalaha() {
        Bowl kalaha = Stream.iterate(this, Bowl::getNeighbour)
                .filter(e -> e.getClass().equals(Kalaha.class))
                .findFirst()
                .orElse(null);
        return (Kalaha) kalaha;
    }

    Pit findOppositePit() {
        long stepsToKalaha = Stream.iterate(this
                        , e -> e.getClass() != Kalaha.class
                        , Bowl::getNeighbour)
                .count();

        return (Pit) Stream.iterate(getKalaha(), Bowl::getNeighbour)
                .skip(stepsToKalaha)
                .findFirst()
                .orElse(null);
    }

    void giveAllToKalaha(Kalaha kalaha) {
        kalaha.addStones(getNumberOfStones());
        removeAllStones();
    }

    public boolean hasGameEnded() {
        Kalaha activeKalaha = this.getKalaha();
        Pit firstOppositePit = (Pit) activeKalaha.getNeighbour();
        if (getPlayer().isActive()) {
            Kalaha oppositeKalaha = firstOppositePit.getKalaha();
            Pit firstOwnPit = (Pit) oppositeKalaha.getNeighbour();
            return checkWholeSideIsEmpty(firstOwnPit);
        } else {
            return checkWholeSideIsEmpty(firstOppositePit);
        }
    }

    private boolean checkWholeSideIsEmpty(Pit firstPit) {
        return Stream.iterate(firstPit, Bowl::getNeighbour)
                .limit(6)
                .allMatch(pit -> pit.getNumberOfStones() == 0);
    }

    public Bowl getNeighbour(int n) {
        return Stream.iterate(this, Bowl::getNeighbour)
                .skip(n)
                .findFirst()
                .orElse(null);
    }

}
