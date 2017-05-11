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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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

import javax.imageio.ImageIO;

import com.sun.glass.ui.Window.Level;
import com.sun.javafx.logging.Logger;

import static main.common.StageManager.getStage;
import static main.controller.NewEventFragment.myEvent;
import static main.controller.NewTimelineFragment.myTime;


public class TimelineDetailsFragment {
    @FXML private Button ButtonBack;
    @FXML private AnchorPane myDisplay;
    @FXML private Button newEventButton;
    @FXML private ImageView imageView;
    @FXML private Button addImage;
    @FXML private Button edittimeline;
    @FXML
    private Button removetimeline;
    @FXML private Text EndTime;
    @FXML private Text StartTime;
    @FXML private Text Description;
    Timeline display = myTime;
    double lineHeight;
 
   @FXML Text title;
   @FXML private AnchorPane Event_Viewer;
    public void initialize() throws SQLException {
        ButtonBack.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonBack.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        lineHeight = myDisplay.getLayoutY() / 2;       
        title.setText(display.getTitle());
        StartTime.setText(display.getStartDate().toString());
        EndTime.setText(display.getEndDate().toString());
        Description.setText(display.getDescription());
        displayEvents();

    }


    private void displayEvents() {
        ArrayList<Event> events = myTime.getListOfEvents();
        for (Event e: events) {
        Label titleOfEvent = new Label(e.getEvent_title());
        Event_Viewer.getChildren().addAll(titleOfEvent);
      
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
             imageView.setImage(image);
         } catch (IOException ex) {
          
         }
    }
    @FXML
    void Removetimeline(ActionEvent event) throws IOException{
   //   myDisplay.getChildren().removeAll(imageView,lineTimeline);
    	myDisplay.getChildren().clear();
    }
    @FXML
    void edittimeline(ActionEvent event) throws IOException{
   
    	myDisplay.getChildren().clear();
    }
}