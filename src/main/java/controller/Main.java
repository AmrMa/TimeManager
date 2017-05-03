package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Timeline;

import java.time.LocalDate;

public class Main extends Application {
    Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        /**
         * Right part, containing view of timelines. A scrollpane inside the right part of the borderpane
         * would be the best solution, imo.
         */
        VBox timelineContent = new VBox(5);
        timelineContent.setId("trying");

        ScrollPane scrollPane = new ScrollPane(timelineContent);
        scrollPane.fitToWidthProperty().setValue(true);
        scrollPane.fitToHeightProperty().setValue(true);

        JFXButton addButton = new JFXButton("Add timeline");
        addButton.getStyleClass().add("button-flat");
        addButton.setId("addtimeline");
        addButton.setOnAction(e -> {
            Stage popUp = new Stage();

            BorderPane timelineDetails = new BorderPane();
            timelineDetails.setPadding(new Insets(10));
            timelineDetails.setPrefHeight(475);
            timelineDetails.setPrefWidth(275);

            JFXTextField timelineTitle = new JFXTextField("Title: ");
            timelineTitle.setPrefHeight(50);

            JFXDatePicker timelineStart = new JFXDatePicker(LocalDate.now());
            timelineStart.setPadding(new Insets(20,0,0,0));

            JFXDatePicker timelineEnd = new JFXDatePicker(LocalDate.now().plusDays(10));

            JFXButton saveTimelineDetails = new JFXButton("Save");
            saveTimelineDetails.getStyleClass().add("button-flat");

            VBox centerContent = new VBox(timelineTitle,timelineStart,timelineEnd);

            timelineDetails.setCenter(centerContent);
            timelineDetails.setBottom(saveTimelineDetails);

            //timelineDetails.getChildren().addAll(timelineTitle, timelineStart, timelineEnd, saveTimelineDetails);
            popUp.setResizable(false);
            popUp.setScene(new Scene(timelineDetails));
            popUp.show();

            window = stage;
            window.setTitle("Events");

            // Save button:
            saveTimelineDetails.setOnAction(f -> {

                assert !timelineTitle.getText().isEmpty();
                Timeline newTimeline = new Timeline(timelineTitle.getText(), timelineStart.getValue(), timelineEnd.getValue());

                popUp.close();
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getStyleClass().add("timeline-background");

                JFXButton button = new JFXButton("Remove");
                button.getStyleClass().add("button-flat");
                button.setOnAction(evt -> timelineContent.getChildren().remove(anchorPane));
                AnchorPane.setRightAnchor(button, 15.0);
                AnchorPane.setTopAnchor(button, 15.0);
                AnchorPane.setBottomAnchor(button, 15.0);

                JFXButton button1 = new JFXButton("View events");
                button1.getStyleClass().add("button-flat");
                AnchorPane.setRightAnchor(button1, 200.0);
                AnchorPane.setTopAnchor(button1, 15.0);
                AnchorPane.setBottomAnchor(button1, 15.0);
                anchorPane.getChildren().addAll(button, button1);

                Label title = new Label(newTimeline.getTitle());
                AnchorPane.setLeftAnchor(title, 15.0);
                AnchorPane.setBottomAnchor(title, 15.0);
                AnchorPane.setTopAnchor(title, 15.0);
                anchorPane.getChildren().add(title);

                //Get the width of the title:
                FontLoader fl = Toolkit.getToolkit().getFontLoader();
                double labelWidth = fl.computeStringWidth(title.getText(), Font.getDefault());
                System.out.println(fl.computeStringWidth(title.getText(), Font.getDefault()));

                // Timelineview is the representation of a timeline.
                // This Timelineview should later on process the events inside its pane.
                TimelineView timeline = new TimelineView(newTimeline);
                AnchorPane.setTopAnchor(timeline, 20.0);
                AnchorPane.setLeftAnchor(timeline,labelWidth + 30.0);
                anchorPane.getChildren().addAll(timeline);

                timelineContent.getChildren().add(anchorPane);
            });
        });

        /**
         * Left part:
         */
        VBox leftContent = new VBox(15);
        leftContent.setPadding(new Insets(15,12,15,12));
        ScrollPane buttonList = new ScrollPane(leftContent);
        buttonList.fitToWidthProperty().setValue(true);
        buttonList.fitToHeightProperty().setValue(true);

        leftContent.getChildren().add(addButton);


        /**
         * Assign the parts to the borderpane
         */
        BorderPane borderPane = new BorderPane(scrollPane, null, null, null, buttonList);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().addAll(this.getClass().getResource("/stylesheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        stage.setHeight(600);
        stage.setWidth(1100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}