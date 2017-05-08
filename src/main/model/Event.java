package main.model;

import java.time.LocalDateTime;


public class Event {
    private String event_title, event_description;
    private LocalDateTime event_startDate,event_endDate;
    private int timeline_id, event_id;


    public Event(String eventName, String eventDescription, LocalDateTime start, LocalDateTime end) {
    }

    public Event(String eventName, String eventDescription, LocalDateTime date) {
    }

}
