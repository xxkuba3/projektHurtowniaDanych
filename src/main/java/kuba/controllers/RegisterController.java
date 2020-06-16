package kuba.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

// Class handling Login Window actions
public class RegisterController {
    public static int loggedUserRole;
    public Button GoToLoginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public TextField codeLabel;
    public TextField nameLabel;
    public TextField surnameLabel;
    public TextField PESELabel;
    public TextField phoneLabel;
    public TextField cityLabel;
    public TextField streetLabel;
    public TextField numberLabel;
    public TextField ageLabel;
    public TextField genderLabel;
    public Label infoLabel;
    public Button addUserBT;


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
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/login.fxml")));
        Stage primaryStage = (Stage) GoToLoginBT.getScene().getWindow();
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(pane));

    }

    //    Function that handle Close button clicks
    //    Closes application
    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleRegisterButtonAction() throws IOException, SQLException {
        loggedUserRole = authenticateUser();

        switch (loggedUserRole) {
            case -1:
                infoLabel.setTextFill(Color.web("red"));
                infoLabel.setText("Proszę uzupełnić wszystkie pola!");
                break;
            case -3:
                infoLabel.setTextFill(Color.web("red"));
                infoLabel.setText("Login lub Pesel zajete albo wprowadzono niepoprawne dane!");
                break;
            default:
                infoLabel.setTextFill(Color.web("green"));
                infoLabel.setText("Użytkownik został dodany!");
                break;
        }
    }

    private int authenticateUser() throws SQLException {
        String login = loginLabel.getText().toLowerCase();
        String pass = passwordLabel.getText();
        String code = codeLabel.getText();
        String name = nameLabel.getText();
        String surname = surnameLabel.getText();
        String PESEL = PESELabel.getText();
        String phone = phoneLabel.getText();
        String city = cityLabel.getText();
        String street = streetLabel.getText();
        String number = numberLabel.getText();
        String age = ageLabel.getText();
        String gender = genderLabel.getText();

        Statement stmt = null;
        String queryy = "SELECT * FROM klienci";
        int count = 1;

        try {
            Connection con = this.getDBConnection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryy);
            while (rs.next())
            {
                count = rs.getInt("id_klienta")+1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) stmt.close();
        }


        if (login.isEmpty() || pass.isEmpty() || code.isEmpty() || name.isEmpty() || surname.isEmpty() || PESEL.isEmpty() || phone.isEmpty() || city.isEmpty() || street.isEmpty() || number.isEmpty() || age.isEmpty()|| gender.isEmpty())
            return -1;



        String query = "INSERT INTO klienci (id_klienta, login, haslo, ulica, nr_domu, nazwisko, miejscowosc, imie, kod_pocztowy, pesel, telefon, wiek, plec) " +
                        "VALUES ('" + count + "','" + login + "','" + pass + "','" + street + "','" + number + "','" + surname + "','" + city + "','" + name + "','" + code + "','" + PESEL + "','" + phone + "','" + age + "','" + gender + "')";

        try {
            Connection con = this.getDBConnection();
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (PSQLException e) {
            return -3;
        }
        finally {
            if (stmt != null) stmt.close();
        }

        return 0;
    }
}
