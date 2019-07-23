package board;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;

public class Board {

    private Graph<Integer, DefaultEdge> graph;
    private int cntWalls;
    private int cntPieces;
    private ArrayList<Cell> cells;
    private ArrayList<Wall> walls;
    private ArrayList<Piece> pieces;

    private int cnt = 0;

    public Board(int cntPieces, int cntWalls) {
        this.graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        cells = new ArrayList<>();
        walls = new ArrayList<>();
        pieces = new ArrayList<>();
        this.cntPieces = cntPieces;
        this.cntWalls = cntWalls;
    }

    public Graph<Integer, DefaultEdge> getGraph() {
        return graph;
    }

    public void setGraph(Graph<Integer, DefaultEdge> graph) {
        this.graph = graph;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    private void initialGraphCells() {
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (int i = 0; i < 81; i++) {
            Cell vCell = new Cell(-1, null);
            graph.addVertex(i);
            vCell.setPosition(i);
            cells.add(vCell);
        }
        for (int i = 0; i < 81; i++) {
            Cell[] adjCell = new Cell[4];
            if ((i - 1) > 0 && i % 9 != 0) {
                graph.addEdge(i, i - 1);
                adjCell[0] = new Cell(i - 1, "#FFF");
            } else {
                adjCell[0] = new Cell(-1, null);
            }

            if ((i + 1) < 81 && (i + 1) % 9 != 0) {
                graph.addEdge(i, i + 1);
                adjCell[1] = new Cell(i + 1, "#FFF");
            } else {
                adjCell[1] = new Cell(-1, null);
            }

            if ((i - 9) > 0) {
                graph.addEdge(i, i - 9);
                adjCell[2] = new Cell(i - 9, "#FFF");
            } else {
                adjCell[2] = new Cell(-1, "#FFF");
            }

            if ((i + 9) < 81) {
                graph.addEdge(i, i + 9);
                adjCell[3] = new Cell(i + 9, "#FFF");
            } else {
                adjCell[3] = new Cell(-1, null);
            }

            cells.get(i).setCells(adjCell);
        }

        setGraph(graph);
    }

    private void initialPieces() {
        pieces.add(new Piece(4, "#000", 1));
        pieces.add(new Piece(76, "#100", 2));
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getPosition() == pieces.get(0).getPosition()) {
                cells.get(i).setFill(1);
            } else if (cells.get(i).getPosition() == pieces.get(1).getPosition()) {
                cells.get(i).setFill(1);
            }
        }
    }

    private void initialWall() {

        for (int i = 0; i < cntWalls; i++) {
            walls.add(new Wall(-1, "#e5e5e5", -2));
        }
    }

    public void createBoard() {
        initialGraphCells();
        initialPieces();
        initialWall();
    }

    public void showBoardCellsPosition() {
        for (int i = 0; i < cells.size(); i++) {
            System.out.print(cells.get(i).getPosition() + " ");
        }
    }

    public int[] getBoardAdjCellAt(int positions) {
        Cell c = cells.get(positions);
        Cell[] adj = c.getCells();
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = adj[i].getPosition();
        }
        return a;
    }

    public int positionPieceInBoard(int id) {
        int p = 0;
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getId() == id) {
                p = pieces.get(i).getPosition();
            }
        }
        return p;
    }

    public boolean changePositionPieceOnBoard(int id, int pos) {
        int lastPosition = -1;
        boolean f1 = false;
        boolean f2 = false;
        boolean f3 = false;

        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getId() == id) {
                lastPosition = pieces.get(i).getPosition();
                pieces.get(i).setPosition(pos);
                f1 = true;
            }
        }

        for (int j = 0; j < cells.size(); j++) {
            if (cells.get(j).getPosition() == lastPosition) {
                cells.get(j).setFill(0);
                f2 = true;
            }
        }

        for (int j = 0; j < cells.size(); j++) {
            if (cells.get(j).getPosition() == pieces.get(0).getPosition()) {
                cells.get(j).setFill(1);

            } else if (cells.get(j).getPosition() == pieces.get(1).getPosition()) {
                cells.get(j).setFill(1);
                f3 = true;
            }
        }

        return f1 && f2 && f3;
    }

    public boolean isPieceOnCellOfBoard(int number) {
        boolean result = false;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getPosition() == number) {
                result = cells.get(i).getFill() == 0;
            }
        }
        return result;
    }

    public boolean insertEdgeAt(int start, int destination) {
        boolean flag = false;
        Graph<Integer, DefaultEdge> graph = getGraph();
        Cell[] adj1 = getCell(start).getCells();
        Cell[] adj2 = getCell(destination).getCells();
        int cnt = getCnt();

        if (isEdgeBetween(start, destination)) {
            graph.removeEdge(start, destination);
            walls.get(cnt).setPosition(start);
            walls.get(cnt).setDestination(destination);
            walls.get(cnt).setIsUsed(1);
            walls.get(cnt).setColor("e5e5e5");

            for (int i = 0; i < 4; i++) {
                if (adj1[i].getPosition() == destination) {
                    adj1[i].setPosition(-1);
                }

                if (adj2[i].getPosition() == start) {
                    adj2[i].setPosition(-1);
                }
            }
            flag = true;
            setCnt(++cnt);
        }

        return flag;
    }

    public boolean isEdgeBetween(int start, int destination) {
        boolean flag = false;
        Cell[] adj1 = getCell(start).getCells();
        Cell[] adj2 = getCell(destination).getCells();
        for (int i = 0; i < adj1.length; i++) {
            if (adj1[i].getPosition() == destination || adj2[i].getPosition() == start) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isPathBetween(int pos1, int pos2) {
        boolean result = false;
        Graph<Integer, DefaultEdge> graph = getGraph();
        if (DijkstraShortestPath.findPathBetween(graph, pos1, pos2) != null) {
            result = true;
        }
        return result;
    }

    public boolean isPathExcept(int pos1, int pos2, int start, int end, int dir) {
        boolean result = false;
        Graph<Integer, DefaultEdge> graph = getGraph();

        if (dir == 1) {
            if (graph.containsEdge(pos1, pos2) && graph.containsEdge(pos1 + 1, pos2 + 1)) {
                graph.removeEdge(pos1, pos2);
                graph.removeEdge(pos1 + 1, pos2 + 1);
                result = isPathBetween(start, end);
            }

            graph.addEdge(pos1, pos2);
            graph.addEdge(pos1 + 1, pos2 + 1);
        } else if (dir == 2) {
            if (graph.containsEdge(pos1, pos1 + 1) && graph.containsEdge(pos2, pos2 + 1)) {
                graph.removeEdge(pos1, pos1 + 1);
                graph.removeEdge(pos2, pos2 + 1);
                result = isPathBetween(start, end);
            }

            graph.addEdge(pos1, pos1 + 1);
            graph.addEdge(pos2, pos2 + 1);
        }

        return result;
    }


    public Wall getWall(int position) {
        Wall w = new Wall(-1, null, 'H');

        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).getPosition() == position)
                w = walls.get(i);
        }

        return w;
    }

    public Cell getCell(int position) {
        Cell c = null;

        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getPosition() == position)
                c = cells.get(i);
        }
        return c;
    }

    public Piece getPiece(int id) {
        Piece p = null;

        for (int i = 0; i < cntPieces; i++) {
            if (pieces.get(i).getId() == id)
                p = pieces.get(i);
        }
        return p;
    }

    public void showBoard() {

        ArrayList positions = new ArrayList<>();
        Graph<Integer, DefaultEdge> graph = getGraph();
        for (Piece var : pieces) {
            positions.add(var.getPosition());
        }

        for (int var : graph.vertexSet()) {
            if (positions.contains(var)) {
                if (getWall(var).getPosition() == var) {
                    if (!graph.containsEdge(var, var + 1) && (var + 1) % 9 != 0) {
                        System.out.print("  ");
                        System.out.print(positions.indexOf(var) + 1);
                        System.out.print(" ");
                        System.out.print(" # ");
                    } else {
                        System.out.print("   ");
                        System.out.print(positions.indexOf(var) + 1);
                        System.out.print("  ");

                    }
                } else {
                    System.out.print("   ");
                    System.out.print(positions.indexOf(var) + 1);
                    System.out.print("   ");
                }
            } else {
                System.out.print("   *   ");
                if (!graph.containsEdge(var, var + 1) && (var + 1) % 9 != 0) {
                    System.out.print("\b#");
                }
            }

            if ((var + 1) % 9 == 0 && var != 0) {
                System.out.println();
                for (int i = var - 8; i <= var; i++) {
                    if (!graph.containsEdge(i, i + 9) && var < 72) {
                        System.out.print("   #   ");
                    } else
                        System.out.print("       ");
                }
                System.out.println();
            }
        }


    }
}
