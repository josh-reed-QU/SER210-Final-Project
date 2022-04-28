package edu.quinnipiac.ser210.remindersapp;

public class EventModal {
    // variables for our event,
    // category, name, date, time, description and id.
    private String eventCategory;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventDescription;
    private int id;

    // creating getter and setter methods
    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public EventModal(String eventCategory,String eventName, String eventDate, String eventTime, String eventDescription) {
        this.eventCategory = eventCategory;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
    }

}