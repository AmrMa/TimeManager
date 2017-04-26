package main.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestField {


	@FXML
	private Button loadBtn;

	@FXML
	public Button createBtn;

	@FXML
	void loadBtn(ActionEvent event) throws IOException {
		System.out.print("i am the load button");
		// Here we write a code for loading a timeline
	}

	@FXML
	void createBtn(ActionEvent event) throws IOException {

		// We should close the first stage

		System.out.print("i am the not load button");
		// Here we write a code for creating a timeline
		Parent root = FXMLLoader.load(getClass().getResource("/main/view/AddTimeline.fxml"));// ask
																						// for
																						// the
																						// first
																						// window
		Stage AddTimeline = new Stage();
		AddTimeline.setResizable(false);
		AddTimeline.setTitle("TimeLine Manager");
		AddTimeline.setScene(new Scene(root, 600, 400));
		AddTimeline.show();

	}
}
