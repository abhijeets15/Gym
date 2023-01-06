package com.example.Projv3;

/**
 This is a child class of member as family is a tye of membership.
 This class has an exta instance variable of guest pass because family membership has that capability.
 @author Abhijeet Singh, Khizar Saud
 */
public class Family extends Member {
    protected int numOfGuestPass;

    /**
     This is a constructor for the Family instance to make an object of type family.
     */
    public Family() {
        numOfGuestPass = 1;
    }


    /**
     This class is so that a variable of type FitnessClass gets added to classes array and update schedule.
     @param fitnessClass this is a parameter for the class to add.
     */
    public Family(String firstName, String lastName, Date dob, Date expirationDate, Location location) {
        super(firstName, lastName, dob, expirationDate, location);
        this.numOfGuestPass = 1;
    }

    /**
     This method returns the number of guest passes remaining for the member on a family membership.
     @return the number of guest passes left.
     */
    public int getNumOfGuestPass() {
        return numOfGuestPass;
    }

    /**
     This method sets the number of guest passes for the member on a family membership.
     @param num this is a parameter for what the amount of guest passes should be set to for this member.
     */
    public void setNumOfGuestPass(int num) {
        this.numOfGuestPass += num;
    }

    /**
     This method is to return the membership fees of the member for each billing cycle.
     @return This returns the money that is due for the next billing period.
     */
    @Override
    public double membershipFee() {
        double initialFee = 29.99;
        double billingPeriod = 3.0;
        double monthlyFee = 59.99;
        Date today = new Date();
        Date check = new Date();
        check.setMonth(check.getMonth() + 3);

        if(today.getDay() == check.getDay() && today.getYear() == check.getYear() && today.getMonth() == check.getMonth() - 3)
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