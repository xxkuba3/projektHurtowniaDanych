package kuba.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class SplashScreenController implements Initializable {
    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread {
        public static final String MAIN_FXML_BORDER_PANE = "/fxml/mainWindow.fxml";

        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Parent root = null;
                    root = FxmlUtils.fxmlLoader(MAIN_FXML_BORDER_PANE);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Sklep Obuwniczy");
                    stage.setScene(scene);
                    stage.show();
                    rootPane.getScene().getWindow().hide();
                }
            });
        }
    }
}
