/**
 *  This is the main to run the program
 *  @author Abhijeet Singh, Khizar Saud
 */


package com.example.Projv3;
import com.example.Projv3.GymManagerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class GymManagerMain extends Application {
    @Override
    public void start(Stage stage){
        new GymManagerController();
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene sceneOne;
        Parent rootRoot;
        try {
            rootRoot = fxmlLoader.load();
            sceneOne = new Scene(rootRoot);
            stage.setTitle("Gym Manager!");
            stage.setScene(sceneOne);
            stage.show();
        }catch(Exception e){
            System.out.println("File doesn't exist");
            throw new RuntimeException(e);


        }
    }
    public static void main (String args[]){
        launch();
    }

}