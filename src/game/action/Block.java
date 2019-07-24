package game.action;

public class Block extends Action {
    private Direction direction;

    public Block(ActionType actionType, int position, Direction direction) {
        super(actionType, position);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
