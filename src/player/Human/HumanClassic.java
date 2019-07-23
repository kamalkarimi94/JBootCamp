package player.Human;

import java.util.Scanner;

public class HumanClassic extends Human {


    public HumanClassic(String name, int pieceId, boolean turn, boolean isHuman, int cntBlock) {
        super(name, pieceId, turn, isHuman, cntBlock);
    }

    @Override
    public int nextAction() {
        Scanner scanner = new Scanner(System.in);
        int act = scanner.nextInt();
        return act;
    }

    @Override
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
    }
}
