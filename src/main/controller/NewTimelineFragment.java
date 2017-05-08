package main.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.animation.FadeInRightTransition;
import main.common.AlertMessage;
import main.common.ScreenController;
import main.model.Timeline;
import main.model.TimelineDB;
import main.utils.Loading;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static main.common.StageManager.getStage;


public class NewTimelineFragment {
    @FXML private GridPane PaneAdd;
    @FXML private GridPane PaneTabel;
    @FXML private HBox PaneTop;

    @FXML private JFXButton deleteBtn;
    @FXML private JFXButton displayBtn;
    @FXML private JFXButton cancelBtn;
    @FXML private JFXButton saveBtn;
    @FXML private Button ButtonBack;
    @FXML private AnchorPane PaneMain;
    @FXML private JFXTextField timelineTitle;
    @FXML private JFXTextField timelineDescription;
    @FXML private JFXDatePicker timelineStartDate;
    @FXML private JFXDatePicker timelineEndDate;
    static Timeline myTime = new Timeline();
    static int numberOfTimelines=0;
    private boolean isCreated=false;

    public void initialize() throws SQLException {
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));

        cancelBtn.getStyleClass().add("button-flat");
        saveBtn.getStyleClass().add("button-flat");

        cancelBtn.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        cancelBtn.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
    }
    @FXML
    public void back() throws IOException{ScreenController.setScreen(ScreenController.Screen.HOME);}

    @FXML
    public void cancelCreate() throws IOException {ScreenController.setScreen(ScreenController.Screen.HOME);}

    @FXML
    public void saveTimelineDetails() throws IOException,NumberFormatException {
        if (correctDuration(timelineStartDate.getValue(),timelineEndDate.getValue()) && !timelineTitle.getText().equals("")){
            myTime.setId(numberOfTimelines++);
            myTime.setTitle(timelineTitle.getText());
            myTime.setDescription(timelineDescription.getText());
            isCreated=true;
            //change button save with button display and cancel with a delete button if user clicks display we move to timeline view fragment (projects fragment)

            ScreenController.setScreen(ScreenController.Screen.TIMELINE_DETAILS);
        }else{
            new FadeInRightTransition(timelineStartDate).play();
            new FadeInRightTransition(timelineEndDate).play(); // we could choose a better description for the alert of course.
            AlertMessage msg = new AlertMessage("Wrong Duration","Please specify correct timeline duration", Alert.AlertType.WARNING);
        }
    }

    private boolean correctDuration(LocalDate start, LocalDate end) { //this checks that end is older that the start.
        if(start.isAfter(end)|| start.isEqual(end))return false;
        else{
            myTime.setStartDate(timelineStartDate.getValue());
            myTime.setEndDate(timelineEndDate.getValue());
            return true;
        }

    }

    @FXML
    public void addEvent() {
       // if(isCreated) then pop up window or anchor pane fields fade in.
    }
}

