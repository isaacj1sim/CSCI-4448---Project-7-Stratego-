import java.util.ArrayList;
import java.util.List;

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
        map.display();
    }

    public static synchronized Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
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

}