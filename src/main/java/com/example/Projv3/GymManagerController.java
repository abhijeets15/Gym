package com.example.Projv3;


/**
 * This is the Gym Manager which is where the program run function is.
 *  There are helper methods for adding a member to database, and reading commands.
 *  This class takes care of adding members, removing members, printing members, adding and dropping memeber from fitness
 *  class and displaying the fitness Schedule.
 *  ALl of this is done with a UI using JavaFX.
 *  @author Abhijeet Singh, Khizar Saud
 */


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;


public class GymManagerController {

    MemberDatabase db = new MemberDatabase();
    private final int finalIntClassSize=3;
    FitnessClass[] fitnessClasses = new FitnessClass[finalIntClassSize];
    int classes = 4;
    Family member = new Family();
    ClassSchedule c = new ClassSchedule();





    private int countAttemptsToNotLoadMore;
    @FXML
    private TextField fnameTextField;

    @FXML
    private TextField lnameTextField;

    @FXML
    private TextField dobTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private RadioButton standard;
    @FXML
    private RadioButton family;
    @FXML
    private RadioButton premium;
    @FXML
    private TextArea outputText;

    @FXML
    private TextField fnameTextField1;
    @FXML
    private TextField lnameTextField1;
    @FXML
    private TextField dobTextField1;
    @FXML
    private TextField locationTextField1;
    @FXML
    private TextField iName;
    @FXML
    private TextField CName;
    @FXML
    private TextArea outputText1;

    /**
     The constructor for the GymManagerController class
     */
    public GymManagerController() {
        this.countAttemptsToNotLoadMore = 1;

        fitnessClasses[0] = new FitnessClass(db,"Jennifer","Pilates", Time.Pilates, fitnessClasses);
        fitnessClasses[1] = new FitnessClass(db,"Denise","Spinning", Time.Spinning, fitnessClasses);
        fitnessClasses[2] = new FitnessClass(db,"Kim","Cardio", Time.Cardio, fitnessClasses);
        FitnessClass Ab= new FitnessClass(null, null, null, null, fitnessClasses);

    }

    /**
     Prints all members based on however it is sorted by Last name, and then first name if last name value is the same.
     */
    @FXML
    private void printByFee(){
        outputText.appendText("--Begin List-- \n");
        for(int i = 0; i < db.getSizeOfDB(); i++){
            outputText.appendText(db.printByFee(i));
            outputText.appendText("\n");
        }
        outputText.appendText("--End List--\n");
    }

    /**
     Prints all members based on however it is sorted by Last name, and then first name if last name value is the same.
     */
    @FXML
    private void printByName(){
        db.printByName();
        outputText.appendText("--Begin List-- \n");
        for(int i = 0; i < db.getSizeOfDB(); i++){
            outputText.appendText(db.print(i));
            outputText.appendText("\n");
        }
        outputText.appendText("--End List--\n");
    }

    /**
     Prints all members based on however it is sorted by Expiration Date
     */
    @FXML
    private void printByDate(){
        db.printByExpirationDate();
        outputText.appendText("--Begin List-- \n");
        for(int i = 0; i < db.getSizeOfDB(); i++){
            outputText.appendText(db.print(i));
            outputText.appendText("\n");
        }
        outputText.appendText("--End List--\n");
    }

    /**
     Prints all members based on however it is sorted by County
     */
    @FXML
    private void printByCounty(){
        db.printByCounty();
        outputText.appendText("--Begin List-- \n");
        for(int i = 0; i < db.getSizeOfDB(); i++){
            outputText.appendText(db.print(i));
            outputText.appendText("\n");
        }
        outputText.appendText("--End List--\n");
    }

    /**
     Prints all members based on however the current database for members is like.
     */
    @FXML
    private void printMembers(){
        outputText.appendText("--Begin List-- \n");
        for(int i = 0; i < db.getSizeOfDB(); i++){
            outputText.appendText(db.print(i));
            outputText.appendText("\n");
        }
        outputText.appendText("--End List--\n");
    }
    /**
     This checks in the guest into a fitness class when the button for check in is pushed.
     This takes care of how the check in process is done based on what type of membership the member has.
     */
    @FXML
    private void Checkin2() {

        String fname = fnameTextField1.getText();
        String lname = lnameTextField1.getText();
        String stringDob = dobTextField1.getText();
        String enteredlocation = locationTextField1.getText().toUpperCase();
        String instrName = iName.getText();
        String className = CName.getText();
        Date bornDate = new Date(stringDob);
        Location Ab;

        if(bornDate.isValid() == false){
            outputText1.appendText("Invalid Date!\n");
            return;
        }

        if (enteredlocation.equals("EDISON")) {
            Ab = Location.Edison;
        } else if (enteredlocation.equals("BRIDGEWATER")) {
            Ab = Location.Bridgewater;
        } else if (enteredlocation.equals("SOMERVILLE")) {
            Ab = Location.Somerville;
        } else if (enteredlocation.equals("PISCATAWAY")) {
            Ab = Location.Piscataway;
        } else if (enteredlocation.equals("FRANKLIN")) {
            Ab = Location.Franklin;
        } else {
            Ab = null;
            outputText1.appendText("Invalid Location!\n");
            return;
        }
        Member tempMember = new Member(fname, lname, bornDate);
        FitnessClass tempFitness;
        Location guestLocation = null;
        String location2 = "";
        boolean flag1 = false;
        int numOfPass = 0;
        FitnessClass A = new FitnessClass(db, instrName, className, null, fitnessClasses);
        A.setLocation(Ab);

        //fix nonexistent instructor
        int checkInstructor = 0;
        for(int i = 0; i < fitnessClasses.length; i++){
            if(fitnessClasses[i].getInstructorName().equals(instrName)){
                break;
            }
            checkInstructor++;
        }
        if(checkInstructor == fitnessClasses.length){
            outputText1.appendText("Instructor doesn't exist\n");
            return;
        }

        for(int i = 0; i < fitnessClasses.length; i++){
            if(fitnessClasses[i].getAttending().contains(tempMember) != -1){
                outputText1.appendText("Already checked in!\n");
                return;
            }
        }

        int checkClasses = 0;
        for(int i = 0; i < fitnessClasses.length; i++){
            if(fitnessClasses[i].getFitnessName().equals(className)){
                break;
            }
            checkClasses++;
        }
        if(checkClasses == fitnessClasses.length){
            outputText1.appendText("Class doesn't exist!\n");
            return;
        }

        if (db.contains(tempMember) != -1) {

            tempMember = db.getMember(db.contains(tempMember));
            for (int i = 0; i < fitnessClasses.length; i++) {
                if ((fitnessClasses[i].getFitnessName()).equalsIgnoreCase(className)) {
                    fitnessClasses[i].checkIn(tempMember, db, className, A);
                    outputText1.appendText(tempMember.getFname() + " " + tempMember.getLname() + " checked in\n");
                    return;
                }
            }
            System.out.println(className + " class does not exist");
        } else {
            outputText1.appendText(tempMember.getFname() + " " + tempMember.getLname() + " is not in the database. \n");
        }
    }




    /**
     This method allows for checking out the member that checked into the fitness class.
     Checkout is done based on if they were in the fitness class to begin with.
     */
    @FXML
    private void Checkout(){
        String fname = fnameTextField1.getText();
        String lname = lnameTextField1.getText();
        String stringDob = dobTextField1.getText();
        String enteredlocation = locationTextField1.getText().toUpperCase();
        String instrName = iName.getText();
        String className = CName.getText();
        Date bornDate = new Date(stringDob);
        FitnessClass A = new FitnessClass(db, instrName, className, null, fitnessClasses);
        Member tempMember = new Member(fname, lname, bornDate);

        try {
            Date dob = new Date(stringDob);
            Member member = new Member();
            member.setLname(lname);
            member.setFname(fname);
            member.setDob(dob);
            for (int i = 0; i < fitnessClasses.length; i++) {
                if (fitnessClasses[i].getFitnessName().equalsIgnoreCase(className)) {
                    if (fitnessClasses[i].dropClass(new Member(fname, lname, dob), db) == true){
                        outputText1.appendText(member.getFname()  + " dropped " + className + "\n");
                    }
                    else {
                        outputText1.appendText(member.getFname()+ " is not a participant in " + className + "\n");
                    }
                    return;
                }
            }
            outputText1.appendText("class doesnt exist");
        } catch (ArrayIndexOutOfBoundsException e) {
            outputText1.appendText("BAD LINES!");
        }

    }

    /**
     Prints out the schedule of the fitness classes along with the participants.
     The participants are listed underneath the class times and schedule.
     */
    @FXML
    private void printSchedule(){

    }

    /**
     This method is to check in a guest based on the guest passes and membership type of the member.
     This takes care of all family plan, premium plan, and standard plan.
     */
    @FXML
    private void guestCheckIn(){
        String fname = fnameTextField1.getText();
        String lname = lnameTextField1.getText();
        String stringDob = dobTextField1.getText();
        String enteredlocation = locationTextField1.getText().toUpperCase();
        String instrName = iName.getText();
        String className = CName.getText();
        Date bornDate = new Date(stringDob);
        FitnessClass A = new FitnessClass(db, instrName, className, null, fitnessClasses);
        Member tempMember = new Member(fname, lname, bornDate);
        String Cname = A.getFitnessName();
        String CInstructor = A.getInstructorName();

        Location Ab;

        if (enteredlocation.equals("EDISON")) {
            Ab = Location.Edison;
        } else if (enteredlocation.equals("BRIDGEWATER")) {
            Ab = Location.Bridgewater;
        } else if (enteredlocation.equals("SOMERVILLE")) {
            Ab = Location.Somerville;
        } else if (enteredlocation.equals("PISCATAWAY")) {
            Ab = Location.Piscataway;
        } else if (enteredlocation.equals("FRANKLIN")) {
            Ab = Location.Franklin;
        } else {
            Ab = null;
        }


        if(db.contains(tempMember) >= 0){
            tempMember = db.getMember(db.contains(tempMember));
        }
        int numOfPass = 0;
        int count = 0;
        for (int i = 0; i < fitnessClasses.length; i++) {
            if (fitnessClasses[i].getFitnessName().equalsIgnoreCase(Cname) && fitnessClasses[i].getInstructorName().equalsIgnoreCase(CInstructor))
                count += 1;
        }
        if (count > 0) {
            if(tempMember instanceof Premium){
                numOfPass = ((Premium) tempMember).getNumOfGuestPass();
                if (numOfPass == 0) {
                    outputText1.appendText("NO PASSES! left\n");
                } else {
                    ((Premium) tempMember).setNumOfGuestPass(-1);
                    A.addGuest(tempMember);
                    outputText1.appendText(tempMember.getFname() + " " + tempMember.getLname() + " (guest) checked in " + A.toString() + "\n");

                }
            }
            else if (tempMember instanceof Family) {
                numOfPass = ((Family) tempMember).getNumOfGuestPass();
                if (numOfPass == 0) {
                    outputText1.appendText("NO PASSES! left\n");
                } else {
                    if (tempMember.getLocation().compareLocations(Ab) == "Equal") {
                        ((Family) tempMember).setNumOfGuestPass(-1);
                        A.addGuest(tempMember);
                        outputText1.appendText(tempMember.getFname() + " " + tempMember.getLname() + " (guest) checked in " + enteredlocation + "\n");

                    } else {
                        outputText1.appendText("Guest Location restriction\n");
                    }
                }
            }

            else {
                outputText1.appendText("User only has a standard membership - guest check-in is not allowed.\n");

            }
        }
    }

    /**
     This method is to check the guest out from a fitness class.
     */
    @FXML
    private void guestCheckout(){
        String fname = fnameTextField1.getText();
        String lname = lnameTextField1.getText();
        String stringDob = dobTextField1.getText();
        String enteredlocation = locationTextField1.getText().toUpperCase();
        String instrName = iName.getText();
        String className = CName.getText();
        Date bornDate = new Date(stringDob);
        FitnessClass A = new FitnessClass(db, instrName, className, null, fitnessClasses);
        Member tempMember = new Member(fname, lname, bornDate);

        A.dropGuest(tempMember);
        outputText1.appendText(tempMember.getFname() + " " + tempMember.getLname() + " Guest done with class\n");

    }


    @FXML
    private void printv2(){
        for(int i = 0; i < fitnessClasses.length; i++){
             outputText1.appendText(fitnessClasses[i].getFitnessName() + " - - - - " + fitnessClasses[i].getInstructorName() + "\n");
           if(fitnessClasses[i] != null && fitnessClasses[i].getAttending().getSizeOfDB() != 0){
               outputText1.appendText("\t** participants **\n");
                for(int j = 0; j < fitnessClasses[i].getAttending().getSizeOfDB(); j++){
                    outputText1.appendText("\t \t" + fitnessClasses[i].getAttending().print(j) + "\n");
                }
           }

        }
        outputText1.appendText("--end of list--\n");
    }
    @FXML

    /**
     This method is the load the fitness schedule from a txt file and then print the schedule after loading it.
     */
    private void LS() {
        String fileName = "classSchedule.txt";
        String[] userLines;


        FitnessClass [] A = new FitnessClass[1];
        File inputFile = new File("classSchedule.txt");
        try {

            Scanner sc = new Scanner(inputFile);
            String line;
            int countNumOfLine = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line == null || line.length() == 0) {
                    break;
                }
                countNumOfLine++;
            }
            String lines[] = new String[countNumOfLine + 1];
            lines[0] = Integer.toString(countNumOfLine);
            sc = new Scanner(inputFile);
            for (int i = 1; i < lines.length; i++) {
                lines[i] = sc.nextLine();
            }
            userLines = lines;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FitnessClass[] IOClasses = new FitnessClass[Integer.parseInt(userLines[0])];
        int index = 0;
        for (int i = 1; i < userLines.length; i++) {
            String aLine = userLines[i];
            String[] read = aLine.split(" ");
            String location = read[3].toUpperCase();
            classTimes classTime = classTimes.valueOf(read[2].toUpperCase());
            Location classLocation = Location.valueOf(read[3]);
            boolean flag = false;
            //Add Location check
            //  tempMember.getLocation().compareLocations(A.getLocation()) == "Equal";
            for (Location iterate : Location.values()) {
                if (iterate.toString().equalsIgnoreCase(location)) {
                    flag = true;
                }


            }


            if (flag == true) {
                FitnessClass fitnessClass = new FitnessClass(read[0], read[1],
                        classTime, classLocation);
                IOClasses[index++] = fitnessClass;
            } else {
                outputText1.appendText("Location loaded from text file  doesnt match actual class location");
            }
        }
        c = new ClassSchedule(IOClasses, IOClasses.length);



        FitnessClass [] newClasses = new FitnessClass[IOClasses.length + fitnessClasses.length];
        for(int i = 0; i < fitnessClasses.length; i++){
            newClasses[i] = fitnessClasses[i];
        }
        int originalSize = fitnessClasses.length;
        fitnessClasses = newClasses;
        for(int i = 0; i < IOClasses.length; i++){
            fitnessClasses[originalSize] = IOClasses[i];
            originalSize++;
        }



        for(int i = 0; i <c.getNumClasses()-1; i++)
        {
            outputText.appendText(c.printSchedulerGUI(i)+"\n");
        }
    }
    //@FXML



    /**
     This methid is to remove a member from the database.
     This checks to see if the member is already in the database so that they can be removed.
     */
    @FXML
    private void remove(){
        String fname = fnameTextField.getText();
        String lname = lnameTextField.getText();
        String stringDob = dobTextField.getText();
        String location = locationTextField.getText().toUpperCase();
        Date bornDate = new Date(stringDob);
        Location A;
        if (location.equals("EDISON")) {
            A = Location.Edison;
        } else if (location.equals("BRIDGEWATER")) {
            A = Location.Bridgewater;
        } else if (location.equals("SOMERVILLE")) {
            A= Location.Somerville;
        } else if (location.equals("PISCATAWAY")) {
            A = Location.Piscataway;
        } else if (location.equals("FRANKLIN")) {
            A = Location.Piscataway;
        } else {
            A = null;
        }
        Member GuiMember = new Member(fname,lname,bornDate,new Date("11/2/2022"),A);
        if(db.contains3(GuiMember)=="Found"){
            outputText.appendText("Member" + " "+ GuiMember.getFname()  + " " +GuiMember.getLname() + " has been sucessfully removed\n");
            db.remove(GuiMember);

        }else{
            outputText.appendText("User not found");
        }



    }


    /**
     This method is so that you can add a member based on the click of a button.
     This will also check if the added member is a premium, family, or normal member.
     */
    @FXML
    private void clickAdd() {
        Date bornDate = new Date();

        //Create a variable which stores what type of membership is stored, 0 for standard 1 for premum, 2 for famiyl
        int result=-1;
        //grab text field info from respecive text slots and store them into variables.
        String fname = fnameTextField.getText();
        String lname = lnameTextField.getText();
        String stringDob = dobTextField.getText();
        String location = locationTextField.getText().toUpperCase();
        Location A;
        if (location.equals("EDISON")) {
            A = Location.Edison;
        } else if (location.equals("BRIDGEWATER")) {
            A = Location.Bridgewater;
        } else if (location.equals("SOMERVILLE")) {
            A= Location.Somerville;
        } else if (location.equals("PISCATAWAY")) {
            A = Location.Piscataway;
        } else if (location.equals("FRANKLIN")) {
            A = Location.Piscataway;
        } else {
            A = null;
            outputText.appendText("Invalid Location!\n");
            return;
        }        //Check to see which button will be seleceted update result to proper membership.
        if(standard.isSelected()){
            result=0;
            //outputText.appendText("Standard");

        }
       else if(premium.isSelected()){
            result=1;
            //outputText.appendText("Wow premium");

        }
       else if(family.isSelected()){
            result=2;

        }else{
            outputText.appendText("Empty fields!\n");
            outputText.appendText("\n");
            result=-2;


        }


        if(stringDob != "" || fname!=""||  lname!="" || location != "") {
             bornDate = new Date(stringDob);

            if (bornDate.isValid() && isValidLocation(location)) {
                //outputText.appendText("Date valid");
              outputText.appendText("\n");
              if(isValidLocation(location)==false){
                  outputText.appendText("Entered invalid location!\n");
              }
              if(bornDate.isValid()==false){
                  outputText.appendText("Entered invalid DOB!\n");
              }


            } else {
                outputText.appendText("Invalid Date!\n");
                return;
            }

        }else{
            outputText.appendText("fix empty fields!\n");

        }
        Member GuiMember = new Member(fname,lname,bornDate,new Date("11/2/2022"),A);
        MemberDatabase db = new MemberDatabase();
        int finalIntClassSize=3;
        FitnessClass[] fitnessClasses = new FitnessClass[finalIntClassSize];
        int classes = 4;
        Family member = new Family();
        ClassSchedule c = new ClassSchedule();



        //Now Add staandard member
        addMember(GuiMember,location,result);







    }


    /**
     This method is so that you can add a member based on the click of a button.
     This will also check if the added member is a premium, family, or normal member.
     */
    private void addMember(Member guiMember, String Loc, int result){
        String tempFname = guiMember.getFname();
        String tempLname=guiMember.getLname();
        Date bornDay = guiMember.getDob();
        guiMember.getLocation();
        Date expire = new Date();
        Date todaysDate = new Date();
        //Premium ready
            if(result==1){
                expire.setYear(expire.getYear() + 1);
                guiMember.setExpire(expire);
                if(db.contains3(guiMember)=="Not found") {
                    guiMember = new Premium(tempFname, tempLname, bornDay, expire, guiMember.getLocation());

                    outputText.appendText(guiMember.getFname() + " " + guiMember.getLname() +  " Has been sucessfully added!\n");
                    db.add(guiMember);
                }else{
                    outputText.appendText("User already exists!!!\n");
                }


            }
            if(result==0|| result==2){
                int tempMonth, tempYear, tempDay;
                tempMonth=todaysDate.getMonth()+3;
                tempYear=todaysDate.getYear();
                tempDay=todaysDate.getDay();

                guiMember.setExpire(new Date(tempMonth+ "/"+tempDay+ "/"+ tempYear));
                if (todaysDate.checkNextYear(3) >= 0) {
                    expire = new Date(todaysDate.checkNextYear(3) + "/" + todaysDate.getDay() + "/" +
                            (todaysDate.getYear() + 1));
                } else {
                    expire = new Date(todaysDate.getMonth() + 3 + "/" + todaysDate.getDay() + "/" +
                            todaysDate.getYear());
                }
                if(result==0){
                    //Standard
                    guiMember = new Member(tempFname, tempLname, bornDay, expire, guiMember.getLocation());
                    if(db.contains3(guiMember)=="Not found"){
                        outputText.appendText(guiMember.getFname() + " " + guiMember.getLname() +  " Has been sucessfully added!\n");

                        db.add(guiMember);
                    }else{
                        outputText.appendText("User already Exists!\n");
                    }
                }

                if(result==2){
                    guiMember = new Family(tempFname, tempLname, bornDay, expire, guiMember.getLocation());
                    if(db.contains3(guiMember)=="Not found"){
                        outputText.appendText(guiMember.getFname() + " " + guiMember.getLname() +  " Has been sucessfully added!\n");

                        db.add(guiMember);
                    }else{
                        outputText.appendText("User already exists\n");
                    }

                }




            }



    }




    /**
     This method checks to see if the location that is entered is a valid location.
     */
    private boolean isValidLocation(String location){
        boolean checkLocation = false;
        location.toUpperCase();
        if (location.equals("EDISON")) {
            checkLocation = true;
        } else if (location.equals("BRIDGEWATER")) {
            checkLocation = true;
        } else if (location.equals("SOMERVILLE")) {
            checkLocation = true;
        } else if (location.equals("PISCATAWAY")) {
            checkLocation = true;
        } else if (location.equals("FRANKLIN")) {
            checkLocation = true;
        } else {
            checkLocation = false;
        }
        return checkLocation;
    }



    /**
     This method is to load members from a txt file into the database.
     Once the members are loaded then the members are printed into the output text.
     */
    @FXML
    private void loadMember(){

        try {

            File file = new File("memberList.txt");
            Scanner sc = new Scanner(file);
            String newCommand = "";
            String[] newCommands;
            String user = "";
            int ptr = 0;
            while (sc.hasNext()) {
                newCommand = sc.nextLine();
                newCommands = newCommand.split("\\s+");
                if (db.contains2(newCommands) != "FOUND") {
                    addListMembers(newCommands);
                } else {
                    outputText.appendText("Members already Loaded\n");
                }
            }outputText.appendText("Members Loaded--Printing member list...\n");
            outputText.appendText("\n");


        } catch (FileNotFoundException e) {
            outputText.appendText("File Not Found\n");
        }
        outputText.appendText("---Begin list--\n");
        outputText.appendText("\n");



        for(int i = 0; i< db.getSizeOfDB(); i++) {
            outputText.appendText(db.print(i));
            outputText.appendText("\n");
        }





        outputText.appendText("--End of List--\n");
    }
    public void addListMembers (String[]commands){
        String location = commands[4];
        Date exp = new Date(commands[3]);
        Date dob = new Date(commands[2]);
        if (dob.isValid() && dob.isAdult() && exp.isValid()) {
            Member member = new Member();
            member.setDob(dob);
            member.setExpire(exp);
            member.setFname(commands[0]);
            member.setLname(commands[1]);

            //Split location back to match enum location
            String finalLocation1 = location.substring(1).toLowerCase();
            String finalLocation2 = location.substring(0, 1).toUpperCase();

            //Put it back together here
            location = finalLocation2 + finalLocation1;
            member.setLocation(Location.valueOf(location));
            db.add(member);
        }
    }

}
