module com.example.navigator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.management;
    requires com.fasterxml.jackson.annotation;

    opens com.example.navigator to javafx.fxml;
    exports com.example.ElTawoonHospitalFullProject;
    opens com.example.ElTawoonHospitalFullProject to javafx.fxml;
}