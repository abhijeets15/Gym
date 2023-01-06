package com.example.Projv3;

/**
 *  This class is for the Dates that are being used throughout the program.
 *  This class takes care of any invalid Dates and also checks to see if the member is an adult.
 *  This class is able to make date objects that are comaprable to today's date to see if a date is valid.
 *  Today's date also helps with seeing if the member is an adult.
 *  @author Abhijeet Singh, Khizar Saud
 */

import java.util.Calendar;
public class Date implements Comparable<Date>{
    private int year=0;
    private int month=0;
    private int day=0;
    //Create object with todays date.


    /**
     This is the Date constructor for creating a date object given the string for the date.
     @param D the string to convert the string into type Date
     */
    public Date(String D){
        String[] Values=D.split("/");
        boolean leap=false;
        int checkYear=Integer.parseInt(Values[2]);
        int checkMonth = Integer.parseInt(Values[0]);
        int checkDay=Integer.parseInt(Values[1]);
        year=checkYear;
        month=checkMonth;
        day=checkDay;
    }

    public int checkNextYear(int addMonth) {
        if (this.month + addMonth > 12) {
            return this.month + addMonth - 12;
        } else {
            return -1;
        }
    }



    /**
     This is the Date constructor for creating a date object with the current date, having the elements of today's date.
     */
    public Date(){

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DAY_OF_MONTH);




    }

    /**
     This is a setter method for setting the year of a Date object.
     @param year the year which is passed to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     This is a setter method for setting the month of a Date object.
     @param month the month which is passed to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     This is a setter method for setting the day of a Date object.
     @param day the day which is passed to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     This is a getter method for getting the year of a Date object.
     @return the year which the current Date object has.
     */
    public int getYear() {
        return year;
    }

    /**
     This is a getter method for getting the month of a Date object.
     @return the month which is set in the current Date object.
     */
    public int getMonth() {
        return month;
    }

    /**
     This is a getter method for getting the month of a Date object.
     @return the day which the current Date object is set at.
     */
    public int getDay() {

        return day;
    }



    /**
     This compares the passed date with the current this.date, but checking the year, then month, then day.
     @param date the date of the current date object.
     @return the value 0 if the years are same, 1 if this.year is greater, -1 if this.year is less
     */
    @Override
    public int compareTo(Date date) {
        //This denotes the year you created, date.year denotes the current date
        int FirstYear= this.year;
        int SecondYear=date.year;
        int Winner=0;
        int SecondDayBigger=1;
        int FirstMonth=0;
        int SecondMont=0;
        int diff = date.year-this.year;
        if (FirstYear>SecondYear){
            Winner= 1;
        }

        if(FirstYear<SecondYear){
            Winner= -1;
        }
        if(FirstYear==SecondYear){
            FirstMonth=this.month;
            SecondMont=date.month;

            if(FirstMonth>SecondMont){
                Winner= 1;
            }
            if(FirstMonth<SecondMont){
                Winner= -1;
            }
            if(FirstMonth==SecondMont){
                int FirstDay=this.day;
                int SecondDay=date.day;


                if(FirstDay>SecondDay){
                    Winner= 1;
                }
                if(FirstDay>SecondDay){
                    Winner= -1;
                }
                if(SecondDay==FirstDay){
                    Winner= 0;
                }
            }
        }
        return Winner;
    }

    /**
     This is a method to return if the current date is a leap year.
     @return true if the year is a leap year, false otherwise
     */
    public boolean isLeap(){
        if (year % 4 == 0) {

            // if the year is century
            if (year % 100 == 0) {

                // if year is divided by 400
                // then it is a leap year
                if (year % 400 == 0)
                    return true;
                else
                    return false;
            }

            // if the year is not century
            else
                return true;
        }

        else
            return false;
    }

    /**
     This method checks to see if the year that is passed is a leap year instead of checking this.date
     @param year the year which is being checked.
     */
    public boolean isLeap(int year){

        if (year % 4 == 0) {

            // if the year is century
            if (year % 100 == 0) {

                // if year is divided by 400
                // then it is a leap year
                if (year % 400 == 0)
                    return true;
                else
                    return false;
            }

            // if the year is not century
            else
                return true;
        }

        else
            return false;
    }

    /**
     This returns if the current member, this.date is an adult by seeing if they are 18 or older.
     @return true if the member is an adult, false otherwise.
     */
    public boolean isAdult(){
        boolean isAdult = false;
        Date A = new Date();
        int Minyear = A.getYear()-18;
        int month = A.getMonth();
        int day = A.getDay();
        int day2=this.getDay();
        if(this.getYear()==Minyear){
            if(month==this.getMonth() || this.getMonth()<month){
                if(day2<=day){
                    isAdult=true;
                }


            }
        }
        else if(this.getYear() < Minyear){
            return true;
        }


        else {
            isAdult=false;
        }

        return isAdult;
    }
    /**
     Return true if the current date of birth is an adult and false otherwise.
     @param DOB The date of birth which is passed.
     @return true if the DOB is adult date of birth, false otherwise.
     */
    public boolean isAdult(Date DOB){
        boolean isAdult = false;
        Date A = new Date();
        int Minyear = A.getYear()-18;
        int month = A.getMonth();
        int day = A.getDay();
        int day2=DOB.getDay();
        if(DOB.getYear()<=Minyear){
            if(month==DOB.getMonth() || DOB.getMonth()<month){
                if(day2<=day){
                    isAdult=true;
                }


            }
        }


        else {
            isAdult=false;
        }

        return isAdult;
    }

    /**
     This is a method for checking if the date is an adult, having a String passed foe date.
     @param Date The date which is passed
     @return true if the date passed is of adult age, false otherwise.
     */
    public boolean isAdult(String Date){
        String[] Values=Date.split("/");
        int checkYear=Integer.parseInt(Values[2]);
        int checkMonth = Integer.parseInt(Values[0]);
        int checkDay=Integer.parseInt(Values[1]);
        boolean isAdult = false;
        Date A = new Date();
        int Minyear = A.getYear()-18;
        int month = A.getMonth();
        int day = A.getDay();
        int day2=checkDay;
        if (checkYear<Minyear){


            isAdult=true;
        }
        if(checkYear==Minyear){


            if(month==checkMonth){
                if(checkDay<=day){



                    isAdult=true;
                }
            }
            if(checkMonth<month){

                isAdult=true;

            }

        }



        else{
            isAdult=false;
        }

        return isAdult;
    }





    /**
     This is a method to check if the current date this.date is a date that exists.
     @return true if the date is a valid date, false otherwise.
     */
    public boolean isValid(){
        if(month <= 0 || month > 12){
            return false;
        }
        if(year <= 1900){
            return false;
        }
        if(day <= 0){
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30){
            return true;
        }
        // For months with 31 days.
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31){
            return true;
        }
        // For February.
        if(month == 2)
        {


            if(day <= 28){
                return true;
            }
            if(day == 29){
                if(isLeap(year))
                    return true;
            }
        }
        return false;


    }

    /**
     This method checks if the date is a valid date given multiple paramaeter of date.
     @param month the month of the date which is passed.
     @param day The day which is passed.
     @param year The year which is passed.
     */
    public boolean isValid(int month,int day , int year){

        if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30){
            return true;
        }
        // For months with 31 days.
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31){
            return true;
        }
        // For February.
        if(month == 2)
        {
            if(day <= 28){
                return true;
            }
            if(day == 29){
                if(isLeap(year))
                    return true;
            }
        }
        return false;


    }

}