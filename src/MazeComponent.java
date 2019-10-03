import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Stack;

public class MazeComponent extends JComponent {

    static final int NUM_OF_ROWS = 13;
    static final int NUM_OF_COLS = NUM_OF_ROWS;
    private static final int CELL_WIDTH = 20;
    private ArrayList<Cell> cellArray = new ArrayList<>();
    private ArrayList<Cell> unvisitedArray = new ArrayList<>();
    private Cell startCell;
    private Cell endCell;

    public MazeComponent() {
        createCells();
        setUpMaze();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawCells(graphics);
    }

    private void setUpMaze() {
        Stack<Cell> stack = new Stack<>();
        Cell chosenCell;

        startCell = cellArray.get(0);
        endCell = cellArray.get(cellArray.size() - 1);
        Cell currentCell = startCell;
        currentCell.setVisited();
        unvisitedArray.remove(currentCell);

        while (unvisitedArray.size() > 0) {
            currentCell.setUnvisitedNeighborList(currentCell.checkNeighboringCells(cellArray, currentCell.getCol(), currentCell.getRow()));
            if (currentCell.getUnvisitedNeighborList().size() > 0 ) {
                chosenCell = currentCell.getRandomNeighbor(currentCell.getUnvisitedNeighborList());
                removeWalls(currentCell, chosenCell);

                if (currentCell.getUnvisitedNeighborList().size() > 1) {
                    stack.push(currentCell);        //push the current cell to the stack if it has more than one unvisited neighbor
                }

                currentCell = chosenCell;
                currentCell.setVisited();
                unvisitedArray.remove(currentCell);

            }
            else if (!stack.empty()) {
                currentCell = stack.pop();
            }
        }
    }

    private void createCells() {
        for (int row = 0; row < NUM_OF_COLS; row++) {
            for (int col = 0; col < NUM_OF_ROWS; col++) {
                Cell cell = new Cell(row, col);
                cellArray.add(cell);
                unvisitedArray.add(cell);
            }
        }
    }

    private void drawCells(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setStroke(new BasicStroke(4));
        graphics.setColor(Color.BLUE);
        createEntranceAndExit();

        for (Cell cell : cellArray) {
            int row = cell.getRow() * CELL_WIDTH;
            int col = cell.getCol() * CELL_WIDTH;
            Point firstPoint = new Point(row, col);
            Point secondPoint = new Point(row + CELL_WIDTH, col);
            Point thirdPoint = new Point(row + CELL_WIDTH, col + CELL_WIDTH);
            Point fourthPoint = new Point(row, col + CELL_WIDTH);

            if (cell.isTopWall()) {
                graphics2d.draw(new Line2D.Double(firstPoint, secondPoint));
            }
            if (cell.isRightWall()) {
                graphics2d.draw(new Line2D.Double(secondPoint, thirdPoint));
            }
            if (cell.isBottomWall()) {
                graphics2d.draw(new Line2D.Double(thirdPoint, fourthPoint));
            }
            if (cell.isLeftWall()) {
                graphics2d.draw(new Line2D.Double(fourthPoint, firstPoint));
            }
        }
        graphics.drawString("Start", startCell.getRow() + 5, startCell.getCol() + 13);
        graphics.drawString("Finish", endCell.getRow() * CELL_WIDTH + 5, endCell.getCol() * CELL_WIDTH + 13);
    }

    private void createEntranceAndExit() {
        startCell.setLeftWall(false);
        endCell.setRightWall(false);
    }

    private void removeWalls(Cell current, Cell chosen) {
        int rowDifference = current.getRow() - chosen.getRow();
        if (rowDifference == -1) //chosen is Right of current
        {
            current.setRightWall(false);
            chosen.setLeftWall(false);
        }
        if (rowDifference == 1) //chosen is Left of Current
        {
            current.setLeftWall(false);
            chosen.setRightWall(false);
        }
        int colDifference = current.getCol() - chosen.getCol();
        if (colDifference == -1) //chosen is Below Current
        {
            current.setBottomWall(false);
            chosen.setTopWall(false);
        }
        if (colDifference == 1) //chosen is on Top of Current
        {
            current.setTopWall(false);
            chosen.setBottomWall(false);
        }
    }
}