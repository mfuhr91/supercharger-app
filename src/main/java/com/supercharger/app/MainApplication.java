package com.supercharger.app;

import com.supercharger.app.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        Utils.newWindow("","", "main");
    }

    public static void main(String[] args) {
        launch();
    }
}