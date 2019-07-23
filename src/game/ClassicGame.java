package game;

import board.*;
import game.action.Action;
import game.action.Block;
import game.action.Move;
import player.AI.AIClassic;
import player.Human.HumanClassic;
import player.*;

import java.util.Scanner;

public class ClassicGame extends Game {

    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private ValidateAct validateAct = new ValidateAct();

    private int ActPrint(Player player) {
        if (player.isHuman()){
            System.out.println("select your action: (" + player.getName() + ")");
            System.out.println("1. Move");
            System.out.println("2. Block");
        }
        return player.nextAction();

    }

    private int MoveSelectBetweenRightAndLeft(Player player) {
        if (player.isHuman()){
            System.out.println("select direction between right and left:");
            System.out.println("3. Right corner");
            System.out.println("4. Left corner");
        }
        return player.nextMoveLeftOrRight();
    }

    private int MovePrint(Player player) {
        if (player.isHuman()) {
            System.out.println("select direction:");
            System.out.println("1. Up");
            System.out.println("2. Down");
            System.out.println("3. Right");
            System.out.println("4. Left");
        }
        return player.nextMove();
    }

    private int BlockPrint(Player player) {
        if (player.isHuman())
            System.out.print("Enter wall pos: ");
        return player.blockPos();
    }

    private int dirPrint(Player player) {
        if (player.isHuman()){
            System.out.println("Horizontal or Vertical?");
            System.out.println("1. Horizontal");
            System.out.println("2. Vertical");
        }
        return player.blocking();
    }

    private Action checkAct(Player player, int act) {
        Action action = null;
        if (act == 1) {
            int dir = MovePrint(player);
            switch (dir) {
                case 1:
                    action = new Move(player, "U");
                    break;
                case 2:
                    action = new Move(player, "D");
                    break;
                case 3:
                    action = new Move(player, "R");
                    break;
                case 4:
                    action = new Move(player, "L");
                    break;
            }
        } else if (act == 2) {
            int wallPos = BlockPrint(player);
            int dir = dirPrint(player);
            action = new Block(player, wallPos, dir);
        }
        return action;
    }

    private void addWall(int wallPos, int dir) {
        board.insertEdgeAt(wallPos, wallPos + dir);
        switch (dir) {
            case -9:
                board.insertEdgeAt(wallPos + 1, wallPos + 1 + dir);
                break;
            case 1:
                board.insertEdgeAt(wallPos - 9, wallPos - 9 + dir);
                break;
        }
    }

    private int startUpMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n\nSelect game mode:");
            System.out.println("1. Human VS Human");
            System.out.println("2. Human VS AI");
            System.out.println("3. AI VS Human");
            System.out.println("4. AI VS AI");
            int mode = scanner.nextInt();
            if (mode>0 && mode<5)
                return mode;
            else
                System.out.println("Invalid mode");
        }

    }

    private void initialPlayerMode(int walls){
        int select = startUpMenu();
        switch (select){
            case 1:
                playerOne = new HumanClassic("player1",1,true,true,walls/4);
                playerTwo = new HumanClassic("player2",2,false,true,walls/4);
                break;
            case 2:
                playerOne = new HumanClassic("player1",1,true,true,walls/4);
                playerTwo = new AIClassic("AI",2,false,false,walls/4);
                break;
            case 3:
                playerOne = new AIClassic("AI",1,true,false,walls/4);
                playerTwo = new HumanClassic("player1",2,false,true,walls/4);
                break;
            case 4:
                playerOne = new AIClassic("AI1",1,true,false,walls/4);
                playerTwo = new AIClassic("AI2",2,false,false,walls/4);
                break;
        }
    }

    @Override
    public void setup() {
        int players = 2;
        int walls = 40;
        board = new Board(players, walls);
        board.createBoard();
        initialPlayerMode(walls);
        System.out.println();
        board.showBoard();
    }

    @Override
    public void run() {
        int turn = 0 ;
        int act = 0;
        Action action = null;
        do {
            if (playerOne.isTurn()) {
                act = ActPrint(playerOne);
                action = checkAct(playerOne, act);
                if (act == 1) {
                    turn = nextMove(validateAct,action);
                    switchTurn(playerOne,playerTwo,turn);
                } else if (act == 2) {
                    turn = insertBlock(validateAct,action);
                    switchTurn(playerOne,playerTwo,turn);
                }
                showGamePlaneAndMessage(turn,playerOne);
            } else {
                 act = ActPrint(playerTwo);
                 action = checkAct(playerTwo, act);
                if (act == 1) {
                    turn = nextMove(validateAct,action);
                    switchTurn(playerOne,playerTwo,turn);
                } else if (act == 2) {
                    turn = insertBlock(validateAct,action);
                    switchTurn(playerOne,playerTwo,turn);
                }
                showGamePlaneAndMessage(turn,playerTwo);
            }
        } while (!evaluate(board));
    }

    private int nextMove(ValidateAct act , Action action){
        int result = 0;
        if (act.checkMove(board, (Move) action) == 1) {
            board.changePositionPieceOnBoard(((Move) action).getPlayer().getPieceId(), ((Move) action).getNextPos());
            result = 1;
        } else if (act.checkMove(board, (Move) action) == 2) {
            moveToLeftOrRight(board, (Move) action, MoveSelectBetweenRightAndLeft(((Move) action).getPlayer()));
            result = 1;
        }
        return result;
    }

    private int insertBlock(ValidateAct act, Action action) {

        int result = 0;
        if (act.checkBlock(board, (Block) action)) {
            if (((Block) action).getWallDir() == 1) {
                addWall(((Block) action).getWallPos(), (-9));
            } else {
                addWall(((Block) action).getWallPos(), 1);
            }
            ((Block) action).getPlayer().setCntBlock(((Block) action).getPlayer().getCntBlock() - 1);
            result = 1;

        }
        return result;
    }

    private void showGamePlaneAndMessage(int i,Player player) {
        if (i == 0) {
            System.out.println();
            if (player.isHuman()){
                board.showBoard();
                System.out.println("Wrong action !!!");
            }
        } else if (i == 1) {
            System.out.println();
            board.showBoard();
        }
    }

    private void switchTurn(Player playerOne, Player playerTwo, int turn) {

        if (turn == 1) {
            if (playerOne.isTurn()) {
                playerOne.setTurn(false);
                playerTwo.setTurn(true);
            } else if (playerTwo.isTurn()) {
                playerOne.setTurn(true);
                playerTwo.setTurn(false);
            }
        }
    }

    private void moveToLeftOrRight(Board board, Move move, int dir) {
        int pieceId = move.getPlayer().getPieceId();
        int pos = board.getPiece(pieceId).getPosition();
        String mDir = move.getDirection();
        if (dir == 4) {

            if (mDir.equals("U")  && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos - 10);
            } else if (mDir.equals("D") && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos + 8);
            } else if (mDir.equals("L") && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos + 8);
            }else if (mDir.equals("R") && mDir != null){
                board.changePositionPieceOnBoard(pieceId,pos+10);
            }
        } else if (dir == 3) {
            if (mDir.equals("U") && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos - 8);
            } else if (mDir.equals("D") && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos + 8);
            } else if (mDir.equals("L") && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos - 10);
            } else if (mDir.equals("R") && mDir != null) {
                board.changePositionPieceOnBoard(pieceId, pos -8);
            }
        }
    }

    @Override
    public boolean evaluate(Board board) {

        for (int i = 0; i < 9; i++) {
            if (board.isPathBetween(board.getPiece(playerTwo.getPieceId()).getPosition(),i)){
                if (board.getPiece(playerTwo.getPieceId()).getPosition() == i) {
                    setWinPlayer(playerTwo);
                    System.out.println(playerTwo.getName() + " Won!!!");
                    return true;
                }
            }else {
                setWinPlayer(playerTwo);
                System.out.println(playerTwo.getName() + " Won!!!");
                return true;
            }
        }
        for (int i = 72; i < 81; i++) {
            if (board.isPathBetween(board.getPiece(playerOne.getPieceId()).getPosition(),i)){
                if (board.getPiece(playerOne.getPieceId()).getPosition() == i) {
                    setWinPlayer(playerOne);
                    System.out.println(playerOne.getName() + " Won!!!");
                    return true;
                }

            }else {
                setWinPlayer(playerOne);
                System.out.println(playerOne.getName() + " Won!!!");
                return true;
            }
        }
        return false;
    }
}
