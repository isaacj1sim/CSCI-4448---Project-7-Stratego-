import java.awt.Point;

public class Stratego{
    public static void main(String[] args){
        Game game = Game.getInstance();
        Input input = Input.getInstance();
        int whoseTurn;
        String[] turnColor = {"RED", "BLUE"};
        //System.out.println("hello");

        Player player1;
        Player player2;
        String player1Name = input.getString();
        player1 = new Player(player1Name);
        String player2Name = input.getString();
        player2 = new Player(player2Name);
        //set the players color
        //each player has a 50% chance of being either red or blue
        if(Math.random() < 0.5){
            player1.setColor("RED");
            player1.setPieces(game.getPieces("RED"));
            player2.setColor("BLUE");
            player2.setPieces(game.getPieces("BLUE"));
            whoseTurn = 0;
        }
        else{
            player1.setColor("BLUE");
            player1.setPieces(game.getPieces("BLUE"));
            player2.setColor("RED");
            player2.setPieces(game.getPieces("RED"));
            whoseTurn = 1;
        }

        //let the player set up their side of the board
        //once the player is done setting up, then
        //they are are ready
        boolean player1Ready = false;
        boolean player2Ready = false;
        while(!player1Ready && !player2Ready){
            if(!player1Ready){
                player1Ready = true;
            }
            else{
                player2Ready = true;
            }
        }
        Point start;
        Point end;
        while(game.getCont()){
            game.display(turnColor[whoseTurn]);
            start = input.getPoint();
            System.out.println(start.getX() + " " + start.getY());
            whoseTurn = 1 - whoseTurn;
            if (whoseTurn == 0){
                break;
            }
        }
    }
}