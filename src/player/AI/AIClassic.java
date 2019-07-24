package player.AI;

import game.WorldModel;
import game.action.*;

import java.util.Random;
import java.util.Scanner;

public class AIClassic extends AI {
    private Random random;
    private Action action;
    public AIClassic(String name) {
        super(name);
        random  = new Random();
    }

    @Override
    public Action nextAction(WorldModel worldModel) {

        int act = random.nextInt(2)+1;
        int position;
        if (act==1){
            position = random.nextInt(80);
            action = new Move(worldModel.getCurrentPosition(worldModel.getTurn()%2),position);
        }else if (act==2){
            position = random.nextInt(80)+1;
            int direction = random.nextInt(2)+1;
            if (direction == 1)
                action = new Block(ActionType.BLOCK,position, Direction.HORIZENTAL );
            else if (direction == 2)
                action = new Block(ActionType.BLOCK,position,Direction.VERTICAL);
        }
        return action;
    }
}
