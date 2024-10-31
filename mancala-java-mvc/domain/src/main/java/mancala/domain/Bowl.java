package mancala.domain;

abstract public class Bowl {
    private int stones = 0;
    private final Player player;
    private Bowl neighbour;

    Bowl(Player thisPlayer){
        this.player = thisPlayer;
    }

    public int getNumberOfStones() {
        return stones;
    }

    void addStones(int extraStones){
       this.stones += extraStones;
    }

    void removeAllStones(){
        this.stones = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public Bowl getNeighbour() {
        return neighbour;
    }

    void setNeighbour(Bowl neighbour) {
        this.neighbour = neighbour;
    }

    abstract void receiveStonesAndDecideWhatToDo(int numberOfStones);
}

