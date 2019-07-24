package game;

import board.Board;
import game.action.Action;
import game.action.ActionType;

public class WorldModel implements Cloneable{
    Board board;
    Board getBoard(){
        return null;
    }

    public int getCurrentPosition(int id){
        return board.getPiece(id).getPosition();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    void execute(int id,Action action){
        board = getBoard();
        if (action.getActionType()== ActionType.MOVE){
            board.changePositionPieceOnBoard(id,action.getPosition());
        }else if (action.getActionType() == ActionType.BLOCK){
            //add Wall
        }
    }
}
