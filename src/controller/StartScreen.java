package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends Application { // I think this class should
    // extend the

    Parent root;
    FXMLLoader load;
    static Stage window;
    static Stage AddTimeline;

    @Override
    public void start(Stage primaryStage) { // Here is the main class in our
        // project
        try {
            load = new FXMLLoader(getClass().getResource("/view/StartScreen.fxml"));
            // load.setLocation(getClass().getResource("/view/StartScreen.fxml"));
            root = load.load();

            primaryStage.setResizable(false);
            primaryStage.setTitle("TimeLine Manager");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            window = primaryStage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private Button loadBtn;

    @FXML
    public Button createBtn;

    @FXML
    void loadBtn(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(window);
        System.out.print("i am the load button");
        // Here we write a code for loading a timeline
    }

    @FXML
    void createBtn(ActionEvent event) throws IOException {

        // We should close the first stage


        System.out.print("i am the not load button");
        // Here we write a code for creating a timeline
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddTimeline.fxml"));

        AddTimeline = new Stage();

        window.hide();
        AddTimeline.setResizable(false);
        AddTimeline.setTitle("TimeLine Manager");
        AddTimeline.setScene(new Scene(root, 600, 400));
        AddTimeline.show();

    }
}