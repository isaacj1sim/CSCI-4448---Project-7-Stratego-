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

    private Point startPoint = new Point(-1,-1);
    private Point endPoint = new Point(-1, -1);

    private Piece startPiece;
    private Piece endPiece;

    private boolean isAttack;
    private boolean isAttackWin;
    private boolean isDefendWin;

    //getter & setter for isAttack
    public boolean getIsAttack(){
        return isAttack;
    }

    public void setIsAttack(boolean bool){
        isAttack = bool;
    }

    //Getter & Setter for isAttackWin
    public boolean getAttackWin(){
        return isAttackWin;
    }

    public void setAttackWin(boolean bool){
        this.isAttackWin = bool;
    }

    //Getter & Setters for isDefendWin
    public boolean getDefendWin(){
        return isDefendWin;
    }

    public void setDefendWin(boolean bool){
        this.isDefendWin = bool;
    }

    public void setStartPoint(int x)
}
