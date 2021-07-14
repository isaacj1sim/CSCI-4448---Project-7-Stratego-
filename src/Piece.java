public class Piece {
    int number;
    String color;
    int[] position = new int[2];

    //number from -1 - 10. -1 = bomb, 0 = flag, 1 = spy, 2 = scout, 3 = miner
    //index converts to a two dimensional coordinate
    public Piece(int n, String c, int index){
        number = n;
        color = c;
        //index 0 - 9 is first row
        //index 10-19 is second row
        //index 20-29 is third row
        //index 30-39 is fourth row
        position[0] = index / 10; //get row
        position[1] = index % 10; //get column
    }

    public int getNumber(){
        return number;
    }

    public int[] getPosition(){
        return position;
    }

    public String getColor(){
        return color;
    }
}
