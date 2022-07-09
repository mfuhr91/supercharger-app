module com.supercharger.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires slf4j.api;
    requires lombok;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;


    exports com.supercharger.app;
    exports com.supercharger.app.controllers;
    exports com.supercharger.app.models;
    exports com.supercharger.app.services;
    exports com.supercharger.app.dao;
    opens com.supercharger.app.controllers to javafx.fxml;
    exports com.supercharger.app.models.tablas;
    opens com.supercharger.app.models;
}