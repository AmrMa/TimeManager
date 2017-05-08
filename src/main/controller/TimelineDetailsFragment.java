package main.controller;

import com.jfoenix.controls.JFXButton;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.common.ScreenController;
import main.model.Timeline;
import main.model.TimelineDB;
import main.model.TimelineView;
import main.utils.Loading;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

    @FXML
    public void addTask(){

    }

}
