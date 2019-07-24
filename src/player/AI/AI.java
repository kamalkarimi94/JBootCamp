package player.AI;

import game.WorldModel;
import game.action.Action;
import player.Player;

public abstract class AI extends Player{

    public AI(String name) {
        super(name);
    }

    @Override
    public abstract Action nextAction(WorldModel worldModel);

   /* @Override
    public abstract int nextMove() ;

    @Override
    public abstract int blocking() ;

    @Override
    public abstract int nextMoveLeftOrRight() ;*/
}
