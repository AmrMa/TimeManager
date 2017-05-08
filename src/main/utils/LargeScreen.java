package main.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import static main.common.StageManager.getStage;


public class LargeScreen {

    public static void setLargeScreen() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        getStage().setX(bounds.getMinX());
        getStage().setY(bounds.getMinY());
        getStage().setWidth(bounds.getWidth());
        getStage().setHeight(bounds.getHeight());
    }
}
