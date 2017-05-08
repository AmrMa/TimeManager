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
import main.model.Event;
import main.model.Timeline;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import static main.common.StageManager.getStage;
import static main.controller.NewTimelineFragment.myTime;


public class TimelineDetailsFragment {
    @FXML private Button ButtonBack;
    @FXML private AnchorPane myDisplay;
    @FXML private Button newEventButton;

    Timeline display = myTime;
    double lineHeight;
    Period timelinePeriod;
    private Line lineTimeline;


    public void initialize() throws SQLException {
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        lineHeight = myDisplay.getLayoutY() / 2;
        timelinePeriod = display.getStartDate().until(display.getEndDate());

        displayTimeline();
        displayEvents();

    }

    private void displayTimeline() {
        lineTimeline = new Line(0,lineHeight,myDisplay.getPrefWidth(),lineHeight);
        myDisplay.getChildren().add(lineTimeline);
    }

    private void displayEvents() {
        ArrayList<Event> events = myTime.getListOfEvents();

        /**
         * For each event, I try to calculate the position of the event on the timeline.
         * I do this by getting the total length of the line, being the total period in days of the timeline.
         * I compare this with the days until the event takes place, given the date of the event and the start date of
         * the timeline.
         *
         * Dividing the days until the event with the total days of the timeline, I get the relative position of where to
         * put the event.
         *
         */
        for (Event e: events) {
            LocalDate eventMoment = e.getEvent_startDate();
            Period periodUntilEvent = display.getStartDate().until(eventMoment);

            int totalDays = timelinePeriod.getDays();
            int daysUntilEvent = periodUntilEvent.getDays();
            System.out.println("Totaldays: " + totalDays);
            System.out.println("Days until event: " + daysUntilEvent);

            int relativePosition = (daysUntilEvent * 100) / totalDays;

            double positionToPutEvent = myDisplay.getPrefWidth() * relativePosition / 100;
            System.out.println("Position to put event: " + positionToPutEvent);

            Line eventLine = new Line(positionToPutEvent,lineHeight - 10,positionToPutEvent,lineHeight + 10);
            myDisplay.getChildren().add(eventLine);

        }


        Line eventMoment = new Line();
    }

    @FXML
    public void back() throws IOException {ScreenController.setScreen(ScreenController.Screen.HOME);}

    //It is not a task, it is an event we should add.
    public void addTask(){

    }

    @FXML
    public void addEvent() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.NEW_EVENT);
    }
}
