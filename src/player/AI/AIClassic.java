package player.AI;

import java.util.Random;

public class AIClassic extends AI {
    private Random random;
    public AIClassic(String name, int pieceId, boolean turn, boolean isHuman, int cntBlock) {
        super(name, pieceId, turn, isHuman, cntBlock);
        random  = new Random();
    }

    @Override
    public int nextAction() {
        int act = random.nextInt(2);
        return ++act;
    }

    @Override
    public int nextMove() {
        int act = random.nextInt(4);
        return ++act;
    }

    @Override
    public int blocking() {
        int dir = random.nextInt(2);
        return ++dir;
    }

    @Override
    public int blockPos() {
        int pos = random.nextInt(81);
        return pos;
    }

    @Override
    public int nextMoveLeftOrRight() {
        int dir = random.nextInt(2);
        return dir+3;
    }
}
