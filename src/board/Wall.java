package board;

public class Wall extends BoardItem {

    private int destination;
    private int isUsed;

    public Wall(int position, String color, int destination) {
        super(position, color);
        this.destination = destination;
        this.isUsed = 0;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setDestination(char destination) {
        this.destination = destination;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }
}
