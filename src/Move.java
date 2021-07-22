/**
 * Execute from a variety of possible player moves during turn.
 *
 */

import java.awt.Point;

public class Move {

    /** Future Implementation of startPiece & endPiece
     *  Make functional with Piece Class
     *   Piece objects contains coordinates for each piece
     */
    
    static boolean validMove(Cell startCell, Cell endCell){
        String startColor = startCell.getPieceColor();
        String endColor = endCell.getPieceColor();
        int startValue = startCell.getPieceValue();
        int endValue = endCell.getPieceValue();
        Point start = startCell.getPoint();
        Point end = endCell.getPoint();
        int startX = (int)start.getX();
        int startY = (int)start.getY();
        int endX = (int)end.getX();
        int endY = (int)end.getY();//calculate if the positions are adjacent
        int x = startX - endX;
        int y = startY - endY;
        int a = x + y;
        //check if the move is diagonal
        if(x != 0 && y != 0){
            return false;
        }
        //cannot move into lake
        //cannot move bomb or flag pieces
        else if(endValue == -3 || startValue == -1 || startValue == 0){
            return false;
        }
        //cannot move a piece onto a piece of the same color
        else if(startColor.equalsIgnoreCase(endColor)){
            return false;
        }
        //invalid if the positions are on top of each other, 
        //or not adjacent when the piece is not a scout
        /**/else if(startValue != 2 && !(a == 1 || a == -1)){
            return false;
        }
            //if the piece is the scout, make sure it doesn't go over lakes.
        else if(startValue == 2){
            //making a move horizontally over the lake
            if(x != 0){
                if(startX == 4 || startX == 5){
                    if(startY <= 3 && endY >= 2){
                        return false;
                    }
                    else if(startY >= 2 && endY <= 3){
                        return false;
                    }
                    else if(startY <= 7 && endY >= 6){
                        return false;
                    }
                    else if(startY >= 6 && endY <= 7){
                        return false;
                    }
                }
            }
            //making a move vertically over the lake
            else if(y != 0){
                if(startY == 2 || startY == 3 || startY == 6 || startY == 7){
                    if(startX <= 5 && endX >= 4){
                        return false;
                    }
                    else if(startX >= 4 && endX <= 5){
                        return false;
                    }
                }
            }
        }
        
        System.out.println("bye");
        return true;
    }

    static int win(Cell startCell, Cell endCell){
        int startValue = startCell.getPieceValue();
        int endValue = endCell.getPieceValue();
        if(startValue == 1 && endValue == 10){
            return 1;
        }
        else if(startValue == 10 && endValue == 1){
            return 0;
        }
        else if(endValue == -1){
            if(startValue == 3){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if (endValue == 0){
            return -2;
        }
        else if (endValue > startValue){
            return 0;
        }
        else if (endValue == startValue){
            return -1;
        }
        else{
            return 1;
        }
    }
}
