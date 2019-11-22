package main.java.nl.iipsen2server.dao;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.ExperimentModel;

import java.util.ArrayList;
import java.util.List;

public class ExperimentDAO {
    private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
    private PreparedStatmentDatabaseUtilities preparedStatmentDatabaseUtilities = new PreparedStatmentDatabaseUtilities();

/**
 * @author Jesse Poleij
 *
 */
    public void deleteExperiment(ExperimentModel project) {
        String query = "DELETE FROM projects WHERE id = ?;";

        List< String > arrayList = new ArrayList<>();
        arrayList.add(Long.toString(project.getId()));

        try {
            preparedStatmentDatabaseUtilities.connectDatabaseJson(databaseModel, query, arrayList, false);
        } catch (Exception e) {

        }

    }
    public void showOverview(ExperimentModel project) {
        String query = "";



    }
}
