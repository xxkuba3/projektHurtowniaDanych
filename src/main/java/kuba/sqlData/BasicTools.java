package kuba.sqlData;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BasicTools {

    public static Instances loadData(String fileName) throws IOException {
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(fileName));
        return loader.getDataSet();
    }

    public static void importCSVtoARFF(String fileNameCSV,String fileNameARFF) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(fileNameCSV));
        Instances data = loader.getDataSet();
        saveData(data,fileNameARFF);
    }

    public static void saveData(Instances data,String fileName) throws IOException {
        ArffSaver saver = new ArffSaver();
        saver.setFile(new File(fileName));
        saver.setInstances(data);
        saver.writeBatch();
    }

    static public void processToApriori() throws Exception {
        String username = "postgres";
        String password = "admin";
        String query = "SELECT k.plec,k2.nazwa as \"kategorie_nazwa\",m.nazwa as \"marka_nazwa\",k.wiek \n" +
                "FROM klienci k \n" +
                "inner join zamowienie z on z.id_klienta=k.id_klienta\n" +
                "inner join obuwie o on z.id_obuwia=o.id_obuwia \n" +
                "inner join kategoria k2 on k2.id_kategorii=o.id_kategorii \n" +
                "inner join marka m on m.id_marki=o.id_marki;";

        Instances nazwa = SQLDataImporter.getDataSetFromPostgreSQL(username, password, query, 0);

        ArrayList<String> dataOut = new ArrayList<String>();

        for (weka.core.Instance datum : nazwa) {

            String s = "";
            StringBuilder sB = new StringBuilder(s);

            for (int j = 0; j < datum.numValues(); j++) {
                if (j == datum.numValues() - 1)
                    sB.append(datum.toString(j).replace("'",""));
                else
                    sB.append(datum.toString(j).replace("'","")).append(",");
            }

            s = sB.toString();
            dataOut.add(s);

        }

        String filename = "./src/main/java/kuba/data/Apriori.arff";
        PrintWriter out = new PrintWriter(filename);
        out.println("@relation Polecone");
        out.println();
        out.println("@attribute 'Plec' {m,k}");
        out.println("@attribute 'Kategoria' {Klapki,Botki,Kalosze,Półbuty,Sandały,Mokasyny,Sportowe,Trampki,Trekkingowe,Szpilki,Koturny,Kozaki,Korki}");
        out.println("@attribute 'Marka' {Nike,Adidas,Campus,Lacoste,Converse,TommyHilfiger}");
        out.println("@attribute 'Wiek' {15,18,19,20,22,23,24,25,26,28,29,30,32,34,35,36,37,38,40,42,44,45,48,50,53,54,55,57,60,65}");
        out.println();
        out.println("@data");
        for(String s : dataOut){
            out.println(s);
        }
        out.close();
    }
}
