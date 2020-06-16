package kuba.sqlData;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

import java.io.File;

public class SQLDataImporter {
    static File propsFile = new File("./src/main/java/kuba/settings/DatabaseUtils.props");

    public static Instances getDataSetFromPostgreSQL(String userName, String password, String queryText, int limit) throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setUsername(userName);
        query.setPassword(password);
        
        query.setCustomPropsFile(propsFile);

        if (limit>0) query.setQuery(queryText + " limit "+ limit + ";");
        else query.setQuery(queryText + ";");

        return query.retrieveInstances();
    }
}