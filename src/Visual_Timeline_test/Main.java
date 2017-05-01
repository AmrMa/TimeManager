package Visual_Timeline_test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Timeline");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
