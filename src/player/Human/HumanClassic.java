package player.Human;

import board.Board;
import game.WorldModel;
import game.action.Action;
import game.action.Block;
import game.action.Move;

import java.util.Scanner;

public class HumanClassic extends Human {
    private Action action;
    //private WorldModel worldModel;
    //private Board board;
    public HumanClassic(String name) {
        super(name);
    }

    @Override
    public Action nextAction(WorldModel worldModel) {
        Scanner scanner = new Scanner(System.in);
        int act = scanner.nextInt();
        System.out.println("enter position:");
        int position;
        if (act==1){
            position = scanner.nextInt();
            action = new Move(worldModel.getCurrentPosition(worldModel.getTurn()%2),position);
        }else if (act==2){
            position = scanner.nextInt();
            System.out.println("enter direction:");
            System.out.println("1. horizontal");
            System.out.println("2. vertical");
            int direction = scanner.nextInt();
            action = new Block(position,direction);
        }
        return action;
    }

    /*@Override
    public int nextMove() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int dir = scanner.nextInt();
            if (dir > 0 && dir < 5) {
                return dir;
            } else {
                System.out.println("Enter valid direction");
            }
        }
    }

    @Override
    public int blocking() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            int dir = scanner.nextInt();
            if (dir>0 && dir<3)
                return dir;
            else
                System.out.println("Enter valid direction");
        }
    }

    @Override
    public int blockPos() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int pos = scanner.nextInt();
            if (pos>=0 && pos <81)
                return pos;
            else
                System.out.println("Enter valid position (0 - 80)");
        }
    }

    @Override
    public int nextMoveLeftOrRight() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int pos = scanner.nextInt();
            if (pos>=3 && pos <5)
                return pos;
            else
                System.out.println("Enter valid direction");
        }
    }*/
}
