package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import main.common.ScreenController;
import main.model.Event;
import main.model.Timeline;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import static main.common.StageManager.getStage;
import static main.controller.NewEventFragment.myEvent;
import static main.controller.NewTimelineFragment.myTime;


public class TimelineDetailsFragment {
    @FXML private Button ButtonBack;
    @FXML private AnchorPane myDisplay;
    @FXML private Button newEventButton;
    //I am Alex. Hello from the other side
    @FXML private Button editButton;

    Timeline display = myTime;
    double lineHeight;
    Period timelinePeriod;
    private Line lineTimeline;


    public void initialize() throws SQLException {
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        lineHeight = myDisplay.getLayoutY() / 2;
        timelinePeriod = display.getStartDate().until(display.getEndDate());

        displayTimeline(timelinePeriod.getDays());
        displayEvents();

    }

    private void displayTimeline(int period) {
        lineTimeline = new Line(0,lineHeight,myDisplay.getPrefWidth(),lineHeight);
        myDisplay.getChildren().add(lineTimeline);
        double distanceBetweenLines = myDisplay.getPrefWidth() / period;
        for (int i = 1; i < timelinePeriod.getDays(); i++) {
            Line verticalLine = new Line(i * distanceBetweenLines,lineHeight-5,i * distanceBetweenLines, lineHeight+5);
            myDisplay.getChildren().add(verticalLine);
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
            Period periodUntilEvent = display.getStartDate().until(eventMoment);

            int totalDays = timelinePeriod.getDays();
            int daysUntilEvent = periodUntilEvent.getDays();
            System.out.println("Totaldays: " + totalDays);
            System.out.println("Days until event: " + daysUntilEvent);

            int relativePosition = (daysUntilEvent * 100) / totalDays;

            double positionToPutEvent = myDisplay.getPrefWidth() * relativePosition / 100;
            System.out.println("Position to put event: " + positionToPutEvent);

            //ImageView eventCircle = new ImageView(new Image("resources/img/anEvent.png"));
            Circle eventCircle = new Circle(10, Color.WHITE);
            eventCircle.setStrokeWidth(1.0);
            eventCircle.setStroke(Color.BLACK);
            eventCircle.relocate(positionToPutEvent - 5,lineHeight - 10);
//            eventCircle.setFitHeight(20);
//            eventCircle.setPreserveRatio(true);
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
            //Line eventLine = new Line(positionToPutEvent,lineHeight - 10,positionToPutEvent,lineHeight + 10);
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
    
    
    // Alex returns you to edit the timeline to create place
    @FXML
    public void editTimeline() throws IOException{
    	 ScreenController.setScreen(ScreenController.Screen.NEW_TIMELINE);
    }
    
    
    
    
}
