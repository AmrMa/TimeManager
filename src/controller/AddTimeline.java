package controller;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

import controller.MainStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.awt.SystemColor.window;

//Controller for the window where the user is creating new timeline
public class AddTimeline {
    @FXML
    private Button cancel;

    @FXML
    private Button display;
    
    @FXML
    void display(ActionEvent event) throws IOException {
        // this method should instantiate a timeline then jump to main stage
        // and create canvas and display menu and buttons and the timeline itself.

        MainStage appStage = new MainStage();

        // there is no left part there can be only one canvas.

        // there is no button list.

        BorderPane pane= new BorderPane();
        pane.prefHeightProperty().bind(appStage.getStage().heightProperty());
        pane.prefWidthProperty().bind(appStage.getStage().widthProperty());

        Scene sc = new Scene(pane);
        // a representation of the timeline should be called here we could create a rectangle or a hbox what do you guys think ?)
        // Scratch that i tried i think i will work on this tomorrow easy not really hard.



        Stage stage = null;
        stage.setScene(sc);
        stage.show();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {

    }

}
