package Visual_test_with_graphs;


import java.time.LocalDateTime;

/**
 * Created by Void on 26/04/2017.
 */
public class Event {

    private String event_title, event_description;
    private LocalDateTime event_startDate,event_endDate;
    private int timeline_id, event_id;


    public Event(String eventName, String eventDescription, LocalDateTime start, LocalDateTime end) {
        this.event_title = eventName;
        this.event_description = eventDescription;
        this.event_startDate = start;
        this.event_endDate = end;
    }

    public Event(String eventName, String eventDescription, LocalDateTime date) {
        this.event_title = eventName;
        this.event_description = eventDescription;
        this.event_startDate = date;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public LocalDateTime getEvent_startDate() {
        return event_startDate;
    }

    public void setEvent_startDate(LocalDateTime event_startDate) {
        this.event_startDate = event_startDate;
    }

    public LocalDateTime getEvent_endDate() {
        return event_endDate;
    }

    public void setEvent_endDate(LocalDateTime event_endDate) {
        this.event_endDate = event_endDate;
    }

    public int getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(int timeline_id) {
        this.timeline_id = timeline_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }


}