import java.util.List;

public class Player {
    String name; 
    List<Piece> pieces;

    public Player(String n){
        name = n;
    }

    public void setPieces(List<Piece> p){
        pieces = p;
    }

    public List<Piece> getPieces(){
        return pieces;
    }
}
