package game.action;

import player.Player;

public class Block extends Action {
    private Player player;
    private int wallPos;
    private int wallDir;

    public Block(/*Player player,*/ int wallPos, int wallDir) {
        super(ActionType.BLOCK,wallPos);
        this.player = player;
        this.wallPos = wallPos;
        this.wallDir = wallDir;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getWallPos() {
        return wallPos;
    }

    public void setWallPos(int wallPos) {
        this.wallPos = wallPos;
    }

    public int getWallDir() {
        return wallDir;
    }

    public void setWallDir(int wallDir) {
        this.wallDir = wallDir;
    }
}
