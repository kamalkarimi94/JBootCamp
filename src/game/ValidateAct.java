package game;

import board.Board;
import game.action.Action;
import game.action.ActionType;
import game.action.Block;
import game.action.Move;
import player.Player;

public class ValidateAct {

    //checkMove handel return!!!!
    private boolean checkMove(Board board, Move action) {
       /* Player player = action.getPlayer();
        String dir = action.getDirection();
        int pos1 = board.getPiece(player.getPieceId()).getPosition();
        int result = 0;
        if (isPositionInsideTheRange(pos1)) {
            if (dir.equals("U") && dir != null) {
                result = selectMove(board, action, pos1, 9, 1);
            } else if (dir.equals("D") && dir != null) {
                result = selectMove(board, action, pos1, -9, 1);
            } else if (dir.equals("R") && dir != null) {
                result = selectMove(board, action, pos1, -1, 9);
            } else if (dir.equals("L") && dir != null) {
                result = selectMove(board, action, pos1, 1, 9);
            }
        }*/
        return false;
    }

    private boolean isPositionInsideTheRange(int pos) {
        return pos >= 0 && pos < 81;
    }

    private boolean isPositionOnBoarderRange(int pos, String dir) {
        boolean flag = false;
        if (dir.equals("R") && dir != null) {
            int x = pos + 1;
            for (int i = 1; i < 9; i++) {
                if (x == i * 9 - 1)
                    flag = true;
            }
        } else if (dir.equals("L") && dir != null) {
            int x = pos - 1;
            for (int i = 0; i < 9; i++) {
                if (x == 9 * i)
                    flag = true;
            }
        }

        return flag;
    }

    private int selectMove(Board board, Move action, int x, int y, int z) {
        int result = -1;
        int i = -1;
        if (isPositionInsideTheRange(x - y)) {
            result = simpleMove(board, action, x, y);
            if (!board.isPieceOnCellOfBoard(x - y) && !isPositionOnBoarderRange(x, action.getDirection())) {
                if (isPositionInsideTheRange(x - 2 * y)) {
                    if (board.isEdgeBetween(x - y, x - 2 * y)) {
                        action.setNextPos(x - 2 * y);
                        result = simpleJump(board, action, x, y);
                        return result;
                    }
                    i = isRightVertexEmpty(x, y, z);
                    if (i == 1) {
                        result = complexJumpRightOrLeft(board, action, x, y, z);
                    } else if (i == 2) {
                        result = complexJumpRight(board, action, x, y, z);
                    } else if (i == 3) {
                        result = complexJumpLeft(board, action, x, y, z);
                    }
                }
            }
        }
        return result;
    }

    private int simpleMove(Board board, Move action, int x, int y) {
        int result = -1;
        if (board.isPieceOnCellOfBoard(x - y)
                && board.isEdgeBetween(x, x - y)) {
            action.setNextPos(x - y);
            result = 1;
        }

        return result;
    }

    private int simpleJump(Board board, Move action, int x, int y) {
        int result = -1;
        if (board.isEdgeBetween(x - y, x - 2 * y)) {
            action.setNextPos(x - 2 * y);
            result = 1;
        }

        return result;
    }

    private int complexJumpRightOrLeft(Board board, Move action, int x, int y, int z) {
        int result = -1;
        if (board.isEdgeBetween(x - y, x - y - z) &&
                board.isEdgeBetween(x - y, x - y + z)) {
            result = 2;
        } else if (board.isEdgeBetween(x - y, x - y - z) &&
                !board.isEdgeBetween(x - y, x - y + z)) {
            action.setNextPos(x - y - z);
            result = 1;
        } else if (!board.isEdgeBetween(x - y, x - y - z) &&
                board.isEdgeBetween(x - y, x - y + z)) {
            action.setNextPos(x - y + z);
            result = 1;
        }

        return result;
    }

    private int complexJumpRight(Board board, Move action, int x, int y, int z) {
        int result = -1;
        if (board.isEdgeBetween(x - y, x - y - z)) {
            action.setNextPos(x - y - z);
            result = 1;
        }
        return result;
    }

    private int complexJumpLeft(Board board, Move action, int x, int y, int z) {
        int result = -1;
        if (board.isEdgeBetween(x - y, x - y + z)) {
            action.setNextPos(x - y + z);
            result = 1;
        }
        return result;
    }

    private int isRightVertexEmpty(int x, int y, int z) {
        int result = -1;

        if (isPositionInsideTheRange(x - y - z) &&
                isPositionInsideTheRange(x - y + z)) {
            result = 1;
        } else if (isPositionInsideTheRange(x - y - z) &&
                !isPositionInsideTheRange(x - y + z)) {
            result = 2;
        } else if (!isPositionInsideTheRange(x - y - z) &&
                isPositionInsideTheRange(x - y + z)) {
            result = 3;
        }

        return result;
    }

    boolean checkBlock(Board board, Block action) {
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
    }

    boolean checkAct(Action action,WorldModel worldModel) {
        if (action.getActionType()== ActionType.MOVE){
            return checkMove(worldModel.getBoard(),(Move) action);
        }else if (action.getActionType() == ActionType.BLOCK){

        }
        return false;
    }
}
