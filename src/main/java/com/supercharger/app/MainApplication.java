package com.supercharger.app;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        Utils.newWindow("","", "main");

        DBConnection.start();

    }

    public static void main(String[] args) {
        launch();
    }
}