public class Piece {
    int number;
    String color;
    int[] position = new int[2];

    //number from -1 - 10. -1 = bomb, 0 = flag, 1 = spy, 2 = scout, 3 = miner
    public Piece(int n, String c){
        number = n;
        color = c;
    }

    public int getNumber(){
        return number;
    }

    public int[] getPosition(){
        return position;
    }
}
