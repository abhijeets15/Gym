package com.example.Projv3;

/**
 This class is an extension of family as it has the same properties as family, however the only difference being the
 number of guest passes they are given when signing up, and their membership fees every billing cycle.
 @author Abhijeet Singh, Khizar Saud
 */

public class Premium extends Family{

    /**
     This is a constructor for the Premium class to make an object of type premium.
     */
    public Premium(){
        this.numOfGuestPass = 3;
    }

    /**
     This is a constructor for the Premium class to make an object of type premium but overloaded with parameters.
     @param firstName is the first name of the premium member being added.
     @param lastName is the last name of the premium member being added.
     @param dob is the date of birth of the member being added.
     @param expireDate the expiration date of the member's membership being added
     @param location is the location of the premium member's membership at the given location gym.
     */
    public Premium(String firstName, String lastName, Date dob, Date expireDate, Location location){
        super(firstName,lastName,dob,expireDate,location);
        this.numOfGuestPass = 3;
    }


    /**
     This method is to return the membership fees of the member for each billing cycle.
     @return This returns the money that is due for the next billing period.
     */
    @Override
    public double membershipFee() {
        double initialFee = 0;
        double billingPeriod =  11.0;
        double monthlyFee = 59.99;
        Date today = new Date();
        Date check = new Date();
        check.setYear(check.getYear() + 1);

        if(today.getDay() == check.getDay() && today.getYear() == check.getYear() - 1 && today.getMonth() == check.getMonth())
        {
            return initialFee + (monthlyFee * billingPeriod);
        }
        else if(this.getExpire().compareTo(today) < 0){
            return -1;
        }
        else{
            return monthlyFee * billingPeriod;
        }
    }

}