package main.controller;

import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Controller for the window where the user is creating new timeline
public class AddTimeline {
    @FXML
    private Button cancelBtn;

    @FXML
    private Button displayBtn;
    
    @FXML
    void displayBtn(ActionEvent event) throws IOException { 
    	StartScreen.AddTimeline.hide();
    	Stage stage = new Stage();
    	System.out.print("i am the display button");
    	// Here we write a code for creating a timeline
    	final Random rng = new Random();
        VBox timelineContent = new VBox(5);
        timelineContent.setPrefWidth(900);
        timelineContent.setPrefHeight(400);

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
    @FXML
    void cancelBtn(ActionEvent event) throws IOException { 
    	System.out.print("i am the not display button");
    	// Here we write a code for canceling creating  a timeline
    	
    }

}
