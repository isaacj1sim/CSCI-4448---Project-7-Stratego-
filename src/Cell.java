/**
 * Represents a single cell on the game board. 10x10 board has 100 Individual cells.
 */

//amazing import class for using coordinates: docs.oracle.com/javase/10/docs/api/java/awt/Point.html
import java.awt.Point;

public class Cell {
    //position of the cell on the board
    private Point position_on_board;

    //contains the Piece that is currently on cell or null for nothing.
    private Piece piece_on_cell = null;

    //pass in x and y coordinate to initialize the Point coordinates of each cell
    public Cell(int x_coordinate, int y_coordinate) {
        Point position_on_board = new Point(x_coordinate, y_coordinate);
    }

    public void setPiece(Piece piece){
        piece_on_cell = piece;
    }

    public Piece getPiece(){
        return piece_on_cell;
    }
}