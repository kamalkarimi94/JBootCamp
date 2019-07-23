package player;

public abstract class Player {

    private String name;
    private int pieceId;
    private boolean turn;
    private boolean isHuman;
    private int cntBlock;

    public Player(String name, int pieceId, boolean turn, boolean isHuman, int cntBlock) {
        this.name = name;
        this.pieceId = pieceId;
        this.turn = turn;
        this.isHuman = isHuman;
        this.cntBlock = cntBlock;
    }

    public abstract int nextMoveLeftOrRight();
    public abstract int nextAction();
    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPieceId() {
        return pieceId;
    }

    public void setPieceId(int position) {
        this.pieceId = position;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public int getCntBlock() {
        return cntBlock;
    }

    public void setCntBlock(int cntBlock) {
        this.cntBlock = cntBlock;
    }

    public abstract int nextMove();

    public abstract int blocking();
    public abstract int blockPos();


}
