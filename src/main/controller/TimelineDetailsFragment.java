package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import main.common.ScreenController;
import main.model.Event;
import main.model.Timeline;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static main.common.StageManager.getStage;
import static main.controller.NewEventFragment.myEvent;
import static main.controller.NewTimelineFragment.myTime;


public class TimelineDetailsFragment {
    @FXML private Button ButtonBack;
    @FXML private AnchorPane myDisplay;
    @FXML private Button newEventButton;
    @FXML private ScrollPane scrollPane;
    @FXML private Separator separator;
    @FXML private AnchorPane PaneMain;

    Timeline display = myTime;
    double lineHeight;
    int timelinePeriodInDays;
    private Line lineTimeline;


    public void initialize() throws SQLException {
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));

        // The height of the actual line is calculated based on the height of the Scrollpane:
        lineHeight = scrollPane.getPrefHeight() / 2;
        timelinePeriodInDays = (int) ChronoUnit.DAYS.between(display.getStartDate(),display.getEndDate());

        displayTimeline();
        displayEvents();

        // The PaneMain is the parent pane. It holds the Scrollpane inside it and therefore must be bounded to the
        // changing size of the stage.
        PaneMain.prefHeightProperty().bind(getStage().heightProperty());
        PaneMain.prefWidthProperty().bind(getStage().widthProperty());

        // The area in which the Timeline is shown is a Scrollpane. If the user makes the screen wider, the width of the
        // scrollpane is bound to the changing width of the stage.
        // The subtraction is needed to account for the width of the ListView.
        scrollPane.prefWidthProperty().bind(getStage().widthProperty().subtract(255));
    }

    /*
     * The length of the line is always fixed to an X of 1600.
     */

    private void displayTimeline() {
        lineTimeline = new Line(0,lineHeight,1600,lineHeight); //TODO make 1600 based on user input?
        myDisplay.getChildren().add(lineTimeline);

        double distanceBetweenLines = 1600 / timelinePeriodInDays;
        if (timelinePeriodInDays < 60) {
            for (int i = 1; i < timelinePeriodInDays; i++) {
                Line verticalLine = new Line(i * distanceBetweenLines, lineHeight - 5, i * distanceBetweenLines, lineHeight + 5);
                myDisplay.getChildren().add(verticalLine);
            }
        } else if (timelinePeriodInDays < 300) {
            for (int i = 0; i < timelinePeriodInDays; i += 7) {
                Line verticalLine = new Line(i * distanceBetweenLines, lineHeight - 5, i * distanceBetweenLines, lineHeight + 5);
                myDisplay.getChildren().add(verticalLine);
            }
        }
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
         * put the event. This position is then used by creating a line on the timeline.
         *
         */
        for (Event e: events) {
            LocalDate eventMoment = e.getEvent_startDate();
            long totalDays = ChronoUnit.DAYS.between(display.getStartDate(),display.getEndDate()); //timelinePeriod.getDays();
            long daysUntilEvent = ChronoUnit.DAYS.between(display.getStartDate(),eventMoment);

            // Calculate position on line to put event.
            int relativePositionOfEvent = (int) ((daysUntilEvent * 100) / totalDays);
            double positionToPutEvent = 1600 * relativePositionOfEvent / 100;

            Circle eventCircle = new Circle(10, Color.WHITE);
            eventCircle.setStrokeWidth(1.0);
            eventCircle.setStroke(Color.BLACK);
            eventCircle.relocate(positionToPutEvent - 5,lineHeight - 10);
            eventCircle.setOnMouseEntered(event -> getStage().getScene().setCursor(Cursor.HAND));
            eventCircle.setOnMouseExited(event -> getStage().getScene().setCursor(Cursor.DEFAULT));
            eventCircle.setOnMouseClicked(event -> {
                myEvent = e;
                try {
                    ScreenController.setScreen(ScreenController.Screen.NEW_EVENT);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            myDisplay.getChildren().add(eventCircle);

            Label dateOfEvent = new Label(e.getEvent_startDate().toString());
            dateOfEvent.relocate(positionToPutEvent - 5,lineHeight + 20);
            myDisplay.getChildren().add(dateOfEvent);

            Label titleOfEvent = new Label(e.getEvent_title());
            titleOfEvent.relocate(positionToPutEvent - 5,lineHeight - 40);
            titleOfEvent.setFont(Font.font(15));
            myDisplay.getChildren().add(titleOfEvent);
        }
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
