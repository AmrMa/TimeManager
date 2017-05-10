package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.animation.FadeInRightTransition;
import main.common.AlertMessage;
import main.common.ScreenController;
import main.model.Timeline;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static main.common.StageManager.getStage;


public class NewTimelineFragment {
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;

    @FXML private Button ButtonBack;
    @FXML private AnchorPane PaneMain;
    @FXML private TextField timelineTitle;
    @FXML private TextField timelineDescription;
    @FXML private DatePicker timelineStartDate;
    @FXML private DatePicker timelineEndDate;
    @FXML private ChoiceBox<Integer> starthour;
    @FXML private ChoiceBox<Integer> starthour1;
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
        starthour.getItems().addAll(1,2,4,6,8,10,12,14,16,18,20,22,24);
        starthour1.getItems().addAll(1,2,4,6,8,10,12,14,16,18,20,22,24);
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
            System.out.println(starthour.getValue());
            ScreenController.setScreen(ScreenController.Screen.TIMELINE_DETAILS);
        }else{
            new FadeInRightTransition(timelineStartDate).play();
            new FadeInRightTransition(timelineEndDate).play(); // we could choose a better description for the alert of course.
            AlertMessage msg = new AlertMessage("Wrong Duration","Please specify correct timeline duration", Alert.AlertType.WARNING);
        }
    }

    private boolean correctDuration(LocalDate start, LocalDate end) { //this checks that end is older that the start.
        if(start.isAfter(end)||starthour1.getValue()<starthour.getValue())return false;
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

