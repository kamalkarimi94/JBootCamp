package game.action;

public class Move extends Action {
    private int currentPosition;
    private Direction direction;

    public Move(int currentPosition,int position) {
        super(ActionType.MOVE,position);
        this.currentPosition = currentPosition;
    }

    public int getPosition() {
        return currentPosition;
    }
}
