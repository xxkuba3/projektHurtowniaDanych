package kuba.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import mapping.Kategoria;
import mapping.Obuwie;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CategoriesController {
    @FXML
    public TableView<Obuwie> tableview;
    public BorderPane mainWindowBorderPane;
    public ListView<Obuwie> list;
    public TextField nothingFound;
    public ChoiceBox<Kategoria> KategoriaChoiceBox;





    @FXML
    void initialize() {
        wyswietl();
    }

    public void wyswietl() {
        try (Session session = SessionController.getSession()) {
            if (KategoriaChoiceBox.getSelectionModel().isEmpty()) {
                Query<Kategoria> query1 = session.createQuery("FROM Kategoria k", Kategoria.class);

                KategoriaChoiceBox.setItems(FXCollections.observableArrayList(query1.list()));
                KategoriaChoiceBox.getSelectionModel().select(0);
            }



            Query<Obuwie> query1 = session.createQuery("FROM Obuwie o JOIN FETCH o.kategoriaByIdKategorii JOIN FETCH o.markaByIdMarki JOIN FETCH o.rozmiarByIdRozmiaru WHERE o.kategoriaByIdKategorii.idKategorii="+KategoriaChoiceBox.getValue().getIdKategorii(), Obuwie.class);

            ObservableList<Obuwie> listaNowosci = FXCollections.observableArrayList(query1.list());

            list.setItems(listaNowosci);
            list.setCellFactory(ListView -> new ListController());

            nothingFound.visibleProperty().setValue(list.getItems().isEmpty());
        }
    }
}
