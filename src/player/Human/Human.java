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
}
