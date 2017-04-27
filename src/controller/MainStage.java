package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

//main/starting window
public class MainStage extends Application{
    static Stage mainWindow;
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/view/MainStage.fxml"));
            Parent root = load.load();

            HBox canvas = new HBox(); // where time lines are! the empty space down under(Australian accent)

            // there is no button list.


            Scene scene = new Scene(root, 850, 700);
            primaryStage.setTitle("MyTime");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public Stage getStage() {
        return mainWindow;
    }
}
