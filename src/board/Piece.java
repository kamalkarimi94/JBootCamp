package board;

public class Piece extends BoardItem {

    private int id;

    public Piece(int position, String color, int id) {
        super(position, color);
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
