import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    int[][] digitalGrid = new int[40][40];

    public GridPane grid(int gridSizeX, int gridSizeY, BorderPane gui){
        GridPane grid = new GridPane();
        for (int x = 0; x < gridSizeX; x++){
            for (int y = 0; y < gridSizeY; y++){
                Rectangle rectangle = new Rectangle(10,10);
                Button button = new Button();
                button.set(rectangle);
                grid.add(button,x,y);
                digitalGrid[x][y] = 0;
                VBox rightPanel = buttons();
                gui.setRight(rightPanel);
                button.setOnAction(a ->{
                    String[] colors = {"RED", "BLUE", "GREEN", "BLACK"};
                    for(String i : colors){
                        Button color = new Button(i);
                        color.setOnMouseClicked(e->{
                            button.setStyle("-fx-background-color: " + i);;
                        });
                        rightPanel.getChildren().add(color);
                    }
                    gui.setRight(rightPanel);
                });


            }
        }
        return grid;
    }


    public VBox buttons(){
        VBox buttonPanel = new VBox();

        Text description = new Text("GridSize X");
        TextField gridSizeBox = new TextField();
        buttonPanel.getChildren().addAll(description, gridSizeBox);

        Text description2 = new Text("GridSize Y");
        TextField gridSizeBox2 = new TextField();
        buttonPanel.getChildren().addAll(description2, gridSizeBox2);
        return buttonPanel;
    }


    public void start(Stage stage){
        BorderPane gui = new BorderPane();

        gui.setCenter(grid(40, 40, gui));

        gui.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(gui);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
