package com.example.Projv3;

/**
 First, a single, very descriptive sentence describing the class.
 Then, a couple more sentences of description to elaborate.
 @author Abhijeet Singh, Khizar Saud
  *
 import java.util.Locale;
 /**
 This is an enum class for having all the information about cities and their zipcodes and county's stored.
 */
public enum Location {
    Bridgewater("08807", "Somerset County"),
    Edison("08837", "Middlesex County"),
    Franklin("08873", "Somerset County"),
    Piscataway ("08854", "Middlesex County"),
    Somerville("08876", "Somerset County");

    String zipcode;
    String County;

    /**
     This is the constructor for the location class, to set a Location object given the Zipcode and County
     @param Z This is the string parameter for setting zipcode.
     @param C This is the string for setting the county.
     */
    Location(String Z, String C) {
        this.zipcode = Z;
        this.County = C;


    }

    /**
     * This method compares locations and sees which one is alphabetically greater or smaller.
     * @param location this is the location being compared to this.location
     * @return Equal if they are equal, first if this.location is bigger, second if parameter location is bigger.
     */
    public String compareLocations(Location location){
        String localCounty=(this.getCounty()).toUpperCase();
        String passedCounty=(location.getCounty()).toUpperCase();
        int localZip=Integer.parseInt(this.getZipcode());
        int passedZip=Integer.parseInt(location.getZipcode());
        String winner="";
        if(localCounty.compareTo(passedCounty)==0){
            if(localZip>passedZip){
                winner="First";
            }
            else if(localZip<passedZip){
                winner="Second";
            }else{
                winner="Equal";
            }

        }
        return  winner;

    }


    /**
     Returns the Zipcode of a given location.
     @param a is the location to get the zipcode of.
     @return String of the zipcode.
     */
    public String getZipcode(){
        return this.zipcode;
    }
    public String getZipcode(Location a){
        return a.zipcode;
    }

    /**
     Returns the County of a given location.
     @param a is the location to get the county of.
     @return String of the County.
     */
    public String getCounty(Location a) {
        return a.County;
    }

    /**
     Returns the County of a given location.
     @return String of the County.
     */
    public String getCounty(){
        return this.County;
    }

    /**
     Returns the County and the zipcode of a given location.
     @return String of the County and zipcode.
     */
    public String getBoth() {
        return County + zipcode;
    }
}