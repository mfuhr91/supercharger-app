package com.supercharger.app.utils;

import com.supercharger.app.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static void newWindow(String name, String folder, String fileName) {
        try {
            if (!Objects.equals(folder, "")) {
                folder = folder + "/";
            }

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(folder + fileName + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            if (!Objects.equals(name, "")) {
                stage.setTitle("SuperCharger APP - " + name);
            } else {
                stage.setTitle("SuperCharger APP");
            }
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            LOG.error("Failed to create the window: " + name + " error: ", e);
        }
    }

    public static void newWindowsWithController(String name, String folder, String fileName, Object controller) {
        try {
            if (!Objects.equals(folder, "")) {
                folder = folder + "/";
            }

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(folder + fileName + ".fxml"));

            fxmlLoader.setController(controller);

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setTitle("SuperCharger APP - " + name);

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            LOG.error("Failed to create the window: " + name + " error: ", e);
        }
    }


}

