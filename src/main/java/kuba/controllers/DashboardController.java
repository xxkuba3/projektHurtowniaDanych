package kuba.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class DashboardController {
    private MainWindowController mainWindowController;
    public Button mainWindow;
    public Button news;
    public Button brands;
    public Button categories;
    public Button zestawieniazamowien;
    public Connection getDBConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sklepp", "postgres", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }

    public void mainWindow(ActionEvent actionEvent) throws IOException {
            Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/mainWindow.fxml")));
            Stage primaryStage = (Stage) mainWindow.getScene().getWindow();
            primaryStage.setTitle("Sklep Obuwniczy");
            primaryStage.setResizable(true);
            primaryStage.setScene(new Scene(pane));
        }

    public void news(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/news.fxml"));
        Stage primaryStage = (Stage) news.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root));
    }



    public void brands(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/brands.fxml"));
        Stage primaryStage = (Stage) brands.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root));
    }

    public void categories(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/categories.fxml"));
        Stage primaryStage = (Stage) categories.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root));
    }

    public void zestawieniazamowien(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/weka.fxml"));
        Stage primaryStage = (Stage) zestawieniazamowien.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root));
    }


    public void quit(ActionEvent actionEvent) {
        Optional<ButtonType> resoult = DialogsUtil.dialogConfirm();
        if (resoult.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }


}
