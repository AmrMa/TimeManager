package main.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import main.common.ScreenController;

public class eventDetailsfragment {

    @FXML
    private Button ButtonBack;

    @FXML
    private AnchorPane PaneMain;

    @FXML
    private Button Addimage;
    @FXML ImageView imageview;
   

    @FXML
    void back(ActionEvent event) throws IOException {
    	ScreenController.setScreen(ScreenController.Screen.TIMELINE_DETAILS);
    }

    @FXML
    void addEvent(ActionEvent event) {
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
             imageview.setImage(image);
         } catch (IOException ex) {
          
         }
    }

}
