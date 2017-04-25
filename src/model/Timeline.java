package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Timeline {
	// Creating timeline with its components
	private String title;
	private String description;
	private LocalDate startDate, endDate;

	public Timeline(String t, String des, LocalDate stDate, LocalDate endDate) {

		this.title = t;
		this.description = des;
		this.startDate = stDate;
		this.endDate = endDate;

	}

	// setters and getter of the timeline componenets

	public void setTitle(String t) {
		title = t;
	}

	public void setDesctription(String d) {
		description = d;
	}

	public void setStartDate(LocalDate sD) {
		startDate = sD;
	}

	public void setEndDate(LocalDate eD) {
		endDate = eD;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getStartDate() {
		return startDate;

	}

	public LocalDate getEndDate() {
		return endDate;
	}

}
