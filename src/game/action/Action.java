package game.action;

public abstract class Action  {
    private ActionType actionType;
    private int position;

    Action(ActionType actionType,int position) {
        this.actionType = actionType;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
