package controller;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.Timeline;

public class TimelineView extends Pane {
    Timeline timeline;


    public TimelineView() {
        Line line = new Line(5.0, 35.0, 50.0,35);
        this.getChildren().add(line);
    }
}
