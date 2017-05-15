package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import main.animation.FadeInRightTransition;
import main.common.AlertMessage;

import javafx.scene.input.MouseEvent;

import main.common.ScreenController;
import main.model.Event;

import java.io.IOException;
import java.time.LocalDate;

import static main.controller.NewTimelineFragment.myTime;

public class NewEventFragment {

    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private TextField eventTitle;
    @FXML private DatePicker eventDate;
    static Event myEvent=  new Event();

   

    public void back() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.TIMELINE_DETAILS);
    }

    @FXML
    public void cancelNewEvent() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.TIMELINE_DETAILS);
    }

    @FXML
    public void saveEvent() throws IOException {
   
    	if(eventDate.getValue().isBefore(myTime.getEndDate())&eventDate.getValue().isAfter(myTime.getStartDate())&eventTitle!=null){
        myEvent = new Event(eventTitle.getText(),"TEST DESCRIPTION",eventDate.getValue());
        myTime.addEvent(myEvent);
        ScreenController.setScreen(ScreenController.Screen.eventDetailsfragment);
    	}
    	else{
           
            AlertMessage msg = new AlertMessage("Wrong Duration","Please specify correct event duration", Alert.AlertType.WARNING);
        }
    }


    @FXML
    public void durationalEvent(MouseEvent mouseEvent) {
    }
}
