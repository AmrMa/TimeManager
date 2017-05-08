package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import main.common.ScreenController;
import main.model.Timeline;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Period;

import static main.common.StageManager.getStage;
import static main.controller.NewTimelineFragment.myTime;


public class TimelineDetailsFragment {
    @FXML private Button ButtonBack;
    @FXML private AnchorPane myDisplay;
    @FXML private ImageView ButtonAdd;
    @FXML private ImageView event;
    Timeline display = myTime;

    public void initialize() throws SQLException {
        event.setVisible(false);
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        Period s =display.getStartDate().until(display.getEndDate());
        displayTime(s.getDays());

    }

    private void displayTime(int sep) {
        Line doIt = new Line(0,myDisplay.getLayoutY()/2,myDisplay.getPrefWidth(),myDisplay.getLayoutY()/2);
        myDisplay.getChildren().add(doIt);
    }

    @FXML
    public void back() throws IOException {ScreenController.setScreen(ScreenController.Screen.HOME);}

    //It is not a task, it is an event we should add.
    public void addTask(){

    }

    @FXML
    public void addEvent() {

    }
}
