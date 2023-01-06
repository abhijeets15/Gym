module com.example.projectthree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Projv3 to javafx.fxml;
    exports com.example.Projv3;
}