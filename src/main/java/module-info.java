module com.supercharger.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires slf4j.api;
    requires lombok;

    opens com.supercharger.app to javafx.fxml;
    exports com.supercharger.app;
    exports com.supercharger.app.controllers;
    exports com.supercharger.app.models;
    opens com.supercharger.app.controllers to javafx.fxml;
    exports com.supercharger.app.models.tablas;
}