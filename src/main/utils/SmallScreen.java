package main.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import static main.common.StageManager.getStage;

public class SmallScreen {

    public static void setSmallScreen() {
        getStage().setWidth(800);
        getStage().setHeight(550);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        getStage().setX((primScreenBounds.getWidth() - getStage().getWidth()) / 2);
        getStage().setY((primScreenBounds.getHeight() - getStage().getHeight()) / 2);
    }

}
