import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance = null;
    List<Piece> red;
    List<Piece> blue;
    PieceFactory pFact = new PieceFactory();

    private Game(){
        red = new ArrayList<Piece>();
        blue = new ArrayList<Piece>();
        
        int[] number_of_piece = {6, 1, 1, 8, 5, 4, 4, 4, 3, 2, 1, 1};
        for(int i = 0; i < 12; i++){
            for(int j = -1; j <= 10; j++){
                for(int k = 0; k < number_of_piece[i]; k++){
                    red.add(pFact.getPiece(j, "red"));
                    blue.add(pFact.getPiece(j, "blue"));
                }
            }
        }
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

}
