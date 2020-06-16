package kuba.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainWindowController {


    @FXML
    private BorderPane mainWindowBorderPane;

    @FXML
    private DashboardController dashboardController;

    public MainWindowController() throws IOException {
    }

    @FXML
    void initialize() {
        this.dashboardController.setMainWindowController(this);
    }


}