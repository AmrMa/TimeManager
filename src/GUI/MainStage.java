package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class MainStage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // I thought making a BorderPane would be an idea to divide parts, such as canvas on the right
        // and buttons on the left?


        /**
         * Right part, containing view of timelines. A scrollpane inside the right part of the borderpane
         * would be the best solution, imo.
         */
        final Random rng = new Random();
        VBox timelineContent = new VBox(5);
        timelineContent.setPrefWidth(600);
        timelineContent.setPrefHeight(900);

        ScrollPane scrollPane = new ScrollPane(timelineContent);
        // To determine the area where scrollbars will come into play:
        //scrollPane.minViewportHeightProperty().set(600);
        //scrollPane.minViewportWidthProperty().set(800);

        Button addButton = new Button("Add timeline");
        addButton.setOnAction(e -> {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                            "-fx-background-color: -fx-background;",
                    rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            anchorPane.setStyle(style);
            Label label = new Label("Timeline "+(timelineContent.getChildren().size()+1));
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
            Button button = new Button("Remove");
            button.setOnAction(evt -> timelineContent.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            timelineContent.getChildren().add(anchorPane);
        });

        /**
         * Left part:
         */
        VBox leftContent = new VBox(5);
        ScrollPane buttonList = new ScrollPane(leftContent);
        leftContent.setPrefHeight(900);
        leftContent.setPrefWidth(200);

        leftContent.getChildren().add(addButton);

        BorderPane borderPane = new BorderPane(scrollPane, null, null, null, buttonList);
        //borderPane.minHeight(400);
        Scene scene = new Scene(borderPane);

        // Set the size of the main window:
        //stage.setMinWidth(1600);
        //stage.setMinHeight(900);
        stage.setScene(scene);
        stage.show();
    }
}
