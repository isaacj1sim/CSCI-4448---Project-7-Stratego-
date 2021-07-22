/**
 * Represents a single cell on the game board. 10x10 board has 100 Individual cells.
 */

//amazing import class for using coordinates: docs.oracle.com/javase/10/docs/api/java/awt/Point.html
import java.awt.Point;
import java.io.Serializable;

public class Cell implements Serializable{
    //position of the cell on the board
    //
    private Point position_on_board;

    private static final long serialVersionUID = 1L;

    //contains the Piece that is currently on cell or null for nothing.
    private Piece piece_on_cell = null;

    //pass in x and y coordinate to initialize the Point coordinates of each cell
    public Cell(int x_coordinate, int y_coordinate) {
        position_on_board = new Point(x_coordinate, y_coordinate);
    }

    public void setPiece(Piece piece){
        piece_on_cell = piece;
    }

    public void setPoint(Point p){
        position_on_board = p;
    }

    

    public void emptyCell(){
        piece_on_cell = null;
    }

    public Piece getPiece(){
        return piece_on_cell;
    }

    public int getPieceValue(){
        if(piece_on_cell == null){
            return -2;
        }
        return piece_on_cell.getNumber();
    }

    public String getPieceColor(){
        if(piece_on_cell == null){
            return "";
        }
        return piece_on_cell.getColor();
    }

    public boolean getPieceRevealed(){
        return piece_on_cell.getRevealed();
    }

    public Point getPoint(){
        return position_on_board;
    }
}