package com.example.Projv3;

/**
 This class is to set the time for fitness classes.
 There is an enum for making each class a morning, afternoon, or evening class based on the time of the class
 @author Abhijeet Singh, Khizar Saud
 */
public enum classTimes {
    MORNING("9:30"),
    AFTERNOON("14:00"),
    EVENING("18:30");

    private final String dateTime;

    /**
     * This is a constructor method of enum Time class that takes one parameter.
     * Automatically creates the enum constants above with the times given for them
     *
     * @param dateTime the time given class starts
     */
    classTimes(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * The method is used when getting the time of a fitness class.
     *
     * @return the time of the desired class in String format
     */
    public String getDateTime() {
        return dateTime;
    }

}