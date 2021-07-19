import java.awt.Point;

public class Piece {

    /**
     * To-Do:
     */

    String color;
    String pieceName;
    int number_value;
    Point position;
    //whether or not the opposing player knows the value of the piece
    boolean revealed;


    //number_value from -1 - 10. -1 = bomb, 0 = flag, 1 = spy, 2 = scout, 3 = miner
    //index converts to a two dimensional coordinate
    public Piece(int num, String c, int index){
        revealed = false;
        number_value = num;
        color = c;
        //index 0 - 9 is first row
        //index 10-19 is second row
        //index 20-29 is third row
        //index 30-39 is fourth row
        int x = index / 10; //get row
        int y = index % 10; //get column
        position = new Point(x,y);

        //Adds name for Piece from number_value
        switch(number_value){
            case -3:
                pieceName = "Lake";
                break;
            case -1:
                pieceName = "Bomb";
                break;
            case 0:
                pieceName = "Flag";
                break;
            case 1:
                pieceName = "Spy";
                break;
            case 2:
                pieceName = "Scout";
                break;
            case 3:
                pieceName = "Miner";
                break;
            case 4:
                pieceName = "Seargeant";
                break;
            case 5:
                pieceName = "Lieutenant";
                break;
            case 6:
                pieceName = "Captain";
                break;
            case 7:
                pieceName = "Major";
                break;
            case 8:
                pieceName = "Colonel";
                break;
            case 9:
                pieceName = "General";
                break;
            case 10:
                pieceName = "Marshall";
                break;
            default:
                break;
        }
    }

    public int getNumber(){
        return number_value;
    }

    public Point getPosition(){
        return position;
    }

    public String getColor(){
        return color;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void revealed(){
        revealed = true;
    }

    public void conceal(){
        revealed = false;
    }
}
