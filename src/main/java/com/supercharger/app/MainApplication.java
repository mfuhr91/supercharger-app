package com.supercharger.app;

import com.supercharger.app.database.DBConnection;
import com.supercharger.app.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;

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

class MyAppLauncher {public static void main(String[] args) {MainApplication.main(args);}}
