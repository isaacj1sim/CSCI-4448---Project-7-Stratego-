public class PieceFactory {
    public Piece getPiece(int number, String color, int index){
        return new Piece(number, color, index);
    }
}
