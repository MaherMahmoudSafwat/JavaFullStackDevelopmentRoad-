module com.example.checkbuttons {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.checkbuttons to javafx.fxml;
    exports com.example.checkbuttons;
}