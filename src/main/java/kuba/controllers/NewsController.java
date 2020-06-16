package kuba.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import mapping.Obuwie;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

public class NewsController {
    @FXML
    public TableView<Obuwie> tableview;


    public BorderPane mainWindowBorderPane;
    public ListView<Obuwie> list;
    public TextField nothingFound;


    public NewsController() throws IOException {
    }


    @FXML
    void initialize() {
        wyswietl();
    }

    public void wyswietl() {
        try (Session session = SessionController.getSession()) {
            Query<Obuwie> query1 = session.createQuery("FROM Obuwie o JOIN FETCH o.rozmiarByIdRozmiaru JOIN FETCH o.markaByIdMarki WHERE o.rokProdukcji=2020", Obuwie.class);

            ObservableList<Obuwie> listaNowosci = FXCollections.observableArrayList(query1.list());

            list.setItems(listaNowosci);
            list.setCellFactory(ListView -> new ListController());

            nothingFound.visibleProperty().setValue(list.getItems().isEmpty());
        }
    }
}