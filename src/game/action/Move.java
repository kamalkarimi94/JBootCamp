package game.action;

public class Move extends Action {
    private int currentPosition;

    public Move(int currentPosition,int position) {
        super(ActionType.MOVE,position);
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
