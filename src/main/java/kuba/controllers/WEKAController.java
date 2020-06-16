package kuba.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kuba.sqlData.BasicTools;
import kuba.sqlData.SQLDataImporter;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.Utils;

public class WEKAController {

    @FXML
    private TextArea associationData;

    @FXML
    private TextField minRules;

    @FXML
    private TextField minSupp;

    @FXML
    private TextField minMetric;

    @FXML
    void pokaz(ActionEvent event) {
        przeladuj();
    }

    // "-N 10 -C 0.2 -M 0.01"

    @FXML
    void initialize() {
       przeladuj();
    }

    void przeladuj() {
        try {
            String fileName = "./src/main/java/kuba/data/Apriori.arff"; //Lokalizacja pliku z danymi
            Instances data = BasicTools.loadData(fileName);
            data.setClassIndex(data.numAttributes() - 1);


            String s = "-N ";
            StringBuilder sB = new StringBuilder(s);
            if (minRules.getText().isEmpty()) {
                sB.append(5);
            }else {
                sB.append(minRules.getText());
            }

            sB.append(" -C ");

            if (minSupp.getText().isEmpty()) {
                sB.append(0.2);
            }else {
                sB.append(minSupp.getText());
            }


            sB.append(" -M ");

            if (minMetric.getText().isEmpty()) {
                sB.append(0.01);
            }else {
                sB.append(minMetric.getText());
            }

            String[] options = Utils.splitOptions(sB.toString());
            Apriori apriori = new Apriori();
            apriori.setOptions(options);
            apriori.buildAssociations(data);

            System.out.println(apriori.toString());

            associationData.setText(apriori.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
