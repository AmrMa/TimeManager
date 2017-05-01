package Visual_test_with_graphs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.*;

public class Main extends Application{


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Timeline");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
	}
	
	
}
