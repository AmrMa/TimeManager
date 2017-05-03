package controller;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.Timeline;

public class TimelineView extends GridPane {
    double endOfBar = 500.0;


    TimelineView(Timeline timeline) {
        Line horizontalLine = new Line(5.0, 35.0, endOfBar,35);
        Line startLine = new Line(5.0, 20.0, 5.0,50);
        Line endLine = new Line(endOfBar, 20.0, endOfBar,50);

        Group bar = new Group(horizontalLine, startLine, endLine);
        this.add(bar, 0, 0);


        // very, very ugly representation of dates:
        Label startDate = new Label(timeline.getStartDate().toString());
        this.add(startDate,0,1);

        Label endDate = new Label(timeline.getEndDate().toString());
        this.add(endDate, 1,1 );
    }
}
