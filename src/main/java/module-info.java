module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires junit;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
}