module com.example.kalkulatorvat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kalkulatorvat to javafx.fxml;
    exports com.example.kalkulatorvat;
}