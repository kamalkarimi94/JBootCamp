package game;

import board.Board;
import game.action.Action;
import game.action.ActionType;
import game.action.Block;
import game.action.Direction;
import player.AI.AI;
import player.AI.AIClassic;
import player.Human.Human;
import player.Human.HumanClassic;
import player.Player;

import java.util.Scanner;

public class GameClassic extends Game {

    private Board board;
    private WorldModel worldModel;
    private Player playerOne;
    private Player playerTwo;
    private ValidateAct validateAct = new ValidateAct();

    private void ActPrint(Player player) {
        if (player instanceof Human) {
            System.out.println("select your action: (" + player.getName() + ")");
            System.out.println("1. Move");
            System.out.println("2. Block");
        }
    }
 /*   private int MoveSelectBetweenRightAndLeft(Player player) {
        if (player.isHuman()){
            System.out.println("select direction between right and left:");
            System.out.println("3. Right corner");
            System.out.println("4. Left corner");
        }
        return player.nextMoveLeftOrRight();
    }*/

    /*private int MovePrint(Player player) {
        if (player.isHuman()) {
            System.out.println("select direction:");
            System.out.println("1. Up");
            System.out.println("2. Down");
            System.out.println("3. Right");
            System.out.println("4. Left");
        }
        return player.nextMove();
    }*/

    /*private int BlockPrint(Player player) {
        if (player.isHuman())
            System.out.print("Enter wall pos: ");
        return player.blockPos();
    }*/

/*    private int dirPrint(Player player) {
        if (player.isHuman()){
            System.out.println("Horizontal or Vertical?");
            System.out.println("1. Horizontal");
            System.out.println("2. Vertical");
        }
        return player.blocking();
    }*/

  /*  private Action checkAct(Player player, int act) {
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
    }*/

    /*private void addWall(int wallPos, int dir) {
        board.insertEdgeAt(wallPos, wallPos + dir);
        switch (dir) {
            case -9:
                board.insertEdgeAt(wallPos + 1, wallPos + 1 + dir);
                break;
            case 1:
                board.insertEdgeAt(wallPos - 9, wallPos - 9 + dir);
                break;
        }
    }*/

    private int startUpMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\nSelect game mode:");
            System.out.println("1. Human VS Human");
            System.out.println("2. Human VS AI");
            System.out.println("3. AI VS Human");
            System.out.println("4. AI VS AI");
            int mode = scanner.nextInt();
            if (mode > 0 && mode < 5)
                return mode;
            else
                System.out.println("Invalid mode");
        }

    }

    private void initialPlayerMode() {
        int select = startUpMenu();
        switch (select) {
            case 1:
                playerOne = new HumanClassic("player1");
                playerTwo = new HumanClassic("player2");
                break;
            case 2:
                playerOne = new HumanClassic("player1");
                playerTwo = new AIClassic("AI");
                break;
            case 3:
                playerOne = new AIClassic("AI");
                playerTwo = new HumanClassic("player1");
                break;
            case 4:
                playerOne = new AIClassic("AI1");
                playerTwo = new AIClassic("AI2");
                break;
        }
    }

    @Override
    public void setup() {
        int walls = 40;
        board = new Board(2, walls);
        board.createBoard();
        worldModel = new WorldModel();
        worldModel.setBoard(board);
        worldModel.getBoard().getPieces().forEach(piece -> {
            worldModel.setRemindWall(piece.getId());
        });
        initialPlayerMode();
        System.out.println();
        board.showBoard();
    }

    @Override
    public void run() {
        int turn = worldModel.getTurn();
        Action action;
        do {

            if (turn % 2 == 0) {
                ActPrint(playerOne);
                action = playerOne.nextAction(worldModel);
                if (validateAct.checkAct(action, worldModel)) {
                    execute(turn % 2, action);
                    worldModel.setTurn(++turn);
                } else {
                    if (playerOne instanceof Human)
                        System.out.println("Wrong action!");
                }
            } else if (turn % 2 == 1) {
                ActPrint(playerTwo);
                action = playerTwo.nextAction(worldModel);
                if (validateAct.checkAct(action, worldModel)) {
                    execute(turn % 2, action);
                    worldModel.setTurn(++turn);
                } else {
                    if (playerTwo instanceof Human)
                        System.out.println("Wrong action!");
                }
            }
        } while (!evaluate(worldModel));
    }


    /*private int insertBlock(ValidateAct act, Action action) {

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
    }*/


    @Override
    public boolean evaluate(WorldModel worldModel) {
        Board board = worldModel.getBoard();
        for (int i = 0; i < 9; i++) {
            if (board.isPathBetween(worldModel.getCurrentPosition(1), i)) {
                if (worldModel.getCurrentPosition(1) == i) {
                    setWinPlayer(playerTwo);
                    System.out.println(playerTwo.getName() + " Won!!!");
                    return true;
                }
            } else {
                setWinPlayer(playerTwo);
                System.out.println(playerTwo.getName() + " Won!!!");
                return true;
            }
        }
        for (int i = 72; i < 81; i++) {
            if (board.isPathBetween(worldModel.getCurrentPosition(0), i)) {
                if (worldModel.getCurrentPosition(0) == i) {
                    setWinPlayer(playerOne);
                    System.out.println(playerOne.getName() + " Won!!!");
                    return true;
                }

            } else {
                setWinPlayer(playerOne);
                System.out.println(playerOne.getName() + " Won!!!");
                return true;
            }
        }
        return false;
    }

    private void execute(int id, Action action) {
        board = worldModel.getBoard();
        if (action.getActionType() == ActionType.MOVE) {
            board.changePositionPieceOnBoard(id, action.getPosition());

        } else if (action.getActionType() == ActionType.BLOCK) {
            Block block = (Block) action;
            Direction direction = block.getDirection();
            int wallPosition = block.getPosition();
            if (direction == Direction.HORIZENTAL){
                board.addWall(wallPosition,wallPosition-9);
                board.addWall(wallPosition+1,wallPosition-8);
                worldModel.setRemindWall(worldModel.getTurn()%2);

            }else if (direction == Direction.VERTICAL){
                board.addWall(wallPosition,wallPosition+1);
                board.addWall(wallPosition-9,wallPosition-8);
                worldModel.setRemindWall(worldModel.getTurn()%2);
            }
        }
        worldModel.setBoard(board);
        board.showBoard();
    }
}
