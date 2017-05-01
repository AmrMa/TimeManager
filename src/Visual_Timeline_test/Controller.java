package Visual_Timeline_test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private TableView<Event> timeline;
    @FXML private TableColumn<Event, String> eventName;
    @FXML private TableColumn<Event, String> description;
    @FXML private TableColumn<Event, String> date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("event_title"));
        description.setCellValueFactory(new PropertyValueFactory<Event, String>("event_description"));
        date.setCellValueFactory(new PropertyValueFactory<Event, String>("event_startDate"));

        timeline.getColumns().add(eventName);
        timeline.getColumns().add(description);
        timeline.getColumns().add(date);
        timeline.getItems().setAll(parseEvents());

    }

    private List<Event> parseEvents() {
        Timeline tline = new Timeline();
        tline.addEvent(new Event("eventName1", "eventDescription1", LocalDateTime.now()));
        tline.addEvent(new Event("eventName2", "eventDescription2", LocalDateTime.now()));
        tline.addEvent(new Event("eventName3", "eventDescription3", LocalDateTime.now()));

        return tline.getListOfEvents();
    }
}
