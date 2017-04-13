package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainStage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // I thought making a BorderPane would be an idea to divide parts, such as canvas on the right
        // and buttons on the left?

        BorderPane mainWindow = new BorderPane();

        Scene scene = new Scene(mainWindow);
        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.setScene(scene);
        stage.show();
    }
}
