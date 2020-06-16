package kuba.controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import mapping.Marka;
import mapping.Obuwie;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class BrandsController {
    @FXML
    public TableView<Obuwie> tableview;
    public BorderPane mainWindowBorderPane;
    public ListView<Obuwie> list;
    public TextField nothingFound;
    public ChoiceBox<Marka> ProducenciChoiceBox;



    @FXML
    void initialize() {
        wyswietl();
    }

    public void wyswietl() {
        try (Session session = SessionController.getSession()) {
                if (ProducenciChoiceBox.getSelectionModel().isEmpty()) {
                    Query<Marka> query1 = session.createQuery("FROM Marka m",Marka.class);

                    ProducenciChoiceBox.setItems(FXCollections.observableArrayList(query1.list()));
                    ProducenciChoiceBox.getSelectionModel().select(0);
                }



            Query<Obuwie> query1 = session.createQuery("FROM Obuwie o JOIN FETCH o.markaByIdMarki JOIN FETCH o.rozmiarByIdRozmiaru WHERE o.markaByIdMarki.idMarki="+ProducenciChoiceBox.getValue().getIdMarki(), Obuwie.class);

            ObservableList<Obuwie> listaNowosci = FXCollections.observableArrayList(query1.list());

            list.setItems(listaNowosci);
            list.setCellFactory(ListView -> new ListController());

            nothingFound.visibleProperty().setValue(list.getItems().isEmpty());
        }
    }
}
