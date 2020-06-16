package kuba.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class FxmlUtils {
    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));

        try {
            final Pane load = loader.load();
            return load;
        } catch (IOException e) {
            System.out.println("FXML loading problem.");
            e.printStackTrace();
        }

        return null;
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        return loader;
    }
}
