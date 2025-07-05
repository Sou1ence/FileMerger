module com.apokalist.filemerger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.apokalist.filemerger to javafx.fxml;
    exports com.apokalist.filemerger;
}