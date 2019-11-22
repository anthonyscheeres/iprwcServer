package main.java.nl.iipsen2server.controlllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.postgresql.util.PSQLException;

import main.java.nl.iipsen2server.dao.DatabaseUtilities;
import main.java.nl.iipsen2server.dao.PreparedStatmentDatabaseUtilities;
import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.LogModel;

public class LoggingController {


    private String tableName = "logs";
    private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
    SimpleDateFormat dateFormat;





    /**
     * @author Anthony Scheeres, Anthony Schuijlenburg
     */

    public String showlogs(int id) throws Exception {
        String query = String.format("SELECT title FROM %s WHERE project_id = ", tableName);
        query += id + ";";
        System.out.println(query);
        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabase2(databaseModel, query);
    }




    /**
     * @author Anthony Scheeres, Anthony Schuijlenburg
     */
    public void createLog(LogModel l, int project_id) {
        DatabaseUtilities d = new DatabaseUtilities();
        String query = String.format("select id from %s;", tableName);
        LogController r = new LogController();
        PreparedStatmentDatabaseUtilities f = new PreparedStatmentDatabaseUtilities();
        HashMap < String, List < String >> e1;
        try {
            e1 = d.connectThisDatabase(databaseModel, query);
            long id = r.createUserId2(e1.get("id"));
            String query2 = "INSERT INTO logs(title, id, project_id) VALUES (" +
                "?," +
                "?," +
                "?" +
                ");";
            System.out.println(query2);
            try {
                List <String> f2 = new ArrayList < > ();
                f2.add(l.getTitle());
                f2.add(Long.toString(id));
                f2.add(Integer.toString(project_id));
                f.connectDatabaseJson(databaseModel, query2, f2, true);
            } catch (PSQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}