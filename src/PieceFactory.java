public class PieceFactory {
    public Piece getPiece(int number, String color){
        return new Piece(number, color);
    }
}
