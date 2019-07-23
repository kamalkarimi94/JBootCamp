package game.action;

public class Move extends Action {
    private int position;
    private Direction direction;

    public Move(int position) {
        super(ActionType.MOVE);
        this.position = position;
        this.direction = Direction.NO_DIRECTION;
    }

    public int getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
