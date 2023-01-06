package com.example.Projv3;

/**
 Create a enumeration called time for 3 classes, PILATES, SPINNING, AND CARDIO, Each of these Enumerations
 have a dateTime field which pertains to the time of each class.
 @author Abhijeet Singh, Khizar Saud
 */

public enum Time {
    Pilates("9:30"),
    Spinning("14:00"),
    Cardio("14:00");


    private final String dateTime;

    /**
     This is a Time constructor which takes in time as a parameter and creates a time object.
     @param dateTime The String which is going to be converted to a Time object.
     */
    Time(String dateTime) {
        this.dateTime = dateTime;
    }


    /**
     This returns time of the current Time object.
     @return String of the time
     */
    public String getDateTime() {
        return dateTime;
    }

}