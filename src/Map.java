public class Map {
    private final int size = 10;
    int[][] array = new int[size][size];
    int[][] color = new int[size][size];

    public Map(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                array[i][j] = -2;
                color[i][j] = 0;
            }
        }
        //set the lake areas as -3;
        array[4][2] = -3;
        array[4][3] = -3;
        array[4][6] = -3;
        array[4][7] = -3;
        array[5][2] = -3;
        array[5][3] = -3;
        array[5][6] = -3;
        array[5][7] = -3;
    }

    public void setPiecePosition(Piece p){
        int[] pos = p.getPosition();
        int num = p.getNumber();
        String col = p.getColor();
        array[pos[0]][pos[1]] = num;

        //keep track of which pieces are red and blue
        if(col.equalsIgnoreCase("RED")){
            color[pos[0]][pos[1]] = -1;
        }
        else if(col.equalsIgnoreCase("BLUE")){
            color[pos[0]][pos[1]] = 1;
        }
        else{
            color[pos[0]][pos[1]] = 0;
        }

    }

    public void display(){
        for(int i = 0; i < 10; i++){
            System.out.print("| ");
            for(int j = 0; j < 10; j ++){
                switch(array[i][j]){
                    case -3:
                        System.out.print("# ");
                        break;
                    case -2:
                        System.out.print("  ");
                        break;
                    case -1:
                        System.out.print("B ");
                        break;
                    case 0:
                        System.out.print("F ");
                        break;
                    case 1:
                        System.out.print("S ");
                        break;
                    case 10:
                        System.out.print("0 ");
                        break;
                    default:
                        System.out.print(array[i][j] + " ");
                        break;
                }
            }
            System.out.println("|");
        }
    }

    //check if a move is valid, and then update the map accordingly
    public boolean movePiece(int[] prev, int[] now){
        //calculate if the positions are adjacent
        int x = prev[0] - now[0];
        int y = prev[1] - now[1];
        int a = x + y;
        //check if the move is diagonal
        boolean diag = false;
        if(x != 0 && y != 0){
            diag = true;
        }
        //cannot move into lake
        //cannot move bomb or flag pieces
        if(array[now[0]][now[1]] == -3 || array[prev[0]][prev[1]] == -1 || array[prev[0]][prev[1]] == 0){
            return false;
        }
        //cannot move a piece onto a piece of the same color
        else if(color[prev[0]][prev[1]] == color[now[0]][now[1]]){
            return false;
        }
        //invalid if the positions are on top of each other, 
        //or not adjacent when the piece is not a scout
        else if(array[prev[0]][prev[1]] != 2 && a != 1){
            return false;
        }
        //cannot move a piece diagonally
        else if(diag){
            return false;
        }
        else{
            if(array[now[0]][now[1]] == -1){
                //if a piece that is a miner moves onto a square with a bomb
                //the square is now the miner
                //if not, the square is still a bomb
                if(array[prev[0]][prev[1]] == 3){
                    array[now[0]][now[1]] = 3;
                    color[now[0]][now[1]] = color[prev[0]][prev[1]];
                }
                //the previous square is now empty
                array[prev[0]][prev[1]] = -2;
                color[prev[0]][prev[1]] = 0;
                
            }
            //if the piece is the scout, make sure it doesn't go over lakes.
            else if(array[prev[0]][prev[1]] == 2){
                if(x != 0){
                    if(prev[0] == 4 || prev[0] == 5){
                        if(prev[1] <= 3 && now[1] >= 2){
                            return false;
                        }
                        else if(prev[1] >= 2 && now[1] <= 3){
                            return false;
                        }
                        else if(prev[1] <= 7 && now[1] >= 6){
                            return false;
                        }
                        else if(prev[1] >= 6 && now[1] <= 7){
                            return false;
                        }
                    }
                }
                else if(y != 0){
                    if(prev[1] == 2 || prev[1] == 3 || prev[1] == 6 || prev[1] == 7){
                        if(prev[0] <= 5 && now[0] >= 4){
                            return false;
                        }
                        else if(prev[0] >= 4 && now[0] <= 5){
                            return false;
                        }
                    }
                }
            }
            //if the value of the piece1 in the previuos square is greater than
            //the piece2 currently in the square, piece1 replaces piece2
            //otherwise, piece2 remains
            if(array[prev[0]][prev[1]] > array[now[0]][now[1]]){
                array[now[0]][now[1]] = array[prev[0]][prev[1]];
                color[now[0]][now[1]] = color[prev[0]][prev[1]];
            }
            //the previous square is now empty;
            array[prev[0]][prev[1]] = -2;
            color[prev[0]][prev[1]] = 0;
            return true;
        }
    }
}
