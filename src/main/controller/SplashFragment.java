package main.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.animation.FadeInLeftTransition;
import main.animation.FadeInRightTransition;
import main.animation.FadeInTransition;
import main.common.ScreenController;

import java.io.IOException;

import static main.common.StageManager.getStage;


public class SplashFragment { // The work here is done.

    @FXML private Text TextWelcome;
    @FXML private Text TextCompany;
    @FXML private VBox PaneBottom;
    @FXML private Label LabelClose;

    public void initialize() {
        LabelClose.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND)); // really cool
        LabelClose.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        LabelClose.setOnMouseClicked(event -> {
            Platform.exit();
            System.exit(-1); // YESS !
        });
        start();
    }
    private void start() {   // this is great found this online;
        Platform.runLater(()->{
            new FadeInLeftTransition(TextWelcome).play();
            new FadeInRightTransition(TextCompany).play();
            new FadeInTransition(PaneBottom).play();
        });
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> {
            try {
                ScreenController.setScreen(ScreenController.Screen.MENU);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        new Thread(sleeper).start();
    }
}
