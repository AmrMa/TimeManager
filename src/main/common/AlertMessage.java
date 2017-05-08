package main.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;


public class AlertMessage {

    public AlertMessage(String title,String content,AlertType type) {
        Alert alert = new Alert(type,content);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public static boolean AlertMessageConfirmation() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Delete sequence");
        alert.setContentText("Are you sure about this delete?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
