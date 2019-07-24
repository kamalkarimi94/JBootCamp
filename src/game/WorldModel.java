package game;

import board.Board;
import game.action.Action;
import game.action.ActionType;

public class WorldModel implements Cloneable{
    private Board board;
    private int turn;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    Board getBoard(){
        return null;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getCurrentPosition(int id){
        return board.getPiece(id).getPosition();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }



}
