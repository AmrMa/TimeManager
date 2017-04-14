package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class MainStage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // I thought making a BorderPane would be an idea to divide parts, such as canvas on the right
        // and buttons on the left?
        // Test

        //BorderPane mainWindow = new BorderPane();

        final Random rng = new Random();
        VBox content = new VBox(5);
        ScrollPane scrollPane = new ScrollPane(content);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                            "-fx-background-color: -fx-background;",
                    rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            anchorPane.setStyle(style);
            Label label = new Label("Pane "+(content.getChildren().size()+1));
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
            Button button = new Button("Remove");
            button.setOnAction(evt -> content.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            content.getChildren().add(anchorPane);
        });
//        Image flowers = new Image(getClass().getResourceAsStream("flowers.jpg"));
//        scrollPane.setContent(new ImageView(flowers));

        //mainWindow.setRight(scrollPane);
        BorderPane borderPane = new BorderPane(scrollPane, null, null, addButton, null);
        borderPane.minHeight(400);
        Scene scene = new Scene(borderPane);
        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.setScene(scene);
        stage.show();
    }
}
