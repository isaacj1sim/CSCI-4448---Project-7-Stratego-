import java.awt.Point;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class Stratego extends Application{
    Button exit;
    Game game = Game.getInstance();
    int whoseTurn = 0;
    String[] turnColor = {"RED", "BLUE"};
    int turnCounter = 0;
    boolean gameOver = false;
    boolean turnDone = false;
    static final DataFormat CELL = new DataFormat("Cell");
    Scene scene;
    ArrayList<Box> boxes = new ArrayList<Box>();

    public static void main(String[] args){
        launch(args);
    }

    private Parent createContent(){
        //set a pane to place all the elements in
        Pane root = new Pane();
        root.setPrefSize(600,600);
        boxes.clear();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                //get a cell in the game;
                Cell cell = game.getCell(i,j);
                //translate it into a box for displaying
                Box box = new Box(cell, whoseTurn);
                box.setTranslateX(50 * ((i * 10 + j) % 10));
                box.setTranslateY(50 * (i % 10));

                //source
                box.setOnDragDetected(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent event) {
                        /* drag was detected, start drag-and-drop gesture*/
                        System.out.println("onDragDetected");
                        
                        /* allow any transfer mode */
                        Dragboard db = box.startDragAndDrop(TransferMode.ANY);
                        
                        /* put a string on dragboard */
                        ClipboardContent content = new ClipboardContent();
                        content.put(CELL, box.getCell());
                        db.setContent(content);
                        
                        event.consume();
                    }
                });
        
                //target
                box.setOnDragOver(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the box */
                        System.out.println("onDragOver");
                        
                        /* accept it only if it is  not dragged from the same node 
                         * and if it has a string data */
                        Dragboard db = event.getDragboard();
                        if (event.getGestureSource() != box &&
                                db.hasContent(CELL)) {
                            /* allow for both copying and moving, whatever user chooses */
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }
                        
                        event.consume();
                    }
                });
                
                //target
                box.setOnDragDropped(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data dropped */
                        System.out.println("onDragDropped");
                        /* if there is a string data on dragboard, read it and use it */
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        Point p = box.getPoint();
                        if (db.hasContent(CELL)) {
                            Cell c = (Cell)db.getContent(CELL);
                            if(Move.validMove(c, box.getCell()) && c.getPieceColor().equalsIgnoreCase(turnColor[whoseTurn])){
                                if(Move.win(c, box.getCell()) == -1){
                                    int x = (int)p.getX();
                                    int y = (int)p.getY();
                                    Cell empty = new Cell(x,y);
                                    box.setCell(empty, whoseTurn);
                                }
                                else if(Move.win(c, box.getCell()) == 1){
                                    c.setPoint(p);
                                    box.setCell(c, whoseTurn);
                                }
                                else if(Move.win(c, box.getCell()) == -2){
                                    c.setPoint(p);
                                    box.setCell(c, whoseTurn);
                                    gameOver = true;
                                    System.out.println("win");
                                }
                                success = true;
                            }
                        }
                        /* let the source know whether the string was successfully 
                         * transferred and used */
                        event.setDropCompleted(success);
                        
                        event.consume();
                    }
                });
                
                //source
                box.setOnDragDone(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture ended */
                        System.out.println("onDragDone");
                        Point p = box.getPoint();
                        int x = (int)p.getX();
                        int y = (int)p.getY();
                        Cell c = new Cell(x,y);
                        /* if the data was successfully moved, clear it */
                        if (event.getTransferMode() == TransferMode.MOVE) {
                            box.setCell(c, whoseTurn);
                            System.out.println(c.getPoint().getX() + " " + c.getPoint().getY());
                            System.out.println("dragcomplete");
                            turnDone = true;
                        }
                        event.consume();
                    }
                });
                boxes.add(box);
            }
        }
        for(Box box : boxes){
            root.getChildren().add(box);
        }
        String s = "Turn: " + turnCounter;
        Label turnCount = new Label(s);
        turnCount.setTranslateX(510);
        turnCount.setTranslateY(50);
        root.getChildren().add(turnCount);
        Button b = new Button("Next Turn");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(turnDone){
                    whoseTurn = 1 - whoseTurn;
                    turnCounter++;
                    turnDone = false;
                    String s = "Turn: " + turnCounter;
                    turnCount.setText(s);
                }
                if(gameOver){
                    System.out.println("winner");
                    whoseTurn = 1 - whoseTurn;
                    Label label = new Label(turnColor[whoseTurn] + " wins!");
                    label.setTranslateX(510);
                    label.setTranslateY(130);

                    root.getChildren().add(label);
                }
            }
        };
        b.setOnAction(event);
        b.setTranslateX(510);
        b.setTranslateY(100);
        root.getChildren().add(b);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Stratego Game");
        scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class Box extends StackPane{
        String color = "";
        String value = "";
        Cell cell = null;
        Point p;
        boolean showRed;
        boolean showBlue;

        public Box(Cell c, int turn){
            if(turn == 0){
                showRed = true;
                showBlue = true;
            }
            else{
                showRed = true;
                showBlue = true;
            }
            cell = c;
            p = c.getPoint();
            Rectangle border = new Rectangle(50,50);
            //if the cel is empty
            if(c.getPiece() == null){
                color = "";
                border.setFill(null);
                border.setStroke(Color.BLACK);
                Text text = new Text("-");
                value = "-";
                text.setFont(Font.font(30));

                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
            //if the cell is red
            else if(c.getPieceColor().equalsIgnoreCase("RED")){
                color = "RED";
                border.setFill(Color.RED);
                border.setStroke(Color.BLACK);
                Text text;
                if(showRed || c.getPieceRevealed()){
                    String pieceValue;
                    switch(c.getPieceValue()){
                        case -1:
                            pieceValue = "B";
                            break;
                        case 0:
                            pieceValue = "F";
                            break;
                        case 1:
                            pieceValue = "S";
                            break;
                        case 10:
                            pieceValue = "0";
                            break;
                        //default: just print the value of the piece
                        default:
                            pieceValue = String.valueOf(c.getPieceValue());
                            break;
                    }
                    text = new Text(pieceValue);
                    value = pieceValue;
                }
                else{
                    text = new Text("-");
                    value = "-";
                }
                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
            //if the cell is blue
            else if(c.getPieceColor().equalsIgnoreCase("BLUE")){
                color = "BLUE";
                border.setFill(Color.BLUE);
                border.setStroke(Color.BLACK);
                Text text;
                if(showBlue || c.getPieceRevealed()){
                    String pieceValue;
                    switch(c.getPieceValue()){
                        case -1:
                            pieceValue = "B";
                            break;
                        case 0:
                            pieceValue = "F";
                            break;
                        case 1:
                            pieceValue = "S";
                            break;
                        case 10:
                            pieceValue = "0";
                            break;
                        //default: just print the value of the piece
                        default:
                            pieceValue = String.valueOf(c.getPieceValue());
                            break;
                    }
                    text = new Text(pieceValue);
                    value = pieceValue;
                }
                else{
                    text = new Text("-");
                    value = "-";
                }
                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
            //anything else is a lake
            else{
                border.setFill(null);
                border.setStroke(Color.BLACK);
                Text text = new Text("#");
                value = "#";
                text.setFont(Font.font(30));

                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
        }

        public void setCell(Cell c, int turn){
            cell = c;
            p = cell.getPoint();
            getChildren().clear();
            Rectangle border = new Rectangle(50,50);
            if(c.getPiece() == null){
                color = "";
                border.setFill(null);
                border.setStroke(Color.BLACK);
                Text text = new Text("-");
                value = "-";
                text.setFont(Font.font(30));

                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
            else if(c.getPieceColor().equalsIgnoreCase("RED")){
                color = "RED";
                border.setFill(Color.RED);
                border.setStroke(Color.BLACK);
                Text text;
                if(showRed || c.getPieceRevealed()){
                    String pieceValue;
                    switch(c.getPieceValue()){
                        case -1:
                            pieceValue = "B";
                            break;
                        case 0:
                            pieceValue = "F";
                            break;
                        case 1:
                            pieceValue = "S";
                            break;
                        case 10:
                            pieceValue = "0";
                            break;
                        //default: just print the value of the piece
                        default:
                            pieceValue = String.valueOf(c.getPieceValue());
                            break;
                    }
                    text = new Text(pieceValue);
                    value = pieceValue;
                }
                else{
                    text = new Text("-");
                    value = "-";
                }
                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
            else if(c.getPieceColor().equalsIgnoreCase("BLUE")){
                color = "BLUE";
                border.setFill(Color.BLUE);
                border.setStroke(Color.BLACK);
                Text text;
                if(showBlue || c.getPieceRevealed()){
                    String pieceValue;
                    switch(c.getPieceValue()){
                        case -1:
                            pieceValue = "B";
                            break;
                        case 0:
                            pieceValue = "F";
                            break;
                        case 1:
                            pieceValue = "S";
                            break;
                        case 10:
                            pieceValue = "0";
                            break;
                        //default: just print the value of the piece
                        default:
                            pieceValue = String.valueOf(c.getPieceValue());
                            break;
                    }
                    text = new Text(pieceValue);
                    value = pieceValue;
                }
                else{
                    text = new Text("-");
                    value = "-";
                }
                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
            else{
                border.setFill(null);
                border.setStroke(Color.BLACK);
                Text text = new Text("#");
                value = "#";
                text.setFont(Font.font(30));

                setAlignment(Pos.CENTER);
                getChildren().addAll(border, text);
            }
        }

        public Cell getCell(){
            return cell;
        }

        public Point getPoint(){
            return p;
        }
    }
}