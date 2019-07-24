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
}
