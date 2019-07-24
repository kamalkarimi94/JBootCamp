package player.AI;

import game.WorldModel;
import game.action.Action;

import java.util.Random;

public class AIClassic extends AI {
    private Random random;
    public AIClassic(String name) {
        super(name);
        random  = new Random();
    }

   /* @Override
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
    }*/

    @Override
    public Action nextAction(WorldModel worldModel) {
        return null;
    }
}
