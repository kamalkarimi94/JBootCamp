package player.Human;

import game.WorldModel;
import game.action.Action;
import player.Player;

public abstract class Human extends Player {


    Human(String name) {
        super(name);
    }

    @Override
    public abstract Action nextAction(WorldModel worldModel) ;

/*    @Override
    public abstract int nextMove();

    @Override
    public abstract int blocking() ;

    @Override
    public abstract int blockPos()*/;

  /*  @Override
    public abstract int nextMoveLeftOrRight();*/

}
