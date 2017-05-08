package main.model;

import java.util.ArrayList;
import java.util.List;


public class TimelineDB {
    static ArrayList<Timeline> timelines = new ArrayList<>(1);

    public static Timeline getFirstTimeline() {
        return timelines.get(0);//contact database and get allTimelines;
    }

    public static void addTimeline(Timeline timeline) {
        timelines.add(timeline);
    }
}
