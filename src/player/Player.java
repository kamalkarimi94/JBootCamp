package player;

import game.WorldModel;
import game.action.Action;

public abstract class Player {
    private String name;
    //private int pieceId;
    //private boolean turn;
   // private boolean isHuman;
    //private int cntBlock;

    public Player(String name) {
        this.name = name;
        //this.pieceId = pieceId;
        //this.turn = turn;
       // this.isHuman = isHuman;
        //this.cntBlock = cntBlock;
    }

    public abstract Action nextAction(WorldModel worldModel);

    /*public abstract int nextMove();

    public abstract int blocking();

    public abstract int blockPos();*/

   // public abstract int nextMoveLeftOrRight();

    //public boolean isTurn() {
//        return turn;
//    }

//    public void setTurn(boolean turn) {
//        this.turn = turn;
//    }


    public String getName() {
        return name;
    }

//    public int getPieceId() {
//        return pieceId;
//    }

//    public boolean isHuman() {
//        return isHuman;
//    }

//    public int getCntBlock() {
//        return cntBlock;
//    }
//
//    public void setCntBlock(int cntBlock) {
//        this.cntBlock = cntBlock;
//    }
}
