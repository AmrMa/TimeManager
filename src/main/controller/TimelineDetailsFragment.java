package main.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.common.ScreenController;
import main.model.Event;
import main.model.Timeline;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import static main.common.StageManager.getStage;
import static main.controller.NewEventFragment.myEvent;
import static main.controller.NewTimelineFragment.myTime;


public class TimelineDetailsFragment {
    @FXML private Button ButtonBack;
    @FXML private AnchorPane myDisplay;
    @FXML private Button newEventButton;
    @FXML private Text title;
    @FXML private Label EndDate;
    @FXML private Label startDate;
    @FXML private Label Description;
    @FXML private ImageView timeline_image;
    @FXML private Button RemoveTimeline;
    @FXML private Button AddImage;
    @FXML private AnchorPane LeftPane;
    Timeline display = myTime;
    double lineHeight;
    Period timelinePeriod;
    private Line lineTimeline;
    ArrayList<LocalDate> duplicates = new ArrayList<LocalDate>();
    public void initialize() throws SQLException {
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        lineHeight = myDisplay.getLayoutY() / 2;
        timelinePeriod = display.getStartDate().until(display.getEndDate());
        title.setText("Title: "+display.getTitle());
        EndDate.setText("StartDate"+display.getEndDate().toString());
        startDate.setText("EndDate"+display.getStartDate().toString());
        Description.setText(display.getDescription());
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
        	VBox vbox = new VBox();
            LocalDate eventMoment = e.getEvent_startDate();
            Period periodUntilEvent = display.getStartDate().until(eventMoment);

            int totalDays = timelinePeriod.getDays();
            int daysUntilEvent = periodUntilEvent.getDays();
            System.out.println("Totaldays: " + totalDays);
            System.out.println("Days until event: " + daysUntilEvent);

            int relativePosition = (daysUntilEvent * 100) / totalDays;

            double positionToPutEvent = myDisplay.getPrefWidth() * relativePosition / 100;
            System.out.println("Position to put event: " + positionToPutEvent);
            Circle eventCircle = new Circle(10, Color.WHITE);
            eventCircle.setStrokeWidth(1.0);
            eventCircle.setStroke(Color.BLACK);
            eventCircle.setOnMouseEntered(event -> getStage().getScene().setCursor(Cursor.HAND));
            eventCircle.setOnMouseExited(event -> getStage().getScene().setCursor(Cursor.DEFAULT));
            eventCircle.setOnMouseClicked(event -> {
                myEvent = e;
                try {
                    ScreenController.setScreen(ScreenController.Screen.eventDetailsfragment);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            Label dateOfEvent = new Label(e.getEvent_startDate().toString());
            Label titleOfEvent = new Label(e.getEvent_title());
            titleOfEvent.setFont(Font.font(15));
            vbox.getChildren().addAll(eventCircle,titleOfEvent,dateOfEvent);        
            duplicates.add(e.getEvent_startDate());
            int y=100;
           if(duplicates(duplicates)){
        	   vbox.relocate(positionToPutEvent - 5,lineHeight-y);
        	   y=y+10;
        	   duplicates.clear();
           }
           else
            vbox.relocate(positionToPutEvent - 5,lineHeight-10);
            
          
            myDisplay.getChildren().add(vbox);
           
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
   
    boolean duplicates(final ArrayList<LocalDate> zipcodelist)
    {
      Set<LocalDate> lump = new HashSet<LocalDate>();
      for (LocalDate i : zipcodelist)
      {
        if (lump.contains(i)) return true;
        lump.add(i);
      }
      return false;
    }
    @FXML
    void Addimage(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
         
         //Set extension filter
         FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
         FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
         fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
           
         //Show open file dialog
         File file = fileChooser.showOpenDialog(null);
                    
         try {
             BufferedImage bufferedImage = ImageIO.read(file);
             Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             timeline_image.setImage(image);
         } catch (IOException ex) {
          
         }
    }
    @FXML
    void Removetimeline(ActionEvent event) throws IOException{
    	myDisplay.getChildren().clear();
    	LeftPane.getChildren().clear();
    	timeline_image.setImage(null);
    }
}