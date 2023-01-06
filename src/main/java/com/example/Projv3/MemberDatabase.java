package com.example.Projv3;

/**
 This class is meant for storing members in the database and conducting other operations along with it.
 This class conducts operations such as add member, remove member, find member.
 This class also sorts and prints by different orders such as County, Expire Date, Name, or no specific order.
 @author Abhijeet Singh, Khizar Saud
 */

public class MemberDatabase {
    enum Locations{
        Bridgewater("08807","Somerset County"),
        Edison("08837","Middlesex County"),
        Franklin("08873","Somerset County"),
        Piscataway("08854","Middlesex County"),
        Somerville("08876","Somerset County");

        String zipcode;
        String County;
        Locations(String Z, String C){
            zipcode=Z;
            County=C;


        }
        public String getCounty(Locations a){
            return a.County;

        }
    }
    /**
     Checks to see if member is in mlist[].
     @param member to find the member in the database.
     @return the index of the member in the database.
     */
    public int contains(Member member) {
        return find(member);
    }
    public String contains3(Member member){
        String returnVal="x";
        if(contains(member)==-1){
            returnVal="Not found";

        }else{
            returnVal="Found";
        }
        return returnVal;
    }
    //A Khizar Saud 1/31/2003 BRIDGEWATER

    public String contains2(String [] commands){
        String returnValue="X";
        String fname=commands[1];
        String lname=commands[2];
        Date dob = new Date(commands[3]);
        Member a = new Member(fname,lname,dob);
        if(contains(a)==-1){
            returnValue="NOT FOUND";


        }
        else{
            returnValue="FOUND";
        }
        return returnValue;
    }





    /**
     Returns member from the datbase based on the index passed.
     @param index of the member to be retrieved from the mlist database.
     @return the member at that specific index
     */
    public Member getMember(int index){
        return mlist[index];

    }

    public void printSchedule() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            //System.out.print("\t\t");
            count++;
        }
    }
    public String printSchedule2(int i) {

        return mlist[i].toString();
    }



    private Member[] mlist;
    private int size;

    /**
     This is the constructor for the database and the mlist array database is configured along with the size.
     */
    public MemberDatabase(){
        this.size = size;
        this.mlist = new Member[4];
    }

    /**
     * Find the member if it is in the database by comparing the information of the passed member to all other members.
     @param member The member which is trying to be found in the dadtabase.
     @return the index of the member if it is found else return -1.
     */

    private int find(Member member) {
        int NOT_FOUND = -1;
        for (int i = 0; i < mlist.length; i++) {
            if (mlist[i] != null && mlist[i].equals(member)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method is for growing the mlist[] array to increase the size of the database in case it is full.
     */
    private void grow() {
        Member[] newList = new Member[mlist.length+4];
        for(int i = 0; i < mlist.length; i++){
            newList[i] = mlist[i];
        }
        mlist=newList;
    }

    /**
     * This adds a member based on whether the member exists in the database.
     * If the member exists in the database then they are not added.
     * If the member doesn't exist in the database then they are added.
     @param member The member which is being added to the database
     @return true if the member was added, false if the member was found in the database or not added
     */
    public boolean add(Member member) {
        if(find(member) != -1){
            return false;
        }
        if(size == mlist.length-1){
            grow();
        }
        mlist[size] = member;
        size++;
        return true;
    }

    /**
     * This removes a member based on whether the member exists in the database.
     * If the member exists in the database then they are removed.
     * If the member doesn't exist in the database then nothing happens.
     @param member The member which is being removed from the database
     @return true if the member was removed, false if the member was not found or removed.
     */
    public boolean remove(Member member) {
        if(find(member) == -1){
            return false;
        }
        Member[]temp = new Member[size];
        boolean found = false;
        int memberIDX = 0;
        for(int i = 0; i < size; i++){
            if(mlist[i] != null && member.equals(mlist[i])){
                found = true;
            }
            else{
                temp[memberIDX] = mlist[i];
                memberIDX++;
            }
        }
        size--;
        mlist = temp;
        return found;
    }

    /**
     * Prints all members in the database in whatever order the array is currently set at.
     */
    public void print() {
        int count = 0;
        for(int i = 0; i < mlist.length; i++) {
            if(mlist[i] != null){
                count = i;
            }
        }
    } //print the array contents as is
    public String baba(){
        for(int i = 0; i < mlist.length; i++) {
            if(mlist[i] != null){
               return mlist[i].toString();
            }
        }return "";

    }
    public String print(int num) {
        return mlist[num].toString();
    }



    /**
     * Prints all members in the database in whatever order the array is currently set at but includes fee.
     */
    public String printByFee(int i) {
        return mlist[i].toStringCost();
    }

    /**
     * Prints the members by counties and orders them by those counties.
     */
    public Member[] printByCounty() {
        int increment = 1;
        while (increment < size) {
            increment = 2 * increment + 1;
        }
        while (increment >= 1) {
            for (int i = increment; i < size; i++) {
                for (int j = i; j >= increment; j -= increment) {
                    if (mlist[j].getLocation().getBoth().compareTo(mlist[j - increment].getLocation().getBoth()) < 0) {
                        Member temp = mlist[j];
                        mlist[j] = mlist[j - increment];
                        mlist[j - increment] = temp;
                    } else {
                        break;
                    }
                }
            }
            increment /= 2;
        }
        return mlist;
    } //sort by county and then zipcode


    /**
     * Prints all members in order of expiration date by coparing each date to each other and sorting it.
     */
    public Member[] printByExpirationDate() {
        for (int i = 1; i < this.size; i++) {
            Member member = mlist[i];
            int j = i - 1;
            while (j >= 0 && mlist[j].getExpire().compareTo(member.getExpire()) == 1) {
                mlist[j + 1] = mlist[j];
                j--;
            }
            mlist[j + 1] = member;
        }
        return mlist;
    }

    /**
     * This is a method which prints every member by it's name in alphabetical order.
     * This goes in alphabetical order when printing.
     */
    public Member[] printByName() {
        for(int i = 0; i < size; i++){
            Member member = mlist[i];
            int j = i - 1;
            while(j >= 0 && (mlist[j] != null && mlist[i] != null) && (mlist[j].getLname().compareTo(member.getLname())) > 0){
                mlist[j + 1] = mlist[j];
                j = j - 1;
            }
            mlist[j + 1] = member;
        }
        return mlist;
    }

    /**
     This method is for returning the size of the database.
     @return Returns the size of the current database.
     */
    public int getSizeOfDB() {
        return size;
    }

    public void setMlist(){

    }
}