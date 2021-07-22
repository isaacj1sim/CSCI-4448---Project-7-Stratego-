import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Game {
    private static Game instance = null;
    Map map;
    List<Piece> red;
    List<Piece> blue;
    PieceFactory pFact = new PieceFactory();
    boolean cont;

    private Game(){
        map = new Map();
        red = new ArrayList<Piece>();
        blue = new ArrayList<Piece>();
        cont = true;

        //number_of_pieces corresponds to the amount of each category of pieces
        int[] number_of_piece = {6, 1, 1, 8, 5, 4, 4, 4, 3, 2, 1, 1};
        int redIndex = 0;
        int blueIndex = 99;
        for(int i = 0; i < 12; i++){
            for(int k = 0; k < number_of_piece[i]; k++){
                red.add(pFact.getPiece(i - 1, "red", redIndex));
                blue.add(pFact.getPiece(i - 1, "blue", blueIndex));
                redIndex++;
                blueIndex--;
            }
        }
        for(Piece p : red){
            map.setPiecePosition(p);
        }
        for(Piece p : blue){
            map.setPiecePosition(p);
        }

        int[] lakeIndex = {42,43,46,47,52,53,56,57};
        for(int i : lakeIndex){
            map.setPiecePosition(pFact.getPiece(-3, "", i));
        }

        //map.display("");
    }

    public static synchronized Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void reset(){
        cont = true;
        for(Piece p : red){
            map.setPiecePosition(p);
        }
        for(Piece p : blue){
            map.setPiecePosition(p);
        }

        int[] lakeIndex = {42,43,46,47,52,53,56,57};
        for(int i : lakeIndex){
            map.setPiecePosition(pFact.getPiece(-3, "", i));
        }

        //map.display("");
    }

    public List<Piece> getPieces(String color){
        if (color.equalsIgnoreCase("RED")){
            return red;
        }
        else{
            return blue;
        }
    }

    public boolean getCont(){
        return cont;
    }
    
    public void end(){
        cont = false;
    }

    public void display(String color){
        //map.display(color);
    }
    
    public Cell getCell(int i, int j){
        return map.getCell(i, j);
    }

    public Cell getEmptyCell(int i, int j){
        return map.getEmptyCell(i, j);
    }
}
