import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.*;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;
        import javafx.scene.shape.Rectangle;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;
        import javafx.scene.Parent;

        import javafx.scene.layout.BackgroundImage;
        import javafx.scene.layout.Background;
        import javafx.scene.layout.BackgroundPosition;
        import javafx.scene.layout.BackgroundRepeat;
        import javafx.scene.layout.BackgroundSize;
        import javafx.scene.layout.StackPane;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

public class GUI extends Application {
    Button button;
    private static final int size = 10;
    public static void main(String[] args){
        launch(args);
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(600,600);


        /**
         * Populates cells array with Cell objects
         * c is placeholder for value (future implementation)
         */
        int c = 1;
        Cell[][] cells = new Cell[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                cells[i][j] = new Cell(String.valueOf(c));
                c++;
            }
        }

        /**
         * Translate and Display each individual cell on grid
         */
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                Cell cell = cells[i][j];
                cell.setTranslateX(50 * ((i*10+j) % size));
                cell.setTranslateY(50 * (i % size));
                root.getChildren().add(cell);
            }
        }
        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Stratego Online Game");

        //use lambda functions to expand buttons later
        button = new Button();
        button.setText("Exit Game");

        /**
         * Import Images from web into Objects
         */
        //primaryStage.setWidth(300);
        //primaryStage.setHeight(300);
        //VBox root = new VBox();
        //InputStream inputStream = new FileInputStream("/Users/admin/Desktop/greenbackground.jpeg");
        //Image image = new Image(inputStream);
        //ImageView imageView = new ImageView();
        //imageView.setImage(image);
        //imageView.setX(100);
        //imageView.setY(100);
        //root.getChildren().addAll(imageView);
        //Scene scene = new Scene(root);
        //primaryStage.setScene(scene);

        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

    }


    private class Cell extends StackPane {
        //Needs to pass in parameters
        //Temp String in as place holder
        public Cell(String value) {
            Rectangle border = new Rectangle(50,50);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            Text text = new Text(value);
            text.setFont(Font.font(30));

            //Centralize alignment in cell
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

        }
    }
}
