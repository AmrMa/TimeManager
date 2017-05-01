import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

            window = stage;
            window.setTitle("Events");
            timelineContent.getChildren().add(anchorPane);
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

        BorderPane borderPane = new BorderPane(scrollPane, null, null, null, buttonList);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        stage.setHeight(600);
        stage.setWidth(1200);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
