package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	@Override
	public void start(Stage primaryStage){     // Here is the main class in our project
		try{
			 Parent root = FXMLLoader.load(getClass().getResource("/view/StartScreen.fxml"));// ask for the first window
			 primaryStage.setTitle("TimeLine Manager");
			 primaryStage.setScene(new Scene(root, 900, 700));
			 primaryStage.show();
		} catch(Exception e){
			e.printStackTrace(); // TODO: gotta link this somehow
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
