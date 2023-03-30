module controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens controllers to javafx.fxml;
    exports controllers to javafx.graphics;
    exports service to javafx.graphics;
}