package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

    Timeline display = myTime;
    int sep;
    boolean isWeeks,isYears,isDays;
    public void initialize() throws SQLException {
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

    @FXML
    public void eventAdd(){}

    public void eventAdd(MouseEvent mouseEvent) {
    }
}
