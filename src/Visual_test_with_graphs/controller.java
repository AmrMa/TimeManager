package Visual_test_with_graphs;
import javafx.fxml.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class controller implements Initializable{
	@FXML private Button button;
	@FXML private LineChart<Number, Number> chart;
	@FXML private Button update;
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	@FXML
	void button(ActionEvent event){
		NumberAxis xAxis = new NumberAxis(1700,1800,10);
		NumberAxis yAxis = new NumberAxis(0,100,10);
		
		chart = new LineChart<Number,Number>(xAxis,yAxis);
		XYChart.Series points = new XYChart.Series();
		points.getData().add(new XYChart.Data<>(1700,50));
		points.getData().add(new XYChart.Data<>(1800,50));
		points.getData().add(new XYChart.Data<>(1775,50));
		points.getData().add(new XYChart.Data<>(1775,50));
		XYChart.Series points2 = new XYChart.Series<>();
		points2.getData().add(new XYChart.Data<>(1710,50));
		points2.getData().add(new XYChart.Data<>(1710,65));
		XYChart.Series points3 = new XYChart.Series<>();
		points3.getData().add(new XYChart.Data<>(1725,50));
		points3.getData().add(new XYChart.Data<>(1725,65));
		points3.getData().add(new XYChart.Data<>(1740,65));
		points3.getData().add(new XYChart.Data<>(1740,50));
		
		
		chart.getData().addAll(points,points2,points3);
	}
	@FXML
	void update(ActionEvent event){
		Scene scene = new Scene(chart);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		System.out.println("fiskebåt");
		
	}

}




