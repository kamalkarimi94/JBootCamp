package player.AI;

import player.Player;

public abstract class AI extends Player{

    public AI(String name, int pieceId, boolean turn, boolean isHuman, int cntBlock) {
        super(name, pieceId, turn, isHuman, cntBlock);
    }

    @Override
    public abstract int nextAction();

    @Override
    public abstract int nextMove() ;

    @Override
    public abstract int blocking() ;

    @Override
    public abstract int nextMoveLeftOrRight() ;
}
