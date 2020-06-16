package kuba.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogsUtil {

    public static Optional<ButtonType> dialogConfirm() {
        Alert dialogConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        dialogConfirm.setTitle("QuitQuestion");
        dialogConfirm.setHeaderText("Czy na pewno chcesz wyjść?");
        Optional<ButtonType> result = dialogConfirm.showAndWait();
        return result;
    }
}

