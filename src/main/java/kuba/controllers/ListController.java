package kuba.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mapping.Klienci;
import mapping.Obuwie;
import mapping.Zamowienie;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

import static kuba.controllers.LoginController.authenticatedUser;

public class ListController extends ListCell<Obuwie> {

    @FXML
    private Label cellNazwa;
    @FXML
    private Label cellCena;
    @FXML
    private Label rozmiar;
    @FXML
    private Label producent;
    @FXML
    private Label dostepnailosc;
    @FXML
    private ImageView cellImageView;
    @FXML
    private Button zamowobuwie;
    @FXML
    private AnchorPane cellAnchor;
    private FXMLLoader mLLoader;
    private int index;
    private Image img;



    @Override
    protected void updateItem(Obuwie item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/listcell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!item.getZdjecie().isEmpty()) {
                img = new Image(item.getZdjecie(), 155, 155, true, false);
            } else {
               img = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png", 155, 155, false, false);
            }

            cellNazwa.setText("Nazwa: "+item.getNazwa());
            cellCena.setText("Cena: "+item.getCenaButa() + "zł");
            rozmiar.setText("Rozmiar: "+ item.getRozmiarByIdRozmiaru().getRozmiar());
            producent.setText("Marka: "+item.getMarkaByIdMarki().getNazwa());
            if(item.getDostepnaIlosc()==0){
                dostepnailosc.setText("Brak obuwia!");
            }
            else{
                dostepnailosc.setText("Dostepna ilosc: "+item.getDostepnaIlosc());
            }
            cellImageView.setImage(img);

            setText(null);
            setGraphic(cellAnchor);


            zamowobuwie.setOnAction(event -> {
                zamowobuwie(item);
            });


        }
    }

    public void zamowobuwie(Obuwie zamow){
        try (Session session = SessionController.getSession()) {
            Query<Obuwie> query = session.createQuery("FROM Obuwie o WHERE o.idObuwia=" + zamow.getIdObuwia(), Obuwie.class);
            Obuwie but = FXCollections.observableArrayList(query.list()).get(0);


            if (but.getDostepnaIlosc() == 0) {
                showAlert(Alert.AlertType.CONFIRMATION, "Obuwie zostało wyprzedane!");
            }
            else {
                but.setDostepnaIlosc(but.getDostepnaIlosc() - 1);
                session.beginTransaction();
                session.update(but);
                session.getTransaction().commit();

                zamow.setDostepnaIlosc(but.getDostepnaIlosc());
                updateItem(zamow, false);
            }

            Query<Klienci> query1 = session.createQuery("FROM Klienci k WHERE k.idKlienta=" + authenticatedUser.getIdKlienta(), Klienci.class);
            Klienci klient = FXCollections.observableArrayList(query1.list()).get(0);

            Query<Obuwie> query2 = session.createQuery("FROM Obuwie o WHERE o.idObuwia=" + zamow.getIdObuwia(), Obuwie.class);
            Obuwie but2 = FXCollections.observableArrayList(query2.list()).get(0);


            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);


            Zamowienie noweZamowienie = new Zamowienie();

            noweZamowienie.setKlienciByIdKlienta(FXCollections.observableArrayList(query1.list()).get(0));
            noweZamowienie.setObuwieByIdObuwia(FXCollections.observableArrayList(query2.list()).get(0));
            noweZamowienie.setDataRealizacji(date);


            session.beginTransaction();
            session.save(noweZamowienie);
            session.getTransaction().commit();

        }
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType buttonTypeYes = new ButtonType("OK");

        alert.getButtonTypes().setAll(buttonTypeYes);

        alert.showAndWait();
    }

}
