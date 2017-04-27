package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Timeline {

	private ArrayList<Event> listOfEvents = new ArrayList<Event>();;
	private String _reference, _description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int id;


	public Timeline(){}

	public Timeline(Event event){
		listOfEvents.add(event);
	}

	public Timeline (String title, LocalDateTime start, LocalDateTime end,String desc) {
		_reference = title;
		startDate = start;
		endDate = end;
		_description = desc;
	}

	public String getReference() {
		return _reference;
	}

	public LocalDateTime getInitDate() {
		return startDate;
	}

	public LocalDateTime getEnd() {
		return endDate;
	}

	public ArrayList<Event> getListOfEvents() {
		return listOfEvents;
	}

	public void setReference(String _reference) {
		this._reference = _reference;
	}

	public void setInitDate(LocalDateTime _initDate) {
		this.startDate = _initDate;
	}

	public void setEnd(LocalDateTime end) {
		this.endDate = end;
	}

	// Methods
	public int size() {
		return listOfEvents.size();
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public void addEvent(Event point) {
		listOfEvents.add(point);
	}

	public void deleteEvent(Event toDelete){
		listOfEvents.remove(toDelete);
	}
	public String toString() {
		return _reference;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String _details) {
		this._description = _details;
	}
}
