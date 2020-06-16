package kuba;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kuba.controllers.FxmlUtils;

import static kuba.sqlData.BasicTools.processToApriori;

public class Main extends Application {



    public static final String SPLASH_FXML_BORDER_PANE = "/fxml/login.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Pane borderPane = FxmlUtils.fxmlLoader(SPLASH_FXML_BORDER_PANE);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sklep Obuwniczy");
        primaryStage.show();

        processToApriori();

    }

}


