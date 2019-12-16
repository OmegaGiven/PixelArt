import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;


public class Main extends Application {
    int[][] digitalGrid;
    String currentColor;

    public GridPane grid(int gridSizeX, int gridSizeY){
        digitalGrid = new int[gridSizeX][gridSizeY];
        GridPane grid = new GridPane();
        for (int x = 0; x < gridSizeX; x++){
            for (int y = 0; y < gridSizeY; y++){
                Button button = new Button();
                changeGrid(x, y, "null");
                button.setMinHeight(8);
                button.setMaxHeight(15);
                button.setMinWidth(8);
                button.setMaxWidth(15);
                grid.add(button,x,y);
                int ButtonX = x;
                int ButtonY = y;
                button.setOnAction(a -> {
                    if (currentColor == "DELETE") button.setStyle(null);
                    else button.setStyle("-fx-background-color: " + currentColor);
                    changeGrid(ButtonX, ButtonY, currentColor);
                });
            }
        }
        return grid;
    }

    public void changeGrid(int x, int y, String color){
        if (color == "RED") digitalGrid[x][y] = 2;
        if (color == "BLUE") digitalGrid[x][y] = 3;
        if (color == "GREEN") digitalGrid[x][y] = 4;
        if (color == "BLACK") digitalGrid[x][y] = 1;
        if (color == "DELETE" || color == "null") digitalGrid[x][y] = 0;

    }

    public void openFile(){
        /**
         * open file implementation here.
         */
    }


    public VBox buttons(BorderPane gui){
        VBox buttonPanel = new VBox();

        Text description = new Text("GridSize X");
        TextField gridSizeBox = new TextField();
        buttonPanel.getChildren().addAll(description, gridSizeBox);

        Text description2 = new Text("GridSize Y");
        TextField gridSizeBox2 = new TextField();
        buttonPanel.getChildren().addAll(description2, gridSizeBox2);

        String[] colors = {"RED", "BLUE", "GREEN", "BLACK", "DELETE"};
        for(String i : colors) {
            Button color = new Button(i);
            color.setOnMouseClicked(e -> {
                currentColor = i;
                Color textColor = Color.BLACK;
                if (i == "RED") textColor = Color.RED;
                if (i == "BLUE") textColor = Color.BLUE;
                if (i == "GREEN") textColor = Color.GREEN;
                if (i == "BLACK") textColor = Color.BLACK;
                if (i == "DELETE") textColor = Color.BLACK;
                Text displayText = new Text("Currently selected color; " + i);
                displayText.setFill(textColor);
                gui.setTop(displayText);
            });
            buttonPanel.getChildren().add(color);
        }

        Text saveName = new Text("Save Name;");
        Label name = new Label("no text");
        TextField nameField = new TextField();
        Button save = new Button("Save To File");
        save.setOnMouseClicked(e->{
            name.setText(nameField.getText());
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(name.getText() + ".txt"));
                bw.write(processOutput(digitalGrid));
                bw.close();
            }catch( IOException v){
                v.printStackTrace();
            }
        });

        buttonPanel.getChildren().addAll(saveName, nameField, save);

        return buttonPanel;
    }

    public String processOutput(int[][] input){
        String output = "";
        for (int x = 0; x < input.length; x++){
            for (int y = 0; y < input.length; y++){
                output += input[x][y];
            }
            output += "\n";
        }
        return output;
    }


    public void start(Stage stage){
        BorderPane gui = new BorderPane();
        currentColor = "BLACK";
        gui.setTop(new Text("Currently selected color; " + currentColor));

        gui.setRight(buttons(gui));
        gui.setCenter(grid(40, 40));

        gui.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(gui);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
