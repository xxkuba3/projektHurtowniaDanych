package kuba.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mapping.Klienci;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

// Class handling Login Window actions
public class LoginController {
    public static int loggedUserRole;
    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;
    public Button registerBT;
    public static Klienci authenticatedUser;

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




    //    Function that handle Login button clicks
    //    If the user is authenticated, opens the window depending on his role
    @FXML
    public void handleLoginButtonAction() throws IOException, SQLException {
        loggedUserRole = authenticateUser();

        switch (loggedUserRole) {
            case -1:
                infoLabel.setText("Proszę uzupełnić wszystkie pola!");
                break;
            case -2:
                infoLabel.setText("Nieprawidłowe dane logowania!");
                break;
            default:
                loadDiary();
                break;
        }
    }

    //    Function that handle Close button clicks
    //    Closes application
    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleRegisterButtonAction() throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/register.fxml")));
        Stage primaryStage = (Stage) registerBT.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(pane));
    }

    private int authenticateUser() {

        String login = loginLabel.getText().toLowerCase();
        String pass = passwordLabel.getText();

        if (login.isEmpty() || pass.isEmpty()) return -1;

        try (Session session = SessionController.getSession()) {

            authenticatedUser = session.createQuery("FROM Klienci k  WHERE k.login='" + login + "' and k.haslo='" + pass + "'", Klienci.class).list().get(0);

            return authenticatedUser.getIdKlienta();

        } catch (IndexOutOfBoundsException e) {
            return -2;
        }

    }

    //    Function that load main diary window with given window title
    private void loadDiary() throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ekranStartowy.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(pane, 750, 490));
    }
}
