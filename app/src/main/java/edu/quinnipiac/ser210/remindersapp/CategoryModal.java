package edu.quinnipiac.ser210.remindersapp;

public class CategoryModal {
    // variables for our event,
    // category and id.
    private String eventCategory;
    private int id;

    // creating getter and setter methods
    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public CategoryModal(String eventCategory) {
        this.eventCategory = eventCategory;
    }

}