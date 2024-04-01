module com.nfs.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires weka.stable;
    requires javafx.base;
    requires transitive javafx.graphics;
    requires org.controlsfx.controls; 

    opens com.nfs.app to javafx.fxml, weka.stable;
    opens com.nfs.app.controllers.base to javafx.fxml, weka.stable;
    opens com.nfs.app.controllers.dashboard to javafx.fxml;
    opens com.nfs.app.controllers.trainning_history to javafx.fxml;
    exports com.nfs.app;
}
