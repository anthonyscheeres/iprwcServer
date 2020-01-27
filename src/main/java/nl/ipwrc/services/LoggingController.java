package nl.ipwrc.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.util.PSQLException;

import nl.ipwrc.dao.DatabaseUtilities;
import nl.ipwrc.dao.PreparedStatmentDatabaseUtilities;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.LogModel;

public class LoggingController {


    private String tableName = "logs";
    private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
    SimpleDateFormat dateFormat;

    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());




    /**
     * @author Anthony Scheeres, Anthony Schuijlenburg
     */

    public String showlogs(int id) throws Exception {
        String query = String.format("SELECT title FROM %s WHERE project_id = ", tableName);
        query += id + ";";
      
        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabaseJson(databaseModel, query, false);
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
            e1 = d.connectThisDatabaseHashMap(databaseModel, query, true);
            long id = r.createUserId2(e1.get("id"));
            String query2 = "INSERT INTO logs(title, id, project_id) VALUES (" +
                "?," +
                "?," +
                "?" +
                ");";
          
            try {
                List <String> f2 = new ArrayList < > ();
                f2.add(l.getTitle());
                f2.add(Long.toString(id));
                f2.add(Integer.toString(project_id));
                f.connectDatabaseJson(databaseModel, query2, f2, true);
            } catch (PSQLException e) {
                // TODO Auto-generated catch block
                 LOGGER.log(Level.SEVERE, "Error occur", e);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
             LOGGER.log(Level.SEVERE, "Error occur", e);
        }
    }
}