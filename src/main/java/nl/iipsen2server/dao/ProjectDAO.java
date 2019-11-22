package main.java.nl.iipsen2server.dao;

import main.java.nl.iipsen2server.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectDAO {

    private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
    /*
    *@author Cyriel van der Raaf
    *Gebruikt een prepared statement om waardes in het tabel projects te plaatsen.
    */
    public void sendProject(ExperimentModel2 model){
        PreparedStatmentDatabaseUtilities dbUtilities = new PreparedStatmentDatabaseUtilities();

        long id = model.getId();
        Enum status = model.getStatus();

        String query = "INSERT INTO projects(id, project_name, experiment_leader, description, organisation, " +
                "business_owner, status, inovation_cost, money_source) VALUES(" + "?," + "?,"
                + "?," + "?," + "?," + "?," + "?," + "?," + "?" + ");";

        List <String> project2 = new ArrayList<>();
        project2.add(String.format("%d", id));
        project2.add(model.getName());
        project2.add(model.getExperimentleaders());
        project2.add(model.getDescription());
        project2.add(model.getOrganisations());
        project2.add(model.getBusinessOwners());
        project2.add(String.format("%s", status));
        project2.add(model.getInovationCost());
        project2.add(model.getMoneySource());

        try {
            dbUtilities.connectDatabaseHashmap(databaseModel, query, project2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    *@author Cyriel van der Raaf
    *Gebruikt een prepared statement om een project te verwijderen.
    */
    public void deleteProject(ExperimentModel2 projectModel){
        DatabaseUtilities databaseUtilities = new DatabaseUtilities();

        String query1 = String.format("DELETE FROM projects WHERE id='&d';", projectModel.getId());

        try {
            databaseUtilities.connectThisDatabase2(databaseModel, query1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
