package game;

import board.Board;
import player.Player;

public abstract class Game {
    private Player winPlayer;
    private int turn;
    private Board board;


    public Player getWinPlayer() {
        return winPlayer;
    }

    public void setWinPlayer(Player winPlayer) {
        this.winPlayer = winPlayer;
    }

    public Game() {
        this.winPlayer = null;
    }

    public abstract void setup();

    public abstract void run();

    public abstract boolean evaluate(Board board);
}