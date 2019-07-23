package board;

public class Cell extends BoardItem {

    private int fill;
    private Cell[] cells;

    public Cell(int position, String color) {
        super(position, color);
        this.fill = 0;
        cells = new Cell[4];
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }
}
