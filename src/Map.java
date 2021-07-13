public class Map {
    int[][] array = new int[10][10];

    public Map(){
        //set the lake areas as -100;
        array[4][2] = -100;
        array[4][3] = -100;
        array[4][6] = -100;
        array[4][7] = -100;
        array[5][2] = -100;
        array[5][3] = -100;
        array[5][6] = -100;
        array[5][7] = -100;
    }

    public void setPiecePositin(Piece p){
        int[] pos = p.getPosition();
        int num = p.getNumber();
        array[pos[0]][pos[1]] = num;
    }
}
