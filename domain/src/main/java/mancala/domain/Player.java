package mancala.domain;

public class Player {
    private boolean active;
    private final Player oppositePlayer;
    private final String name;

    Player(){
        oppositePlayer = new Player(this);
        active = true;
        name = "Player 1";
    }

    private Player(Player oppositePlayer){
       this.oppositePlayer = oppositePlayer;
       active = false;
        name = "Player 2";
    }

    public String getName() {
        return name;
    }

    void setActive(Boolean bool) {
        active = bool;
        oppositePlayer.changeActive();
    }

    void changeActive(){
        active = !active;
    }

    public boolean isActive() {
        return active;
    }

    public Player getOppositePlayer() {
        return oppositePlayer;
    }

    void changeTurn() {
        active = !active;
        oppositePlayer.active = !oppositePlayer.active;
    }
}
