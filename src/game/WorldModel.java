package game;

import board.Board;
import board.Cell;
import board.Piece;
import board.Wall;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorldModel {
    private Board board;
    private int turn;
    private Map<Integer, Integer> remindWalls = new HashMap<>();


    public void setRemindWall(Integer id) {

        if (!remindWalls.containsKey(id)){
            remindWalls.put(id,board.getCntWalls()/4);
        }else {
            remindWalls.put(id,remindWalls.get(id)-1);
        }
    }

    public Integer getReminderWall(Integer id) {
        return remindWalls.get(id);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getCurrentPosition(int id) {
        board = getBoard();
        return board.getPiece(id).getPosition();
    }

     Board boardDeepCopy() throws CloneNotSupportedException {
        Graph<Integer, DefaultEdge> graph = board.getGraph();

        ArrayList<Cell> cells = (ArrayList<Cell>) board.getCells().clone();
        ArrayList<Wall> walls = (ArrayList<Wall>) board.getWalls().clone();
        ArrayList<Piece> pieces = (ArrayList<Piece>) board.getPieces().clone();

        int cntWalls = board.getCntWalls();
        int cntPieces = board.getCntPieces();

        Board board = new Board(cntPieces, cntWalls);
        board.setGraph(graph);
        board.setCnt(cntWalls);
        board.setCntPieces(cntPieces);
        board.setCells(cells);
        board.setPieces(pieces);
        board.setWalls(walls);
        return board;
    }
}
