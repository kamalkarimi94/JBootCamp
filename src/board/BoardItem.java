package board;

public class BoardItem {

    private int position;
    private String color;

    public BoardItem(int position, String color) {
        this.position = position;
        this.color = color;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
