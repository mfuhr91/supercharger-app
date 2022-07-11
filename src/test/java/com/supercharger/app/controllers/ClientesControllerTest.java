package com.supercharger.app.controllers;

import com.supercharger.app.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class ClientesControllerTest {

    @Start
    private void start(Stage stage) throws IOException {
        System.out.println(MainApplication.class.getResource("clientes/clientesForm.fxml"));
        Parent fxmlLoader = FXMLLoader.load(MainApplication.class.getResource("clientes/clientesForm.fxml"));
        stage.setTitle("SuperCharger APP - Nuevo Cliente");
        stage.setScene(new Scene(fxmlLoader, 600, 500));
        stage.show();
        stage.toFront();
    }

    @Test
    void shouldValidateForm(FxRobot robot) {

        Button guardarButton = robot.lookup("#guardarButton").queryAs(Button.class);

        Label formOK = robot.lookup("#formOK").queryAs(Label.class);

        robot.clickOn(guardarButton);

        Assertions.assertThat(formOK).isVisible();
    }

}
