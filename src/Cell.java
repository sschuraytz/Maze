import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private int row;
    private int col;
    private boolean visited;
    private ArrayList<Cell> unvisitedNeighbors = new ArrayList<>();
    private Cell parent;

    private boolean topWall = true;
    private boolean rightWall = true;
    private boolean bottomWall = true;
    private boolean leftWall = true;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public ArrayList<Cell> checkNeighboringCells(ArrayList<Cell> cellArray, int localRow, int localCol)
    {
        Cell topNeighbor = null;
        Cell rightNeighbor = null;
        Cell bottomNeighbor = null;
        Cell leftNeighbor = null;

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<>();

        if (localRow > 0)
        {
            topNeighbor = cellArray.get(getCellLocation(localRow - 1, localCol));
        }
        if (localRow < (MazeComponent.NUM_OF_ROWS - 1))
        {
            bottomNeighbor = cellArray.get(getCellLocation(localRow + 1, localCol));
        }
        if (localCol > 0)
        {
            leftNeighbor = cellArray.get(getCellLocation(localRow, localCol - 1));
        }
        if (localCol < (MazeComponent.NUM_OF_COLS - 1))
        {
            rightNeighbor = cellArray.get(getCellLocation(localRow, localCol + 1));
        }

        Cell[] neighborTypes = new Cell[]{topNeighbor, rightNeighbor, bottomNeighbor, leftNeighbor};

        for (Cell neighborType : neighborTypes) {
            if (neighborType != null && !neighborType.visited) {
                unvisitedNeighbors.add(neighborType);
            }
        }
        return unvisitedNeighbors;
    }


    public ArrayList<Cell> getUnvisitedNeighborList() {
        return unvisitedNeighbors;
    }

    public void setUnvisitedNeighborList(ArrayList<Cell> unvisitedNeighborList) {
        unvisitedNeighbors = unvisitedNeighborList;
    }

    public Cell getRandomNeighbor(ArrayList<Cell> unvisitedNeighbors) {
        Random rand = new Random();
        return unvisitedNeighbors.get(rand.nextInt(unvisitedNeighbors.size()));
    }

    public int getCellLocation(int row, int col) {
        if (row < 0 || col < 0 || row > MazeComponent.NUM_OF_COLS - 1 || col > MazeComponent.NUM_OF_ROWS - 1) {
            return -1;
        } else {
            int location = (col * MazeComponent.NUM_OF_ROWS) + row;
            return location;
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public String toString() {
        return String.format("Grid location: (%s, %s)  visited: %s", row, col, visited);
    }

    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }

    public boolean isTopWall() {
        return topWall;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public boolean isBottomWall() {
        return bottomWall;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public void setParent(Cell parent) { this.parent = parent; }

}