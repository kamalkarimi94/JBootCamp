package game;

import board.Board;
import board.Piece;
import game.action.Action;
import game.action.ActionType;
import game.action.Move;

public class ValidateAct {

    private Integer nextPosition;
    private Integer id;

    private Boolean checkMove(Board board, Move action) {
        boolean result = false;
        int currentPosition = action.getCurrentPosition();
        nextPosition = action.getPosition();

        if (isPositionInsideTheRange(nextPosition)) {
            result = simpleMove(board, currentPosition, nextPosition) ||
                     simpleJump(board,currentPosition,nextPosition) ||
                     complexJump(board,currentPosition,nextPosition);
        }
        return result;
    }

    private boolean simpleMove(Board board, int current, int next) {
            if (board.isPieceOnCellOfBoard(nextPosition)) {
                return board.isCellAdjWith(current, next);
            }
        return false;
    }

    private boolean simpleJump(Board board, int current, int next) {
        boolean result = false;
        int middle = (current + next) / 2;
            if (!board.isPieceOnCellOfBoard(middle)) {
                if (board.isCellAdjWith(current, middle) &&
                        board.isCellAdjWith(middle, nextPosition)) {
                    result = true;
                }
        }
        return result;
    }

    private boolean complexJump(Board board,int current , int next){
        boolean result = false;
        for (Piece piece: board.getPieces()){
            if (piece.getId() != id){
                int enemyPosition = piece.getPosition();
                int diff = (current - enemyPosition);
                if (!isPositionOnBoarder(current,enemyPosition)){
                    if ( Math.abs(diff) == 9 || Math.abs(diff) == 1){
                        if (board.isEdgeBetween(enemyPosition,enemyPosition+diff)){
                            if (board.isCellAdjWith(enemyPosition,next)){
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean isPositionInsideTheRange(int pos) {
        return pos >= 0 && pos < 81;
    }


    private boolean isPositionOnBoarder(int current,int enemy){
        boolean result = false;
        int difference = Math.abs(enemy - current);
        if (difference == 1){
            if (((enemy+1)%9 == 0) || (enemy%9 == 0))
                result = true;
        }
        if (difference == 9){
            if ((enemy>=0 && enemy<=8) || (enemy<81 && enemy>71))
                result = true;
        }

        return result;
    }

   /* boolean checkBlock(Board board, Block action) {
        int start = action.getWallPos();
        if (action.getPlayer().getCntBlock() > 0) {
            if (start > 8 && start < 80 && (start + 1) % 9 != 0) {
                if (action.getWallDir() == 1) {
                    if ((board.isEdgeBetween(start, start + 1)
                            || board.isEdgeBetween(start - 9, start - 8))
                            && board.isEdgeBetween(start + 1, start - 8))
                        return board.isEdgeBetween(start, start - 9);
                } else if (action.getWallDir() == 2) {
                    if ((board.isEdgeBetween(start, start - 9) || board.isEdgeBetween(start + 1, start - 8)) && board.isEdgeBetween(start - 9, start - 8))
                        return board.isEdgeBetween(start, start + 1);

                }
            }
        }
        return false;
    }*/

    boolean checkAct(Action action, WorldModel worldModel) {
        id = worldModel.getTurn()%2;
        if (action.getActionType() == ActionType.MOVE) {
            try {
                return checkMove(worldModel.boardDeepCopy(), (Move) action);
            } catch (CloneNotSupportedException e) {
                System.out.println("Board Clone not work!");
            }
        } else if (action.getActionType() == ActionType.BLOCK) {

        }
        return false;
    }
}
